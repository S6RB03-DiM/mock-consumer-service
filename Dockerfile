#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY pom.xml /opt/
COPY src /opt/src/
WORKDIR /opt
RUN mvn -f /opt/pom.xml clean package
COPY target/*.jar /opt/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /opt/target/mock-consumer-service-0.0.1-SNAPSHOT.jar /usr/local/lib/mock.jar
ENV PORT 8003
EXPOSE 8003
ENTRYPOINT ["java","-jar","/usr/local/lib/mock.jar"]