package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SocialActivityService}.
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivityService
 * @generated
 */
public class SocialActivityServiceWrapper implements SocialActivityService,
    ServiceWrapper<SocialActivityService> {
    private SocialActivityService _socialActivityService;

    public SocialActivityServiceWrapper(
        SocialActivityService socialActivityService) {
        _socialActivityService = socialActivityService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _socialActivityService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _socialActivityService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _socialActivityService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public SocialActivityService getWrappedSocialActivityService() {
        return _socialActivityService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedSocialActivityService(
        SocialActivityService socialActivityService) {
        _socialActivityService = socialActivityService;
    }

    @Override
    public SocialActivityService getWrappedService() {
        return _socialActivityService;
    }

    @Override
    public void setWrappedService(SocialActivityService socialActivityService) {
        _socialActivityService = socialActivityService;
    }
}
