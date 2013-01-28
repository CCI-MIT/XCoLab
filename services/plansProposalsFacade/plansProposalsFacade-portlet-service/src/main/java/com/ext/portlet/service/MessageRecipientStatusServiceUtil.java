package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the message recipient status remote service. This utility wraps {@link com.ext.portlet.service.impl.MessageRecipientStatusServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessageRecipientStatusService
 * @see com.ext.portlet.service.base.MessageRecipientStatusServiceBaseImpl
 * @see com.ext.portlet.service.impl.MessageRecipientStatusServiceImpl
 * @generated
 */
public class MessageRecipientStatusServiceUtil {
    private static MessageRecipientStatusService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.MessageRecipientStatusServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static MessageRecipientStatusService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    MessageRecipientStatusService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    MessageRecipientStatusService.class.getName(),
                    portletClassLoader);

            _service = new MessageRecipientStatusServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(MessageRecipientStatusServiceUtil.class,
                "_service");
            MethodCache.remove(MessageRecipientStatusService.class);
        }

        return _service;
    }

    public void setService(MessageRecipientStatusService service) {
        MethodCache.remove(MessageRecipientStatusService.class);

        _service = service;

        ReferenceRegistry.registerReference(MessageRecipientStatusServiceUtil.class,
            "_service");
        MethodCache.remove(MessageRecipientStatusService.class);
    }
}
