FROM openjdk:8u191-jdk-alpine

ADD target/content-service-1.0-SNAPSHOT.jar content-service-1.0-SNAPSHOT.jar
ADD entrypoint.sh /entrypoint.sh

EXPOSE 18087
ENTRYPOINT ["/entrypoint.sh"]
