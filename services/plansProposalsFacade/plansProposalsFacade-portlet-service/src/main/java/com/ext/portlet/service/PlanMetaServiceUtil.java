package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan meta remote service. This utility wraps {@link com.ext.portlet.service.impl.PlanMetaServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanMetaService
 * @see com.ext.portlet.service.base.PlanMetaServiceBaseImpl
 * @see com.ext.portlet.service.impl.PlanMetaServiceImpl
 * @generated
 */
public class PlanMetaServiceUtil {
    private static PlanMetaService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.PlanMetaServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static PlanMetaService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanMetaService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlanMetaService.class.getName(), portletClassLoader);

            _service = new PlanMetaServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanMetaServiceUtil.class,
                "_service");
            MethodCache.remove(PlanMetaService.class);
        }

        return _service;
    }

    public void setService(PlanMetaService service) {
        MethodCache.remove(PlanMetaService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlanMetaServiceUtil.class,
            "_service");
        MethodCache.remove(PlanMetaService.class);
    }
}
