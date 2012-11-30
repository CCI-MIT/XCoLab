#!/bin/bash
for plik in `grep com.liferay.portal.PortalException . -r  --exclude="*svn*" -l --include="*java"`
do
    cat $plik | sed s/com.liferay.portal.PortalException/com.liferay.portal.kernel.exception.PortalException/g > ../../../tmp_src
    cp ../../../tmp_src $plik
done

for plik in `grep com.liferay.portal.SystemException . -r  --exclude="*svn*" -l --include="*java"`
do
    cat $plik | sed s/com.liferay.portal.SystemException/com.liferay.portal.kernel.exception.SystemException/g > ../../../tmp_src
    cp ../../../tmp_src $plik
done

