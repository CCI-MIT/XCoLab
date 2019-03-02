#!/bin/sh

# Stop execution if any command returns nonzero exit
set -e

JAVA_OPTS="-Dserver.port=18090 -Xmx1G -Xms256M -XX:-OmitStackTraceInFastThrow"

exec java -jar $JAVA_OPTS /moderation-service-1.0-SNAPSHOT.jar
