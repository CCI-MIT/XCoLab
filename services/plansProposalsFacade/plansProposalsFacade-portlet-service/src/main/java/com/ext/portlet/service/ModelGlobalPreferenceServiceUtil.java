package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the model global preference remote service. This utility wraps {@link com.ext.portlet.service.impl.ModelGlobalPreferenceServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelGlobalPreferenceService
 * @see com.ext.portlet.service.base.ModelGlobalPreferenceServiceBaseImpl
 * @see com.ext.portlet.service.impl.ModelGlobalPreferenceServiceImpl
 * @generated
 */
public class ModelGlobalPreferenceServiceUtil {
    private static ModelGlobalPreferenceService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ModelGlobalPreferenceServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static ModelGlobalPreferenceService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ModelGlobalPreferenceService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ModelGlobalPreferenceService.class.getName(),
                    portletClassLoader);

            _service = new ModelGlobalPreferenceServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ModelGlobalPreferenceServiceUtil.class,
                "_service");
            MethodCache.remove(ModelGlobalPreferenceService.class);
        }

        return _service;
    }

    public void setService(ModelGlobalPreferenceService service) {
        MethodCache.remove(ModelGlobalPreferenceService.class);

        _service = service;

        ReferenceRegistry.registerReference(ModelGlobalPreferenceServiceUtil.class,
            "_service");
        MethodCache.remove(ModelGlobalPreferenceService.class);
    }
}
