package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan item remote service. This utility wraps {@link com.ext.portlet.service.impl.PlanItemServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanItemService
 * @see com.ext.portlet.service.base.PlanItemServiceBaseImpl
 * @see com.ext.portlet.service.impl.PlanItemServiceImpl
 * @generated
 */
public class PlanItemServiceUtil {
    private static PlanItemService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.PlanItemServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static PlanItemService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanItemService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlanItemService.class.getName(), portletClassLoader);

            _service = new PlanItemServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanItemServiceUtil.class,
                "_service");
            MethodCache.remove(PlanItemService.class);
        }

        return _service;
    }

    public void setService(PlanItemService service) {
        MethodCache.remove(PlanItemService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlanItemServiceUtil.class,
            "_service");
        MethodCache.remove(PlanItemService.class);
    }
}
