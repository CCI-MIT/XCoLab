package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan description remote service. This utility wraps {@link com.ext.portlet.plans.service.impl.PlanDescriptionServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanDescriptionService
 * @see com.ext.portlet.plans.service.base.PlanDescriptionServiceBaseImpl
 * @see com.ext.portlet.plans.service.impl.PlanDescriptionServiceImpl
 * @generated
 */
public class PlanDescriptionServiceUtil {
    private static PlanDescriptionService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.plans.service.impl.PlanDescriptionServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static PlanDescriptionService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanDescriptionService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlanDescriptionService.class.getName(), portletClassLoader);

            _service = new PlanDescriptionServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanDescriptionServiceUtil.class,
                "_service");
            MethodCache.remove(PlanDescriptionService.class);
        }

        return _service;
    }

    public void setService(PlanDescriptionService service) {
        MethodCache.remove(PlanDescriptionService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlanDescriptionServiceUtil.class,
            "_service");
        MethodCache.remove(PlanDescriptionService.class);
    }
}
