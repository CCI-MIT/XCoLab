package com.ext.portlet.service.base;

import com.ext.portlet.service.EmailListServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EmailListServiceClpInvoker {
    private String _methodName382;
    private String[] _methodParameterTypes382;
    private String _methodName383;
    private String[] _methodParameterTypes383;
    private String _methodName388;
    private String[] _methodParameterTypes388;

    public EmailListServiceClpInvoker() {
        _methodName382 = "getBeanIdentifier";

        _methodParameterTypes382 = new String[] {  };

        _methodName383 = "setBeanIdentifier";

        _methodParameterTypes383 = new String[] { "java.lang.String" };

        _methodName388 = "helloWorld";

        _methodParameterTypes388 = new String[] {
                "java.lang.String", "java.lang.String"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName382.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes382, parameterTypes)) {
            return EmailListServiceUtil.getBeanIdentifier();
        }

        if (_methodName383.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes383, parameterTypes)) {
            EmailListServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName388.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes388, parameterTypes)) {
            return EmailListServiceUtil.helloWorld((java.lang.String) arguments[0],
                (java.lang.String) arguments[1]);
        }

        throw new UnsupportedOperationException();
    }
}
