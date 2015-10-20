package com.ext.portlet.service.base;

import com.ext.portlet.service.PlansFilterServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PlansFilterServiceClpInvoker {
    private String _methodName628;
    private String[] _methodParameterTypes628;
    private String _methodName629;
    private String[] _methodParameterTypes629;

    public PlansFilterServiceClpInvoker() {
        _methodName628 = "getBeanIdentifier";

        _methodParameterTypes628 = new String[] {  };

        _methodName629 = "setBeanIdentifier";

        _methodParameterTypes629 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName628.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes628, parameterTypes)) {
            return PlansFilterServiceUtil.getBeanIdentifier();
        }

        if (_methodName629.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes629, parameterTypes)) {
            PlansFilterServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
