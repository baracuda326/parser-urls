FROM openjdk:8-jdk-alpine
ADD target/peer.jar peer.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/peer.jar"]