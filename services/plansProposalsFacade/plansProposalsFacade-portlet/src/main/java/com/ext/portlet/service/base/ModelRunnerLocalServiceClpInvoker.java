package com.ext.portlet.service.base;

import com.ext.portlet.service.ModelRunnerLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ModelRunnerLocalServiceClpInvoker {
    private String _methodName488;
    private String[] _methodParameterTypes488;
    private String _methodName489;
    private String[] _methodParameterTypes489;

    public ModelRunnerLocalServiceClpInvoker() {
        _methodName488 = "getBeanIdentifier";

        _methodParameterTypes488 = new String[] {  };

        _methodName489 = "setBeanIdentifier";

        _methodParameterTypes489 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName488.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes488, parameterTypes)) {
            return ModelRunnerLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName489.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes489, parameterTypes)) {
            ModelRunnerLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
