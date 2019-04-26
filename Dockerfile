FROM openjdk:8-jdk-alpine

ADD target/springboot-lambda-function*.jar springboot-lambda-function.jar

ENTRYPOINT ["java", "-jar", "springboot-lambda-function.jar" ]
