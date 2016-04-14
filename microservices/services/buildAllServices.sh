#!/bin/sh
for D in *; do
    if [ -d "${D}" ]; then
        cd $D
                pwd
                mvn compile package spring-boot:repackage
                cd ..
    fi
done