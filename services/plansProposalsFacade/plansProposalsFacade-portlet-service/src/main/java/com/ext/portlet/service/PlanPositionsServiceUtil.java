package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan positions remote service. This utility wraps {@link com.ext.portlet.service.impl.PlanPositionsServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanPositionsService
 * @see com.ext.portlet.service.base.PlanPositionsServiceBaseImpl
 * @see com.ext.portlet.service.impl.PlanPositionsServiceImpl
 * @generated
 */
public class PlanPositionsServiceUtil {
    private static PlanPositionsService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.PlanPositionsServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static PlanPositionsService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanPositionsService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlanPositionsService.class.getName(), portletClassLoader);

            _service = new PlanPositionsServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanPositionsServiceUtil.class,
                "_service");
            MethodCache.remove(PlanPositionsService.class);
        }

        return _service;
    }

    public void setService(PlanPositionsService service) {
        MethodCache.remove(PlanPositionsService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlanPositionsServiceUtil.class,
            "_service");
        MethodCache.remove(PlanPositionsService.class);
    }
}
