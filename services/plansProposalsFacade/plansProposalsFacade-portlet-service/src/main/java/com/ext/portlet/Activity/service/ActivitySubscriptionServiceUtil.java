package com.ext.portlet.Activity.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the activity subscription remote service. This utility wraps {@link com.ext.portlet.Activity.service.impl.ActivitySubscriptionServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ActivitySubscriptionService
 * @see com.ext.portlet.Activity.service.base.ActivitySubscriptionServiceBaseImpl
 * @see com.ext.portlet.Activity.service.impl.ActivitySubscriptionServiceImpl
 * @generated
 */
public class ActivitySubscriptionServiceUtil {
    private static ActivitySubscriptionService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.Activity.service.impl.ActivitySubscriptionServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static ActivitySubscriptionService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ActivitySubscriptionService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ActivitySubscriptionService.class.getName(),
                    portletClassLoader);

            _service = new ActivitySubscriptionServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ActivitySubscriptionServiceUtil.class,
                "_service");
            MethodCache.remove(ActivitySubscriptionService.class);
        }

        return _service;
    }

    public void setService(ActivitySubscriptionService service) {
        MethodCache.remove(ActivitySubscriptionService.class);

        _service = service;

        ReferenceRegistry.registerReference(ActivitySubscriptionServiceUtil.class,
            "_service");
        MethodCache.remove(ActivitySubscriptionService.class);
    }
}
