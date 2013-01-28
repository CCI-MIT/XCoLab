package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the contest phase type remote service. This utility wraps {@link com.ext.portlet.service.impl.ContestPhaseTypeServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhaseTypeService
 * @see com.ext.portlet.service.base.ContestPhaseTypeServiceBaseImpl
 * @see com.ext.portlet.service.impl.ContestPhaseTypeServiceImpl
 * @generated
 */
public class ContestPhaseTypeServiceUtil {
    private static ContestPhaseTypeService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ContestPhaseTypeServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static ContestPhaseTypeService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ContestPhaseTypeService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ContestPhaseTypeService.class.getName(), portletClassLoader);

            _service = new ContestPhaseTypeServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ContestPhaseTypeServiceUtil.class,
                "_service");
            MethodCache.remove(ContestPhaseTypeService.class);
        }

        return _service;
    }

    public void setService(ContestPhaseTypeService service) {
        MethodCache.remove(ContestPhaseTypeService.class);

        _service = service;

        ReferenceRegistry.registerReference(ContestPhaseTypeServiceUtil.class,
            "_service");
        MethodCache.remove(ContestPhaseTypeService.class);
    }
}
