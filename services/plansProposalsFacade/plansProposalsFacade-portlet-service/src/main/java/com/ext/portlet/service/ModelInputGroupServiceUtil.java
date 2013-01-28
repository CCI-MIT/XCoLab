package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the model input group remote service. This utility wraps {@link com.ext.portlet.service.impl.ModelInputGroupServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelInputGroupService
 * @see com.ext.portlet.service.base.ModelInputGroupServiceBaseImpl
 * @see com.ext.portlet.service.impl.ModelInputGroupServiceImpl
 * @generated
 */
public class ModelInputGroupServiceUtil {
    private static ModelInputGroupService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ModelInputGroupServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static ModelInputGroupService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ModelInputGroupService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ModelInputGroupService.class.getName(), portletClassLoader);

            _service = new ModelInputGroupServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ModelInputGroupServiceUtil.class,
                "_service");
            MethodCache.remove(ModelInputGroupService.class);
        }

        return _service;
    }

    public void setService(ModelInputGroupService service) {
        MethodCache.remove(ModelInputGroupService.class);

        _service = service;

        ReferenceRegistry.registerReference(ModelInputGroupServiceUtil.class,
            "_service");
        MethodCache.remove(ModelInputGroupService.class);
    }
}
