#!/bin/bash
for plik in `grep "\&amp;nbsp;" . -r  --exclude="*svn*" -l --include="*xhtml"`
do
	echo fixing file $plik
    cat $plik | sed s/amp\;nbsp\;/#160\;/g > /tmp/tmp_src
    cp /tmp/tmp_src $plik
done


