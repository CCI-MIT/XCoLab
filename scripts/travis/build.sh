#!/bin/bash

set -e

if [ ${BUILD_TYPE} == 'deploy' ]; then
   mvn clean package -B -T 3
else
   if [ ${RUN_SLOW_TESTS} == 'true' ]; then
       mvn clean test -B -T 3 -Pslow-tests,skip-sass
   else
       mvn clean test -B -T 3
   fi
fi
