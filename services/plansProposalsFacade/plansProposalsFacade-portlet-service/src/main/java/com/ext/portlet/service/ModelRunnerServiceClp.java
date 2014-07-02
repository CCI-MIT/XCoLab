package com.ext.portlet.service;

import com.liferay.portal.service.InvokableService;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ModelRunnerServiceClp implements ModelRunnerService {
    private InvokableService _invokableService;
    private String _methodName0;
    private String[] _methodParameterTypes0;
    private String _methodName1;
    private String[] _methodParameterTypes1;
    private String _methodName3;
    private String[] _methodParameterTypes3;
    private String _methodName4;
    private String[] _methodParameterTypes4;
    private String _methodName5;
    private String[] _methodParameterTypes5;
    private String _methodName6;
    private String[] _methodParameterTypes6;

    public ModelRunnerServiceClp(InvokableService invokableService) {
        _invokableService = invokableService;

        _methodName0 = "getBeanIdentifier";

        _methodParameterTypes0 = new String[] {  };

        _methodName1 = "setBeanIdentifier";

        _methodParameterTypes1 = new String[] { "java.lang.String" };

        _methodName3 = "getScenario";

        _methodParameterTypes3 = new String[] { "long" };

        _methodName4 = "getModel";

        _methodParameterTypes4 = new String[] { "long" };

        _methodName5 = "runModel";

        _methodParameterTypes5 = new String[] { "long", "java.lang.String" };

        _methodName6 = "refreshModels";

        _methodParameterTypes6 = new String[] {  };
    }

    @Override
    public java.lang.String getBeanIdentifier() {
        Object returnObj = null;

        try {
            returnObj = _invokableService.invokeMethod(_methodName0,
                    _methodParameterTypes0, new Object[] {  });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.String) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        try {
            _invokableService.invokeMethod(_methodName1,
                _methodParameterTypes1,
                new Object[] { ClpSerializer.translateInput(beanIdentifier) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        throw new UnsupportedOperationException();
    }

    @Override
    public com.liferay.portal.kernel.json.JSONObject getScenario(
        long scenarioId) {
        Object returnObj = null;

        try {
            returnObj = _invokableService.invokeMethod(_methodName3,
                    _methodParameterTypes3, new Object[] { scenarioId });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.liferay.portal.kernel.json.JSONObject) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public com.liferay.portal.kernel.json.JSONObject getModel(long modelId)
        throws com.ext.portlet.models.ui.IllegalUIConfigurationException,
            com.liferay.portal.kernel.exception.SystemException,
            java.io.IOException {
        Object returnObj = null;

        try {
            returnObj = _invokableService.invokeMethod(_methodName4,
                    _methodParameterTypes4, new Object[] { modelId });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.ext.portlet.models.ui.IllegalUIConfigurationException) {
                throw (com.ext.portlet.models.ui.IllegalUIConfigurationException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof java.io.IOException) {
                throw (java.io.IOException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.liferay.portal.kernel.json.JSONObject) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public com.liferay.portal.kernel.json.JSONObject runModel(long modelId,
        java.lang.String inputs)
        throws com.ext.portlet.models.ui.IllegalUIConfigurationException,
            com.liferay.portal.kernel.exception.SystemException,
            com.liferay.portal.kernel.json.JSONException,
            edu.mit.cci.roma.client.comm.ModelNotFoundException,
            edu.mit.cci.roma.client.comm.ScenarioNotFoundException,
            java.io.IOException {
        Object returnObj = null;

        try {
            returnObj = _invokableService.invokeMethod(_methodName5,
                    _methodParameterTypes5,
                    new Object[] { modelId, ClpSerializer.translateInput(inputs) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.ext.portlet.models.ui.IllegalUIConfigurationException) {
                throw (com.ext.portlet.models.ui.IllegalUIConfigurationException) t;
            }

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof com.liferay.portal.kernel.json.JSONException) {
                throw (com.liferay.portal.kernel.json.JSONException) t;
            }

            if (t instanceof edu.mit.cci.roma.client.comm.ModelNotFoundException) {
                throw (edu.mit.cci.roma.client.comm.ModelNotFoundException) t;
            }

            if (t instanceof edu.mit.cci.roma.client.comm.ScenarioNotFoundException) {
                throw (edu.mit.cci.roma.client.comm.ScenarioNotFoundException) t;
            }

            if (t instanceof java.io.IOException) {
                throw (java.io.IOException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.liferay.portal.kernel.json.JSONObject) ClpSerializer.translateOutput(returnObj);
    }

    @Override
    public void refreshModels()
        throws com.liferay.portal.kernel.exception.SystemException,
            java.io.IOException {
        try {
            _invokableService.invokeMethod(_methodName6,
                _methodParameterTypes6, new Object[] {  });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
                throw (com.liferay.portal.kernel.exception.SystemException) t;
            }

            if (t instanceof java.io.IOException) {
                throw (java.io.IOException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }
}
