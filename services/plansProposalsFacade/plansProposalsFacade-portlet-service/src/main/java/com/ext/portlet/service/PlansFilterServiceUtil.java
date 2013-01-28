package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plans filter remote service. This utility wraps {@link com.ext.portlet.service.impl.PlansFilterServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlansFilterService
 * @see com.ext.portlet.service.base.PlansFilterServiceBaseImpl
 * @see com.ext.portlet.service.impl.PlansFilterServiceImpl
 * @generated
 */
public class PlansFilterServiceUtil {
    private static PlansFilterService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.PlansFilterServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static PlansFilterService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlansFilterService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlansFilterService.class.getName(), portletClassLoader);

            _service = new PlansFilterServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlansFilterServiceUtil.class,
                "_service");
            MethodCache.remove(PlansFilterService.class);
        }

        return _service;
    }

    public void setService(PlansFilterService service) {
        MethodCache.remove(PlansFilterService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlansFilterServiceUtil.class,
            "_service");
        MethodCache.remove(PlansFilterService.class);
    }
}
