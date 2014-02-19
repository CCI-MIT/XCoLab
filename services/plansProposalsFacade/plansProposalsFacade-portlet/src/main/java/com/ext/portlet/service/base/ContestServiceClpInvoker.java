package com.ext.portlet.service.base;

import com.ext.portlet.service.ContestServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ContestServiceClpInvoker {
    private String _methodName468;
    private String[] _methodParameterTypes468;
    private String _methodName469;
    private String[] _methodParameterTypes469;
    private String _methodName474;
    private String[] _methodParameterTypes474;

    public ContestServiceClpInvoker() {
        _methodName468 = "getBeanIdentifier";

        _methodParameterTypes468 = new String[] {  };

        _methodName469 = "setBeanIdentifier";

        _methodParameterTypes469 = new String[] { "java.lang.String" };

        _methodName474 = "getContestsOpenForProposals";

        _methodParameterTypes474 = new String[] {  };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName468.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes468, parameterTypes)) {
            return ContestServiceUtil.getBeanIdentifier();
        }

        if (_methodName469.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes469, parameterTypes)) {
            ContestServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName474.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes474, parameterTypes)) {
            return ContestServiceUtil.getContestsOpenForProposals();
        }

        throw new UnsupportedOperationException();
    }
}
