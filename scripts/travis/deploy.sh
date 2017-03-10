#!/bin/bash

set -e

cd $TRAVIS_BUILD_DIR/

if [ $TRAVIS_BRANCH == 'homolog' ] || [ $TRAVIS_BRANCH == 'master' ]; then
   mvn clean package -B
else
   mvn clean test -B
fi

cd $TRAVIS_BUILD_DIR/
