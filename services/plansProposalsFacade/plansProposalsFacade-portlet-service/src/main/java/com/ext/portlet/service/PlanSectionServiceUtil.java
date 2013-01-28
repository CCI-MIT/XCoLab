package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan section remote service. This utility wraps {@link com.ext.portlet.service.impl.PlanSectionServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanSectionService
 * @see com.ext.portlet.service.base.PlanSectionServiceBaseImpl
 * @see com.ext.portlet.service.impl.PlanSectionServiceImpl
 * @generated
 */
public class PlanSectionServiceUtil {
    private static PlanSectionService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.PlanSectionServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static PlanSectionService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanSectionService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlanSectionService.class.getName(), portletClassLoader);

            _service = new PlanSectionServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanSectionServiceUtil.class,
                "_service");
            MethodCache.remove(PlanSectionService.class);
        }

        return _service;
    }

    public void setService(PlanSectionService service) {
        MethodCache.remove(PlanSectionService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlanSectionServiceUtil.class,
            "_service");
        MethodCache.remove(PlanSectionService.class);
    }
}
