
### 安装配置Nacos
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
#### Docker安装，支持MySQL数据库

在你的MySQL数据库中创建库nacos，并执行下面脚本：
```
sams\doc\db\nacos-2.0.0-mysql-schema.sql
```

#### 启动容器
```bash
docker run -d \
  -e MODE=standalone \
  -e JVM_XMS=256m -e JVM_XMX=256m \
  -e PREFER_HOST_MODE=hostname \
  -e SPRING_DATASOURCE_PLATFORM=mysql \
  -e MYSQL_SERVICE_HOST=127.0.0.1 \
  -e MYSQL_SERVICE_PORT=3306 \
  -e MYSQL_SERVICE_USER=root \
  -e MYSQL_SERVICE_PASSWORD=123456 \
  -e MYSQL_SERVICE_DB_NAME=nacos \
  -p 8848:8848 -p 9848:9848 -p 9849:9849 \
  --privileged=true \
  --restart=always \
  --name nacos-2.2.0 \
  nacos/nacos-server:v2.2.0
```

#### 访问Nacos
```
http://127.0.0.1:8848/nacos
账密：nacos\nacos
```