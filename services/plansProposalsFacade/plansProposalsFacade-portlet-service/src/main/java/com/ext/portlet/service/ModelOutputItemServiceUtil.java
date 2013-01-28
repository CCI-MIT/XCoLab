package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the model output item remote service. This utility wraps {@link com.ext.portlet.service.impl.ModelOutputItemServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelOutputItemService
 * @see com.ext.portlet.service.base.ModelOutputItemServiceBaseImpl
 * @see com.ext.portlet.service.impl.ModelOutputItemServiceImpl
 * @generated
 */
public class ModelOutputItemServiceUtil {
    private static ModelOutputItemService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ModelOutputItemServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static ModelOutputItemService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ModelOutputItemService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ModelOutputItemService.class.getName(), portletClassLoader);

            _service = new ModelOutputItemServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ModelOutputItemServiceUtil.class,
                "_service");
            MethodCache.remove(ModelOutputItemService.class);
        }

        return _service;
    }

    public void setService(ModelOutputItemService service) {
        MethodCache.remove(ModelOutputItemService.class);

        _service = service;

        ReferenceRegistry.registerReference(ModelOutputItemServiceUtil.class,
            "_service");
        MethodCache.remove(ModelOutputItemService.class);
    }
}
