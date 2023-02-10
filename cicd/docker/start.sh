#!/bin/sh

echo '192.168.0.158 nacos-server' >> /etc/hosts
echo '192.168.0.158 mysql-server' >> /etc/hosts
echo '192.168.0.158 redis-server' >> /etc/hosts
echo '192.168.0.158 seata-server' >> /etc/hosts
echo '192.168.0.158 sentinel-server' >> /etc/hosts

nohup /usr/local/nginx/sbin/nginx &
nohup java -jar -Xms128M -Xmx256M /compsvc-gateway.jar &
nohup java -jar -Xms128M -Xmx256M /apisvc-auth.jar &
nohup java -jar -Xms128M -Xmx256M /apisvc-mall.jar &
nohup java -jar -Xms128M -Xmx256M /microsvc-user.jar &
nohup java -jar -Xms128M -Xmx256M /microsvc-org.jar &

while [[ true ]]; do
sleep 1
done