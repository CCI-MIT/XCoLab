package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the model discussion remote service. This utility wraps {@link com.ext.portlet.service.impl.ModelDiscussionServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelDiscussionService
 * @see com.ext.portlet.service.base.ModelDiscussionServiceBaseImpl
 * @see com.ext.portlet.service.impl.ModelDiscussionServiceImpl
 * @generated
 */
public class ModelDiscussionServiceUtil {
    private static ModelDiscussionService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ModelDiscussionServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static ModelDiscussionService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ModelDiscussionService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ModelDiscussionService.class.getName(), portletClassLoader);

            _service = new ModelDiscussionServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ModelDiscussionServiceUtil.class,
                "_service");
            MethodCache.remove(ModelDiscussionService.class);
        }

        return _service;
    }

    public void setService(ModelDiscussionService service) {
        MethodCache.remove(ModelDiscussionService.class);

        _service = service;

        ReferenceRegistry.registerReference(ModelDiscussionServiceUtil.class,
            "_service");
        MethodCache.remove(ModelDiscussionService.class);
    }
}
