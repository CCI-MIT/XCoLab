package com.ext.portlet.service.base;

import com.ext.portlet.service.ContestServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ContestServiceClpInvoker {
    private String _methodName610;
    private String[] _methodParameterTypes610;
    private String _methodName611;
    private String[] _methodParameterTypes611;
    private String _methodName616;
    private String[] _methodParameterTypes616;
    private String _methodName617;
    private String[] _methodParameterTypes617;

    public ContestServiceClpInvoker() {
        _methodName610 = "getBeanIdentifier";

        _methodParameterTypes610 = new String[] {  };

        _methodName611 = "setBeanIdentifier";

        _methodParameterTypes611 = new String[] { "java.lang.String" };

        _methodName616 = "getContestsOpenForProposals";

        _methodParameterTypes616 = new String[] {  };

        _methodName617 = "getNumberOfUnreadMessages";

        _methodParameterTypes617 = new String[] {  };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName610.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes610, parameterTypes)) {
            return ContestServiceUtil.getBeanIdentifier();
        }

        if (_methodName611.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes611, parameterTypes)) {
            ContestServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName616.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes616, parameterTypes)) {
            return ContestServiceUtil.getContestsOpenForProposals();
        }

        if (_methodName617.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes617, parameterTypes)) {
            return ContestServiceUtil.getNumberOfUnreadMessages();
        }

        throw new UnsupportedOperationException();
    }
}
