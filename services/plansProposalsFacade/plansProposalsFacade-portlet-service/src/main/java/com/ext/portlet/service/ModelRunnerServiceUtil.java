package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service utility for ModelRunner. This utility wraps
 * {@link com.ext.portlet.service.impl.ModelRunnerServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
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

    public static java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return getService().invokeMethod(name, parameterTypes, arguments);
    }

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
            InvokableService invokableService = (InvokableService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ModelRunnerService.class.getName());

            if (invokableService instanceof ModelRunnerService) {
                _service = (ModelRunnerService) invokableService;
            } else {
                _service = new ModelRunnerServiceClp(invokableService);
            }

            ReferenceRegistry.registerReference(ModelRunnerServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(ModelRunnerService service) {
    }
}
