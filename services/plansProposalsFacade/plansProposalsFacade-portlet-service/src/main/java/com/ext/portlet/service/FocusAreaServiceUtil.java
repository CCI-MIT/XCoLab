package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the focus area remote service. This utility wraps {@link com.ext.portlet.service.impl.FocusAreaServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FocusAreaService
 * @see com.ext.portlet.service.base.FocusAreaServiceBaseImpl
 * @see com.ext.portlet.service.impl.FocusAreaServiceImpl
 * @generated
 */
public class FocusAreaServiceUtil {
    private static FocusAreaService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.FocusAreaServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static FocusAreaService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    FocusAreaService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    FocusAreaService.class.getName(), portletClassLoader);

            _service = new FocusAreaServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(FocusAreaServiceUtil.class,
                "_service");
            MethodCache.remove(FocusAreaService.class);
        }

        return _service;
    }

    public void setService(FocusAreaService service) {
        MethodCache.remove(FocusAreaService.class);

        _service = service;

        ReferenceRegistry.registerReference(FocusAreaServiceUtil.class,
            "_service");
        MethodCache.remove(FocusAreaService.class);
    }
}
