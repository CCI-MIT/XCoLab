package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plans user settings remote service. This utility wraps {@link com.ext.portlet.service.impl.PlansUserSettingsServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlansUserSettingsService
 * @see com.ext.portlet.service.base.PlansUserSettingsServiceBaseImpl
 * @see com.ext.portlet.service.impl.PlansUserSettingsServiceImpl
 * @generated
 */
public class PlansUserSettingsServiceUtil {
    private static PlansUserSettingsService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.PlansUserSettingsServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static PlansUserSettingsService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlansUserSettingsService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlansUserSettingsService.class.getName(), portletClassLoader);

            _service = new PlansUserSettingsServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlansUserSettingsServiceUtil.class,
                "_service");
            MethodCache.remove(PlansUserSettingsService.class);
        }

        return _service;
    }

    public void setService(PlansUserSettingsService service) {
        MethodCache.remove(PlansUserSettingsService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlansUserSettingsServiceUtil.class,
            "_service");
        MethodCache.remove(PlansUserSettingsService.class);
    }
}
