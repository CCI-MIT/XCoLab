package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan type column remote service. This utility wraps {@link com.ext.portlet.plans.service.impl.PlanTypeColumnServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTypeColumnService
 * @see com.ext.portlet.plans.service.base.PlanTypeColumnServiceBaseImpl
 * @see com.ext.portlet.plans.service.impl.PlanTypeColumnServiceImpl
 * @generated
 */
public class PlanTypeColumnServiceUtil {
    private static PlanTypeColumnService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.plans.service.impl.PlanTypeColumnServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static PlanTypeColumnService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanTypeColumnService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlanTypeColumnService.class.getName(), portletClassLoader);

            _service = new PlanTypeColumnServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanTypeColumnServiceUtil.class,
                "_service");
            MethodCache.remove(PlanTypeColumnService.class);
        }

        return _service;
    }

    public void setService(PlanTypeColumnService service) {
        MethodCache.remove(PlanTypeColumnService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlanTypeColumnServiceUtil.class,
            "_service");
        MethodCache.remove(PlanTypeColumnService.class);
    }
}
