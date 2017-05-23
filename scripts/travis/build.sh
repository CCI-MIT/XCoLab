#!/bin/bash

set -e

if [ ${BUILD_TYPE} == 'deploy' ]; then
   mvn clean package -B -T 4
else
   if [ ${RUN_INTEGRATION_TESTS} == 'true' ]; then
       mvn clean test -B -T 3 -Pintegration-tests
   else
       mvn clean test -B -T 4
   fi
fi
