package com.ext.portlet.service.base;

import com.ext.portlet.service.EmailListServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EmailListServiceClpInvoker {
    private String _methodName462;
    private String[] _methodParameterTypes462;
    private String _methodName463;
    private String[] _methodParameterTypes463;
    private String _methodName468;
    private String[] _methodParameterTypes468;

    public EmailListServiceClpInvoker() {
        _methodName462 = "getBeanIdentifier";

        _methodParameterTypes462 = new String[] {  };

        _methodName463 = "setBeanIdentifier";

        _methodParameterTypes463 = new String[] { "java.lang.String" };

        _methodName468 = "helloWorld";

        _methodParameterTypes468 = new String[] {
                "java.lang.String", "java.lang.String"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName462.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes462, parameterTypes)) {
            return EmailListServiceUtil.getBeanIdentifier();
        }

        if (_methodName463.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes463, parameterTypes)) {
            EmailListServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName468.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes468, parameterTypes)) {
            return EmailListServiceUtil.helloWorld((java.lang.String) arguments[0],
                (java.lang.String) arguments[1]);
        }

        throw new UnsupportedOperationException();
    }
}
