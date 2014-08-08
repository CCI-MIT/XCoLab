#!/bin/sh
parent=`pwd`
for dir in `ls -1`
	do
		cd $dir
		mvn eclipse:eclipse
		cd $parent
	done

