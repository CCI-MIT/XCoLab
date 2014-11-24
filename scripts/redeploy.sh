
cd ../portlets
for D in *; do
	if [ -d "${D}" ]; then
		cd $D
        mvn compile package liferay:deploy
        cd ..
	fi;
done
