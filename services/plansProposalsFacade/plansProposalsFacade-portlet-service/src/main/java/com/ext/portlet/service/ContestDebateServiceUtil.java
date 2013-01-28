package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the contest debate remote service. This utility wraps {@link com.ext.portlet.service.impl.ContestDebateServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestDebateService
 * @see com.ext.portlet.service.base.ContestDebateServiceBaseImpl
 * @see com.ext.portlet.service.impl.ContestDebateServiceImpl
 * @generated
 */
public class ContestDebateServiceUtil {
    private static ContestDebateService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ContestDebateServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static ContestDebateService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ContestDebateService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ContestDebateService.class.getName(), portletClassLoader);

            _service = new ContestDebateServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ContestDebateServiceUtil.class,
                "_service");
            MethodCache.remove(ContestDebateService.class);
        }

        return _service;
    }

    public void setService(ContestDebateService service) {
        MethodCache.remove(ContestDebateService.class);

        _service = service;

        ReferenceRegistry.registerReference(ContestDebateServiceUtil.class,
            "_service");
        MethodCache.remove(ContestDebateService.class);
    }
}
