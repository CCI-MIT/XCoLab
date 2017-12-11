#!/bin/bash

VCS_ROOT="../.."

# Root path for binary and deploy directories
# Default points to the repository root to run
ROOT_PATH="${VCS_ROOT}"
# ROOT_PATH="/home/myuser"


# Source directory from which to retrieve the latest binaries
export BINARY_SOURCE_DIR="${ROOT_PATH}/binaries"

# Directory from which to run binaries
export DEPLOY_DIR="${ROOT_PATH}/bundle"

CLOUD_DEPLOY_DIR=${DEPLOY_DIR}/cloud
SERVICE_DEPLOY_DIR=${DEPLOY_DIR}/services
VIEW_DEPLOY_DIR=${DEPLOY_DIR}/view
