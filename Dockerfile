FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ADD target/shivaknt-0.0.1-SNAPSHOT.jar shivaknt.jar
ENTRYPOINT ["java", "-jar", "shivaknt-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080