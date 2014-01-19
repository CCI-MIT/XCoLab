package com.ext.portlet.service.base;

import com.ext.portlet.service.PlanTemplateServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PlanTemplateServiceClpInvoker {
    private String _methodName468;
    private String[] _methodParameterTypes468;
    private String _methodName469;
    private String[] _methodParameterTypes469;

    public PlanTemplateServiceClpInvoker() {
        _methodName468 = "getBeanIdentifier";

        _methodParameterTypes468 = new String[] {  };

        _methodName469 = "setBeanIdentifier";

        _methodParameterTypes469 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName468.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes468, parameterTypes)) {
            return PlanTemplateServiceUtil.getBeanIdentifier();
        }

        if (_methodName469.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes469, parameterTypes)) {
            PlanTemplateServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
