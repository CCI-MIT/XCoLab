#!/bin/bash

EUREKA=eureka-server

echo "#####################################################################################"
echo "[INFO] Starting Eureka server..."

cd microservices/cloud/eureka-server/target
echo "[INFO] Sending kill signal to ${EUREKA}"
PIDFile="${EUREKA}.pid"
kill $(<"${PIDFile}")
rm ${EUREKA}.pid
cd ../../../..

echo "[INFO] Done."
echo "#####################################################################################"
