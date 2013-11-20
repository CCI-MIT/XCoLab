package com.ext.portlet.service.base;

import com.ext.portlet.service.PlanRelatedServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PlanRelatedServiceClpInvoker {
    private String _methodName462;
    private String[] _methodParameterTypes462;
    private String _methodName463;
    private String[] _methodParameterTypes463;

    public PlanRelatedServiceClpInvoker() {
        _methodName462 = "getBeanIdentifier";

        _methodParameterTypes462 = new String[] {  };

        _methodName463 = "setBeanIdentifier";

        _methodParameterTypes463 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName462.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes462, parameterTypes)) {
            return PlanRelatedServiceUtil.getBeanIdentifier();
        }

        if (_methodName463.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes463, parameterTypes)) {
            PlanRelatedServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
