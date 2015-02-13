package com.ext.portlet.service.base;

import com.ext.portlet.service.EmailListServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EmailListServiceClpInvoker {
    private String _methodName572;
    private String[] _methodParameterTypes572;
    private String _methodName573;
    private String[] _methodParameterTypes573;
    private String _methodName578;
    private String[] _methodParameterTypes578;

    public EmailListServiceClpInvoker() {
        _methodName572 = "getBeanIdentifier";

        _methodParameterTypes572 = new String[] {  };

        _methodName573 = "setBeanIdentifier";

        _methodParameterTypes573 = new String[] { "java.lang.String" };

        _methodName578 = "helloWorld";

        _methodParameterTypes578 = new String[] {
                "java.lang.String", "java.lang.String"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName572.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes572, parameterTypes)) {
            return EmailListServiceUtil.getBeanIdentifier();
        }

        if (_methodName573.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes573, parameterTypes)) {
            EmailListServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName578.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes578, parameterTypes)) {
            return EmailListServiceUtil.helloWorld((java.lang.String) arguments[0],
                (java.lang.String) arguments[1]);
        }

        throw new UnsupportedOperationException();
    }
}
