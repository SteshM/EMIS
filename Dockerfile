FROM openjdk:21
ARG JAR_FILE=target/*.jar
ENTRYPOINT ["java" , "-jar", "EMIS-0.0.1-SNAPSHOT.jar"]