#!/bin/sh
cd services/plansProposalsFacade/plansProposalsFacade-portlet-service
mvn clean package install
cd ../plansProposalsFacade-portlet
mvn clean compile package liferay:deploy -DskipTests=true
