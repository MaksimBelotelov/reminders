FROM openjdk:21-jdk
WORKDIR /app
COPY target/reminders-0.0.1-SNAPSHOT app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]q