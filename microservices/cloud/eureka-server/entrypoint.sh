#!/bin/sh

# Stop execution if any command returns nonzero exit
set -e

JAVA_OPTS="-Dserver.port=8761 -Xmx1G -Xms256M -XX:-OmitStackTraceInFastThrow"

exec java -jar $JAVA_OPTS /eureka-server-1.0-SNAPSHOT.jar
