#!/bin/bash

# stop script if any build fails
set -e

cd microservices/util/xcolab-utils
mvn clean compile package install clean
cd ../../..

cd microservices/clients
for D in *-client; do
    if [ -d "${D}" ]; then
        cd $D
		pwd
		mvn clean compile package install clean
		cd ..
    fi
done
cd ../..

cd services/plansProposalsFacade/plansProposalsFacade-portlet-service
mvn clean compile package install clean
cd ../../..

cd taglibs/colabJspTags
mvn clean compile package install clean
cd ../..

cd services/plansProposalsFacade/plansProposalsFacade-portlet
mvn clean compile package liferay:deploy -DskipTests=true clean
cd ../../..

cd layouts/climatecolab-layout
  mvn clean compile package liferay:deploy clean
cd ../..

cd hooks/climatecolab-hooks
  mvn clean compile package liferay:deploy clean
cd ../..

cd portlets
for D in *; do
    if [ -d "${D}" ]; then
        cd $D
		pwd
		mvn clean compile package liferay:deploy clean
		cd ..
    fi
done
