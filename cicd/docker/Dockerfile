FROM bestaone/centos:7.9.1

# 设置语言
ENV LANG en_US.UTF-8

# 设置地理位置
ENV TZ=Asia/Shanghai

# 设置时区
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 解决绘制图形验证码报错问题
RUN yum install dejavu-sans-fonts fontconfig -y

# 挂在目录
VOLUME /data

# 暴露远程deug端口
EXPOSE 5005

ADD compsvc-gateway.jar     compsvc-gateway.jar
ADD apisvc-auth.jar         apisvc-auth.jar
ADD apisvc-mall.jar         apisvc-mall.jar
ADD microsvc-user.jar       microsvc-user.jar
ADD microsvc-org.jar        microsvc-org.jar

COPY dist /usr/local/nginx/html/app
ADD app.conf /usr/local/nginx/conf/vhost/

ADD start.sh start.sh
RUN chmod +x start.sh

ENTRYPOINT ["sh","-c","./start.sh"]
