#!/bin/bash

# stop script if any build fails
set -e

for D in *; do
    if [ -d "${D}" ]; then
        cd $D
                pwd
                mvn clean compile package liferay:deploy clean
                cd ..
    fi
done 
