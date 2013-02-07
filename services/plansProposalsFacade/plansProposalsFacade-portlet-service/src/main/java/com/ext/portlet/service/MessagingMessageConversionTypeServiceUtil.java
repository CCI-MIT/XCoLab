package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the messaging message conversion type remote service. This utility wraps {@link com.ext.portlet.service.impl.MessagingMessageConversionTypeServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingMessageConversionTypeService
 * @see com.ext.portlet.service.base.MessagingMessageConversionTypeServiceBaseImpl
 * @see com.ext.portlet.service.impl.MessagingMessageConversionTypeServiceImpl
 * @generated
 */
public class MessagingMessageConversionTypeServiceUtil {
    private static MessagingMessageConversionTypeService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.MessagingMessageConversionTypeServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static MessagingMessageConversionTypeService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    MessagingMessageConversionTypeService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    MessagingMessageConversionTypeService.class.getName(),
                    portletClassLoader);

            _service = new MessagingMessageConversionTypeServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(MessagingMessageConversionTypeServiceUtil.class,
                "_service");
            MethodCache.remove(MessagingMessageConversionTypeService.class);
        }

        return _service;
    }

    public void setService(MessagingMessageConversionTypeService service) {
        MethodCache.remove(MessagingMessageConversionTypeService.class);

        _service = service;

        ReferenceRegistry.registerReference(MessagingMessageConversionTypeServiceUtil.class,
            "_service");
        MethodCache.remove(MessagingMessageConversionTypeService.class);
    }
}
