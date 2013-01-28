package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan template section remote service. This utility wraps {@link com.ext.portlet.service.impl.PlanTemplateSectionServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTemplateSectionService
 * @see com.ext.portlet.service.base.PlanTemplateSectionServiceBaseImpl
 * @see com.ext.portlet.service.impl.PlanTemplateSectionServiceImpl
 * @generated
 */
public class PlanTemplateSectionServiceUtil {
    private static PlanTemplateSectionService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.PlanTemplateSectionServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static PlanTemplateSectionService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanTemplateSectionService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlanTemplateSectionService.class.getName(),
                    portletClassLoader);

            _service = new PlanTemplateSectionServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanTemplateSectionServiceUtil.class,
                "_service");
            MethodCache.remove(PlanTemplateSectionService.class);
        }

        return _service;
    }

    public void setService(PlanTemplateSectionService service) {
        MethodCache.remove(PlanTemplateSectionService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlanTemplateSectionServiceUtil.class,
            "_service");
        MethodCache.remove(PlanTemplateSectionService.class);
    }
}
