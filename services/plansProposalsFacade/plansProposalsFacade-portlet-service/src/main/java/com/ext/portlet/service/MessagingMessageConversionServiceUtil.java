package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the messaging message conversion remote service. This utility wraps {@link com.ext.portlet.service.impl.MessagingMessageConversionServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingMessageConversionService
 * @see com.ext.portlet.service.base.MessagingMessageConversionServiceBaseImpl
 * @see com.ext.portlet.service.impl.MessagingMessageConversionServiceImpl
 * @generated
 */
public class MessagingMessageConversionServiceUtil {
    private static MessagingMessageConversionService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.MessagingMessageConversionServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static MessagingMessageConversionService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    MessagingMessageConversionService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    MessagingMessageConversionService.class.getName(),
                    portletClassLoader);

            _service = new MessagingMessageConversionServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(MessagingMessageConversionServiceUtil.class,
                "_service");
            MethodCache.remove(MessagingMessageConversionService.class);
        }

        return _service;
    }

    public void setService(MessagingMessageConversionService service) {
        MethodCache.remove(MessagingMessageConversionService.class);

        _service = service;

        ReferenceRegistry.registerReference(MessagingMessageConversionServiceUtil.class,
            "_service");
        MethodCache.remove(MessagingMessageConversionService.class);
    }
}
