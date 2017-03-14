#!/bin/bash

source config.sh

EUREKA=eureka-server

echo "#####################################################################################"
echo "[INFO] Starting Eureka server..."

echo "[INFO] Sending kill signal to ${EUREKA}"
PID_FILE="${CLOUD_DEPLOY_DIR}/${EUREKA}.pid"
kill $(<"${PID_FILE}")
rm ${PID_FILE}

echo "[INFO] Done."
echo "#####################################################################################"
