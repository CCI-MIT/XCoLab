#!/bin/bash

source config.sh

EUREKA=eureka-server

echo "#####################################################################################"
echo "[INFO] Starting Eureka server..."

mkdir -p ${CLOUD_DEPLOY_DIR}

PID_FILE="${EUREKA}.pid"
if [ -f ${CLOUD_DEPLOY_DIR}/${PID_FILE} ]; then
    echo "[WARN] ${EUREKA} is already running (${EUREKA}.pid file exists)."
else
    cp ${BINARY_SOURCE_DIR}/cloud/${EUREKA}-1.0-SNAPSHOT.jar ${CLOUD_DEPLOY_DIR}/
    SAVED_DIR=`pwd`
    cd ${CLOUD_DEPLOY_DIR}
    OUT_FILE="${EUREKA}.out"
    rm ${OUT_FILE} > /dev/null 2>&1
    exec java -Xmx1G -Xms256M -jar ${EUREKA}-1.0-SNAPSHOT.jar > ${OUT_FILE}  & echo $! > ${PID_FILE}
    cd ${SAVED_DIR}
fi

echo "[INFO] Done."
echo "#####################################################################################"
