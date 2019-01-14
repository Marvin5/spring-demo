# Spring Demo

## 环境要求

[JDK11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)

Gradle 5.0 可以直接使用gradlew，但是需要保证JAVA_HOME为JDK11

## 项目结构

spring-demo-core，属于library module。不可以独立运行，只能被web/job引用。

spring-demo-web, 依赖core,主要包括对外提供web api的服务,是一个单独的Spring Boot Application.可独立运行。

spring-demo-job, 依赖core,主要包括jms,scheduler服务,是一个单独的Spring Boot Application。可独立运行。

## Get Start

### 导入Idea

先点Project from exist source，然后再点gradle。导入完成后，Idea会有两个Run项目。

1. SpringBootDemoWebApplication, 隶属于spring-demo-web模块. 
2. SpringBootDemoJobApplication, 隶属于spring-demo-job模块。

### 运行

只有Application module才可以运行。若导入idea，可以直接运行对应的Application即可。也可以从gradle task运行。

```bash
cd spring-demo-web
gradlew bootRun
```

### 构建

只有Application module才可以构建，进入spring-demo-web或spring-demo-job。执行

```bash
cd spring-demo-web
gradlew bootJar
java -jar -Dspring.profiles.active spring-demo-web/build/libs/spring-demo-web.jar
```

打包docker镜像
```bash
docker build --tag spring-demo-web --build-arg profile=production .
```

运行docker镜像

```bash
docker run --name spring-demo-web --network my-bridge -p 7301:7301 spring-demo-web
```

## Contribute

### java格式化工具

请安装[google-java-format](https://plugins.jetbrains.com/plugin/8527-google-java-format)，并且使用Default Google Java Style。]

