package com.ext.portlet.service.base;

import com.ext.portlet.service.ModelRunnerServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ModelRunnerServiceClpInvoker {
    private String _methodName586;
    private String[] _methodParameterTypes586;
    private String _methodName587;
    private String[] _methodParameterTypes587;
    private String _methodName591;
    private String[] _methodParameterTypes591;
    private String _methodName592;
    private String[] _methodParameterTypes592;
    private String _methodName593;
    private String[] _methodParameterTypes593;
    private String _methodName594;
    private String[] _methodParameterTypes594;

    public ModelRunnerServiceClpInvoker() {
        _methodName586 = "getBeanIdentifier";

        _methodParameterTypes586 = new String[] {  };

        _methodName587 = "setBeanIdentifier";

        _methodParameterTypes587 = new String[] { "java.lang.String" };

        _methodName591 = "getScenario";

        _methodParameterTypes591 = new String[] { "long" };

        _methodName592 = "getModel";

        _methodParameterTypes592 = new String[] { "long" };

        _methodName593 = "runModel";

        _methodParameterTypes593 = new String[] { "long", "java.lang.String" };

        _methodName594 = "refreshModels";

        _methodParameterTypes594 = new String[] {  };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName586.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes586, parameterTypes)) {
            return ModelRunnerServiceUtil.getBeanIdentifier();
        }

        if (_methodName587.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes587, parameterTypes)) {
            ModelRunnerServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName591.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes591, parameterTypes)) {
            return ModelRunnerServiceUtil.getScenario(((Long) arguments[0]).longValue());
        }

        if (_methodName592.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes592, parameterTypes)) {
            return ModelRunnerServiceUtil.getModel(((Long) arguments[0]).longValue());
        }

        if (_methodName593.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes593, parameterTypes)) {
            return ModelRunnerServiceUtil.runModel(((Long) arguments[0]).longValue(),
                (java.lang.String) arguments[1]);
        }

        if (_methodName594.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes594, parameterTypes)) {
            ModelRunnerServiceUtil.refreshModels();

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
