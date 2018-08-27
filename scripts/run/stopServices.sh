#!/bin/bash

source config.sh

echo "#####################################################################################"
echo "[INFO] Stopping service(s)..."


function stopService {
    service=$1
    if [ -f ${SERVICE_DEPLOY_DIR}/${service}.pid ]; then
        echo "[INFO] Sending kill signal to ${service}"
        PID_FILE="${SERVICE_DEPLOY_DIR}/${service}.pid"
        kill $(<"${PID_FILE}")
        rm ${PID_FILE}
    else
        echo "[INFO] ${service} is not running"
    fi
}

PREVIOUS_DIR=`pwd`
cd ${BINARY_SOURCE_DIR}/services

if [ $# -gt 0 ]; then
    stopService $1
else
    for serviceJar in *-service-*; do
        service="$(echo ${serviceJar} | cut -d'-' -f1,2)"
        stopService ${service}
    done
fi

cd "${PREVIOUS_DIR}"

echo "[INFO] Done."
echo "#####################################################################################"