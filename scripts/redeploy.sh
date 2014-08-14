cd services/plansProposalsFacade/plansProposalsFacade-portlet-service
mvn clean compile package install

cd ../plansProposalsFacade-portlet
mvn clean compile package liferay:deploy -DskipTests

cd ../../../portlets
for D in *; do     if [ -d "${D}" ]; then         cd $D;                 pwd;                 mvn compile package liferay:deploy;                 cd ..;     fi; done