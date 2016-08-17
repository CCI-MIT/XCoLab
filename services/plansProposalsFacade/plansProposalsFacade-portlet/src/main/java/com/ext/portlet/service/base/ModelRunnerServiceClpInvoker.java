package com.ext.portlet.service.base;

import com.ext.portlet.service.ModelRunnerServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ModelRunnerServiceClpInvoker {
    private String _methodName382;
    private String[] _methodParameterTypes382;
    private String _methodName383;
    private String[] _methodParameterTypes383;
    private String _methodName387;
    private String[] _methodParameterTypes387;
    private String _methodName388;
    private String[] _methodParameterTypes388;
    private String _methodName389;
    private String[] _methodParameterTypes389;
    private String _methodName390;
    private String[] _methodParameterTypes390;

    public ModelRunnerServiceClpInvoker() {
        _methodName382 = "getBeanIdentifier";

        _methodParameterTypes382 = new String[] {  };

        _methodName383 = "setBeanIdentifier";

        _methodParameterTypes383 = new String[] { "java.lang.String" };

        _methodName387 = "getScenario";

        _methodParameterTypes387 = new String[] { "long" };

        _methodName388 = "getModel";

        _methodParameterTypes388 = new String[] { "long" };

        _methodName389 = "runModel";

        _methodParameterTypes389 = new String[] { "long", "java.lang.String" };

        _methodName390 = "refreshModels";

        _methodParameterTypes390 = new String[] {  };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName382.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes382, parameterTypes)) {
            return ModelRunnerServiceUtil.getBeanIdentifier();
        }

        if (_methodName383.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes383, parameterTypes)) {
            ModelRunnerServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName387.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes387, parameterTypes)) {
            return ModelRunnerServiceUtil.getScenario(((Long) arguments[0]).longValue());
        }

        if (_methodName388.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes388, parameterTypes)) {
            return ModelRunnerServiceUtil.getModel(((Long) arguments[0]).longValue());
        }

        if (_methodName389.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes389, parameterTypes)) {
            return ModelRunnerServiceUtil.runModel(((Long) arguments[0]).longValue(),
                (java.lang.String) arguments[1]);
        }

        if (_methodName390.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes390, parameterTypes)) {
            ModelRunnerServiceUtil.refreshModels();

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
