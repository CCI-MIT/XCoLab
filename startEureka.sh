#!/bin/bash

BINARY_DIR=binaries/cloud
COMPILE_DIR=microservices/cloud/eureka-server/target
EUREKA=eureka-server

echo "#####################################################################################"
echo "[INFO] Starting Eureka server..."

mkdir -p ${BINARY_DIR}

PID_FILE="${EUREKA}.pid"
if [ -f ${BINARY_DIR}/${PID_FILE} ]; then
    echo "[WARN] ${EUREKA} is already running (${EUREKA}.pid file exists)."
else
    cp ${COMPILE_DIR}/${EUREKA}-1.0-SNAPSHOT.jar ${BINARY_DIR}/
    SAVED_DIR=`pwd`
    cd ${BINARY_DIR}
    OUT_FILE="${EUREKA}.out"
    rm ${OUT_FILE} > /dev/null 2>&1
    exec java -Xmx256M -Xms128M -jar ${EUREKA}-1.0-SNAPSHOT.jar > ${OUT_FILE}  & echo $! > ${PID_FILE}
    cd ${SAVED_DIR}
fi

echo "[INFO] Done."
echo "#####################################################################################"
