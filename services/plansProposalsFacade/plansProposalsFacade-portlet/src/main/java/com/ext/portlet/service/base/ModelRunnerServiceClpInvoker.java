package com.ext.portlet.service.base;

import com.ext.portlet.service.ModelRunnerServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ModelRunnerServiceClpInvoker {
    private String _methodName572;
    private String[] _methodParameterTypes572;
    private String _methodName573;
    private String[] _methodParameterTypes573;
    private String _methodName577;
    private String[] _methodParameterTypes577;
    private String _methodName578;
    private String[] _methodParameterTypes578;
    private String _methodName579;
    private String[] _methodParameterTypes579;
    private String _methodName580;
    private String[] _methodParameterTypes580;

    public ModelRunnerServiceClpInvoker() {
        _methodName572 = "getBeanIdentifier";

        _methodParameterTypes572 = new String[] {  };

        _methodName573 = "setBeanIdentifier";

        _methodParameterTypes573 = new String[] { "java.lang.String" };

        _methodName577 = "getScenario";

        _methodParameterTypes577 = new String[] { "long" };

        _methodName578 = "getModel";

        _methodParameterTypes578 = new String[] { "long" };

        _methodName579 = "runModel";

        _methodParameterTypes579 = new String[] { "long", "java.lang.String" };

        _methodName580 = "refreshModels";

        _methodParameterTypes580 = new String[] {  };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName572.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes572, parameterTypes)) {
            return ModelRunnerServiceUtil.getBeanIdentifier();
        }

        if (_methodName573.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes573, parameterTypes)) {
            ModelRunnerServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName577.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes577, parameterTypes)) {
            return ModelRunnerServiceUtil.getScenario(((Long) arguments[0]).longValue());
        }

        if (_methodName578.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes578, parameterTypes)) {
            return ModelRunnerServiceUtil.getModel(((Long) arguments[0]).longValue());
        }

        if (_methodName579.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes579, parameterTypes)) {
            return ModelRunnerServiceUtil.runModel(((Long) arguments[0]).longValue(),
                (java.lang.String) arguments[1]);
        }

        if (_methodName580.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes580, parameterTypes)) {
            ModelRunnerServiceUtil.refreshModels();

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
