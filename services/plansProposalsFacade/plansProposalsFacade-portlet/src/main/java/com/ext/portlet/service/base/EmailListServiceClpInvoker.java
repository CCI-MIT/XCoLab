package com.ext.portlet.service.base;

import com.ext.portlet.service.EmailListServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EmailListServiceClpInvoker {
    private String _methodName472;
    private String[] _methodParameterTypes472;
    private String _methodName473;
    private String[] _methodParameterTypes473;
    private String _methodName478;
    private String[] _methodParameterTypes478;

    public EmailListServiceClpInvoker() {
        _methodName472 = "getBeanIdentifier";

        _methodParameterTypes472 = new String[] {  };

        _methodName473 = "setBeanIdentifier";

        _methodParameterTypes473 = new String[] { "java.lang.String" };

        _methodName478 = "helloWorld";

        _methodParameterTypes478 = new String[] {
                "java.lang.String", "java.lang.String"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName472.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes472, parameterTypes)) {
            return EmailListServiceUtil.getBeanIdentifier();
        }

        if (_methodName473.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes473, parameterTypes)) {
            EmailListServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName478.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes478, parameterTypes)) {
            return EmailListServiceUtil.helloWorld((java.lang.String) arguments[0],
                (java.lang.String) arguments[1]);
        }

        throw new UnsupportedOperationException();
    }
}
