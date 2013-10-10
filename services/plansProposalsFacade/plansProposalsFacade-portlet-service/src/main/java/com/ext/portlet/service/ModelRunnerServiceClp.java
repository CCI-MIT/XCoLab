package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;


public class ModelRunnerServiceClp implements ModelRunnerService {
    private ClassLoaderProxy _classLoaderProxy;
    private MethodKey _getScenarioMethodKey0;
    private MethodKey _getModelMethodKey1;
    private MethodKey _runModelMethodKey2;

    public ModelRunnerServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;

        _getScenarioMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
                "getScenario", long.class);

        _getModelMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
                "getModel", long.class);

        _runModelMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
                "runModel", long.class, java.lang.String.class);
    }

    public com.liferay.portal.kernel.json.JSONObject getScenario(
        long scenarioId) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getScenarioMethodKey0,
                scenarioId);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.liferay.portal.kernel.json.JSONObject) ClpSerializer.translateOutput(returnObj);
    }

    public com.liferay.portal.kernel.json.JSONObject getModel(long modelId) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_getModelMethodKey1,
                modelId);

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.liferay.portal.kernel.json.JSONObject) ClpSerializer.translateOutput(returnObj);
    }

    public com.liferay.portal.kernel.json.JSONObject runModel(long modelId,
        java.lang.String inputs) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_runModelMethodKey2,
                modelId, ClpSerializer.translateInput(inputs));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (com.liferay.portal.kernel.json.JSONObject) ClpSerializer.translateOutput(returnObj);
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
