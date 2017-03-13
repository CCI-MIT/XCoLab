#!/bin/bash

echo "#####################################################################################"
echo "[INFO] Starting service(s)..."

function startService {
    service=$1
    if [ -d "${service}" ]; then
        cd ${service}/target
        if [ -f ${service}.pid ]; then
            echo "[WARN] ${service} is already running (${service}.pid file exists)."
        else
            echo "[INFO] Starting ${service}"
            rm ${service}.out > /dev/null 2>&1
            exec java -Xmx1G -Xms256M -jar ${service}-1.0-SNAPSHOT.jar > ${service}.out  & echo $! > ${service}.pid
        fi
        cd ../..
    fi
}

cd microservices/services

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


