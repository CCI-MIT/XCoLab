#!/bin/bash

source config.sh

VIEW=xcolab-view

echo "#####################################################################################"
echo "[INFO] Stopping view..."

echo "[INFO] Sending kill signal to ${VIEW}"
PID_FILE="${VIEW_DEPLOY_DIR}/${VIEW}.pid"
kill $(<"${PID_FILE}")
rm ${PID_FILE}

echo "[INFO] Done."
echo "#####################################################################################"
