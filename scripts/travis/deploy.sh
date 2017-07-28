#!/bin/bash

if [ ${BUILD_TYPE} == 'deploy' ]; then

   DEPLOY_FOLDER=xcolab/${TRAVIS_BRANCH}
   DEPLOY_SERVER=dumbo@cognosis2.mit.edu

   echo "Copying binaries to ${DEPLOY_SERVER}:${DEPLOY_FOLDER} ..."

   eval "$(ssh-agent -s)"
   ssh-add deploy_rsa

   rsync -r --delete-after --quiet binaries ${DEPLOY_SERVER}:${DEPLOY_FOLDER}

   # Workaround for hanging builds
   # taken from https://github.com/travis-ci/travis-ci/issues/8082#issuecomment-315147561
   ssh-agent -k
else
    echo "Skipping deploy step on branch ${TRAVIS_BRANCH}"
fi
