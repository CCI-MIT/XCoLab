#!/bin/bash

source config.sh

echo "#####################################################################################"
echo "[INFO] Stopping XCoLab..."

./stopEureka.sh

./stopServices.sh

./stopView.sh

echo "[INFO] Done."
echo "#####################################################################################"
