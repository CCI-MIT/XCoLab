package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan type remote service. This utility wraps {@link com.ext.portlet.service.impl.PlanTypeServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTypeService
 * @see com.ext.portlet.service.base.PlanTypeServiceBaseImpl
 * @see com.ext.portlet.service.impl.PlanTypeServiceImpl
 * @generated
 */
public class PlanTypeServiceUtil {
    private static PlanTypeService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.PlanTypeServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static PlanTypeService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanTypeService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlanTypeService.class.getName(), portletClassLoader);

            _service = new PlanTypeServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanTypeServiceUtil.class,
                "_service");
            MethodCache.remove(PlanTypeService.class);
        }

        return _service;
    }

    public void setService(PlanTypeService service) {
        MethodCache.remove(PlanTypeService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlanTypeServiceUtil.class,
            "_service");
        MethodCache.remove(PlanTypeService.class);
    }
}
