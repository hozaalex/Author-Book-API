FROM openjdk:19-jdk-alpine
MAINTAINER Alex.com
COPY target/*jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
