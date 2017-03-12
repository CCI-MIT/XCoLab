#!/bin/bash

echo "#####################################################################################"
echo "[INFO] Stopping service(s)..."

function stopService {
    service=$1
    if [ -d "${service}" ]; then
        cd ${service}/target
        if [ -f ${service}.pid ]; then
            echo "[INFO] Sending kill signal to ${service}"
            PIDFile="${service}.pid"
            kill $(<"${PIDFile}")
            rm ${service}.pid
        else
            echo "[INFO] ${service} is not running"
        fi
        cd ../..
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