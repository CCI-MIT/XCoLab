#!/bin/sh

cd ../other/site-monitor
java -cp "target/dependency/*:target/classes" org.xcolab.utils.sitemonitor.SiteMonitor > siteMonitor.log
