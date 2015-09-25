package com.ext.portlet.service.base;

import com.ext.portlet.service.ContestServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ContestServiceClpInvoker {
    private String _methodName616;
    private String[] _methodParameterTypes616;
    private String _methodName617;
    private String[] _methodParameterTypes617;
    private String _methodName622;
    private String[] _methodParameterTypes622;
    private String _methodName623;
    private String[] _methodParameterTypes623;

    public ContestServiceClpInvoker() {
        _methodName616 = "getBeanIdentifier";

        _methodParameterTypes616 = new String[] {  };

        _methodName617 = "setBeanIdentifier";

        _methodParameterTypes617 = new String[] { "java.lang.String" };

        _methodName622 = "getContestsOpenForProposals";

        _methodParameterTypes622 = new String[] {  };

        _methodName623 = "getNumberOfUnreadMessages";

        _methodParameterTypes623 = new String[] {  };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName616.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes616, parameterTypes)) {
            return ContestServiceUtil.getBeanIdentifier();
        }

        if (_methodName617.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes617, parameterTypes)) {
            ContestServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName622.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes622, parameterTypes)) {
            return ContestServiceUtil.getContestsOpenForProposals();
        }

        if (_methodName623.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes623, parameterTypes)) {
            return ContestServiceUtil.getNumberOfUnreadMessages();
        }

        throw new UnsupportedOperationException();
    }
}
