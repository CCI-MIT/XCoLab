package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan position remote service. This utility wraps {@link com.ext.portlet.plans.service.impl.PlanPositionServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanPositionService
 * @see com.ext.portlet.plans.service.base.PlanPositionServiceBaseImpl
 * @see com.ext.portlet.plans.service.impl.PlanPositionServiceImpl
 * @generated
 */
public class PlanPositionServiceUtil {
    private static PlanPositionService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.plans.service.impl.PlanPositionServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static PlanPositionService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanPositionService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlanPositionService.class.getName(), portletClassLoader);

            _service = new PlanPositionServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanPositionServiceUtil.class,
                "_service");
            MethodCache.remove(PlanPositionService.class);
        }

        return _service;
    }

    public void setService(PlanPositionService service) {
        MethodCache.remove(PlanPositionService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlanPositionServiceUtil.class,
            "_service");
        MethodCache.remove(PlanPositionService.class);
    }
}
