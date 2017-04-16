#!/bin/bash

if [ ${BUILD_TYPE} == 'deploy' ]; then
   DEPLOY_FOLDER=${TRAVIS_BRANCH}
   echo "Copying binaries to folder ${DEPLOY_FOLDER} on cognosis..."
   rsync -r --delete-after --quiet binaries travis@cognosis.mit.edu:${DEPLOY_FOLDER}
else
    echo "Skipping deploy step on branch ${TRAVIS_BRANCH}"
fi
