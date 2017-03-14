#!/bin/bash

source config.sh

echo "#####################################################################################"
echo "[INFO] Starting service(s)..."

mkdir -p ${SERVICE_DEPLOY_DIR}

function startService {
    service=$1
    PID_FILE=${SERVICE_DEPLOY_DIR}/${service}.pid
    if [ -f ${PID_FILE} ]; then
        echo "[WARN] ${service} is already running (${service}.pid file exists)."
    else
        echo "[INFO] Starting ${service}"
        cp ${BINARY_SOURCE_DIR}/services/${service}-1.0-SNAPSHOT.jar ${SERVICE_DEPLOY_DIR}/
        OUT_FILE=${SERVICE_DEPLOY_DIR}/${service}.out
        rm ${OUT_FILE} > /dev/null 2>&1
        exec java -Xmx1G -Xms256M -jar ${SERVICE_DEPLOY_DIR}/${service}-1.0-SNAPSHOT.jar > ${OUT_FILE} & echo $! > ${PID_FILE}
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
        sleep 5
    done
fi

cd ${PREVIOUS_DIR}

echo "[INFO] Done."
echo "#####################################################################################"


