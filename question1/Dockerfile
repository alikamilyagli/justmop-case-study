FROM openjdk:8-jre-alpine
EXPOSE 8081
WORKDIR /app
COPY target/question1-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [ "java", "-jar", "app.jar" ]