package com.ext.portlet.service.base;

import com.ext.portlet.service.Plan2ProposalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class Plan2ProposalServiceClpInvoker {
    private String _methodName468;
    private String[] _methodParameterTypes468;
    private String _methodName469;
    private String[] _methodParameterTypes469;

    public Plan2ProposalServiceClpInvoker() {
        _methodName468 = "getBeanIdentifier";

        _methodParameterTypes468 = new String[] {  };

        _methodName469 = "setBeanIdentifier";

        _methodParameterTypes469 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName468.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes468, parameterTypes)) {
            return Plan2ProposalServiceUtil.getBeanIdentifier();
        }

        if (_methodName469.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes469, parameterTypes)) {
            Plan2ProposalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
