package com.ext.portlet.service.base;

import com.ext.portlet.service.ModelOutputChartOrderServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ModelOutputChartOrderServiceClpInvoker {
    private String _methodName608;
    private String[] _methodParameterTypes608;
    private String _methodName609;
    private String[] _methodParameterTypes609;

    public ModelOutputChartOrderServiceClpInvoker() {
        _methodName608 = "getBeanIdentifier";

        _methodParameterTypes608 = new String[] {  };

        _methodName609 = "setBeanIdentifier";

        _methodParameterTypes609 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName608.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes608, parameterTypes)) {
            return ModelOutputChartOrderServiceUtil.getBeanIdentifier();
        }

        if (_methodName609.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes609, parameterTypes)) {
            ModelOutputChartOrderServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
