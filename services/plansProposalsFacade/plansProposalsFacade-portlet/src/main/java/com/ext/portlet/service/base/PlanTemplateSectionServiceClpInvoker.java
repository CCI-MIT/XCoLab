package com.ext.portlet.service.base;

import com.ext.portlet.service.PlanTemplateSectionServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PlanTemplateSectionServiceClpInvoker {
    private String _methodName510;
    private String[] _methodParameterTypes510;
    private String _methodName511;
    private String[] _methodParameterTypes511;

    public PlanTemplateSectionServiceClpInvoker() {
        _methodName510 = "getBeanIdentifier";

        _methodParameterTypes510 = new String[] {  };

        _methodName511 = "setBeanIdentifier";

        _methodParameterTypes511 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName510.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes510, parameterTypes)) {
            return PlanTemplateSectionServiceUtil.getBeanIdentifier();
        }

        if (_methodName511.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes511, parameterTypes)) {
            PlanTemplateSectionServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
