#!/bin/bash

echo "#####################################################################################"
echo "[INFO] Starting XCoLab..."

./startEureka.sh

./startServices.sh

sleep 10

./startView.sh

echo "[INFO] Done."
echo "#####################################################################################"
