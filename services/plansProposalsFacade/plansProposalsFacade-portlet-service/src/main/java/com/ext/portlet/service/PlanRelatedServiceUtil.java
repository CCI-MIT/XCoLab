package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan related remote service. This utility wraps {@link com.ext.portlet.service.impl.PlanRelatedServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanRelatedService
 * @see com.ext.portlet.service.base.PlanRelatedServiceBaseImpl
 * @see com.ext.portlet.service.impl.PlanRelatedServiceImpl
 * @generated
 */
public class PlanRelatedServiceUtil {
    private static PlanRelatedService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.PlanRelatedServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static PlanRelatedService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanRelatedService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlanRelatedService.class.getName(), portletClassLoader);

            _service = new PlanRelatedServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanRelatedServiceUtil.class,
                "_service");
            MethodCache.remove(PlanRelatedService.class);
        }

        return _service;
    }

    public void setService(PlanRelatedService service) {
        MethodCache.remove(PlanRelatedService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlanRelatedServiceUtil.class,
            "_service");
        MethodCache.remove(PlanRelatedService.class);
    }
}
