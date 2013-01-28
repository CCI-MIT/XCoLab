package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the model position remote service. This utility wraps {@link com.ext.portlet.service.impl.ModelPositionServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelPositionService
 * @see com.ext.portlet.service.base.ModelPositionServiceBaseImpl
 * @see com.ext.portlet.service.impl.ModelPositionServiceImpl
 * @generated
 */
public class ModelPositionServiceUtil {
    private static ModelPositionService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ModelPositionServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static ModelPositionService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ModelPositionService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ModelPositionService.class.getName(), portletClassLoader);

            _service = new ModelPositionServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ModelPositionServiceUtil.class,
                "_service");
            MethodCache.remove(ModelPositionService.class);
        }

        return _service;
    }

    public void setService(ModelPositionService service) {
        MethodCache.remove(ModelPositionService.class);

        _service = service;

        ReferenceRegistry.registerReference(ModelPositionServiceUtil.class,
            "_service");
        MethodCache.remove(ModelPositionService.class);
    }
}
