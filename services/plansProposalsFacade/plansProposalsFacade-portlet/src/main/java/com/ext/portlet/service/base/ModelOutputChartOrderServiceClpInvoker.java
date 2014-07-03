package com.ext.portlet.service.base;

import com.ext.portlet.service.ModelOutputChartOrderServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ModelOutputChartOrderServiceClpInvoker {
    private String _methodName524;
    private String[] _methodParameterTypes524;
    private String _methodName525;
    private String[] _methodParameterTypes525;

    public ModelOutputChartOrderServiceClpInvoker() {
        _methodName524 = "getBeanIdentifier";

        _methodParameterTypes524 = new String[] {  };

        _methodName525 = "setBeanIdentifier";

        _methodParameterTypes525 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName524.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes524, parameterTypes)) {
            return ModelOutputChartOrderServiceUtil.getBeanIdentifier();
        }

        if (_methodName525.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes525, parameterTypes)) {
            ModelOutputChartOrderServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
