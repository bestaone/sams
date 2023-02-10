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

ADD ./microsvc/target/microsvc-user.jar microsvc-user.jar
RUN sh -c 'touch /microsvc-user.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005", "-jar", "/microsvc-user.jar"]
