FROM openjdk:21
ARG JAR_FILE=target/*.jar
COPY ./target/EMIS-0.0.1-SNAPSHOT.jar emis.jar
ENTRYPOINT ["java" , "-jar", "emis.jar"]