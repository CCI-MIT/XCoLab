#!/bin/bash

source config.sh

VIEW=xcolab-view

echo "#####################################################################################"
echo "[INFO] Starting view..."

mkdir -p ${VIEW_DEPLOY_DIR}

function startView {
    maxMem=${1:-4G}

    PID_FILE="${VIEW}.pid"
    if [ -f ${VIEW_DEPLOY_DIR}/${PID_FILE} ]; then
        echo "[WARN] ${VIEW} is already running (${VIEW}.pid file exists)."
    else
        cp ${BINARY_SOURCE_DIR}/view/${VIEW}-1.0-SNAPSHOT.war ${VIEW_DEPLOY_DIR}/
        SAVED_DIR=`pwd`
        cd ${VIEW_DEPLOY_DIR}
        OUT_FILE="${VIEW}.out"
        if ! [ -z ${LOG_SERVER+x} ]; then
            DATE="$(date +"%Y-%m-%d_%H-%M-%S")"
            REMOTE_LOG_FILE_DEST="${LOG_SERVER}:${LOG_SERVER_FOLDER}/${DATE}-${OUT_FILE}"
            echo "[INFO] Copying logs to ${REMOTE_LOG_FILE_DEST}"
            scp ${OUT_FILE} ${REMOTE_LOG_FILE_DEST}
        fi
        rm ${OUT_FILE} > /dev/null 2>&1
        exec java -Xmx${maxMem} -Xms1G -XX:-OmitStackTraceInFastThrow \
            -jar ${VIEW}-1.0-SNAPSHOT.war > ${OUT_FILE}  & echo $! > ${PID_FILE}
    fi
}

if [ $# -gt 0 ]; then
    startView $1
else
    if [ -f ~/.xcolab.run-view.sh ]; then
        # Allow starting the view with a custom script
        # This is to allow changing the max memory configuration
        ~/.xcolab.run-view.sh
    else
        startView
    fi
fi

echo "[INFO] Done."
echo "#####################################################################################"
