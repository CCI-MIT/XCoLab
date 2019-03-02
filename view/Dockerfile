FROM openjdk:8u191-jdk-alpine

ADD target/xcolab-view-1.0-SNAPSHOT.war xcolab-view-1.0-SNAPSHOT.war
ADD entrypoint.sh /entrypoint.sh

EXPOSE 18082
ENTRYPOINT ["/entrypoint.sh"]
