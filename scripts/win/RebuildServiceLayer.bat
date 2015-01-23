@echo off
if defined XCOLAB_SRC  (

echo XCOLAB_SRC found at %XCOLAB_SRC%

cd %XCOLAB_SRC%/services/plansProposalsFacade/plansProposalsFacade-portlet
mvn liferay:build-service

cd %XCOLAB_SRC%/services/plansProposalsFacade
mvn install -N

cd %XCOLAB_SRC%/services/plansProposalsFacade/plansProposalsFacade-portlet-service
mvn compile package install

cd %XCOLAB_SRC%/services/plansProposalsFacade/plansProposalsFacade-portlet
mvn compile package liferay:deploy -DskipTests=true

echo rebuilding the service layer done.

) else (

echo XCOLAB_SRC can't be found, please create an environemt variable point to your XColab source folder
pause > null

)
