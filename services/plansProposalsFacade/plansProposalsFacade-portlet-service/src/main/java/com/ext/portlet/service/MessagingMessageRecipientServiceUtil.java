package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the messaging message recipient remote service. This utility wraps {@link com.ext.portlet.service.impl.MessagingMessageRecipientServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingMessageRecipientService
 * @see com.ext.portlet.service.base.MessagingMessageRecipientServiceBaseImpl
 * @see com.ext.portlet.service.impl.MessagingMessageRecipientServiceImpl
 * @generated
 */
public class MessagingMessageRecipientServiceUtil {
    private static MessagingMessageRecipientService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.MessagingMessageRecipientServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static MessagingMessageRecipientService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    MessagingMessageRecipientService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    MessagingMessageRecipientService.class.getName(),
                    portletClassLoader);

            _service = new MessagingMessageRecipientServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(MessagingMessageRecipientServiceUtil.class,
                "_service");
            MethodCache.remove(MessagingMessageRecipientService.class);
        }

        return _service;
    }

    public void setService(MessagingMessageRecipientService service) {
        MethodCache.remove(MessagingMessageRecipientService.class);

        _service = service;

        ReferenceRegistry.registerReference(MessagingMessageRecipientServiceUtil.class,
            "_service");
        MethodCache.remove(MessagingMessageRecipientService.class);
    }
}
