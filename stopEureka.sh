#!/bin/bash

BINARY_DIR=binaries/cloud
EUREKA=eureka-server

echo "#####################################################################################"
echo "[INFO] Starting Eureka server..."

echo "[INFO] Sending kill signal to ${EUREKA}"
PID_FILE="${BINARY_DIR}/${EUREKA}.pid"
kill $(<"${PID_FILE}")
rm ${PID_FILE}

echo "[INFO] Done."
echo "#####################################################################################"
