package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service utility for TrackedVisitor2User. This utility wraps
 * {@link com.ext.portlet.service.impl.TrackedVisitor2UserServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see TrackedVisitor2UserService
 * @see com.ext.portlet.service.base.TrackedVisitor2UserServiceBaseImpl
 * @see com.ext.portlet.service.impl.TrackedVisitor2UserServiceImpl
 * @generated
 */
public class TrackedVisitor2UserServiceUtil {
    private static TrackedVisitor2UserService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.TrackedVisitor2UserServiceImpl} and rerun ServiceBuilder to regenerate this class.
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

    public static TrackedVisitor2UserService getService() {
        if (_service == null) {
            InvokableService invokableService = (InvokableService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    TrackedVisitor2UserService.class.getName());

            if (invokableService instanceof TrackedVisitor2UserService) {
                _service = (TrackedVisitor2UserService) invokableService;
            } else {
                _service = new TrackedVisitor2UserServiceClp(invokableService);
            }

            ReferenceRegistry.registerReference(TrackedVisitor2UserServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(TrackedVisitor2UserService service) {
    }
}
