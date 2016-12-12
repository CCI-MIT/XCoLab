package com.ext.portlet.service.base;

import com.ext.portlet.service.ModelRunnerServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ModelRunnerServiceClpInvoker {
    private String _methodName334;
    private String[] _methodParameterTypes334;
    private String _methodName335;
    private String[] _methodParameterTypes335;
    private String _methodName339;
    private String[] _methodParameterTypes339;
    private String _methodName340;
    private String[] _methodParameterTypes340;
    private String _methodName341;
    private String[] _methodParameterTypes341;
    private String _methodName342;
    private String[] _methodParameterTypes342;

    public ModelRunnerServiceClpInvoker() {
        _methodName334 = "getBeanIdentifier";

        _methodParameterTypes334 = new String[] {  };

        _methodName335 = "setBeanIdentifier";

        _methodParameterTypes335 = new String[] { "java.lang.String" };

        _methodName339 = "getScenario";

        _methodParameterTypes339 = new String[] { "long" };

        _methodName340 = "getModel";

        _methodParameterTypes340 = new String[] { "long" };

        _methodName341 = "runModel";

        _methodParameterTypes341 = new String[] { "long", "java.lang.String" };

        _methodName342 = "refreshModels";

        _methodParameterTypes342 = new String[] {  };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName334.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes334, parameterTypes)) {
            return ModelRunnerServiceUtil.getBeanIdentifier();
        }

        if (_methodName335.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes335, parameterTypes)) {
            ModelRunnerServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName339.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes339, parameterTypes)) {
            return ModelRunnerServiceUtil.getScenario(((Long) arguments[0]).longValue());
        }

        if (_methodName340.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes340, parameterTypes)) {
            return ModelRunnerServiceUtil.getModel(((Long) arguments[0]).longValue());
        }

        if (_methodName341.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes341, parameterTypes)) {
            return ModelRunnerServiceUtil.runModel(((Long) arguments[0]).longValue(),
                (java.lang.String) arguments[1]);
        }

        if (_methodName342.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes342, parameterTypes)) {
            ModelRunnerServiceUtil.refreshModels();

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
