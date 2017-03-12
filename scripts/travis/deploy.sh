#!/bin/bash

set -e

cd $TRAVIS_BUILD_DIR/

if [ $TRAVIS_BRANCH == 'homolog' ] || [ $TRAVIS_BRANCH == 'master' ]; then
   mvn package -B -T 1C
else
   mvn test -B -T 1C
fi

cd $TRAVIS_BUILD_DIR/
