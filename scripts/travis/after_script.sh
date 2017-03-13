#!/bin/bash

if [ $BUILD_TYPE == 'deploy' ]; then
    echo "Creating binary directories"
    mkdir binaries
    mkdir binaries/view
    mkdir binaries/cloud
    mkdir binaries/services

    echo "Moving view binary"
    mv view/target/xcolab-view-1.0-SNAPSHOT.jar binaries/view/

    echo "Moving eureka binary"
    mv microservices/cloud/eureka-server/target/eureka-server-1.0-SNAPSHOT.jar binaries/cloud/

    echo "Moving service binaries"
    mv microservices/services/*-service/target/*-1.0-SNAPSHOT.jar binaries/services/
else
    echo "Skipping deployment"
fi