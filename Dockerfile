FROM maven:3-amazoncorretto-17 AS mvn
WORKDIR /usr/local
COPY ./  /usr/local/code
RUN cd code && mvn clean install -Dmaven.test.skip=true -Dmaven.javadoc.skip=true


FROM openjdk:17-ea-33-oraclelinux8
WORKDIR /usr/local
COPY --from=mvn /usr/local/code/target/*.jar /usr/local/springboot.jar
# 可以通过启动是 追加命令来获取使用不同的配置
ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/local/springboot.jar"]
