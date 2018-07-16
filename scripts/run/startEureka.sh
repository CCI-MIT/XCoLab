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
    if ! [ -z ${LOG_SERVER+x} ]; then
        DATE="$(date +"%Y-%m-%d_%H-%M-%S")"
        REMOTE_LOG_FILE_DEST="${LOG_SERVER}:${LOG_SERVER_FOLDER}/${DATE}-${OUT_FILE}"
        echo "[INFO] Copying logs to ${REMOTE_LOG_FILE_DEST}"
        scp ${OUT_FILE} ${REMOTE_LOG_FILE_DEST}
    fi
    rm ${OUT_FILE} > /dev/null 2>&1
    exec java -Xmx1G -Xms256M -jar ${EUREKA}-1.0-SNAPSHOT.jar > ${OUT_FILE}  & echo $! > ${PID_FILE}
    cd ${SAVED_DIR}
fi

echo "[INFO] Done."
echo "#####################################################################################"
