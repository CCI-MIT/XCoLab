package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SpamReportService}.
 *
 * @author Brian Wing Shun Chan
 * @see SpamReportService
 * @generated
 */
public class SpamReportServiceWrapper implements SpamReportService,
    ServiceWrapper<SpamReportService> {
    private SpamReportService _spamReportService;

    public SpamReportServiceWrapper(SpamReportService spamReportService) {
        _spamReportService = spamReportService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _spamReportService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _spamReportService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _spamReportService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public SpamReportService getWrappedSpamReportService() {
        return _spamReportService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedSpamReportService(SpamReportService spamReportService) {
        _spamReportService = spamReportService;
    }

    @Override
    public SpamReportService getWrappedService() {
        return _spamReportService;
    }

    @Override
    public void setWrappedService(SpamReportService spamReportService) {
        _spamReportService = spamReportService;
    }
}
