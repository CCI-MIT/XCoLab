#!/bin/bash
mvn install -N
cd taglibs
mvn install -N
cd ..
cd themes
mvn install -N
cd ..
cd services
mvn install -N
cd ..
cd portlets
mvn install -N
cd ..
cd hooks
mvn install -N
cd ..
cd layouts
mvn install -N
cd ..

cd services/plansProposalsFacade
mvn install -N
cd ../..

cd services/plansProposalsFacade/plansProposalsFacade-portlet-service
mvn clean compile package install clean
cd ../../..

cd taglibs/colabIceFacelets
mvn clean compile package install clean
cd ../..

cd taglibs/colabJspTags
mvn clean compile package install clean
cd ../..

cd services/plansProposalsFacade/plansProposalsFacade-portlet
mvn clean compile package liferay:deploy -DskipTests=true clean
cd ../../..

cd themes/climatecolab-theme
  mvn clean compile package liferay:deploy clean
cd ../..

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
