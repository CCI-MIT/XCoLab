for D in *; do
    if [ -d "${D}" ]; then
        cd $D
                pwd
                mvn clean compile package liferay:deploy
                cd ..
    fi
done 
