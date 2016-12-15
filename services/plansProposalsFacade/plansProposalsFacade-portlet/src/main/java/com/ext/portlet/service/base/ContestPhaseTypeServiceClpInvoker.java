package com.ext.portlet.service.base;

import com.ext.portlet.service.ContestPhaseTypeServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ContestPhaseTypeServiceClpInvoker {
    private String _methodName246;
    private String[] _methodParameterTypes246;
    private String _methodName247;
    private String[] _methodParameterTypes247;

    public ContestPhaseTypeServiceClpInvoker() {
        _methodName246 = "getBeanIdentifier";

        _methodParameterTypes246 = new String[] {  };

        _methodName247 = "setBeanIdentifier";

        _methodParameterTypes247 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName246.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes246, parameterTypes)) {
            return ContestPhaseTypeServiceUtil.getBeanIdentifier();
        }

        if (_methodName247.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes247, parameterTypes)) {
            ContestPhaseTypeServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
