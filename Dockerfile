FROM openjdk:8-jdk-alpine

ADD target/springboot-lambda-0.0.1-SNAPSHOT*.jar springboot-lambda-function.jar

ENTRYPOINT ["java", "-jar", "springboot-lambda-function.jar" ]
