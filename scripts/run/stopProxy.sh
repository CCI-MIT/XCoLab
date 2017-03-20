#!/bin/bash

source config.sh

SERVER=service-proxy

echo "#####################################################################################"
echo "[INFO] Stopping proxy server..."

echo "[INFO] Sending kill signal to ${SERVER}"
PID_FILE="${CLOUD_DEPLOY_DIR}/${SERVER}.pid"
kill $(<"${PID_FILE}")
rm ${PID_FILE}

echo "[INFO] Done."
echo "#####################################################################################"
