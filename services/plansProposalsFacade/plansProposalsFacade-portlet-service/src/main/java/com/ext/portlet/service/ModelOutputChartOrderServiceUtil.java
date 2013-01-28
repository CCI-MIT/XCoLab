package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the model output chart order remote service. This utility wraps {@link com.ext.portlet.service.impl.ModelOutputChartOrderServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelOutputChartOrderService
 * @see com.ext.portlet.service.base.ModelOutputChartOrderServiceBaseImpl
 * @see com.ext.portlet.service.impl.ModelOutputChartOrderServiceImpl
 * @generated
 */
public class ModelOutputChartOrderServiceUtil {
    private static ModelOutputChartOrderService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ModelOutputChartOrderServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static void clearService() {
        _service = null;
    }

    public static ModelOutputChartOrderService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ModelOutputChartOrderService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ModelOutputChartOrderService.class.getName(),
                    portletClassLoader);

            _service = new ModelOutputChartOrderServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ModelOutputChartOrderServiceUtil.class,
                "_service");
            MethodCache.remove(ModelOutputChartOrderService.class);
        }

        return _service;
    }

    public void setService(ModelOutputChartOrderService service) {
        MethodCache.remove(ModelOutputChartOrderService.class);

        _service = service;

        ReferenceRegistry.registerReference(ModelOutputChartOrderServiceUtil.class,
            "_service");
        MethodCache.remove(ModelOutputChartOrderService.class);
    }
}
