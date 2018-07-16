#!/bin/bash

#= Directory configuration

# Option 1: Default config for compiling and running inside repo
ROOT_PATH="../.."
VCS_ROOT=${ROOT_PATH}

# Option 2: Custom config for binary deployments
# ROOT_PATH="/home/myuser"
# VCS_ROOT="${ROOT_PATH}/src"


#= Log configuration
# LOG_SERVER=log-user@log-host.com
LOG_SERVER_FOLDER=xcolab


#= Derived configuration variables, should remain unchanged in most deployments

# Source directory from which to retrieve the latest binaries
export BINARY_SOURCE_DIR="${ROOT_PATH}/binaries"

# Directory from which to run binaries
export DEPLOY_DIR="${ROOT_PATH}/bundle"

CLOUD_DEPLOY_DIR=${DEPLOY_DIR}/cloud
SERVICE_DEPLOY_DIR=${DEPLOY_DIR}/services
VIEW_DEPLOY_DIR=${DEPLOY_DIR}/view
