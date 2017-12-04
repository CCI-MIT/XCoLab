#!/bin/bash

if [ ${BUILD_TYPE} == 'deploy' ]; then

    eval "$(ssh-agent -s)"
    ssh-add deploy_rsa

    # Temporarily copy files to second server
    DEPLOY_FOLDER2=${TRAVIS_BRANCH}
    DEPLOY_SERVER2=travis@cognosis.mit.edu
    echo "Copying binaries to ${DEPLOY_SERVER2}:${DEPLOY_FOLDER2} ..."
    rsync -r --delete-after --quiet binaries ${DEPLOY_SERVER2}:${DEPLOY_FOLDER2}

    DEPLOY_FOLDER=xcolab/${TRAVIS_BRANCH}
    DEPLOY_SERVER=binaries@cognosis2.mit.edu
    echo "Copying binaries to ${DEPLOY_SERVER}:${DEPLOY_FOLDER} ..."
    rsync -r --delete-after --quiet binaries ${DEPLOY_SERVER}:${DEPLOY_FOLDER}

    # Workaround for hanging builds
    # taken from https://github.com/travis-ci/travis-ci/issues/8082#issuecomment-315147561
    ssh-agent -k
else
    echo "Skipping deploy step on branch ${TRAVIS_BRANCH}"
fi
