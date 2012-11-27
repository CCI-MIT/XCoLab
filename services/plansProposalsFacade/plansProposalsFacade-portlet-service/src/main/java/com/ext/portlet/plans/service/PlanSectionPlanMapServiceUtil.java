package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan section plan map remote service. This utility wraps {@link com.ext.portlet.plans.service.impl.PlanSectionPlanMapServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanSectionPlanMapService
 * @see com.ext.portlet.plans.service.base.PlanSectionPlanMapServiceBaseImpl
 * @see com.ext.portlet.plans.service.impl.PlanSectionPlanMapServiceImpl
 * @generated
 */
public class PlanSectionPlanMapServiceUtil {
    private static PlanSectionPlanMapService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.plans.service.impl.PlanSectionPlanMapServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static PlanSectionPlanMapService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanSectionPlanMapService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlanSectionPlanMapService.class.getName(),
                    portletClassLoader);

            _service = new PlanSectionPlanMapServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanSectionPlanMapServiceUtil.class,
                "_service");
            MethodCache.remove(PlanSectionPlanMapService.class);
        }

        return _service;
    }

    public void setService(PlanSectionPlanMapService service) {
        MethodCache.remove(PlanSectionPlanMapService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlanSectionPlanMapServiceUtil.class,
            "_service");
        MethodCache.remove(PlanSectionPlanMapService.class);
    }
}
