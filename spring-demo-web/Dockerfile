FROM openjdk:11

WORKDIR /usr/src/app

COPY ./build/libs/spring-demo-web.jar .

EXPOSE 7301

CMD java -jar -Dspring.profiles.active=production spring-demo-web.jar