#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -Dmaven.test.skip=true

#
# Package stage
#
FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=/home/app/target/*.jar
COPY --from=build ${JAR_FILE} application.jar
ARG CONFIG=/home/app/target/classes/config/*.yml
RUN mkdir /config
COPY --from=build ${CONFIG} /config/
EXPOSE 8761
ENTRYPOINT ["java","-jar","/application.jar"]
