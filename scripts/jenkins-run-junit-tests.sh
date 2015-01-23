#!/bin/bash
set -e

#temp dir, where test dependency libs will be copied to. ATTENTION, this directory's contents will be deleted!
test_deps_lib="/home/jenkins/tests/intellij-junit-tmp-dir"
#location of the facade
plansProposalsFacade_dir="/home/jenkins/.jenkins/jobs/xcolab/workspace/services/plansProposalsFacade"
#java binary
java_bin="/opt/jdk/bin/java"
#this dir has to contain idea_rt.jar and junit-rt.jar
intellij_junit_lib_dir="/home/jenkins/.jenkins/jobs/xcolab/workspace/other/tools/intellij-junit-libraries"

email_recipient="thurner@mit.edu"

#copy dependencies
cd $test_deps_lib
set +e
rm *
set -e
cd "$plansProposalsFacade_dir/plansProposalsFacade-portlet"
mvn dependency:copy-dependencies -DoutputDirectory="$test_deps_lib"

#define the tests to be run
#declare -a arr=("com.ext.portlet.service.impl.PointsTest,randomHypotheticalTest" "com.ext.portlet.service.impl.PointsTest,randomMaterializedTest" "com.ext.portlet.service.impl.PhaseTransitionTest,randomTest")
declare -a arr=("com.ext.portlet.service.impl.PointsTest,randomMaterializedTest" "com.ext.portlet.service.impl.PhaseTransitionTest,randomTest")

#debug
#declare -a arr=("com.ext.portlet.service.impl.PhaseTransitionTest,debugTestAlwaysFails")

set +e

## now loop through the above array
for testname in "${arr[@]}"
do
   #use the IntelliJ JUnit runner because with this, we can set single tests to be executed (rather than the whole class with the vanilla junit jar)
	$java_bin -ea -da:org.apache.lucene.analysis.Analyzer -Xms1024m -Xmx2048m -XX:PermSize=512m -XX:MaxPermSize=1024m -Didea.launcher.port=7532 -Dfile.encoding=UTF-8 -classpath "$intellij_junit_lib_dir/*:$plansProposalsFacade_dir/plansProposalsFacade-portlet/target/test-classes:$plansProposalsFacade_dir/plansProposalsFacade-portlet/target/classes:$plansProposalsFacade_dir/plansProposalsFacade-portlet-service/target/classes:$test_deps_lib/*" com.intellij.rt.execution.application.AppMain com.intellij.rt.execution.junit.JUnitStarter -ideVersion5  "$testname" | grep 'Error' >> $test_deps_lib/error-report.log
done

if [[ -s $test_deps_lib/error-report.log ]] ; then
	echo "Tests failed\n"
	/bin/mail -s "Jenkins: Unit tests failed" $email_recipient < $test_deps_lib/error-report.log
else
	echo "Tests successful\n"
	/bin/mail -s "Jenkins: Unit tests successful" $email_recipient <<< "All tests were successful."
fi
