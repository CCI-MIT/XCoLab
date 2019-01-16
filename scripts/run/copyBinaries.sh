#!/bin/bash

# Run this file after compiling from source to update the compiled binaries

source config.sh

BINARY_DIR_CLOUD=${BINARY_SOURCE_DIR}/cloud
BINARY_DIR_VIEW=${BINARY_SOURCE_DIR}/view
BINARY_DIR_SERVICES=${BINARY_SOURCE_DIR}/services

mkdir -p ${BINARY_DIR_CLOUD}
mkdir -p ${BINARY_DIR_VIEW}
mkdir -p ${BINARY_DIR_SERVICES}

cp ${VCS_ROOT}/microservices/cloud/eureka-server/target/eureka-server-1.0-SNAPSHOT.jar ${BINARY_DIR_CLOUD}/
cp ${VCS_ROOT}/view/target/xcolab-view-1.0-SNAPSHOT.war ${BINARY_DIR_VIEW}/

for service in *-service; do
    cp ${VCS_ROOT}/microservices/services/${service}/target/${service}-1.0-SNAPSHOT.jar ${BINARY_DIR_SERVICES}/
done
