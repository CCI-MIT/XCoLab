#!/bin/bash

VIEW=xcolab-view

echo "#####################################################################################"
echo "[INFO] Starting view..."

cd view/target
if [ -f ${VIEW}.pid ]; then
    echo "[WARN] ${VIEW} is already running (${VIEW}.pid file exists)."
else
    rm ${VIEW}.out > /dev/null 2>&1
    exec java -Xmx4G -Xms1G -jar ${VIEW}-1.0-SNAPSHOT.jar > ${VIEW}.out  & echo $! > ${VIEW}.pid
fi
cd ../..

echo "[INFO] Done."
echo "#####################################################################################"
