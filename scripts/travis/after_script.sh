#!/bin/bash

echo "Copying view binary"
cp $TRAVIS_BUILD_DIR/view/target/xcolab-view-1.0-SNAPSHOT.jar $TRAVIS_BUILD_DIR/deploy/view
echo "Copying services binary"
mv $TRAVIS_BUILD_DIR/deploy/apache-tomcat-8.0.33/webapps/*.war $TRAVIS_BUILD_DIR/deploy/webapps/
echo "Deleting apache-tomcat-8 temp dir"
rm -r $TRAVIS_BUILD_DIR/deploy/apache-tomcat-8.0.33/