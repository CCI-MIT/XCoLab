package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan fan remote service. This utility wraps {@link com.ext.portlet.plans.service.impl.PlanFanServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanFanService
 * @see com.ext.portlet.plans.service.base.PlanFanServiceBaseImpl
 * @see com.ext.portlet.plans.service.impl.PlanFanServiceImpl
 * @generated
 */
public class PlanFanServiceUtil {
    private static PlanFanService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.plans.service.impl.PlanFanServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static PlanFanService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanFanService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlanFanService.class.getName(), portletClassLoader);

            _service = new PlanFanServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanFanServiceUtil.class,
                "_service");
            MethodCache.remove(PlanFanService.class);
        }

        return _service;
    }

    public void setService(PlanFanService service) {
        MethodCache.remove(PlanFanService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlanFanServiceUtil.class, "_service");
        MethodCache.remove(PlanFanService.class);
    }
}
