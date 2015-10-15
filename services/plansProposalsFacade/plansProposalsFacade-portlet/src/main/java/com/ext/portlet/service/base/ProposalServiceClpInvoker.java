package com.ext.portlet.service.base;

import com.ext.portlet.service.ProposalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProposalServiceClpInvoker {
    private String _methodName622;
    private String[] _methodParameterTypes622;
    private String _methodName623;
    private String[] _methodParameterTypes623;
    private String _methodName628;
    private String[] _methodParameterTypes628;
    private String _methodName629;
    private String[] _methodParameterTypes629;
    private String _methodName630;
    private String[] _methodParameterTypes630;
    private String _methodName631;
    private String[] _methodParameterTypes631;
    private String _methodName632;
    private String[] _methodParameterTypes632;

    public ProposalServiceClpInvoker() {
        _methodName622 = "getBeanIdentifier";

        _methodParameterTypes622 = new String[] {  };

        _methodName623 = "setBeanIdentifier";

        _methodParameterTypes623 = new String[] { "java.lang.String" };

        _methodName628 = "getProposalVersionFirstIndex";

        _methodParameterTypes628 = new String[] { "long", "long" };

        _methodName629 = "getProposalVersionIndex";

        _methodParameterTypes629 = new String[] { "long", "long" };

        _methodName630 = "getProposalVersions";

        _methodParameterTypes630 = new String[] {
                "long", "long", "long", "int", "int"
            };

        _methodName631 = "getProposalVersions";

        _methodParameterTypes631 = new String[] { "long", "int", "int" };

        _methodName632 = "getProposalContestSections";

        _methodParameterTypes632 = new String[] { "long", "int", "long" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName622.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes622, parameterTypes)) {
            return ProposalServiceUtil.getBeanIdentifier();
        }

        if (_methodName623.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes623, parameterTypes)) {
            ProposalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName628.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes628, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersionFirstIndex(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName629.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes629, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersionIndex(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName630.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes630, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersions(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Long) arguments[2]).longValue(),
                ((Integer) arguments[3]).intValue(),
                ((Integer) arguments[4]).intValue());
        }

        if (_methodName631.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes631, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersions(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName632.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes632, parameterTypes)) {
            return ProposalServiceUtil.getProposalContestSections(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue(),
                ((Long) arguments[2]).longValue());
        }

        throw new UnsupportedOperationException();
    }
}
