FROM openjdk:8
WORKDIR /java
COPY build/libs/spring-demo-0.0.1-SNAPSHOT.jar .
CMD ["java","-jar","spring-demo-0.0.1-SNAPSHOT.jar"]