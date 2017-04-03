#!/bin/bash

# Source directory from which to retrieve the latest binaries
export BINARY_SOURCE_DIR="/home/myuser/binaries"

# Directory from which to run binaries
export DEPLOY_DIR="/home/myuser/bundle"

CLOUD_DEPLOY_DIR=${DEPLOY_DIR}/cloud
SERVICE_DEPLOY_DIR=${DEPLOY_DIR}/services
VIEW_DEPLOY_DIR=${DEPLOY_DIR}/view