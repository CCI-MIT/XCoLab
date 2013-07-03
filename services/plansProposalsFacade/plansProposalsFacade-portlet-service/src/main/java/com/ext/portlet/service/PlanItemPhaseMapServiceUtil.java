package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan item phase map remote service. This utility wraps {@link com.ext.portlet.service.impl.PlanItemPhaseMapServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanItemPhaseMapService
 * @see com.ext.portlet.service.base.PlanItemPhaseMapServiceBaseImpl
 * @see com.ext.portlet.service.impl.PlanItemPhaseMapServiceImpl
 * @generated
 */
public class PlanItemPhaseMapServiceUtil {
    private static PlanItemPhaseMapService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.PlanItemPhaseMapServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static PlanItemPhaseMapService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanItemPhaseMapService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlanItemPhaseMapService.class.getName(), portletClassLoader);

            _service = new PlanItemPhaseMapServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanItemPhaseMapServiceUtil.class,
                "_service");
            MethodCache.remove(PlanItemPhaseMapService.class);
        }

        return _service;
    }

    public void setService(PlanItemPhaseMapService service) {
        MethodCache.remove(PlanItemPhaseMapService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlanItemPhaseMapServiceUtil.class,
            "_service");
        MethodCache.remove(PlanItemPhaseMapService.class);
    }
}
