package com.ext.portlet.contests.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the contest phase column remote service. This utility wraps {@link com.ext.portlet.contests.service.impl.ContestPhaseColumnServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhaseColumnService
 * @see com.ext.portlet.contests.service.base.ContestPhaseColumnServiceBaseImpl
 * @see com.ext.portlet.contests.service.impl.ContestPhaseColumnServiceImpl
 * @generated
 */
public class ContestPhaseColumnServiceUtil {
    private static ContestPhaseColumnService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.contests.service.impl.ContestPhaseColumnServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static ContestPhaseColumnService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ContestPhaseColumnService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ContestPhaseColumnService.class.getName(),
                    portletClassLoader);

            _service = new ContestPhaseColumnServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ContestPhaseColumnServiceUtil.class,
                "_service");
            MethodCache.remove(ContestPhaseColumnService.class);
        }

        return _service;
    }

    public void setService(ContestPhaseColumnService service) {
        MethodCache.remove(ContestPhaseColumnService.class);

        _service = service;

        ReferenceRegistry.registerReference(ContestPhaseColumnServiceUtil.class,
            "_service");
        MethodCache.remove(ContestPhaseColumnService.class);
    }
}
