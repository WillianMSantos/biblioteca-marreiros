FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY target/biblioteca-0.0.1-SNAPSHOT.jar /app/biblioteca.jar

ENTRYPOINT ["java", "-jar", "biblioteca.jar"]