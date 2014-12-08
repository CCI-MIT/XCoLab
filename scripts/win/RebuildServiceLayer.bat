@echo off
if defined XCOLAB_HOME  ( 

echo XCOLAB_HOME found at %XCOLAB_HOME%

cd %XCOLAB_HOME%/services/plansProposalsFacade/plansProposalsFacade-portlet
mvn liferay:build-service

cd %XCOLAB_HOME%/services/plansProposalsFacade
mvn install -N

cd %XCOLAB_HOME%/services/plansProposalsFacade/plansProposalsFacade-portlet-service
mvn compile package install

cd %XCOLAB_HOME%/services/plansProposalsFacade/plansProposalsFacade-portlet
mvn compile package liferay:deploy -DskipTests=true

echo rebuilding the service layer done.

) else (

echo XCOLAB_HOME can't be found, please create an environemt variable point to your XColab source folder
pause > null

)
