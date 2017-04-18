#!/bin/bash

set -e

if [ $BUILD_TYPE == 'deploy' ]; then
   mvn clean package -B -T 4
else
   mvn clean test -B -T 4
fi