package com.ext.portlet.service.base;

import com.ext.portlet.service.ModelRunnerServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ModelRunnerServiceClpInvoker {
    private String _methodName448;
    private String[] _methodParameterTypes448;
    private String _methodName449;
    private String[] _methodParameterTypes449;
    private String _methodName453;
    private String[] _methodParameterTypes453;
    private String _methodName454;
    private String[] _methodParameterTypes454;
    private String _methodName455;
    private String[] _methodParameterTypes455;
    private String _methodName456;
    private String[] _methodParameterTypes456;

    public ModelRunnerServiceClpInvoker() {
        _methodName448 = "getBeanIdentifier";

        _methodParameterTypes448 = new String[] {  };

        _methodName449 = "setBeanIdentifier";

        _methodParameterTypes449 = new String[] { "java.lang.String" };

        _methodName453 = "getScenario";

        _methodParameterTypes453 = new String[] { "long" };

        _methodName454 = "getModel";

        _methodParameterTypes454 = new String[] { "long" };

        _methodName455 = "runModel";

        _methodParameterTypes455 = new String[] { "long", "java.lang.String" };

        _methodName456 = "refreshModels";

        _methodParameterTypes456 = new String[] {  };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName448.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes448, parameterTypes)) {
            return ModelRunnerServiceUtil.getBeanIdentifier();
        }

        if (_methodName449.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes449, parameterTypes)) {
            ModelRunnerServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName453.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes453, parameterTypes)) {
            return ModelRunnerServiceUtil.getScenario(((Long) arguments[0]).longValue());
        }

        if (_methodName454.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes454, parameterTypes)) {
            return ModelRunnerServiceUtil.getModel(((Long) arguments[0]).longValue());
        }

        if (_methodName455.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes455, parameterTypes)) {
            return ModelRunnerServiceUtil.runModel(((Long) arguments[0]).longValue(),
                (java.lang.String) arguments[1]);
        }

        if (_methodName456.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes456, parameterTypes)) {
            ModelRunnerServiceUtil.refreshModels();

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
