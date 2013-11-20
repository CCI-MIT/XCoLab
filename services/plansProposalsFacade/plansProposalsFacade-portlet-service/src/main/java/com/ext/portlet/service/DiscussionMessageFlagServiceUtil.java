package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service utility for DiscussionMessageFlag. This utility wraps
 * {@link com.ext.portlet.service.impl.DiscussionMessageFlagServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionMessageFlagService
 * @see com.ext.portlet.service.base.DiscussionMessageFlagServiceBaseImpl
 * @see com.ext.portlet.service.impl.DiscussionMessageFlagServiceImpl
 * @generated
 */
public class DiscussionMessageFlagServiceUtil {
    private static DiscussionMessageFlagService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.DiscussionMessageFlagServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public static java.lang.String getBeanIdentifier() {
        return getService().getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public static void setBeanIdentifier(java.lang.String beanIdentifier) {
        getService().setBeanIdentifier(beanIdentifier);
    }

    public static java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return getService().invokeMethod(name, parameterTypes, arguments);
    }

    public static void clearService() {
        _service = null;
    }

    public static DiscussionMessageFlagService getService() {
        if (_service == null) {
            InvokableService invokableService = (InvokableService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    DiscussionMessageFlagService.class.getName());

            if (invokableService instanceof DiscussionMessageFlagService) {
                _service = (DiscussionMessageFlagService) invokableService;
            } else {
                _service = new DiscussionMessageFlagServiceClp(invokableService);
            }

            ReferenceRegistry.registerReference(DiscussionMessageFlagServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(DiscussionMessageFlagService service) {
    }
}
