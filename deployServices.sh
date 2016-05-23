#!/bin/sh

cd microservices/util/service-utils
mvn clean compile package install clean
cd ../../..

cd microservices/services
for D in *-service; do
    if [ -d "${D}" ]; then
        cd $D
        pwd
        mvn clean compile package spring-boot:repackage
        cd ..
    fi
done
cd ../..