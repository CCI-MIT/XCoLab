package com.ext.portlet.service.base;

import com.ext.portlet.service.Plan2ProposalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class Plan2ProposalServiceClpInvoker {
    private String _methodName510;
    private String[] _methodParameterTypes510;
    private String _methodName511;
    private String[] _methodParameterTypes511;

    public Plan2ProposalServiceClpInvoker() {
        _methodName510 = "getBeanIdentifier";

        _methodParameterTypes510 = new String[] {  };

        _methodName511 = "setBeanIdentifier";

        _methodParameterTypes511 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName510.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes510, parameterTypes)) {
            return Plan2ProposalServiceUtil.getBeanIdentifier();
        }

        if (_methodName511.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes511, parameterTypes)) {
            Plan2ProposalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
