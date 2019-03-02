FROM openjdk:8u191-jdk-alpine

ADD target/modeling-service-1.0-SNAPSHOT.jar modeling-service-1.0-SNAPSHOT.jar
ADD entrypoint.sh /entrypoint.sh

EXPOSE 18092
ENTRYPOINT ["/entrypoint.sh"]
