package com.ext.portlet.service.impl;

import java.util.Date;

import com.ext.portlet.model.LandingPage;
import com.ext.portlet.service.base.LandingPageLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the landing page local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.LandingPageLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.LandingPageLocalServiceBaseImpl
 * @see com.ext.portlet.service.LandingPageLocalServiceUtil
 */
public class LandingPageLocalServiceImpl extends LandingPageLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.LandingPageLocalServiceUtil} to access the landing page local service.
     */
    @Override
    public LandingPage createNewLandingPage(String baseUrl, String targetUrl) throws SystemException {
        Long id = CounterLocalServiceUtil.increment(LandingPage.class.getName());
        
        LandingPage lp = createLandingPage(id);
        lp.setUpdated(new Date());
        lp.setBaseUrl(baseUrl);
        lp.setTargetUrl(targetUrl);
        
        return lp;
        
    }
    
    @Override
    public void store(LandingPage landingPage) throws SystemException {
        if (landingPage.isNew()) {
            addLandingPage(landingPage);
        }
        else {
            updateLandingPage(landingPage);
        }
    }
}
