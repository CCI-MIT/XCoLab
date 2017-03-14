#!/bin/bash

BINARY_DIR=binaries/cloud
COMPILE_DIR=microservices/cloud/eureka-server/target
EUREKA=eureka-server

echo "#####################################################################################"
echo "[INFO] Starting Eureka server..."

mkdir -p ${BINARY_DIR}

PID_FILE="${BINARY_DIR}/${EUREKA}.pid"
if [ -f ${PID_FILE} ]; then
    echo "[WARN] ${EUREKA} is already running (${EUREKA}.pid file exists)."
else
    cp ${COMPILE_DIR}/${EUREKA}-1.0-SNAPSHOT.jar ${BINARY_DIR}/
    OUT_FILE="${BINARY_DIR}/${EUREKA}.out"
    rm ${OUT_FILE} > /dev/null 2>&1
    exec java -Xmx1G -Xms256M -jar ${BINARY_DIR}/${EUREKA}-1.0-SNAPSHOT.jar > ${OUT_FILE}  & echo $! > ${PID_FILE}
fi

echo "[INFO] Done."
echo "#####################################################################################"
