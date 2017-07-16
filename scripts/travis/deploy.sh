#!/bin/bash

if [ ${BUILD_TYPE} == 'deploy' ]; then
   DEPLOY_FOLDER=${TRAVIS_BRANCH}
   echo "Copying binaries to folder ${DEPLOY_FOLDER} on cognosis..."

   eval "$(ssh-agent -s)"
   ssh-add deploy_rsa

   rsync -r --delete-after --quiet binaries travis@cognosis.mit.edu:${DEPLOY_FOLDER}

   # Workaround for hanging builds
   # taken from https://github.com/travis-ci/travis-ci/issues/8082#issuecomment-315147561
   ssh-agent -k
else
    echo "Skipping deploy step on branch ${TRAVIS_BRANCH}"
fi
