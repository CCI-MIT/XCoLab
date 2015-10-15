package com.ext.portlet.service.base;

import com.ext.portlet.service.ModelRunnerServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ModelRunnerServiceClpInvoker {
    private String _methodName622;
    private String[] _methodParameterTypes622;
    private String _methodName623;
    private String[] _methodParameterTypes623;
    private String _methodName627;
    private String[] _methodParameterTypes627;
    private String _methodName628;
    private String[] _methodParameterTypes628;
    private String _methodName629;
    private String[] _methodParameterTypes629;
    private String _methodName630;
    private String[] _methodParameterTypes630;

    public ModelRunnerServiceClpInvoker() {
        _methodName622 = "getBeanIdentifier";

        _methodParameterTypes622 = new String[] {  };

        _methodName623 = "setBeanIdentifier";

        _methodParameterTypes623 = new String[] { "java.lang.String" };

        _methodName627 = "getScenario";

        _methodParameterTypes627 = new String[] { "long" };

        _methodName628 = "getModel";

        _methodParameterTypes628 = new String[] { "long" };

        _methodName629 = "runModel";

        _methodParameterTypes629 = new String[] { "long", "java.lang.String" };

        _methodName630 = "refreshModels";

        _methodParameterTypes630 = new String[] {  };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName622.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes622, parameterTypes)) {
            return ModelRunnerServiceUtil.getBeanIdentifier();
        }

        if (_methodName623.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes623, parameterTypes)) {
            ModelRunnerServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName627.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes627, parameterTypes)) {
            return ModelRunnerServiceUtil.getScenario(((Long) arguments[0]).longValue());
        }

        if (_methodName628.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes628, parameterTypes)) {
            return ModelRunnerServiceUtil.getModel(((Long) arguments[0]).longValue());
        }

        if (_methodName629.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes629, parameterTypes)) {
            return ModelRunnerServiceUtil.runModel(((Long) arguments[0]).longValue(),
                (java.lang.String) arguments[1]);
        }

        if (_methodName630.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes630, parameterTypes)) {
            ModelRunnerServiceUtil.refreshModels();

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
