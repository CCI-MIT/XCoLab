package com.ext.portlet.messaging.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the message remote service. This utility wraps {@link com.ext.portlet.messaging.service.impl.MessageServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessageService
 * @see com.ext.portlet.messaging.service.base.MessageServiceBaseImpl
 * @see com.ext.portlet.messaging.service.impl.MessageServiceImpl
 * @generated
 */
public class MessageServiceUtil {
    private static MessageService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.messaging.service.impl.MessageServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static MessageService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    MessageService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    MessageService.class.getName(), portletClassLoader);

            _service = new MessageServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(MessageServiceUtil.class,
                "_service");
            MethodCache.remove(MessageService.class);
        }

        return _service;
    }

    public void setService(MessageService service) {
        MethodCache.remove(MessageService.class);

        _service = service;

        ReferenceRegistry.registerReference(MessageServiceUtil.class, "_service");
        MethodCache.remove(MessageService.class);
    }
}
