#!/bin/bash
for plik in `grep "collaboratorium-theme" . -r  --exclude="*svn*" -l --include="*css"`
do
	echo fixing file $plik
    cat $plik | sed s/collaboratorium-theme/climatecolab-theme/g > ../../../tmp_src
    cp ../../../tmp_src $plik
done


