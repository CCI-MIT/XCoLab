package com.ext.portlet.service.base;

import com.ext.portlet.service.MessagingMessageServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MessagingMessageServiceClpInvoker {
    private String _methodName496;
    private String[] _methodParameterTypes496;
    private String _methodName497;
    private String[] _methodParameterTypes497;

    public MessagingMessageServiceClpInvoker() {
        _methodName496 = "getBeanIdentifier";

        _methodParameterTypes496 = new String[] {  };

        _methodName497 = "setBeanIdentifier";

        _methodParameterTypes497 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName496.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes496, parameterTypes)) {
            return MessagingMessageServiceUtil.getBeanIdentifier();
        }

        if (_methodName497.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes497, parameterTypes)) {
            MessagingMessageServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
