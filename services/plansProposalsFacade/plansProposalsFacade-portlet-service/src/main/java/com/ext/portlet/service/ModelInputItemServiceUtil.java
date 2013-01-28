package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the model input item remote service. This utility wraps {@link com.ext.portlet.service.impl.ModelInputItemServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelInputItemService
 * @see com.ext.portlet.service.base.ModelInputItemServiceBaseImpl
 * @see com.ext.portlet.service.impl.ModelInputItemServiceImpl
 * @generated
 */
public class ModelInputItemServiceUtil {
    private static ModelInputItemService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ModelInputItemServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static ModelInputItemService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ModelInputItemService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ModelInputItemService.class.getName(), portletClassLoader);

            _service = new ModelInputItemServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ModelInputItemServiceUtil.class,
                "_service");
            MethodCache.remove(ModelInputItemService.class);
        }

        return _service;
    }

    public void setService(ModelInputItemService service) {
        MethodCache.remove(ModelInputItemService.class);

        _service = service;

        ReferenceRegistry.registerReference(ModelInputItemServiceUtil.class,
            "_service");
        MethodCache.remove(ModelInputItemService.class);
    }
}
