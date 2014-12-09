cls

echo 7. Deploy all portlets
cd %XCOLAB_SRC%\portlets
cd portlets
for /D %%I in (*) DO (
	if exist %%I\nul ( 
	    cd %%I
	    echo %cd%
	    call mvn compile package liferay:deploy || (
			echo "Error: Stopping deploying process, check error message above for detailed information ..."
			echo "Press Key to exit ..."
			pause > nul
			exit
			)
	    cd ..
	)
)


echo Deployment complete!
pause > nul
