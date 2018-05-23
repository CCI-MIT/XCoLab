#!/bin/bash

# stop script if any build fails
set -e

echo "[INFO] Compiling..."

# Build binaries
./mvnw package -T 1C

cd scripts/run
echo "[INFO] Copying binaries..."
./copyBinaries.sh

echo "[INFO] Starting xCoLab..."
./startAll.sh
cd ../..

echo "[INFO] All components started. They may take a few minutes to finish starting up."
echo "[INFO] Once the servers have finished starting up, will be able to access the xCoLab on http://localhost:18082 (default port)"
echo "[INFO] The script will exit now. You can follow the logs by running the tailAll.sh script in scripts/run."
