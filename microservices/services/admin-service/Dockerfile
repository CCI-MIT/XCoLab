FROM openjdk:8u191-jdk-alpine

ADD target/admin-service-1.0-SNAPSHOT.jar admin-service-1.0-SNAPSHOT.jar
ADD entrypoint.sh /entrypoint.sh

EXPOSE 18085
ENTRYPOINT ["/entrypoint.sh"]
