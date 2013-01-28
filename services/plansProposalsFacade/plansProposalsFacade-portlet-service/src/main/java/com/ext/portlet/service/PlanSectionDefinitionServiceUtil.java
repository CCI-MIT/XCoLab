package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan section definition remote service. This utility wraps {@link com.ext.portlet.service.impl.PlanSectionDefinitionServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanSectionDefinitionService
 * @see com.ext.portlet.service.base.PlanSectionDefinitionServiceBaseImpl
 * @see com.ext.portlet.service.impl.PlanSectionDefinitionServiceImpl
 * @generated
 */
public class PlanSectionDefinitionServiceUtil {
    private static PlanSectionDefinitionService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.PlanSectionDefinitionServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static PlanSectionDefinitionService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanSectionDefinitionService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlanSectionDefinitionService.class.getName(),
                    portletClassLoader);

            _service = new PlanSectionDefinitionServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanSectionDefinitionServiceUtil.class,
                "_service");
            MethodCache.remove(PlanSectionDefinitionService.class);
        }

        return _service;
    }

    public void setService(PlanSectionDefinitionService service) {
        MethodCache.remove(PlanSectionDefinitionService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlanSectionDefinitionServiceUtil.class,
            "_service");
        MethodCache.remove(PlanSectionDefinitionService.class);
    }
}
