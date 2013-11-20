package com.ext.portlet.service.base;

import com.ext.portlet.service.ModelRunnerServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ModelRunnerServiceClpInvoker {
    private String _methodName462;
    private String[] _methodParameterTypes462;
    private String _methodName463;
    private String[] _methodParameterTypes463;
    private String _methodName467;
    private String[] _methodParameterTypes467;
    private String _methodName468;
    private String[] _methodParameterTypes468;
    private String _methodName469;
    private String[] _methodParameterTypes469;

    public ModelRunnerServiceClpInvoker() {
        _methodName462 = "getBeanIdentifier";

        _methodParameterTypes462 = new String[] {  };

        _methodName463 = "setBeanIdentifier";

        _methodParameterTypes463 = new String[] { "java.lang.String" };

        _methodName467 = "getScenario";

        _methodParameterTypes467 = new String[] { "long" };

        _methodName468 = "getModel";

        _methodParameterTypes468 = new String[] { "long" };

        _methodName469 = "runModel";

        _methodParameterTypes469 = new String[] { "long", "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName462.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes462, parameterTypes)) {
            return ModelRunnerServiceUtil.getBeanIdentifier();
        }

        if (_methodName463.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes463, parameterTypes)) {
            ModelRunnerServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName467.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes467, parameterTypes)) {
            return ModelRunnerServiceUtil.getScenario(((Long) arguments[0]).longValue());
        }

        if (_methodName468.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes468, parameterTypes)) {
            return ModelRunnerServiceUtil.getModel(((Long) arguments[0]).longValue());
        }

        if (_methodName469.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes469, parameterTypes)) {
            return ModelRunnerServiceUtil.runModel(((Long) arguments[0]).longValue(),
                (java.lang.String) arguments[1]);
        }

        throw new UnsupportedOperationException();
    }
}
