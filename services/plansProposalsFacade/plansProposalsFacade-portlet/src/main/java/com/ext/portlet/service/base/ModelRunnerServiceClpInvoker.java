package com.ext.portlet.service.base;

import com.ext.portlet.service.ModelRunnerServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ModelRunnerServiceClpInvoker {
    private String _methodName610;
    private String[] _methodParameterTypes610;
    private String _methodName611;
    private String[] _methodParameterTypes611;
    private String _methodName615;
    private String[] _methodParameterTypes615;
    private String _methodName616;
    private String[] _methodParameterTypes616;
    private String _methodName617;
    private String[] _methodParameterTypes617;
    private String _methodName618;
    private String[] _methodParameterTypes618;

    public ModelRunnerServiceClpInvoker() {
        _methodName610 = "getBeanIdentifier";

        _methodParameterTypes610 = new String[] {  };

        _methodName611 = "setBeanIdentifier";

        _methodParameterTypes611 = new String[] { "java.lang.String" };

        _methodName615 = "getScenario";

        _methodParameterTypes615 = new String[] { "long" };

        _methodName616 = "getModel";

        _methodParameterTypes616 = new String[] { "long" };

        _methodName617 = "runModel";

        _methodParameterTypes617 = new String[] { "long", "java.lang.String" };

        _methodName618 = "refreshModels";

        _methodParameterTypes618 = new String[] {  };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName610.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes610, parameterTypes)) {
            return ModelRunnerServiceUtil.getBeanIdentifier();
        }

        if (_methodName611.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes611, parameterTypes)) {
            ModelRunnerServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName615.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes615, parameterTypes)) {
            return ModelRunnerServiceUtil.getScenario(((Long) arguments[0]).longValue());
        }

        if (_methodName616.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes616, parameterTypes)) {
            return ModelRunnerServiceUtil.getModel(((Long) arguments[0]).longValue());
        }

        if (_methodName617.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes617, parameterTypes)) {
            return ModelRunnerServiceUtil.runModel(((Long) arguments[0]).longValue(),
                (java.lang.String) arguments[1]);
        }

        if (_methodName618.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes618, parameterTypes)) {
            ModelRunnerServiceUtil.refreshModels();

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
