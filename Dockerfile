FROM 3-amazoncorretto-17 AS mvn
WORKDIR /usr/local
COPY ./  /usr/local/code
RUN cd code && mvn clean install -Dmaven.test.skip=true -Dmaven.javadoc.skip=true


FROM openjdk:17-ea-33-oraclelinux8
WORKDIR /usr/local
COPY mvn://usr/local/code/target/*.jar /usr/local/springboot.jar
CMD ["/usr/bin/java", "-jar", "/usr/local/springboot.jar"]
