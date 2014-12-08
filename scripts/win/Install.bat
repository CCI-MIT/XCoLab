cls

@echo off
if defined XCOLAB_SRC ( 
	echo XCOLAB_SRC found:  %XCOLAB_SRC%
) else (

echo XCOLAB_SRC can't be found, please create an environemt variable pointing to your XColab source folder
pause > null
exit

)

if defined XCOLAB_BUNDLES_DIR ( 
	echo XCOLAB_BUNDLES_DIR found:  %XCOLAB_BUNDLES_DIR%
) else (

echo XCOLAB_BUNDLES_DIR can't be found, please create an environemt variable pointing to your /liferay-portal-6.2-ce-ga2 source folder
pause > null
exit

)

if defined TOMCAT_VERSION ( 
	if exist %XCOLAB_BUNDLES_DIR%/tomcat-%TOMCAT_VERSION%/bin (
		echo Tomcat found:  %XCOLAB_BUNDLES_DIR%/tomcat-%TOMCAT_VERSION%/bin
	)
	else (
		echo TOMCAT_VERSION set to: %TOMCAT_VERSION% but folder %XCOLAB_BUNDLES_DIR%/tomcat-%TOMCAT_VERSION% does not exist.
		pause > null
		exit
	)
	
) else (

echo TOMCAT_VERSION can't be found, please create an environemt variable indicating the version number e.g. 7.0.42
pause > null
exit

)

echo 2. Now it's needed to put all parent artifacts in local maven repository
cd XCOLAB_SRC
call  mvn install -N || (
	echo "Error: Stopping compile / install process, check error message above for detailed information ..."
	echo "Press Key to exit ..."
	pause > nul
	exit
	)

cd %XCOLAB_SRC%\taglibs
call  mvn install -N || (
	echo "Error: Stopping compile / install process, check error message above for detailed information ..."
	echo "Press Key to exit ..."
	pause > nul
	exit
	)

cd %XCOLAB_SRC%\themes
call  mvn install -N || (
	echo "Error: Stopping compile / install process, check error message above for detailed information ..."
	echo "Press Key to exit ..."
	pause > nul
	exit
	)

cd %XCOLAB_SRC%\services
call  mvn install -N || (
	echo "Error: Stopping compile / install process, check error message above for detailed information ..."
	echo "Press Key to exit ..."
	pause > nul
	exit
	)

cd %XCOLAB_SRC%\portlets
call  mvn install -N || (
	echo "Error: Stopping compile / install process, check error message above for detailed information ..."
	echo "Press Key to exit ..."
	pause > nul
	exit
	)

cd %XCOLAB_SRC%\hooks
call  mvn install -N || (
	echo "Error: Stopping compile / install process, check error message above for detailed information ..."
	echo "Press Key to exit ..."
	pause > nul
	exit
	)

cd %XCOLAB_SRC%\layouts
call  mvn install -N || (
	echo "Error: Stopping compile / install process, check error message above for detailed information ..."
	echo "Press Key to exit ..."
	pause > nul
	exit
	)

echo 3. Install plansProposalsFacade-service
cd %XCOLAB_SRC%\services\plansProposalsFacade
call  mvn install -N || (
	echo "Error: Stopping compile / install process, check error message above for detailed information ..."
	echo "Press Key to exit ..."
	pause > nul
	exit
	)

cd %XCOLAB_SRC%\services\plansProposalsFacade\plansProposalsFacade-portlet-service
call  mvn clean compile package install || (
	echo "Error: Stopping compile / install, check error message above for detailed information ..."
	echo "Press Key to exit ..."
	pause > nul
	exit
	)

echo 4. Install common taglibs/libraries
cd %XCOLAB_SRC%\taglibs\colabIceFacelets
call  mvn clean compile package install || (
	echo "Error: Stopping compile / install process, check error message above for detailed information ..."
	echo "Press Key to exit ..."
	pause > nul
	exit
	)

cd %XCOLAB_SRC%\taglibs\colabJspTags
call  mvn clean compile package install || (
	echo "Error: Stopping compile / install process, check error message above for detailed information ..."
	echo "Press Key to exit ..."
	pause > nul
	exit
	)


echo 5. Install plansProposalFacade-portlet
cd %XCOLAB_SRC%\services\plansProposalsFacade\plansProposalsFacade-portlet
call mvn clean compile package liferay:deploy -DskipTests=true || (
	echo "Error: Stopping deploying process, check error message above for detailed information ..."
	echo "Press Key to exit ..."
	pause > nul
	exit
	)

echo 6. Install theme, layout tmpl and hooks
cd %XCOLAB_SRC%\themes\climatecolab-theme
call mvn clean compile package liferay:deploy || (
	echo "Error: Stopping deploying process, check error message above for detailed information ..."
	echo "Press Key to exit ..."
	pause > nul
	exit
	)

cd %XCOLAB_SRC%\layouts\climatecolab-layout
call mvn clean compile package liferay:deploy || (
	echo "Error: Stopping deploying process, check error message above for detailed information ..."
	echo "Press Key to exit ..."
	pause > nul
	exit
	)

cd %XCOLAB_SRC%\hooks\climatecolab-hooks
call mvn clean compile package liferay:deploy || (
	echo "Error: Stopping deploying process, check error message above for detailed information ..."
	echo "Press Key to exit ..."
	pause > nul
	exit
	)

echo 7. Install all portlets
cd %XCOLAB_SRC%\portlets
cd portlets
for /D %%I in (*) DO (
	if exist %%I\nul ( 
	    cd %%I
	    echo %cd%
	    call mvn clean compile package liferay:deploy || (
			echo "Error: Stopping deploying process, check error message above for detailed information ..."
			echo "Press Key to exit ..."
			pause > nul
			exit
			)
	    cd ..
	)
)

echo Install complete!
pause > nul
