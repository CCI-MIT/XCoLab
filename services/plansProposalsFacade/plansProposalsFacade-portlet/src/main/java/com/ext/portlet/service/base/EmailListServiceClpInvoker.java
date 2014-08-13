package com.ext.portlet.service.base;

import com.ext.portlet.service.EmailListServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EmailListServiceClpInvoker {
    private String _methodName536;
    private String[] _methodParameterTypes536;
    private String _methodName537;
    private String[] _methodParameterTypes537;
    private String _methodName542;
    private String[] _methodParameterTypes542;

    public EmailListServiceClpInvoker() {
        _methodName536 = "getBeanIdentifier";

        _methodParameterTypes536 = new String[] {  };

        _methodName537 = "setBeanIdentifier";

        _methodParameterTypes537 = new String[] { "java.lang.String" };

        _methodName542 = "helloWorld";

        _methodParameterTypes542 = new String[] {
                "java.lang.String", "java.lang.String"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName536.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes536, parameterTypes)) {
            return EmailListServiceUtil.getBeanIdentifier();
        }

        if (_methodName537.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes537, parameterTypes)) {
            EmailListServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName542.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes542, parameterTypes)) {
            return EmailListServiceUtil.helloWorld((java.lang.String) arguments[0],
                (java.lang.String) arguments[1]);
        }

        throw new UnsupportedOperationException();
    }
}
