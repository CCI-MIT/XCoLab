package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plans filter position remote service. This utility wraps {@link com.ext.portlet.plans.service.impl.PlansFilterPositionServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlansFilterPositionService
 * @see com.ext.portlet.plans.service.base.PlansFilterPositionServiceBaseImpl
 * @see com.ext.portlet.plans.service.impl.PlansFilterPositionServiceImpl
 * @generated
 */
public class PlansFilterPositionServiceUtil {
    private static PlansFilterPositionService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.plans.service.impl.PlansFilterPositionServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static PlansFilterPositionService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlansFilterPositionService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlansFilterPositionService.class.getName(),
                    portletClassLoader);

            _service = new PlansFilterPositionServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlansFilterPositionServiceUtil.class,
                "_service");
            MethodCache.remove(PlansFilterPositionService.class);
        }

        return _service;
    }

    public void setService(PlansFilterPositionService service) {
        MethodCache.remove(PlansFilterPositionService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlansFilterPositionServiceUtil.class,
            "_service");
        MethodCache.remove(PlansFilterPositionService.class);
    }
}
