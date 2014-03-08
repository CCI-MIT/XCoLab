package com.ext.portlet.service.base;

import com.ext.portlet.service.ModelRunnerServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ModelRunnerServiceClpInvoker {
    private String _methodName486;
    private String[] _methodParameterTypes486;
    private String _methodName487;
    private String[] _methodParameterTypes487;
    private String _methodName491;
    private String[] _methodParameterTypes491;
    private String _methodName492;
    private String[] _methodParameterTypes492;
    private String _methodName493;
    private String[] _methodParameterTypes493;

    public ModelRunnerServiceClpInvoker() {
        _methodName486 = "getBeanIdentifier";

        _methodParameterTypes486 = new String[] {  };

        _methodName487 = "setBeanIdentifier";

        _methodParameterTypes487 = new String[] { "java.lang.String" };

        _methodName491 = "getScenario";

        _methodParameterTypes491 = new String[] { "long" };

        _methodName492 = "getModel";

        _methodParameterTypes492 = new String[] { "long" };

        _methodName493 = "runModel";

        _methodParameterTypes493 = new String[] { "long", "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName486.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes486, parameterTypes)) {
            return ModelRunnerServiceUtil.getBeanIdentifier();
        }

        if (_methodName487.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes487, parameterTypes)) {
            ModelRunnerServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName491.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes491, parameterTypes)) {
            return ModelRunnerServiceUtil.getScenario(((Long) arguments[0]).longValue());
        }

        if (_methodName492.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes492, parameterTypes)) {
            return ModelRunnerServiceUtil.getModel(((Long) arguments[0]).longValue());
        }

        if (_methodName493.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes493, parameterTypes)) {
            return ModelRunnerServiceUtil.runModel(((Long) arguments[0]).longValue(),
                (java.lang.String) arguments[1]);
        }

        throw new UnsupportedOperationException();
    }
}
