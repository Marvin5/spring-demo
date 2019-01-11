FROM openjdk:11

WORKDIR /usr/src/app

COPY ./build/libs/spring-demo-job.jar .

CMD java -jar -Dspring.profiles.active=production spring-demo-web.jar