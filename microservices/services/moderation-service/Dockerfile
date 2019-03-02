FROM openjdk:8u191-jdk-alpine

ADD target/moderation-service-1.0-SNAPSHOT.jar moderation-service-1.0-SNAPSHOT.jar
ADD entrypoint.sh /entrypoint.sh

EXPOSE 18090
ENTRYPOINT ["/entrypoint.sh"]
