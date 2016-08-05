package com.ext.portlet.service.base;

import com.ext.portlet.service.ProposalRatingServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProposalRatingServiceClpInvoker {
    private String _methodName448;
    private String[] _methodParameterTypes448;
    private String _methodName449;
    private String[] _methodParameterTypes449;

    public ProposalRatingServiceClpInvoker() {
        _methodName448 = "getBeanIdentifier";

        _methodParameterTypes448 = new String[] {  };

        _methodName449 = "setBeanIdentifier";

        _methodParameterTypes449 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName448.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes448, parameterTypes)) {
            return ProposalRatingServiceUtil.getBeanIdentifier();
        }

        if (_methodName449.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes449, parameterTypes)) {
            ProposalRatingServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
