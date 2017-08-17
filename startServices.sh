#!/bin/bash

echo "#####################################################################################"
echo "[INFO] Starting service(s)..."

SERVICE_DIR=../../binaries/services

cd microservices/services
mkdir -p ${SERVICE_DIR}

function startService {
    service=$1
    if [ -d "${service}" ]; then
        PID_FILE="${service}.pid"
        if [ -f ${SERVICE_DIR}/${PID_FILE} ]; then
            echo "[WARN] ${service} is already running (${service}.pid file exists)."
        else
            echo "[INFO] Starting ${service}"
            cp ${service}/target/${service}-1.0-SNAPSHOT.jar ${SERVICE_DIR}/
            SAVED_DIR=`pwd`
            cd ${SERVICE_DIR}
            OUT_FILE=${service}.out
            rm ${OUT_FILE} > /dev/null 2>&1
            exec java -Xmx512M -Xms128M -jar ${service}-1.0-SNAPSHOT.jar > ${OUT_FILE} & echo $! > ${PID_FILE}
            cd ${SAVED_DIR}
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


