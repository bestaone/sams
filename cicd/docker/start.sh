#!/bin/sh

echo '192.168.0.158 nacos-server' >> /etc/hosts
echo '192.168.0.158 mysql-server' >> /etc/hosts
echo '192.168.0.158 redis-server' >> /etc/hosts
echo '192.168.0.158 seata-server' >> /etc/hosts
echo '192.168.0.158 sentinel-server' >> /etc/hosts

export SW_AGENT_COLLECTOR_BACKEND_SERVICES=192.168.0.158:11800
export JAVA_AGENT=-javaagent:/agent/skywalking-agent.jar

nohup /usr/local/nginx/sbin/nginx &
nohup java -Xms128M -Xmx256M -javaagent:/agent/skywalking-agent.jar -DSW_AGENT_COLLECTOR_BACKEND_SERVICES=192.168.0.158:11800 -DSW_AGENT_NAME=compsvc-gateway -jar /compsvc-gateway.jar &
nohup java -Xms128M -Xmx256M -javaagent:/agent/skywalking-agent.jar -DSW_AGENT_COLLECTOR_BACKEND_SERVICES=192.168.0.158:11800 -DSW_AGENT_NAME=apisvc-auth     -jar /apisvc-auth.jar &
nohup java -Xms128M -Xmx256M -javaagent:/agent/skywalking-agent.jar -DSW_AGENT_COLLECTOR_BACKEND_SERVICES=192.168.0.158:11800 -DSW_AGENT_NAME=apisvc-mall     -jar /apisvc-mall.jar &
nohup java -Xms128M -Xmx256M -javaagent:/agent/skywalking-agent.jar -DSW_AGENT_COLLECTOR_BACKEND_SERVICES=192.168.0.158:11800 -DSW_AGENT_NAME=apisvc-oms.jar  -jar /apisvc-oms.jar &
nohup java -Xms128M -Xmx256M -javaagent:/agent/skywalking-agent.jar -DSW_AGENT_COLLECTOR_BACKEND_SERVICES=192.168.0.158:11800 -DSW_AGENT_NAME=microsvc-user   -jar /microsvc-user.jar &
nohup java -Xms128M -Xmx256M -javaagent:/agent/skywalking-agent.jar -DSW_AGENT_COLLECTOR_BACKEND_SERVICES=192.168.0.158:11800 -DSW_AGENT_NAME=microsvc-org    -jar /microsvc-org.jar &
nohup java -Xms128M -Xmx256M -javaagent:/agent/skywalking-agent.jar -DSW_AGENT_COLLECTOR_BACKEND_SERVICES=192.168.0.158:11800 -DSW_AGENT_NAME=microsvc-oms    -jar /microsvc-oms.jar

#while [[ true ]]; do
#  sleep 1
#done
