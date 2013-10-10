package com.ext.portlet.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

/**
 * The interface for the model runner remote service.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelRunnerServiceUtil
 * @see com.ext.portlet.service.base.ModelRunnerServiceBaseImpl
 * @see com.ext.portlet.service.impl.ModelRunnerServiceImpl
 * @generated
 */
@JSONWebService
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface ModelRunnerService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ModelRunnerServiceUtil} to access the model runner remote service. Add custom service methods to {@link com.ext.portlet.service.impl.ModelRunnerServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.kernel.json.JSONObject getScenario(
        long scenarioId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.kernel.json.JSONObject getModel(long modelId);

    public com.liferay.portal.kernel.json.JSONObject runModel(long modelId,
        java.lang.String inputs)
        throws com.ext.portlet.models.ui.IllegalUIConfigurationException,
            com.liferay.portal.kernel.exception.SystemException,
            com.liferay.portal.kernel.json.JSONException,
            edu.mit.cci.roma.client.comm.ModelNotFoundException,
            edu.mit.cci.roma.client.comm.ScenarioNotFoundException,
            java.io.IOException;
}
