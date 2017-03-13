#!/bin/bash

BINARY_DIR=binaries/view
COMPILE_DIR=view/target
VIEW=xcolab-view

echo "#####################################################################################"
echo "[INFO] Starting view..."

mkdir -p ${BINARY_DIR}

PID_FILE="${BINARY_DIR}/${VIEW}.pid"
if [ -f ${PID_FILE} ]; then
    echo "[WARN] ${VIEW} is already running (${VIEW}.pid file exists)."
else
    cp ${COMPILE_DIR}/${VIEW}-1.0-SNAPSHOT.jar ${BINARY_DIR}/
    OUT_FILE="${BINARY_DIR}/${VIEW}.out"
    rm ${OUT_FILE} > /dev/null 2>&1
    exec java -Xmx4G -Xms1G -jar ${BINARY_DIR}/${VIEW}-1.0-SNAPSHOT.jar > ${OUT_FILE}  & echo $! > ${PID_FILE}
fi

echo "[INFO] Done."
echo "#####################################################################################"
