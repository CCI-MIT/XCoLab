#!/bin/bash

set -e

if [ ${BUILD_TYPE} == 'deploy' ]; then
   ./mvnw clean package -B -T 3
else
   if [ ${RUN_SLOW_TESTS} == 'true' ]; then
       ./mvnw clean test -B -T 3 -Pslow-tests,skip-frontend
   else
       ./mvnw clean test -B -T 3
   fi
fi
