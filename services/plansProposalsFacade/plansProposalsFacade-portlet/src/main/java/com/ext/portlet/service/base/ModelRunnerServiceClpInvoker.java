package com.ext.portlet.service.base;

import com.ext.portlet.service.ModelRunnerServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ModelRunnerServiceClpInvoker {
    private String _methodName406;
    private String[] _methodParameterTypes406;
    private String _methodName407;
    private String[] _methodParameterTypes407;
    private String _methodName411;
    private String[] _methodParameterTypes411;
    private String _methodName412;
    private String[] _methodParameterTypes412;
    private String _methodName413;
    private String[] _methodParameterTypes413;
    private String _methodName414;
    private String[] _methodParameterTypes414;

    public ModelRunnerServiceClpInvoker() {
        _methodName406 = "getBeanIdentifier";

        _methodParameterTypes406 = new String[] {  };

        _methodName407 = "setBeanIdentifier";

        _methodParameterTypes407 = new String[] { "java.lang.String" };

        _methodName411 = "getScenario";

        _methodParameterTypes411 = new String[] { "long" };

        _methodName412 = "getModel";

        _methodParameterTypes412 = new String[] { "long" };

        _methodName413 = "runModel";

        _methodParameterTypes413 = new String[] { "long", "java.lang.String" };

        _methodName414 = "refreshModels";

        _methodParameterTypes414 = new String[] {  };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName406.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes406, parameterTypes)) {
            return ModelRunnerServiceUtil.getBeanIdentifier();
        }

        if (_methodName407.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes407, parameterTypes)) {
            ModelRunnerServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName411.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes411, parameterTypes)) {
            return ModelRunnerServiceUtil.getScenario(((Long) arguments[0]).longValue());
        }

        if (_methodName412.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes412, parameterTypes)) {
            return ModelRunnerServiceUtil.getModel(((Long) arguments[0]).longValue());
        }

        if (_methodName413.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes413, parameterTypes)) {
            return ModelRunnerServiceUtil.runModel(((Long) arguments[0]).longValue(),
                (java.lang.String) arguments[1]);
        }

        if (_methodName414.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes414, parameterTypes)) {
            ModelRunnerServiceUtil.refreshModels();

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
