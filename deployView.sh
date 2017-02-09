#!/usr/bin/env bash

# stop script if any build fails
set -e

cd view 
mvn clean compile package spring-boot:repackage
cd ../ 
