package com.ext.portlet.service.base;

import com.ext.portlet.service.FocusAreaOntologyTermServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class FocusAreaOntologyTermServiceClpInvoker {
    private String _methodName508;
    private String[] _methodParameterTypes508;
    private String _methodName509;
    private String[] _methodParameterTypes509;

    public FocusAreaOntologyTermServiceClpInvoker() {
        _methodName508 = "getBeanIdentifier";

        _methodParameterTypes508 = new String[] {  };

        _methodName509 = "setBeanIdentifier";

        _methodParameterTypes509 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName508.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes508, parameterTypes)) {
            return FocusAreaOntologyTermServiceUtil.getBeanIdentifier();
        }

        if (_methodName509.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes509, parameterTypes)) {
            FocusAreaOntologyTermServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
