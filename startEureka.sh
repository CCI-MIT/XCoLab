#!/bin/bash

EUREKA=eureka-server

echo "#####################################################################################"
echo "[INFO] Starting Eureka server..."

cd microservices/cloud/eureka-server/target
if [ -f ${EUREKA}.pid ]; then
    echo "[WARN] ${EUREKA} is already running (${EUREKA}.pid file exists)."
else
    rm ${EUREKA}.out > /dev/null 2>&1
    exec java -Xmx1G -Xms256M -jar ${EUREKA}-1.0-SNAPSHOT.jar > ${EUREKA}.out  & echo $! > ${EUREKA}.pid
fi
cd ../../../..

echo "[INFO] Done."
echo "#####################################################################################"
