FROM openjdk:8u191-jdk-alpine

ADD target/contest-service-1.0-SNAPSHOT.jar contest-service-1.0-SNAPSHOT.jar
ADD entrypoint.sh /entrypoint.sh

EXPOSE 18088
ENTRYPOINT ["/entrypoint.sh"]
