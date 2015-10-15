package com.ext.portlet.service.base;

import com.ext.portlet.service.MessagingMessageServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MessagingMessageServiceClpInvoker {
    private String _methodName622;
    private String[] _methodParameterTypes622;
    private String _methodName623;
    private String[] _methodParameterTypes623;

    public MessagingMessageServiceClpInvoker() {
        _methodName622 = "getBeanIdentifier";

        _methodParameterTypes622 = new String[] {  };

        _methodName623 = "setBeanIdentifier";

        _methodParameterTypes623 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName622.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes622, parameterTypes)) {
            return MessagingMessageServiceUtil.getBeanIdentifier();
        }

        if (_methodName623.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes623, parameterTypes)) {
            MessagingMessageServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
