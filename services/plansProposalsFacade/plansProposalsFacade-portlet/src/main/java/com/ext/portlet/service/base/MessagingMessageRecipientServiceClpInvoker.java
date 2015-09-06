package com.ext.portlet.service.base;

import com.ext.portlet.service.MessagingMessageRecipientServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MessagingMessageRecipientServiceClpInvoker {
    private String _methodName616;
    private String[] _methodParameterTypes616;
    private String _methodName617;
    private String[] _methodParameterTypes617;

    public MessagingMessageRecipientServiceClpInvoker() {
        _methodName616 = "getBeanIdentifier";

        _methodParameterTypes616 = new String[] {  };

        _methodName617 = "setBeanIdentifier";

        _methodParameterTypes617 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName616.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes616, parameterTypes)) {
            return MessagingMessageRecipientServiceUtil.getBeanIdentifier();
        }

        if (_methodName617.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes617, parameterTypes)) {
            MessagingMessageRecipientServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
