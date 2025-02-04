FROM openjdk:11
EXPOSE 8090
ADD target/user-1.0.jar user-1.0.jar
ENTRYPOINT ["java","-jar","/user-1.0.jar"]