#
# Build stage
#
FROM maven:3.5-jdk-8 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:8-jre-slim
COPY --from=build /home/app/target/notebook.jar /usr/local/lib/notebook.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","/usr/local/lib/notebook.jar"]