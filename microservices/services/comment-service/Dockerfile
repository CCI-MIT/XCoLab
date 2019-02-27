FROM openjdk:8u191-jdk-alpine

ADD target/comment-service-1.0-SNAPSHOT.jar comment-service-1.0-SNAPSHOT.jar
ADD entrypoint.sh /entrypoint.sh

EXPOSE 18086
ENTRYPOINT ["/entrypoint.sh"]
