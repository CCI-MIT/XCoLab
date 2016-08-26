package com.ext.portlet.service.base;

import com.ext.portlet.service.ImpactIterationServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ImpactIterationServiceClpInvoker {
    private String _methodName382;
    private String[] _methodParameterTypes382;
    private String _methodName383;
    private String[] _methodParameterTypes383;

    public ImpactIterationServiceClpInvoker() {
        _methodName382 = "getBeanIdentifier";

        _methodParameterTypes382 = new String[] {  };

        _methodName383 = "setBeanIdentifier";

        _methodParameterTypes383 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName382.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes382, parameterTypes)) {
            return ImpactIterationServiceUtil.getBeanIdentifier();
        }

        if (_methodName383.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes383, parameterTypes)) {
            ImpactIterationServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
