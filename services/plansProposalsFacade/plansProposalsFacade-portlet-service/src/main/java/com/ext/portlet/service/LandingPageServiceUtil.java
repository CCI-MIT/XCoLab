package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the landing page remote service. This utility wraps {@link com.ext.portlet.service.impl.LandingPageServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LandingPageService
 * @see com.ext.portlet.service.base.LandingPageServiceBaseImpl
 * @see com.ext.portlet.service.impl.LandingPageServiceImpl
 * @generated
 */
public class LandingPageServiceUtil {
    private static LandingPageService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.LandingPageServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static LandingPageService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    LandingPageService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    LandingPageService.class.getName(), portletClassLoader);

            _service = new LandingPageServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(LandingPageServiceUtil.class,
                "_service");
            MethodCache.remove(LandingPageService.class);
        }

        return _service;
    }

    public void setService(LandingPageService service) {
        MethodCache.remove(LandingPageService.class);

        _service = service;

        ReferenceRegistry.registerReference(LandingPageServiceUtil.class,
            "_service");
        MethodCache.remove(LandingPageService.class);
    }
}
