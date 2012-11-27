package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan model run remote service. This utility wraps {@link com.ext.portlet.plans.service.impl.PlanModelRunServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanModelRunService
 * @see com.ext.portlet.plans.service.base.PlanModelRunServiceBaseImpl
 * @see com.ext.portlet.plans.service.impl.PlanModelRunServiceImpl
 * @generated
 */
public class PlanModelRunServiceUtil {
    private static PlanModelRunService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.plans.service.impl.PlanModelRunServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static PlanModelRunService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanModelRunService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlanModelRunService.class.getName(), portletClassLoader);

            _service = new PlanModelRunServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanModelRunServiceUtil.class,
                "_service");
            MethodCache.remove(PlanModelRunService.class);
        }

        return _service;
    }

    public void setService(PlanModelRunService service) {
        MethodCache.remove(PlanModelRunService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlanModelRunServiceUtil.class,
            "_service");
        MethodCache.remove(PlanModelRunService.class);
    }
}
