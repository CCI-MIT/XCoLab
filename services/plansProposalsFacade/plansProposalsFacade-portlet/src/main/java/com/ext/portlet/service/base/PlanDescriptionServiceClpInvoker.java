package com.ext.portlet.service.base;

import com.ext.portlet.service.PlanDescriptionServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PlanDescriptionServiceClpInvoker {
    private String _methodName498;
    private String[] _methodParameterTypes498;
    private String _methodName499;
    private String[] _methodParameterTypes499;

    public PlanDescriptionServiceClpInvoker() {
        _methodName498 = "getBeanIdentifier";

        _methodParameterTypes498 = new String[] {  };

        _methodName499 = "setBeanIdentifier";

        _methodParameterTypes499 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName498.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes498, parameterTypes)) {
            return PlanDescriptionServiceUtil.getBeanIdentifier();
        }

        if (_methodName499.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes499, parameterTypes)) {
            PlanDescriptionServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
