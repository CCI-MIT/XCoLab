package com.ext.portlet.service.base;

import com.ext.portlet.service.MessagingRedirectLinkServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MessagingRedirectLinkServiceClpInvoker {
    private String _methodName472;
    private String[] _methodParameterTypes472;
    private String _methodName473;
    private String[] _methodParameterTypes473;

    public MessagingRedirectLinkServiceClpInvoker() {
        _methodName472 = "getBeanIdentifier";

        _methodParameterTypes472 = new String[] {  };

        _methodName473 = "setBeanIdentifier";

        _methodParameterTypes473 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName472.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes472, parameterTypes)) {
            return MessagingRedirectLinkServiceUtil.getBeanIdentifier();
        }

        if (_methodName473.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes473, parameterTypes)) {
            MessagingRedirectLinkServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
