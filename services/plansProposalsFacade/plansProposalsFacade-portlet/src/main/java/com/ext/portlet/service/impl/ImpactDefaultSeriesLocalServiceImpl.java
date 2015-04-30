package com.ext.portlet.service.impl;

import com.ext.portlet.NoSuchImpactDefaultSeriesException;
import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.ImpactDefaultSeries;
import com.ext.portlet.service.base.ImpactDefaultSeriesLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * The implementation of the impact default series local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.ImpactDefaultSeriesLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.ImpactDefaultSeriesLocalServiceBaseImpl
 * @see com.ext.portlet.service.ImpactDefaultSeriesLocalServiceUtil
 */
public class ImpactDefaultSeriesLocalServiceImpl
    extends ImpactDefaultSeriesLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.ImpactDefaultSeriesLocalServiceUtil} to access the impact default series local service.
     */

    public List<ImpactDefaultSeries> getAllImpactDefaultSeriesWithFocusArea(FocusArea focusArea) throws SystemException {
        return impactDefaultSeriesPersistence.findByFocusAreaId(focusArea.getId());
    }

    public ImpactDefaultSeries getImpactDefaultSeriesWithFocusAreaAndName(FocusArea focusArea, String name) throws SystemException, NoSuchImpactDefaultSeriesException {
        return impactDefaultSeriesPersistence.findByFocusAreaIdAndName(focusArea.getId(), name);
    }
}
