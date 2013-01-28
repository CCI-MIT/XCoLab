package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan template remote service. This utility wraps {@link com.ext.portlet.service.impl.PlanTemplateServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTemplateService
 * @see com.ext.portlet.service.base.PlanTemplateServiceBaseImpl
 * @see com.ext.portlet.service.impl.PlanTemplateServiceImpl
 * @generated
 */
public class PlanTemplateServiceUtil {
    private static PlanTemplateService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.PlanTemplateServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static PlanTemplateService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanTemplateService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlanTemplateService.class.getName(), portletClassLoader);

            _service = new PlanTemplateServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanTemplateServiceUtil.class,
                "_service");
            MethodCache.remove(PlanTemplateService.class);
        }

        return _service;
    }

    public void setService(PlanTemplateService service) {
        MethodCache.remove(PlanTemplateService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlanTemplateServiceUtil.class,
            "_service");
        MethodCache.remove(PlanTemplateService.class);
    }
}
