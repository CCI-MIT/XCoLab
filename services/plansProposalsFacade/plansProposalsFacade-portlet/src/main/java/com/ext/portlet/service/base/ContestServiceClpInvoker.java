package com.ext.portlet.service.base;

import com.ext.portlet.service.ContestServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ContestServiceClpInvoker {
    private String _methodName586;
    private String[] _methodParameterTypes586;
    private String _methodName587;
    private String[] _methodParameterTypes587;
    private String _methodName592;
    private String[] _methodParameterTypes592;
    private String _methodName593;
    private String[] _methodParameterTypes593;

    public ContestServiceClpInvoker() {
        _methodName586 = "getBeanIdentifier";

        _methodParameterTypes586 = new String[] {  };

        _methodName587 = "setBeanIdentifier";

        _methodParameterTypes587 = new String[] { "java.lang.String" };

        _methodName592 = "getContestsOpenForProposals";

        _methodParameterTypes592 = new String[] {  };

        _methodName593 = "getNumberOfUnreadMessages";

        _methodParameterTypes593 = new String[] {  };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName586.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes586, parameterTypes)) {
            return ContestServiceUtil.getBeanIdentifier();
        }

        if (_methodName587.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes587, parameterTypes)) {
            ContestServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName592.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes592, parameterTypes)) {
            return ContestServiceUtil.getContestsOpenForProposals();
        }

        if (_methodName593.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes593, parameterTypes)) {
            return ContestServiceUtil.getNumberOfUnreadMessages();
        }

        throw new UnsupportedOperationException();
    }
}
