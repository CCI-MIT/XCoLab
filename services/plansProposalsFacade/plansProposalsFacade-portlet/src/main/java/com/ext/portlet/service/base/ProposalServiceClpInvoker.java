package com.ext.portlet.service.base;

import com.ext.portlet.service.ProposalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProposalServiceClpInvoker {
    private String _methodName486;
    private String[] _methodParameterTypes486;
    private String _methodName487;
    private String[] _methodParameterTypes487;
    private String _methodName492;
    private String[] _methodParameterTypes492;
    private String _methodName493;
    private String[] _methodParameterTypes493;
    private String _methodName494;
    private String[] _methodParameterTypes494;

    public ProposalServiceClpInvoker() {
        _methodName486 = "getBeanIdentifier";

        _methodParameterTypes486 = new String[] {  };

        _methodName487 = "setBeanIdentifier";

        _methodParameterTypes487 = new String[] { "java.lang.String" };

        _methodName492 = "getProposalVersions";

        _methodParameterTypes492 = new String[] { "long", "long", "int", "int" };

        _methodName493 = "getProposalVersions";

        _methodParameterTypes493 = new String[] { "long", "int", "int" };

        _methodName494 = "getProposalContestSections";

        _methodParameterTypes494 = new String[] { "long", "int", "long" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName486.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes486, parameterTypes)) {
            return ProposalServiceUtil.getBeanIdentifier();
        }

        if (_methodName487.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes487, parameterTypes)) {
            ProposalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName492.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes492, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersions(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Integer) arguments[2]).intValue(),
                ((Integer) arguments[3]).intValue());
        }

        if (_methodName493.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes493, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersions(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName494.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes494, parameterTypes)) {
            return ProposalServiceUtil.getProposalContestSections(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue(),
                ((Long) arguments[2]).longValue());
        }

        throw new UnsupportedOperationException();
    }
}
