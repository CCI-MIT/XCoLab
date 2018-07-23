#!/bin/bash

source config.sh

echo "#####################################################################################"
echo "[INFO] Starting service(s)..."

mkdir -p ${SERVICE_DEPLOY_DIR}

function startService {
    service=$1
    PID_FILE=${service}.pid
    if [ -f ${SERVICE_DEPLOY_DIR}/${PID_FILE} ]; then
        echo "[WARN] ${service} is already running (${service}.pid file exists)."
    else
        echo "[INFO] Starting ${service}"
        cp ${BINARY_SOURCE_DIR}/services/${service}-1.0-SNAPSHOT.jar ${SERVICE_DEPLOY_DIR}/
        SAVED_DIR=`pwd`
        cd ${SERVICE_DEPLOY_DIR}
        OUT_FILE=${service}.out
        if ! [ -z ${LOG_SERVER+x} ]; then
            DATE="$(date +"%Y-%m-%d_%H-%M-%S")"
            REMOTE_LOG_FILE_DEST="${LOG_SERVER}:${LOG_SERVER_FOLDER}/${DATE}-${OUT_FILE}"
            echo "[INFO] Copying logs to ${REMOTE_LOG_FILE_DEST}"
            scp ${OUT_FILE} ${REMOTE_LOG_FILE_DEST}
        fi
        rm ${OUT_FILE} > /dev/null 2>&1
        exec java -Xmx1G -Xms256M -jar ${service}-1.0-SNAPSHOT.jar > ${OUT_FILE} & echo $! > ${PID_FILE}
        cd ${SAVED_DIR}
    fi
}

PREVIOUS_DIR=`pwd`
cd ${BINARY_SOURCE_DIR}/services

if [ $# -gt 0 ]; then
    startService $1
else
    for serviceJar in *-service-*; do
        service="$(echo ${serviceJar} | cut -d'-' -f1,2)"
        startService ${service}
        sleep 1
    done
fi

cd ${PREVIOUS_DIR}

echo "[INFO] Done."
echo "#####################################################################################"


