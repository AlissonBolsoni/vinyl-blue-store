FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/music-sale-api-*.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=prod", "-jar", "/app.jar"]