FROM openjdk:8u191-jdk-alpine

ADD target/emails-service-1.0-SNAPSHOT.jar emails-service-1.0-SNAPSHOT.jar
ADD entrypoint.sh /entrypoint.sh

EXPOSE 18089
ENTRYPOINT ["/entrypoint.sh"]