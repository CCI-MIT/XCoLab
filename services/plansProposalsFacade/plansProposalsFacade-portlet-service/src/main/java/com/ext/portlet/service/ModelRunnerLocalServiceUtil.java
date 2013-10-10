package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the model runner local service. This utility wraps {@link com.ext.portlet.service.impl.ModelRunnerLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelRunnerLocalService
 * @see com.ext.portlet.service.base.ModelRunnerLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.ModelRunnerLocalServiceImpl
 * @generated
 */
public class ModelRunnerLocalServiceUtil {
    private static ModelRunnerLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ModelRunnerLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public static java.lang.String getBeanIdentifier() {
        return getService().getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public static void setBeanIdentifier(java.lang.String beanIdentifier) {
        getService().setBeanIdentifier(beanIdentifier);
    }

    public static void clearService() {
        _service = null;
    }

    public static ModelRunnerLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ModelRunnerLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ModelRunnerLocalService.class.getName(), portletClassLoader);

            _service = new ModelRunnerLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ModelRunnerLocalServiceUtil.class,
                "_service");
            MethodCache.remove(ModelRunnerLocalService.class);
        }

        return _service;
    }

    public void setService(ModelRunnerLocalService service) {
        MethodCache.remove(ModelRunnerLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(ModelRunnerLocalServiceUtil.class,
            "_service");
        MethodCache.remove(ModelRunnerLocalService.class);
    }
}
