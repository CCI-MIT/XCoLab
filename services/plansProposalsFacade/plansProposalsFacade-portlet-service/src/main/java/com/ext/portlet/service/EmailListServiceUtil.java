package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the email list remote service. This utility wraps {@link com.ext.portlet.service.impl.EmailListServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EmailListService
 * @see com.ext.portlet.service.base.EmailListServiceBaseImpl
 * @see com.ext.portlet.service.impl.EmailListServiceImpl
 * @generated
 */
public class EmailListServiceUtil {
    private static EmailListService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.EmailListServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static java.lang.String helloWorld(java.lang.String listName,
        java.lang.String email) {
        return getService().helloWorld(listName, email);
    }

    public static void clearService() {
        _service = null;
    }

    public static EmailListService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    EmailListService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    EmailListService.class.getName(), portletClassLoader);

            _service = new EmailListServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(EmailListServiceUtil.class,
                "_service");
            MethodCache.remove(EmailListService.class);
        }

        return _service;
    }

    public void setService(EmailListService service) {
        MethodCache.remove(EmailListService.class);

        _service = service;

        ReferenceRegistry.registerReference(EmailListServiceUtil.class,
            "_service");
        MethodCache.remove(EmailListService.class);
    }
}
