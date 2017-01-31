#!/usr/bin/env bash

# stop script if any build fails
set -e

cd microservices/util/xcolab-utils
mvn clean compile package install clean

cd ../entity-utils/
mvn clean compile package install clean
cd ../../..

cd microservices/clients
for D in *-client; do
    if [ -d "${D}" ]; then
        cd $D
		pwd
		mvn clean compile package install clean
		cd ..
    fi
done
cd ../..
