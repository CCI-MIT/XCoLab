FROM openjdk:8u191-jdk-alpine

ADD target/tracking-service-1.0-SNAPSHOT.jar tracking-service-1.0-SNAPSHOT.jar
ADD entrypoint.sh /entrypoint.sh

EXPOSE 18094
ENTRYPOINT ["/entrypoint.sh"]
