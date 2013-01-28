package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan attribute filter remote service. This utility wraps {@link com.ext.portlet.service.impl.PlanAttributeFilterServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanAttributeFilterService
 * @see com.ext.portlet.service.base.PlanAttributeFilterServiceBaseImpl
 * @see com.ext.portlet.service.impl.PlanAttributeFilterServiceImpl
 * @generated
 */
public class PlanAttributeFilterServiceUtil {
    private static PlanAttributeFilterService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.PlanAttributeFilterServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static PlanAttributeFilterService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanAttributeFilterService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlanAttributeFilterService.class.getName(),
                    portletClassLoader);

            _service = new PlanAttributeFilterServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanAttributeFilterServiceUtil.class,
                "_service");
            MethodCache.remove(PlanAttributeFilterService.class);
        }

        return _service;
    }

    public void setService(PlanAttributeFilterService service) {
        MethodCache.remove(PlanAttributeFilterService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlanAttributeFilterServiceUtil.class,
            "_service");
        MethodCache.remove(PlanAttributeFilterService.class);
    }
}
