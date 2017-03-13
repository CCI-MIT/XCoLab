#!/bin/bash

echo "#####################################################################################"
echo "[INFO] Stopping service(s)..."

SERVICE_DIR=../../binaries/services

function stopService {
    service=$1
    if [ -d "${service}" ]; then
        if [ -f ${SERVICE_DIR}/${service}.pid ]; then
            echo "[INFO] Sending kill signal to ${service}"
            PID_FILE="${SERVICE_DIR}/${service}.pid"
            kill $(<"${PID_FILE}")
            rm ${PID_FILE}
        else
            echo "[INFO] ${service} is not running"
        fi
    else
        echo "Skipping ${service} - not a directory"
    fi
}

cd microservices/services

if [ $# -gt 0 ]; then
    stopService $1
else
    for service in *-service; do
        stopService ${service}
    done
fi

cd ../..

echo "[INFO] Done."
echo "#####################################################################################"