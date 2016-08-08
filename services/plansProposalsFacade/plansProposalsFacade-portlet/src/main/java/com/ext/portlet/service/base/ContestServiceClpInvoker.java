package com.ext.portlet.service.base;

import com.ext.portlet.service.ContestServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ContestServiceClpInvoker {
    private String _methodName406;
    private String[] _methodParameterTypes406;
    private String _methodName407;
    private String[] _methodParameterTypes407;
    private String _methodName412;
    private String[] _methodParameterTypes412;

    public ContestServiceClpInvoker() {
        _methodName406 = "getBeanIdentifier";

        _methodParameterTypes406 = new String[] {  };

        _methodName407 = "setBeanIdentifier";

        _methodParameterTypes407 = new String[] { "java.lang.String" };

        _methodName412 = "getContestsOpenForProposals";

        _methodParameterTypes412 = new String[] {  };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName406.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes406, parameterTypes)) {
            return ContestServiceUtil.getBeanIdentifier();
        }

        if (_methodName407.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes407, parameterTypes)) {
            ContestServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName412.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes412, parameterTypes)) {
            return ContestServiceUtil.getContestsOpenForProposals();
        }

        throw new UnsupportedOperationException();
    }
}
