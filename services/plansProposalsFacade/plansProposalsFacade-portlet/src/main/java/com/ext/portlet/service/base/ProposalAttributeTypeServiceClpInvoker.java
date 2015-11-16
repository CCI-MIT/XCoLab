package com.ext.portlet.service.base;

import com.ext.portlet.service.ProposalAttributeTypeServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProposalAttributeTypeServiceClpInvoker {
    private String _methodName482;
    private String[] _methodParameterTypes482;
    private String _methodName483;
    private String[] _methodParameterTypes483;

    public ProposalAttributeTypeServiceClpInvoker() {
        _methodName482 = "getBeanIdentifier";

        _methodParameterTypes482 = new String[] {  };

        _methodName483 = "setBeanIdentifier";

        _methodParameterTypes483 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName482.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes482, parameterTypes)) {
            return ProposalAttributeTypeServiceUtil.getBeanIdentifier();
        }

        if (_methodName483.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes483, parameterTypes)) {
            ProposalAttributeTypeServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
