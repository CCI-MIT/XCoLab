package com.ext.portlet.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.security.ac.AccessControlled;
import com.liferay.portal.service.BaseService;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service interface for ModelRunner. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ModelRunnerServiceUtil
 * @see com.ext.portlet.service.base.ModelRunnerServiceBaseImpl
 * @see com.ext.portlet.service.impl.ModelRunnerServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface ModelRunnerService extends BaseService, InvokableService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ModelRunnerServiceUtil} to access the model runner remote service. Add custom service methods to {@link com.ext.portlet.service.impl.ModelRunnerServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier();

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier);

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.kernel.json.JSONObject getScenario(
        long scenarioId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.kernel.json.JSONObject getModel(long modelId)
        throws com.ext.portlet.models.ui.IllegalUIConfigurationException,
            com.liferay.portal.kernel.exception.SystemException,
            java.io.IOException;

    public com.liferay.portal.kernel.json.JSONObject runModel(long modelId,
        java.lang.String inputs)
        throws com.ext.portlet.models.ui.IllegalUIConfigurationException,
            com.liferay.portal.kernel.exception.SystemException,
            com.liferay.portal.kernel.json.JSONException,
            edu.mit.cci.roma.client.comm.ModelNotFoundException,
            edu.mit.cci.roma.client.comm.ScenarioNotFoundException,
            java.io.IOException;

    public void refreshModels()
        throws com.liferay.portal.kernel.exception.SystemException,
            java.io.IOException;
}
