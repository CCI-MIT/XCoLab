#!/usr/bin/env bash
cd microservices/util/xcolab-utils
mvn clean compile package install clean
cd ../../..

cd microservices/clients
for D in *; do
    if [ -d "${D}" ]; then
        cd $D
		pwd
		mvn clean compile package install clean
		cd ..
    fi
done
cd ../..