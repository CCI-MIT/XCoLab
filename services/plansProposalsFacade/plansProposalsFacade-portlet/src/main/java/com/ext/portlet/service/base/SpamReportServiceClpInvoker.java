package com.ext.portlet.service.base;

import com.ext.portlet.service.SpamReportServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SpamReportServiceClpInvoker {
    private String _methodName496;
    private String[] _methodParameterTypes496;
    private String _methodName497;
    private String[] _methodParameterTypes497;

    public SpamReportServiceClpInvoker() {
        _methodName496 = "getBeanIdentifier";

        _methodParameterTypes496 = new String[] {  };

        _methodName497 = "setBeanIdentifier";

        _methodParameterTypes497 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName496.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes496, parameterTypes)) {
            return SpamReportServiceUtil.getBeanIdentifier();
        }

        if (_methodName497.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes497, parameterTypes)) {
            SpamReportServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
