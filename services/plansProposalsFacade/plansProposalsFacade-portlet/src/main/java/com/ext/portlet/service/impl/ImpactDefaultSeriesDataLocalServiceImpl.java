package com.ext.portlet.service.impl;

import com.ext.portlet.NoSuchImpactDefaultSeriesDataException;
import com.ext.portlet.model.ImpactDefaultSeriesData;
import com.ext.portlet.service.base.ImpactDefaultSeriesDataLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * The implementation of the impact default series data local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.ImpactDefaultSeriesDataLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.ImpactDefaultSeriesDataLocalServiceBaseImpl
 * @see com.ext.portlet.service.ImpactDefaultSeriesDataLocalServiceUtil
 */
public class ImpactDefaultSeriesDataLocalServiceImpl
    extends ImpactDefaultSeriesDataLocalServiceBaseImpl {

    public List<ImpactDefaultSeriesData> getDefaultSeriesDataBySeriesId(long seriesId) throws SystemException {
        return impactDefaultSeriesDataPersistence.findBySeriesId(seriesId);
    }

    public ImpactDefaultSeriesData getDefaultSeriesDataBySeriesIdAndYear(long seriesId, int year) throws SystemException, NoSuchImpactDefaultSeriesDataException {
        return impactDefaultSeriesDataPersistence.findBySeriesIdAndYear(seriesId, year);
    }
}
