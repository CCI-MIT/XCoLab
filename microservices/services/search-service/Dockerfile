FROM openjdk:8u191-jdk-alpine

ADD target/search-service-1.0-SNAPSHOT.jar search-service-1.0-SNAPSHOT.jar
ADD entrypoint.sh /entrypoint.sh

EXPOSE 18093
ENTRYPOINT ["/entrypoint.sh"]
