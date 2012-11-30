package com.ext.portlet.messaging.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the messaging user preferences remote service. This utility wraps {@link com.ext.portlet.messaging.service.impl.MessagingUserPreferencesServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingUserPreferencesService
 * @see com.ext.portlet.messaging.service.base.MessagingUserPreferencesServiceBaseImpl
 * @see com.ext.portlet.messaging.service.impl.MessagingUserPreferencesServiceImpl
 * @generated
 */
public class MessagingUserPreferencesServiceUtil {
    private static MessagingUserPreferencesService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.messaging.service.impl.MessagingUserPreferencesServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static MessagingUserPreferencesService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    MessagingUserPreferencesService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    MessagingUserPreferencesService.class.getName(),
                    portletClassLoader);

            _service = new MessagingUserPreferencesServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(MessagingUserPreferencesServiceUtil.class,
                "_service");
            MethodCache.remove(MessagingUserPreferencesService.class);
        }

        return _service;
    }

    public void setService(MessagingUserPreferencesService service) {
        MethodCache.remove(MessagingUserPreferencesService.class);

        _service = service;

        ReferenceRegistry.registerReference(MessagingUserPreferencesServiceUtil.class,
            "_service");
        MethodCache.remove(MessagingUserPreferencesService.class);
    }
}
