package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan remote service. This utility wraps {@link com.ext.portlet.plans.service.impl.PlanServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanService
 * @see com.ext.portlet.plans.service.base.PlanServiceBaseImpl
 * @see com.ext.portlet.plans.service.impl.PlanServiceImpl
 * @generated
 */
public class PlanServiceUtil {
    private static PlanService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.plans.service.impl.PlanServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static PlanService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlanService.class.getName(), portletClassLoader);

            _service = new PlanServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanServiceUtil.class,
                "_service");
            MethodCache.remove(PlanService.class);
        }

        return _service;
    }

    public void setService(PlanService service) {
        MethodCache.remove(PlanService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlanServiceUtil.class, "_service");
        MethodCache.remove(PlanService.class);
    }
}
