#!/bin/sh

# Stop execution if any command returns nonzero exit
set -e

JAVA_OPTS="-Dserver.port=18086 -Xmx1G -Xms256M -XX:-OmitStackTraceInFastThrow"

exec java -jar $JAVA_OPTS /comment-service-1.0-SNAPSHOT.jar