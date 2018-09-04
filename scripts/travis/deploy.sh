#!/bin/bash

# Fail if any steps in the deployment fail
set -e

if [ ${BUILD_TYPE} == 'deploy' ]; then

    eval "$(ssh-agent -s)"
    ssh-add deploy_rsa
    cat scripts/travis/cognosis2.known_host >> $HOME/.ssh/known_hosts

    WORKER_IP="$(dig +short myip.opendns.com @resolver1.opendns.com)"

    DEPLOY_FOLDER=xcolab/${TRAVIS_BRANCH}
    DEPLOY_SERVER=binaries@cognosis2.mit.edu
    echo "Copying binaries from ${WORKER_IP} to ${DEPLOY_SERVER}:${DEPLOY_FOLDER} ..."
    rsync -r --delete-after --quiet binaries ${DEPLOY_SERVER}:${DEPLOY_FOLDER}

    # Workaround for hanging builds
    # taken from https://github.com/travis-ci/travis-ci/issues/8082#issuecomment-315147561
    ssh-agent -k
else
    echo "Skipping deploy step on branch ${TRAVIS_BRANCH}"
fi
