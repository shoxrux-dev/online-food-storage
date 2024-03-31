FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY target/online-food-storage.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]