package com.ext.portlet.service.base;

import com.ext.portlet.service.EmailListServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EmailListServiceClpInvoker {
    private String _methodName448;
    private String[] _methodParameterTypes448;
    private String _methodName449;
    private String[] _methodParameterTypes449;
    private String _methodName454;
    private String[] _methodParameterTypes454;

    public EmailListServiceClpInvoker() {
        _methodName448 = "getBeanIdentifier";

        _methodParameterTypes448 = new String[] {  };

        _methodName449 = "setBeanIdentifier";

        _methodParameterTypes449 = new String[] { "java.lang.String" };

        _methodName454 = "helloWorld";

        _methodParameterTypes454 = new String[] {
                "java.lang.String", "java.lang.String"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName448.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes448, parameterTypes)) {
            return EmailListServiceUtil.getBeanIdentifier();
        }

        if (_methodName449.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes449, parameterTypes)) {
            EmailListServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName454.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes454, parameterTypes)) {
            return EmailListServiceUtil.helloWorld((java.lang.String) arguments[0],
                (java.lang.String) arguments[1]);
        }

        throw new UnsupportedOperationException();
    }
}
