package com.ext.portlet.service.base;

import com.ext.portlet.service.ModelRunnerLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ModelRunnerLocalServiceClpInvoker {
    private String _methodName560;
    private String[] _methodParameterTypes560;
    private String _methodName561;
    private String[] _methodParameterTypes561;

    public ModelRunnerLocalServiceClpInvoker() {
        _methodName560 = "getBeanIdentifier";

        _methodParameterTypes560 = new String[] {  };

        _methodName561 = "setBeanIdentifier";

        _methodParameterTypes561 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName560.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes560, parameterTypes)) {
            return ModelRunnerLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName561.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes561, parameterTypes)) {
            ModelRunnerLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
