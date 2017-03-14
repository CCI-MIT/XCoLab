#!/bin/bash

set -e

if [ $BUILD_TYPE == 'deploy' ]; then
   mvn package -B -T 4
else
   mvn test -B -T 4
fi