#!/bin/bash

BINARY_DIR=binaries/view
VIEW=xcolab-view

echo "#####################################################################################"
echo "[INFO] Stopping view..."

echo "[INFO] Sending kill signal to ${VIEW}"
PID_FILE="${BINARY_DIR}/${VIEW}.pid"
kill $(<"${PID_FILE}")
rm ${PID_FILE}

echo "[INFO] Done."
echo "#####################################################################################"
