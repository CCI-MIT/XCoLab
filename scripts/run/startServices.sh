#!/bin/bash

source config.sh

echo "#####################################################################################"
echo "[INFO] Starting service(s)..."

mkdir -p ${SERVICE_DEPLOY_DIR}

function startService {
    serviceName=$1-service
    port=${2:-0}
    maxMem=${3:-1G}

    PID_FILE=${serviceName}.pid
    if [ -f ${SERVICE_DEPLOY_DIR}/${PID_FILE} ]; then
        echo "[WARN] ${serviceName} is already running (${serviceName}.pid file exists)."
    else
        echo "[INFO] Starting ${serviceName}"
        cp ${BINARY_SOURCE_DIR}/services/${serviceName}-1.0-SNAPSHOT.jar ${SERVICE_DEPLOY_DIR}/
        SAVED_DIR=`pwd`
        cd ${SERVICE_DEPLOY_DIR}
        OUT_FILE=${serviceName}.out
        if ! [ -z ${LOG_SERVER+x} ]; then
            DATE="$(date +"%Y-%m-%d_%H-%M-%S")"
            REMOTE_LOG_FILE_DEST="${LOG_SERVER}:${LOG_SERVER_FOLDER}/${DATE}-${OUT_FILE}"
            echo "[INFO] Copying logs to ${REMOTE_LOG_FILE_DEST}"
            scp ${OUT_FILE} ${REMOTE_LOG_FILE_DEST}
        fi
        rm ${OUT_FILE} > /dev/null 2>&1
        exec java -Dserver.port=${port} -Xmx${maxMem} -Xms256M -XX:-OmitStackTraceInFastThrow \
         -jar ${serviceName}-1.0-SNAPSHOT.jar > ${OUT_FILE} & echo $! > ${PID_FILE}
        cd "${SAVED_DIR}"
    fi
}

PREVIOUS_DIR=`pwd`
cd ${BINARY_SOURCE_DIR}/services

if [ $# -gt 0 ]; then
    startService $1 $2 $3
else
    if [ -f ~/.xcolab.run-services.sh ]; then
        # Allow starting service with a custom script
        # This is to allow changing port numbers, max memory, or which services are run
        source ~/.xcolab.run-services.sh
    else
        startService activity 18084
        startService admin 18085
        sleep 2
        startService comment 18086
        startService content 18087
        sleep 2
        startService contest 18088
        startService email 18089
        sleep 2
        startService user 18090
        startService modeling 18091
        sleep 2
        startService moderation 18092
        startService search 18093
        sleep 1
        startService tracking 18094
    fi
fi

cd "${PREVIOUS_DIR}"

echo "[INFO] Done."
echo "#####################################################################################"


