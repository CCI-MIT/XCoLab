#!/bin/bash

source config.sh

SERVER=service-proxy

echo "#####################################################################################"
echo "[INFO] Starting proxy server..."

./copyBinaries.sh
mkdir -p ${CLOUD_DEPLOY_DIR}

PID_FILE="${SERVER}.pid"
if [ -f ${CLOUD_DEPLOY_DIR}/${PID_FILE} ]; then
    echo "[WARN] ${SERVER} is already running (${SERVER}.pid file exists)."
else
    cp ${BINARY_SOURCE_DIR}/cloud/${SERVER}-1.0-SNAPSHOT.jar ${CLOUD_DEPLOY_DIR}/
    SAVED_DIR=`pwd`
    cd ${CLOUD_DEPLOY_DIR}
    OUT_FILE="${SERVER}.out"
    if ! [ -z ${LOG_SERVER+x} ]; then
        DATE="$(date +"%Y-%m-%d_%H-%M-%S")"
        REMOTE_LOG_FILE_DEST="${LOG_SERVER}:${LOG_SERVER_FOLDER}/${DATE}-${OUT_FILE}"
        echo "[INFO] Copying logs to ${REMOTE_LOG_FILE_DEST}"
        scp ${OUT_FILE} ${REMOTE_LOG_FILE_DEST}
    fi
    rm ${OUT_FILE} > /dev/null 2>&1
    exec java -Xmx512M -Xms128M -jar ${SERVER}-1.0-SNAPSHOT.jar > ${OUT_FILE}  & echo $! > ${PID_FILE}
    cd ${SAVED_DIR}
fi

echo "[INFO] Done."
echo "#####################################################################################"
