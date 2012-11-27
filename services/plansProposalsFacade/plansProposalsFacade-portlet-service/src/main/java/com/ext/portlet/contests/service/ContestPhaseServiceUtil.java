package com.ext.portlet.contests.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the contest phase remote service. This utility wraps {@link com.ext.portlet.contests.service.impl.ContestPhaseServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhaseService
 * @see com.ext.portlet.contests.service.base.ContestPhaseServiceBaseImpl
 * @see com.ext.portlet.contests.service.impl.ContestPhaseServiceImpl
 * @generated
 */
public class ContestPhaseServiceUtil {
    private static ContestPhaseService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.contests.service.impl.ContestPhaseServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static ContestPhaseService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ContestPhaseService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ContestPhaseService.class.getName(), portletClassLoader);

            _service = new ContestPhaseServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ContestPhaseServiceUtil.class,
                "_service");
            MethodCache.remove(ContestPhaseService.class);
        }

        return _service;
    }

    public void setService(ContestPhaseService service) {
        MethodCache.remove(ContestPhaseService.class);

        _service = service;

        ReferenceRegistry.registerReference(ContestPhaseServiceUtil.class,
            "_service");
        MethodCache.remove(ContestPhaseService.class);
    }
}
