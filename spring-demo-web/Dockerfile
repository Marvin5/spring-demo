FROM openjdk:11

ARG profile=default

ENV profile=${profile}

WORKDIR /usr/src/app

COPY ./build/libs/spring-demo-web.jar .

EXPOSE 7301

CMD java -jar -Dspring.profiles.active=${profile} spring-demo-web.jar