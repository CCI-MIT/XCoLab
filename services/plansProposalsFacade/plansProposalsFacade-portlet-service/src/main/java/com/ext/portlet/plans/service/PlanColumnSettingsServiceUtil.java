package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan column settings remote service. This utility wraps {@link com.ext.portlet.plans.service.impl.PlanColumnSettingsServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanColumnSettingsService
 * @see com.ext.portlet.plans.service.base.PlanColumnSettingsServiceBaseImpl
 * @see com.ext.portlet.plans.service.impl.PlanColumnSettingsServiceImpl
 * @generated
 */
public class PlanColumnSettingsServiceUtil {
    private static PlanColumnSettingsService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.plans.service.impl.PlanColumnSettingsServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static PlanColumnSettingsService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanColumnSettingsService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlanColumnSettingsService.class.getName(),
                    portletClassLoader);

            _service = new PlanColumnSettingsServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanColumnSettingsServiceUtil.class,
                "_service");
            MethodCache.remove(PlanColumnSettingsService.class);
        }

        return _service;
    }

    public void setService(PlanColumnSettingsService service) {
        MethodCache.remove(PlanColumnSettingsService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlanColumnSettingsServiceUtil.class,
            "_service");
        MethodCache.remove(PlanColumnSettingsService.class);
    }
}
