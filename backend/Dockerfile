FROM amazoncorretto:11-alpine-jdk
EXPOSE 8081
COPY target/chetty-0.0.1-SNAPSHOT.jar chetty-backend.jar
ENTRYPOINT  ["java", "-jar", "/chetty-backend.jar"]