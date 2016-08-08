package com.ext.portlet.service.base;

import com.ext.portlet.service.ImpactIterationServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ImpactIterationServiceClpInvoker {
    private String _methodName406;
    private String[] _methodParameterTypes406;
    private String _methodName407;
    private String[] _methodParameterTypes407;

    public ImpactIterationServiceClpInvoker() {
        _methodName406 = "getBeanIdentifier";

        _methodParameterTypes406 = new String[] {  };

        _methodName407 = "setBeanIdentifier";

        _methodParameterTypes407 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName406.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes406, parameterTypes)) {
            return ImpactIterationServiceUtil.getBeanIdentifier();
        }

        if (_methodName407.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes407, parameterTypes)) {
            ImpactIterationServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
