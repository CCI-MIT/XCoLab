package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the messaging redirect link remote service. This utility wraps {@link com.ext.portlet.service.impl.MessagingRedirectLinkServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingRedirectLinkService
 * @see com.ext.portlet.service.base.MessagingRedirectLinkServiceBaseImpl
 * @see com.ext.portlet.service.impl.MessagingRedirectLinkServiceImpl
 * @generated
 */
public class MessagingRedirectLinkServiceUtil {
    private static MessagingRedirectLinkService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.MessagingRedirectLinkServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static MessagingRedirectLinkService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    MessagingRedirectLinkService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    MessagingRedirectLinkService.class.getName(),
                    portletClassLoader);

            _service = new MessagingRedirectLinkServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(MessagingRedirectLinkServiceUtil.class,
                "_service");
            MethodCache.remove(MessagingRedirectLinkService.class);
        }

        return _service;
    }

    public void setService(MessagingRedirectLinkService service) {
        MethodCache.remove(MessagingRedirectLinkService.class);

        _service = service;

        ReferenceRegistry.registerReference(MessagingRedirectLinkServiceUtil.class,
            "_service");
        MethodCache.remove(MessagingRedirectLinkService.class);
    }
}
