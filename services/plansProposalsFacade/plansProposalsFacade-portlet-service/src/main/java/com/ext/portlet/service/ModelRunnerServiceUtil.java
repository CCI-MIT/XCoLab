package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the model runner remote service. This utility wraps {@link com.ext.portlet.service.impl.ModelRunnerServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelRunnerService
 * @see com.ext.portlet.service.base.ModelRunnerServiceBaseImpl
 * @see com.ext.portlet.service.impl.ModelRunnerServiceImpl
 * @generated
 */
public class ModelRunnerServiceUtil {
    private static ModelRunnerService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ModelRunnerServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public static com.liferay.portal.kernel.json.JSONObject getScenario(
        long scenarioId) {
        return getService().getScenario(scenarioId);
    }

    public static com.liferay.portal.kernel.json.JSONObject getModel(
        long modelId)
        throws com.ext.portlet.models.ui.IllegalUIConfigurationException,
            com.liferay.portal.kernel.exception.SystemException,
            java.io.IOException {
        return getService().getModel(modelId);
    }

    public static com.liferay.portal.kernel.json.JSONObject runModel(
        long modelId, java.lang.String inputs)
        throws com.ext.portlet.models.ui.IllegalUIConfigurationException,
            com.liferay.portal.kernel.exception.SystemException,
            com.liferay.portal.kernel.json.JSONException,
            edu.mit.cci.roma.client.comm.ModelNotFoundException,
            edu.mit.cci.roma.client.comm.ScenarioNotFoundException,
            java.io.IOException {
        return getService().runModel(modelId, inputs);
    }

    public static void clearService() {
        _service = null;
    }

    public static ModelRunnerService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ModelRunnerService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ModelRunnerService.class.getName(), portletClassLoader);

            _service = new ModelRunnerServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ModelRunnerServiceUtil.class,
                "_service");
            MethodCache.remove(ModelRunnerService.class);
        }

        return _service;
    }

    public void setService(ModelRunnerService service) {
        MethodCache.remove(ModelRunnerService.class);

        _service = service;

        ReferenceRegistry.registerReference(ModelRunnerServiceUtil.class,
            "_service");
        MethodCache.remove(ModelRunnerService.class);
    }
}
