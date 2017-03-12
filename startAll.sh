#!/bin/bash

echo "#####################################################################################"
echo "[INFO] Starting XCoLab..."

./startEureka.sh

./startServices.sh

./startView.sh

echo "[INFO] Done."
echo "#####################################################################################"
