package com.ext.portlet.service.base;

import com.ext.portlet.service.ProposalUnversionedAttributeServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProposalUnversionedAttributeServiceClpInvoker {
    private String _methodName508;
    private String[] _methodParameterTypes508;
    private String _methodName509;
    private String[] _methodParameterTypes509;
    private String _methodName514;
    private String[] _methodParameterTypes514;

    public ProposalUnversionedAttributeServiceClpInvoker() {
        _methodName508 = "getBeanIdentifier";

        _methodParameterTypes508 = new String[] {  };

        _methodName509 = "setBeanIdentifier";

        _methodParameterTypes509 = new String[] { "java.lang.String" };

        _methodName514 = "getAttributes";

        _methodParameterTypes514 = new String[] { "long" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName508.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes508, parameterTypes)) {
            return ProposalUnversionedAttributeServiceUtil.getBeanIdentifier();
        }

        if (_methodName509.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes509, parameterTypes)) {
            ProposalUnversionedAttributeServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }
        if (_methodName514.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes514, parameterTypes)) {
            return ProposalUnversionedAttributeServiceUtil.getAttributes(((Long) arguments[0]).longValue());
        }

        throw new UnsupportedOperationException();
    }
}
