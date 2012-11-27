package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan position item remote service. This utility wraps {@link com.ext.portlet.plans.service.impl.PlanPositionItemServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanPositionItemService
 * @see com.ext.portlet.plans.service.base.PlanPositionItemServiceBaseImpl
 * @see com.ext.portlet.plans.service.impl.PlanPositionItemServiceImpl
 * @generated
 */
public class PlanPositionItemServiceUtil {
    private static PlanPositionItemService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.plans.service.impl.PlanPositionItemServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static PlanPositionItemService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanPositionItemService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlanPositionItemService.class.getName(), portletClassLoader);

            _service = new PlanPositionItemServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanPositionItemServiceUtil.class,
                "_service");
            MethodCache.remove(PlanPositionItemService.class);
        }

        return _service;
    }

    public void setService(PlanPositionItemService service) {
        MethodCache.remove(PlanPositionItemService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlanPositionItemServiceUtil.class,
            "_service");
        MethodCache.remove(PlanPositionItemService.class);
    }
}
