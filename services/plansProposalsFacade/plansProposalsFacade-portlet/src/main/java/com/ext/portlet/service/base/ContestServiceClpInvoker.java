package com.ext.portlet.service.base;

import com.ext.portlet.service.ContestServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ContestServiceClpInvoker {
    private String _methodName572;
    private String[] _methodParameterTypes572;
    private String _methodName573;
    private String[] _methodParameterTypes573;
    private String _methodName578;
    private String[] _methodParameterTypes578;
    private String _methodName579;
    private String[] _methodParameterTypes579;

    public ContestServiceClpInvoker() {
        _methodName572 = "getBeanIdentifier";

        _methodParameterTypes572 = new String[] {  };

        _methodName573 = "setBeanIdentifier";

        _methodParameterTypes573 = new String[] { "java.lang.String" };

        _methodName578 = "getContestsOpenForProposals";

        _methodParameterTypes578 = new String[] {  };

        _methodName579 = "getNumberOfUnreadMessages";

        _methodParameterTypes579 = new String[] {  };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName572.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes572, parameterTypes)) {
            return ContestServiceUtil.getBeanIdentifier();
        }

        if (_methodName573.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes573, parameterTypes)) {
            ContestServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName578.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes578, parameterTypes)) {
            return ContestServiceUtil.getContestsOpenForProposals();
        }

        if (_methodName579.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes579, parameterTypes)) {
            return ContestServiceUtil.getNumberOfUnreadMessages();
        }

        throw new UnsupportedOperationException();
    }
}
