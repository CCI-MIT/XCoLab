#!/bin/bash

echo "#####################################################################################"
echo "[INFO] Starting service(s)..."

SERVICE_DIR=../../binaries/services

cd microservices/services
mkdir -p ${SERVICE_DIR}

function startService {
    service=$1
    if [ -d "${service}" ]; then
        PID_FILE=${SERVICE_DIR}/${service}.pid
        if [ -f ${PID_FILE} ]; then
            echo "[WARN] ${service} is already running (${service}.pid file exists)."
        else
            echo "[INFO] Starting ${service}"
            cp ${service}/target/${service}-1.0-SNAPSHOT.jar ${SERVICE_DIR}/
            OUT_FILE=${SERVICE_DIR}/${service}.out
            rm ${OUT_FILE} > /dev/null 2>&1
            exec java -Xmx1G -Xms256M -jar ${SERVICE_DIR}/${service}-1.0-SNAPSHOT.jar > ${OUT_FILE} & echo $! > ${PID_FILE}
        fi
    fi
}

if [ $# -gt 0 ]; then
    startService $1
else
    for service in *-service; do
        startService ${service}
        sleep 5
    done
fi

cd ../..

echo "[INFO] Done."
echo "#####################################################################################"


