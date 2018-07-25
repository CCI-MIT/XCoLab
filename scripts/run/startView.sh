#!/bin/bash

source config.sh

VIEW=xcolab-view

echo "#####################################################################################"
echo "[INFO] Starting view..."

mkdir -p ${VIEW_DEPLOY_DIR}

PID_FILE="${VIEW}.pid"
if [ -f ${VIEW_DEPLOY_DIR}/${PID_FILE} ]; then
    echo "[WARN] ${VIEW} is already running (${VIEW}.pid file exists)."
else
    cp ${BINARY_SOURCE_DIR}/view/${VIEW}-1.0-SNAPSHOT.war ${VIEW_DEPLOY_DIR}/
    SAVED_DIR=`pwd`
    cd ${VIEW_DEPLOY_DIR}
    OUT_FILE="${VIEW}.out"
    rm ${OUT_FILE} > /dev/null 2>&1
    exec java -Xmx4G -Xms1G -XX:-OmitStackTraceInFastThrow \
        -jar ${VIEW}-1.0-SNAPSHOT.war > ${OUT_FILE}  & echo $! > ${PID_FILE}
fi

echo "[INFO] Done."
echo "#####################################################################################"
