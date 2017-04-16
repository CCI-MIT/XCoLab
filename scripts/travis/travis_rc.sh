#!/bin/bash

# Set additional environment variables for travis

if [ $TRAVIS_BRANCH == 'homolog' ] || [ $TRAVIS_BRANCH == 'master' ] || [[ $TRAVIS_BRANCH =~ ^deploy-.*$ ]]; then
    export BUILD_TYPE=deploy
else
    export BUILD_TYPE=test
fi
