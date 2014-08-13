package com.ext.portlet.service.impl;

import com.ext.portlet.model.TrackedVisit;
import com.ext.portlet.service.base.TrackedVisitLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.Date;

/**
 * The implementation of the tracked visit local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.TrackedVisitLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Manuel Thurner
 * @see com.ext.portlet.service.base.TrackedVisitLocalServiceBaseImpl
 * @see com.ext.portlet.service.TrackedVisitLocalServiceUtil
 */
public class TrackedVisitLocalServiceImpl
        extends TrackedVisitLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.TrackedVisitLocalServiceUtil} to access the tracked visit local service.
     */

    public TrackedVisit addTrackedVisit(
            String uuid,
            String url,
            String ip,
            String browser,
            String referer,
            String headers,
            String city,
            String country

    ) throws SystemException {

        long trackedVisitId = counterLocalService.increment(TrackedVisit.class.getName());

        TrackedVisit trackedVisit = trackedVisitPersistence.create(trackedVisitId);

        trackedVisit.setCreateDate(new Date());
        trackedVisit.setBrowser(browser);
        trackedVisit.setReferer(referer);
        trackedVisit.setHeaders(headers);
        trackedVisit.setCity(city);
        trackedVisit.setCountry(country);
        trackedVisit.setUrl(url);
        trackedVisit.setUuid(uuid);
        trackedVisit.setIp(ip);

        super.addTrackedVisit(trackedVisit);

        return trackedVisit;
    }
}
