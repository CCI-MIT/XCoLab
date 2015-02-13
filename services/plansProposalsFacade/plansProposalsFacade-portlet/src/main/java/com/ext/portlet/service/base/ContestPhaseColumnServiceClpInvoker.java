package com.ext.portlet.service.base;

import com.ext.portlet.service.ContestPhaseColumnServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ContestPhaseColumnServiceClpInvoker {
    private String _methodName572;
    private String[] _methodParameterTypes572;
    private String _methodName573;
    private String[] _methodParameterTypes573;

    public ContestPhaseColumnServiceClpInvoker() {
        _methodName572 = "getBeanIdentifier";

        _methodParameterTypes572 = new String[] {  };

        _methodName573 = "setBeanIdentifier";

        _methodParameterTypes573 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName572.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes572, parameterTypes)) {
            return ContestPhaseColumnServiceUtil.getBeanIdentifier();
        }

        if (_methodName573.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes573, parameterTypes)) {
            ContestPhaseColumnServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
