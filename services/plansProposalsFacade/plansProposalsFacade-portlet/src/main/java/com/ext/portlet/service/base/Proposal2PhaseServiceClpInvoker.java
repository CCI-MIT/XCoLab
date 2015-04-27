package com.ext.portlet.service.base;

import com.ext.portlet.service.Proposal2PhaseServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class Proposal2PhaseServiceClpInvoker {
    private String _methodName610;
    private String[] _methodParameterTypes610;
    private String _methodName611;
    private String[] _methodParameterTypes611;

    public Proposal2PhaseServiceClpInvoker() {
        _methodName610 = "getBeanIdentifier";

        _methodParameterTypes610 = new String[] {  };

        _methodName611 = "setBeanIdentifier";

        _methodParameterTypes611 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName610.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes610, parameterTypes)) {
            return Proposal2PhaseServiceUtil.getBeanIdentifier();
        }

        if (_methodName611.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes611, parameterTypes)) {
            Proposal2PhaseServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
