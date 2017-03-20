#!/bin/bash

DEPLOY_DIR=binaries/cloud
SERVER=service-proxy

echo "#####################################################################################"
echo "[INFO] Starting proxy server..."

echo "[INFO] Sending kill signal to ${SERVER}"
PID_FILE="${DEPLOY_DIR}/${SERVER}.pid"
kill $(<"${PID_FILE}")
rm ${PID_FILE}

echo "[INFO] Done."
echo "#####################################################################################"
