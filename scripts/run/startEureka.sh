#!/bin/bash

source config.sh

EUREKA=eureka-server

echo "#####################################################################################"
echo "[INFO] Starting Eureka server..."

mkdir -p ${CLOUD_DEPLOY_DIR}

PID_FILE="${CLOUD_DEPLOY_DIR}/${EUREKA}.pid"
if [ -f ${PID_FILE} ]; then
    echo "[WARN] ${EUREKA} is already running (${EUREKA}.pid file exists)."
else
    cp ${BINARY_SOURCE_DIR}/cloud/${EUREKA}-1.0-SNAPSHOT.jar ${CLOUD_DEPLOY_DIR}/
    OUT_FILE="${CLOUD_DEPLOY_DIR}/${EUREKA}.out"
    rm ${OUT_FILE} > /dev/null 2>&1
    exec java -Xmx1G -Xms256M -jar ${CLOUD_DEPLOY_DIR}/${EUREKA}-1.0-SNAPSHOT.jar > ${OUT_FILE}  & echo $! > ${PID_FILE}
fi

echo "[INFO] Done."
echo "#####################################################################################"
