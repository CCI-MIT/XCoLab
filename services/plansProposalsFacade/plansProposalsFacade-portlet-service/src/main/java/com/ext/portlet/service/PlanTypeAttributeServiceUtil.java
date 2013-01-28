package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan type attribute remote service. This utility wraps {@link com.ext.portlet.service.impl.PlanTypeAttributeServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTypeAttributeService
 * @see com.ext.portlet.service.base.PlanTypeAttributeServiceBaseImpl
 * @see com.ext.portlet.service.impl.PlanTypeAttributeServiceImpl
 * @generated
 */
public class PlanTypeAttributeServiceUtil {
    private static PlanTypeAttributeService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.PlanTypeAttributeServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static PlanTypeAttributeService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanTypeAttributeService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlanTypeAttributeService.class.getName(), portletClassLoader);

            _service = new PlanTypeAttributeServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanTypeAttributeServiceUtil.class,
                "_service");
            MethodCache.remove(PlanTypeAttributeService.class);
        }

        return _service;
    }

    public void setService(PlanTypeAttributeService service) {
        MethodCache.remove(PlanTypeAttributeService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlanTypeAttributeServiceUtil.class,
            "_service");
        MethodCache.remove(PlanTypeAttributeService.class);
    }
}
