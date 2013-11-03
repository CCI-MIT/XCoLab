package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the contest phase ribbon type remote service. This utility wraps {@link com.ext.portlet.service.impl.ContestPhaseRibbonTypeServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhaseRibbonTypeService
 * @see com.ext.portlet.service.base.ContestPhaseRibbonTypeServiceBaseImpl
 * @see com.ext.portlet.service.impl.ContestPhaseRibbonTypeServiceImpl
 * @generated
 */
public class ContestPhaseRibbonTypeServiceUtil {
    private static ContestPhaseRibbonTypeService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ContestPhaseRibbonTypeServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static ContestPhaseRibbonTypeService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ContestPhaseRibbonTypeService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ContestPhaseRibbonTypeService.class.getName(),
                    portletClassLoader);

            _service = new ContestPhaseRibbonTypeServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ContestPhaseRibbonTypeServiceUtil.class,
                "_service");
            MethodCache.remove(ContestPhaseRibbonTypeService.class);
        }

        return _service;
    }

    public void setService(ContestPhaseRibbonTypeService service) {
        MethodCache.remove(ContestPhaseRibbonTypeService.class);

        _service = service;

        ReferenceRegistry.registerReference(ContestPhaseRibbonTypeServiceUtil.class,
            "_service");
        MethodCache.remove(ContestPhaseRibbonTypeService.class);
    }
}
