package com.ext.portlet.service.base;

import com.ext.portlet.service.PlanSectionDefinitionServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PlanSectionDefinitionServiceClpInvoker {
    private String _methodName334;
    private String[] _methodParameterTypes334;
    private String _methodName335;
    private String[] _methodParameterTypes335;

    public PlanSectionDefinitionServiceClpInvoker() {
        _methodName334 = "getBeanIdentifier";

        _methodParameterTypes334 = new String[] {  };

        _methodName335 = "setBeanIdentifier";

        _methodParameterTypes335 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName334.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes334, parameterTypes)) {
            return PlanSectionDefinitionServiceUtil.getBeanIdentifier();
        }

        if (_methodName335.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes335, parameterTypes)) {
            PlanSectionDefinitionServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
