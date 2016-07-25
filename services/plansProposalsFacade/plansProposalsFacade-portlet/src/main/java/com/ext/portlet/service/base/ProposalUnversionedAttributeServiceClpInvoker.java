package com.ext.portlet.service.base;

import com.ext.portlet.service.ProposalUnversionedAttributeServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProposalUnversionedAttributeServiceClpInvoker {
    private String _methodName472;
    private String[] _methodParameterTypes472;
    private String _methodName473;
    private String[] _methodParameterTypes473;
    private String _methodName478;
    private String[] _methodParameterTypes478;

    public ProposalUnversionedAttributeServiceClpInvoker() {
        _methodName472 = "getBeanIdentifier";

        _methodParameterTypes472 = new String[] {  };

        _methodName473 = "setBeanIdentifier";

        _methodParameterTypes473 = new String[] { "java.lang.String" };

        _methodName478 = "getAttributes";

        _methodParameterTypes478 = new String[] { "long" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName472.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes472, parameterTypes)) {
            return ProposalUnversionedAttributeServiceUtil.getBeanIdentifier();
        }

        if (_methodName473.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes473, parameterTypes)) {
            ProposalUnversionedAttributeServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName478.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes478, parameterTypes)) {
            return ProposalUnversionedAttributeServiceUtil.getAttributes(((Long) arguments[0]).longValue());
        }

        throw new UnsupportedOperationException();
    }
}
