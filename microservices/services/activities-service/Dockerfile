FROM openjdk:8u191-jdk-alpine

ADD target/activities-service-1.0-SNAPSHOT.jar activities-service-1.0-SNAPSHOT.jar
ADD entrypoint.sh /entrypoint.sh

EXPOSE 18084
ENTRYPOINT ["/entrypoint.sh"]
