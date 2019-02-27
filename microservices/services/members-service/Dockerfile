FROM openjdk:8u191-jdk-alpine

ADD target/members-service-1.0-SNAPSHOT.jar members-service-1.0-SNAPSHOT.jar
ADD entrypoint.sh /entrypoint.sh

EXPOSE 18091
ENTRYPOINT ["/entrypoint.sh"]
