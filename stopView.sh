#!/bin/bash

VIEW=xcolab-view

echo "#####################################################################################"
echo "[INFO] Stopping view..."

cd view/target
echo "[INFO] Sending kill signal to ${VIEW}"
PIDFile="${VIEW}.pid"
kill $(<"${PIDFile}")
rm ${VIEW}.pid
cd ../..

echo "[INFO] Done."
echo "#####################################################################################"
