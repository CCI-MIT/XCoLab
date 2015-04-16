package com.ext.portlet.service.base;

import com.ext.portlet.service.ModelOutputItemServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ModelOutputItemServiceClpInvoker {
    private String _methodName604;
    private String[] _methodParameterTypes604;
    private String _methodName605;
    private String[] _methodParameterTypes605;

    public ModelOutputItemServiceClpInvoker() {
        _methodName604 = "getBeanIdentifier";

        _methodParameterTypes604 = new String[] {  };

        _methodName605 = "setBeanIdentifier";

        _methodParameterTypes605 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName604.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes604, parameterTypes)) {
            return ModelOutputItemServiceUtil.getBeanIdentifier();
        }

        if (_methodName605.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes605, parameterTypes)) {
            ModelOutputItemServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
