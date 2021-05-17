FROM openjdk:8-jdk-alpine
# VOLUME 指定了临时文件目录为/tmp。
# 其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp
#VOLUME 指定了临时文件目录为/tmp。其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp。改步骤是可选的，如果涉及到文件系统的应用就很有必要了。/tmp目录用来持久化到 Docker 数据文件夹，因为 Spring Boot 使用的内嵌 Tomcat 容器默认使用/tmp作为工作目录
VOLUME /tmp
ARG JAR_FILE
# 将jar包添加到容器中并更名为cabbage.jar
ADD ${JAR_FILE} cabbage.jar
#ENTRYPOINT 执行项目 cabbage.jar。为了缩短 Tomcat 启动时间，
#添加一个系统属性指向 “/dev/./urandom” 作为 Entropy Source
ENTRYPOINT ["java","-jar","/cabbage.jar"]

