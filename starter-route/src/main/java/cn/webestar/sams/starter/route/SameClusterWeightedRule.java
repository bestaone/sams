package cn.webestar.sams.starter.route;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.api.utils.StringUtils;
import com.alibaba.nacos.client.naming.core.Balancer;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.*;
import org.springframework.cloud.loadbalancer.core.NoopServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.SelectedInstanceCallback;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 自定义负载均衡实现需要实现
 * 一：优先，使用前端指定的ClusterName进行匹配。
 * 二：其次，使用配置中相同ClusterName进行匹配。
 * 三：再次，在所有实例中按权重选择一个。
 */
@Slf4j
public class SameClusterWeightedRule implements ReactorServiceInstanceLoadBalancer {

    /**
     * 注入当前服务的nacos的配置信息
     */
    @Resource
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    /**
     * loadbalancer 提供的访问当前服务的名称
     */
    final String serviceId;

    /**
     * loadbalancer 提供的访问的服务列表
     */
    ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;

    public SameClusterWeightedRule(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider, String serviceId) {
        this.serviceId = serviceId;
        this.serviceInstanceListSupplierProvider = serviceInstanceListSupplierProvider;
    }

    /**
     * 服务器调用负载均衡时调的放啊
     * 此处代码内容与 RandomLoadBalancer 一致
     */
    public Mono<Response<ServiceInstance>> choose(Request request) {
        ServiceInstanceListSupplier supplier = this.serviceInstanceListSupplierProvider.getIfAvailable(NoopServiceInstanceListSupplier::new);
        return supplier.get(request).next().map((serviceInstances) -> this.processInstanceResponse(supplier, request, serviceInstances));
    }

    /**
     * 对负载均衡的服务进行筛选的方法
     * 此处代码内容与 RandomLoadBalancer 一致
     */
    private Response<ServiceInstance> processInstanceResponse(ServiceInstanceListSupplier supplier, Request request, List<ServiceInstance> serviceInstances) {
        Response<ServiceInstance> serviceInstanceResponse = this.getInstanceResponse(request, serviceInstances);
        if (supplier instanceof SelectedInstanceCallback && serviceInstanceResponse.hasServer()) {
            ((SelectedInstanceCallback)supplier).selectedServiceInstance((ServiceInstance)serviceInstanceResponse.getServer());
        }
        return serviceInstanceResponse;
    }

    /**
     * 对负载均衡的服务进行筛选的方法
     * 自定义
     * 此处的 instances 实例列表  只会提供健康的实例  所以不需要担心如果实例无法访问的情况
     */
    private Response<ServiceInstance> getInstanceResponse(Request request, List<ServiceInstance> instances) {
        if (instances.isEmpty()) {
            return new EmptyResponse();
        }

        RequestDataContext rdc = (RequestDataContext) request.getContext();
        RequestData rd = rdc.getClientRequest();

        List<ServiceInstance> matchedInstances = new ArrayList<>();

        // 优先使用前端指定的集群名称匹配
        String customClusterName = rd.getHeaders().getFirst("app-cluster-name");
        if(!StringUtils.isEmpty(customClusterName)){
            matchedInstances = instances.stream()
                    .filter(i-> StringUtils.equals(i.getMetadata().get("nacos.cluster"), customClusterName)).collect(Collectors.toList());
        }

        // 如果没有一个匹配的，则使用配置的集群名匹配
        String currentClusterName = nacosDiscoveryProperties.getClusterName();
        if(matchedInstances.isEmpty()) {
            matchedInstances = instances.stream()
                    .filter(i-> StringUtils.equals(i.getMetadata().get("nacos.cluster"), currentClusterName)).collect(Collectors.toList());
        }

        // 从匹配的服务器中选择一个优先级最高的
        ServiceInstance hitInstance;
        if(!matchedInstances.isEmpty()) {
            hitInstance = getHostByRandomWeight(matchedInstances);
        } else {
            // 从所有的服务器列表中选择一个
            hitInstance = getHostByRandomWeight(instances);
        }
        return new DefaultResponse(hitInstance);
    }

    private ServiceInstance getHostByRandomWeight(List<ServiceInstance> sameClusterNameInstList){

        List<Instance> list = new ArrayList<>();
        Map<String,ServiceInstance> dataMap = new HashMap();
        // 此处将 ServiceInstance 转化为 Instance 是为了接下来调用nacos中的权重算法.
        sameClusterNameInstList.forEach(i->{
            Instance ins = new Instance();
            Map<String, String> metadata = i.getMetadata();
            String instanceId = metadata.get("nacos.instanceId");
            if(instanceId==null){
                instanceId = i.getHost() + ":" + i.getPort();
            }
            ins.setInstanceId(instanceId);
            ins.setWeight(new BigDecimal(metadata.get("nacos.weight")).doubleValue());
            ins.setClusterName(metadata.get("nacos.cluster"));
            ins.setEphemeral(Boolean.parseBoolean(metadata.get("nacos.ephemeral")));
            ins.setHealthy(Boolean.parseBoolean(metadata.get("nacos.healthy")));
            ins.setPort(i.getPort());
            ins.setIp(i.getHost());
            ins.setServiceName(i.getServiceId());

            ins.setMetadata(metadata);

            list.add(ins);
            // key为服务ID，值为服务对象
            dataMap.put(instanceId,i);
        });
        // 调用nacos官方提供的负载均衡权重算法
        Instance hostByRandomWeightCopy = ExtendBalancer.getHostByRandomWeightCopy(list);

        // 根据最终ID获取需要返回的实例对象
        return dataMap.get(hostByRandomWeightCopy.getInstanceId());
    }

}

class ExtendBalancer extends Balancer {

    /**
     * 根据权重选择随机选择一个
     */
    public static Instance getHostByRandomWeightCopy(List<Instance> hosts) {
        return getHostByRandomWeight(hosts);
    }

}