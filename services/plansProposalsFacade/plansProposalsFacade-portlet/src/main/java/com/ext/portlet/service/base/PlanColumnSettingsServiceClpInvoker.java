package com.ext.portlet.service.base;

import com.ext.portlet.service.PlanColumnSettingsServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PlanColumnSettingsServiceClpInvoker {
    private String _methodName536;
    private String[] _methodParameterTypes536;
    private String _methodName537;
    private String[] _methodParameterTypes537;

    public PlanColumnSettingsServiceClpInvoker() {
        _methodName536 = "getBeanIdentifier";

        _methodParameterTypes536 = new String[] {  };

        _methodName537 = "setBeanIdentifier";

        _methodParameterTypes537 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName536.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes536, parameterTypes)) {
            return PlanColumnSettingsServiceUtil.getBeanIdentifier();
        }

        if (_methodName537.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes537, parameterTypes)) {
            PlanColumnSettingsServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
