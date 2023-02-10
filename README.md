
<h1 align="center">SAMS</h1>

<div align="center">

Sams System (Springcloud Alibaba Micro Service) 是一个基于 SpringCloud Alibaba 搭建的微服务框架，本框架旨在帮助大家学习以及在搭建微服务系统时提供参考。

[![Star](https://img.shields.io/github/stars/bestaone/sams?color=42b883&logo=github&style=flat-square)](https://github.com/bestaone/sams/stargazers)
[![Fork](https://img.shields.io/github/forks/bestaone/sams?color=42b883&logo=github&style=flat-square)](https://github.com/bestaone/sams/network/members)
[![Star](https://gitee.com/bestaone/sams/badge/star.svg?theme=gray)](https://gitee.com/bestaone/sams/stargazers)
[![Fork](https://gitee.com/bestaone/sams/badge/fork.svg?theme=gray)](https://gitee.com/bestaone/sams/members)
[![Language](https://img.shields.io/badge/%E8%AF%AD%E8%A8%80-Java17%20%7C%20Springboot3%20%7C%20React-red?style=flat-square&color=42b883)](https://github.com/zuihou/lamp-cloud)
[![License](https://img.shields.io/github/license/bestaone/sams?color=42b883&style=flat-square)](https://github.com/bestaone/sams/blob/main/LICENSE)
[![Author](https://img.shields.io/badge/作者-码道功臣-orange.svg)](https://github.com/bestaone)

</div>

## 一、介绍

目前计划的功能并没有完全实现，将会持续更新。也欢迎有兴趣的朋友一起参与提交代码。
**如果你觉得此项目有价值，请给我点个star，谢谢！**

> 项目地址：https://github.com/bestaone/sams


### 1、目录结构
```
├─doc                   文档目录，架构设计、数据库设计...
├─cicd                  持续集成文件及脚本
├─apisvc-auth           认证授权业务服务
├─apisvc-mall           MALL业务服务
├─apisvc-oms            OMS(Operation Manager System)运营管理系统
├─basic-beans           通用Bean模块，API参数基类、数据实体类基类
├─basic-common          全局同样包
├─basic-core            核心包
├─basic-dependencies    基础的依赖管理
├─compsvc-gateway       网关服务
├─front-mall            MALL前端项目
├─microsvc-org          组织微服务
├─microsvc-user         用户微服务
├─starter-apisvc        业务服务的基础依赖模块，所有的业务服务都需要依赖此模块
├─starter-cache         缓存模块
├─starter-logger        日志模块
├─starter-microsvc      微服务的基础依赖模块，所有的微服务都需要依赖此模块
├─starter-mq            MQ模块
├─starter-mybatis       Mybatis模块，集成了Mybatis-plus
├─starter-pusher        推送模块，SMS、邮件、APP消息...
├─starter-route         路由模块，网关和业务服务需要依赖，微服务不需要，因为微服务不会去调用其他服务
├─starter-security      安全控制模块
├─starter-sequence      发号器模块，主键生成器
├─starter-swagger       Swagger文档模块
├─starter-utils         通用工具模块
```


### 2、功能
- 这个项目可以帮助你快速的启动一个基于`SpringCloud Alibaba`技术栈的微服务框架搭建
- 基于`SpringCloud Gateway`的网关系统，包括：认证、鉴权、路由、负载均衡、流控等
- 基于`AntDesignPro`的登录，包括用户名密码登录、短信验证码登录，实现了图形验证码防刷功能
- `Nacos`负责服务注册、配置管理
- `Sentinel`负责流量控制、熔断降级、系统负载等多个维度保护服务的稳定性
- `Seata`负责分布式事务管理
- 前端使用了`AntDesignPro`框架，参考这个项目，你可以快速的开启集成开发
- 基于 `SpringBoot3` 项目更容易集成到多个平台（K8S、Istio）
- 演示了如何通过starter扩展功能(Doc、Route)
- 演示了如何统一控制接口规范
- 演示了如何规范异常处理
- 演示了如何规范使用`Mybaits`、`Mybatis-Plus`、分页
- 演示了单元测试、mock测试、测试数据回滚，包括对controller、service的测试
- 项目分包明确，规范微服务的开发模式，使包与包之间的分工清晰
- 链路追踪
- ES日志管理


### 3、架构图（蓝色为计划实现，黄色为不打算实现）

<p align="center">
  <img width="900" src="https://earven.oss-cn-shanghai.aliyuncs.com/sams/imgs/samsjg1.png">
</p>

<p align="center">
  <img width="900" src="https://earven.oss-cn-shanghai.aliyuncs.com/sams/imgs/samsjg2.png">
</p>


### 4、功能截图

<p align="center">
  <img width="300" src="https://earven.oss-cn-shanghai.aliyuncs.com/sams/imgs/sams_demo1.gif">
</p>


### 5、在线演示
- 前台地址：http://sams.webestar.cn
- 后台地址：http://sams-oms.webestar.cn
- Sentinel: http://sams.webestar.cn:8858 sentinel/sentinel


## 二、快速启动

### 1、环境依赖
- JDK 17
- MySQL 8
- Redis
- NodeJS 16+
- Yarn
- Nacos 2.2.0
- Sentinel 1.8.6
- Seata 1.6.1
- SpringBoot 3
- SpringCloud 2022.0.0
- SpringCloud Alibaba 2022.0.0.0
>需要提前下载安装好以上内容


### 2、下载源码
```
>git clone https://github.com/bestaone/sams.git
```


### 3、创建数据库
在你的mysql8数据库中创建两个库sams_user、sams_org，并分别执行下面脚本:
```
sams\doc\db\sams_user.sql
sams\doc\db\sams_org.sql
```


### 4、安装配置Nacos
#### 安装包安装 略...
#### Docker安装 **推荐**
```bash
docker run -d \
  -e MODE=standalone \
  -e JVM_XMS=256m -e JVM_XMX=256m \
  -e PREFER_HOST_MODE=hostname \
  -p 8848:8848 -p 9848:9848 -p 9849:9849 \
  --privileged=true \
  --restart=always \
  --name nacos-2.2.0 \
  nacos/nacos-server:v2.2.0
```
>需要Nacos支持MySQL数据库，参考文档：[Nacos使用](https://github.com/bestaone/sams/blob/main/doc/nacos/readme.md)

#### 访问Nacos
```
http://127.0.0.1:8848/nacos
账密：nacos\nacos
```

#### 导入配置
- 进入nacos管理页面，在"命名空间"中创建命名空间`sams`
- 在`配置管理->配置列表`中，导入配置`sams\doc\nacos\nacos_config_export.zip`



### 5、安装部署Sentinel
下列两种方式任选一种
- 下载Jar安装启动
```
>java -Dserver.port=8858 -Dcsp.sentinel.dashboard.server=localhost:8858 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard.jar
```
- Docker安装启动
```bash
>docker run --name sentinel  -d -p 8858:8858 -p 8719:8719 -d  bladex/sentinel-dashboard:1.8.0
```


### 6、安装部署Seata
- 创建数据库seata，导入数据`doc/seata/seata-server-1.6.1.sql`到`seata`库，导入`doc/seata/seata-client-1.6.1.sql`到`sams_user`和`sams_org`库
- 直接部署【推荐】
```bash
#下载解压后，修改配置文件 seta-1.6.1/conf/application.yml
>cd seata-1.6.1
>./bin/seata-server.sh -h 127.0.0.1
```
>参考 [application.yml](https://github.com/bestaone/sams/blob/main/doc/seata/application.yml)

- Docker部署，支持自定义配置
```bash
#先启动容器
>docker run -d -p 8091:8091 -p 7091:7091  --name seata-serve seataio/seata-server:1.6.1
#将配置复制到宿主机
>docker cp seata-serve:/seata-server/resources /User/seata/config
#修改完配置，通过挂在目录的方式启动
>docker run -d --name seata-server -p 8091:8091 -p 7091:7091 -e SEATA_IP=127.0.0.1 -v /Users/zgs/Documents/install/seata-1.6.1/config/resources:/seata-server/resources seataio/seata-server:1.6.1 
```
>如果是新手，建议用`直接部署方式`安装，因为Docker安装容易出现网络问题。
>Docker安装的Seata，如果用的是远程Nacos，在注册到Nacos时，可能会使用Docker IP，从而导致网络不通。

> 参考地址：http://seata.io/zh-cn/docs/ops/deploy-by-docker.html

- 控制台地址：http://127.0.0.1:7091 seata/seata


### 7、添加本地hosts配置
在`/etc/hosts`文件中加入下列配置（Mac电脑）
```bash
xx.xx.xx.xx nacos-server
xx.xx.xx.xx redis-server
xx.xx.xx.xx mysql-server
xx.xx.xx.xx sentinel-server
#换成正确的IP
```


### 8、构建、启动
- 构建
```bash
>cd sams
>mvn clean install
>cd front-mall
>yarn
```

- 启动网关`compsvc-gateway`
```bash
>cd compsvc-gateway
>mvn spring-boot:run
```

- 启动认证服务`apisvc-auth`
```bash
>cd apisvc-auth
>mvn spring-boot:run
```

- 启动用户微服务`microsvc-user`
```bash
>cd microsvc-user/microsvc
>mvn spring-boot:run
```

- 启动前端`front-mall`
```bash
>cd front-mall
>yarn start
```


### 9、测试验证
#### 访问前端页面
>http://127.0.0.1:8000

#### 测试接口

- 账号登录
```bash
>curl --location --request POST 'http://127.0.0.1:7070/apisvc-auth/api/open/auth/login/account' \
  --header 'Content-Type: application/json' \
  --data-raw '{
    "username": "admin",
    "password": "123456"
  }'
  
#返回结果
{
  "code":100000,
  "data":{
    "accessToken":"eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NzU1MDI3NzAsInN1YiI6IjY3ODkyMDUxOTc4MTE3MTIiLCJjcmVhdGVkIjoxNjc1NDkxOTcwOTUyfQ.SWddCcr1Se6e9lJUXlRJAfQr0zE27iNbSQs2AGwj5pY41O7vDVK6Wm11mbx86JfOBY05nTkl1hQ0S5DGQ3NQiA",
    "expireIn":10800,
    "refreshToken":"1589fea7f09f40e7af7713a4b570e8be"
  }
}
```

- 获取登录用户信息
```bash
>curl --location --request POST 'http://127.0.0.1:7070/apisvc-auth/api/auth/currentUser' \
  --header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NzU1MDI3MjAsInN1YiI6IjY3ODkyMDUxOTc4MTE3MTIiLCJjcmVhdGVkIjoxNjc1NDkxOTIwNTU2fQ.jHaUv6Pi48w6XXcX0rvU-8BxkGQF56hKWJDm3INdm091i8pMYacuAM6_Zkv9PxalhSgKhVlUURE9Ylr2D9RYCA'

#返回结果
{
    "code": 100000,
    "data": {
        "name": "admin",
        "avatar": "https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png"
    }
}
```

- 获取短信验证码
```bash
>curl --location --request POST 'http://127.0.0.1:7070/apisvc-auth/api/open/auth/send/sms?phoneNum=13712345678'

#返回结果
{
    "code": 100000,
    "data": 244249
}
```

- 短信验证码登录
```bash
>curl --location --request POST 'http://127.0.0.1:7070/apisvc-auth/api/open/auth/login/sms' \
  --header 'Content-Type: application/json' \
  --data-raw '{
    "phoneNum": "13712345678",
    "smsCode": "244249"
  }'

#返回结果
{
    "code": 100000,
    "data": {
        "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NzU1MDM0OTgsInN1YiI6IjY3ODkyMDUxOTc4MTE3MTIiLCJjcmVhdGVkIjoxNjc1NDkyNjk4MDMwfQ.1bBFQ5G2Vl-xT880ecuUPfUSqaiNCv-q4_DlCJAIW1iU0FSk49sYHKjFJTdICOpwsOtHcxgHL20cTpltfMbHYA",
        "expireIn": 10800,
        "refreshToken": "f5e1912b338742f5aa7fafd4e50d62e0"
    }
}
```

>`accessToken`即为访问接口的凭证


## 三、集成部署

### 1、Docker环境
- 修改配置值
修改文件 sams/cicd/docker/start.sh
```
#将下列内容，换成你的ip
echo '192.168.0.158 nacos-server' >> /etc/hosts
echo '192.168.0.158 mysql-server' >> /etc/hosts
echo '192.168.0.158 redis-server' >> /etc/hosts
echo '192.168.0.158 sentinel-server' >> /etc/hosts
```

- 构建镜像
```bash
#编译构建完后执行下列命令
>cd sams
>mkdir temp
>cp -rf ./front-mall/dist temp/
>cp -rf ./cicd/docker/* temp/
>cp ./apisvc-auth/target/apisvc-auth.jar temp/
>cp ./apisvc-mall/target/apisvc-mall.jar temp/
>cp ./compsvc-gateway/target/compsvc-gateway.jar temp/
>cp ./microsvc-user/user-microsvc/target/microsvc-user.jar temp/
>cp ./microsvc-org/org-microsvc/target/microsvc-org.jar temp/
>cd temp
>docker build -t sams:1.0 .
#完成后会生成镜像 sams:1.0
```

- 启动运行
```bash
>docker run -d --name sams -p 10080:80 sams:1.0
```

- 验证，访问页面： http://127.0.0.1:10080


## 四、授权协议
本项目执行 [MIT](https://github.com/bestaone/sams/blob/main/LICENSE) 协议

