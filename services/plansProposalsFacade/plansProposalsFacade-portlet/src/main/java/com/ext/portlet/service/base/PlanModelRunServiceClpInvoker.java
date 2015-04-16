package com.ext.portlet.service.base;

import com.ext.portlet.service.PlanModelRunServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PlanModelRunServiceClpInvoker {
    private String _methodName604;
    private String[] _methodParameterTypes604;
    private String _methodName605;
    private String[] _methodParameterTypes605;

    public PlanModelRunServiceClpInvoker() {
        _methodName604 = "getBeanIdentifier";

        _methodParameterTypes604 = new String[] {  };

        _methodName605 = "setBeanIdentifier";

        _methodParameterTypes605 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName604.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes604, parameterTypes)) {
            return PlanModelRunServiceUtil.getBeanIdentifier();
        }

        if (_methodName605.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes605, parameterTypes)) {
            PlanModelRunServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
