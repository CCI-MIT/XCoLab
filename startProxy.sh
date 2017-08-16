#!/bin/bash

DEPLOY_DIR=binaries/cloud
COMPILE_DIR=microservices/cloud/service-proxy/target
SERVER=service-proxy

echo "#####################################################################################"
echo "[INFO] Starting proxy server..."

mkdir -p ${DEPLOY_DIR}

PID_FILE="${SERVER}.pid"
if [ -f ${DEPLOY_DIR}/${PID_FILE} ]; then
    echo "[WARN] ${SERVER} is already running (${SERVER}.pid file exists)."
else
    cp ${COMPILE_DIR}/${SERVER}-1.0-SNAPSHOT.jar ${DEPLOY_DIR}/
    SAVED_DIR=`pwd`
    cd ${DEPLOY_DIR}
    OUT_FILE="${SERVER}.out"
    rm ${OUT_FILE} > /dev/null 2>&1
    exec java -Xmx256M -Xms128M -jar ${SERVER}-1.0-SNAPSHOT.jar > ${OUT_FILE}  & echo $! > ${PID_FILE}
    cd ${SAVED_DIR}
fi

echo "[INFO] Done."
echo "#####################################################################################"
