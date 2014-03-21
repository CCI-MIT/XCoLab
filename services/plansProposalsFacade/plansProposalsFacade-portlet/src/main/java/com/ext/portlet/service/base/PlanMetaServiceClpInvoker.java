package com.ext.portlet.service.base;

import com.ext.portlet.service.PlanMetaServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PlanMetaServiceClpInvoker {
    private String _methodName486;
    private String[] _methodParameterTypes486;
    private String _methodName487;
    private String[] _methodParameterTypes487;

    public PlanMetaServiceClpInvoker() {
        _methodName486 = "getBeanIdentifier";

        _methodParameterTypes486 = new String[] {  };

        _methodName487 = "setBeanIdentifier";

        _methodParameterTypes487 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName486.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes486, parameterTypes)) {
            return PlanMetaServiceUtil.getBeanIdentifier();
        }

        if (_methodName487.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes487, parameterTypes)) {
            PlanMetaServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
