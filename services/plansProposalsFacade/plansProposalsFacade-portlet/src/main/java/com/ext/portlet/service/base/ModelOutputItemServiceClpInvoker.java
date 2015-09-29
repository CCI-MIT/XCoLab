package com.ext.portlet.service.base;

import com.ext.portlet.service.ModelOutputItemServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ModelOutputItemServiceClpInvoker {
    private String _methodName616;
    private String[] _methodParameterTypes616;
    private String _methodName617;
    private String[] _methodParameterTypes617;

    public ModelOutputItemServiceClpInvoker() {
        _methodName616 = "getBeanIdentifier";

        _methodParameterTypes616 = new String[] {  };

        _methodName617 = "setBeanIdentifier";

        _methodParameterTypes617 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName616.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes616, parameterTypes)) {
            return ModelOutputItemServiceUtil.getBeanIdentifier();
        }

        if (_methodName617.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes617, parameterTypes)) {
            ModelOutputItemServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
