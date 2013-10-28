package com.ext.portlet.service.http;

import com.ext.portlet.service.ModelRunnerServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * <p>
 * This class provides a SOAP utility for the
 * {@link com.ext.portlet.service.ModelRunnerServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at
 * http://localhost:8080/api/secure/axis. Set the property
 * <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ModelRunnerServiceHttp
 * @see       com.ext.portlet.service.ModelRunnerServiceUtil
 * @generated
 */
public class ModelRunnerServiceSoap {
    private static Log _log = LogFactoryUtil.getLog(ModelRunnerServiceSoap.class);

    public static java.lang.String getScenario(long scenarioId)
        throws RemoteException {
        try {
            com.liferay.portal.kernel.json.JSONObject returnValue = ModelRunnerServiceUtil.getScenario(scenarioId);

            return returnValue.toString();
        } catch (Throwable e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static java.lang.String getModel(long modelId)
        throws RemoteException {
        try {
            com.liferay.portal.kernel.json.JSONObject returnValue = ModelRunnerServiceUtil.getModel(modelId);

            return returnValue.toString();
        } catch (Throwable e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static java.lang.String runModel(long modelId,
        java.lang.String inputs) throws RemoteException {
        try {
            com.liferay.portal.kernel.json.JSONObject returnValue = ModelRunnerServiceUtil.runModel(modelId,
                    inputs);

            return returnValue.toString();
        } catch (Throwable e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }
}
