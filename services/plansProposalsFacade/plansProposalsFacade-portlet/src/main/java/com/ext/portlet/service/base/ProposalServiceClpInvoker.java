package com.ext.portlet.service.base;

import com.ext.portlet.service.ProposalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProposalServiceClpInvoker {
    private String _methodName448;
    private String[] _methodParameterTypes448;
    private String _methodName449;
    private String[] _methodParameterTypes449;
    private String _methodName454;
    private String[] _methodParameterTypes454;
    private String _methodName455;
    private String[] _methodParameterTypes455;
    private String _methodName456;
    private String[] _methodParameterTypes456;
    private String _methodName457;
    private String[] _methodParameterTypes457;
    private String _methodName458;
    private String[] _methodParameterTypes458;

    public ProposalServiceClpInvoker() {
        _methodName448 = "getBeanIdentifier";

        _methodParameterTypes448 = new String[] {  };

        _methodName449 = "setBeanIdentifier";

        _methodParameterTypes449 = new String[] { "java.lang.String" };

        _methodName454 = "getProposalVersionFirstIndex";

        _methodParameterTypes454 = new String[] { "long", "long" };

        _methodName455 = "getProposalVersionIndex";

        _methodParameterTypes455 = new String[] { "long", "long" };

        _methodName456 = "getProposalVersions";

        _methodParameterTypes456 = new String[] {
                "long", "long", "long", "int", "int"
            };

        _methodName457 = "getProposalVersions";

        _methodParameterTypes457 = new String[] { "long", "int", "int" };

        _methodName458 = "getProposalContestSections";

        _methodParameterTypes458 = new String[] { "long", "int", "long" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName448.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes448, parameterTypes)) {
            return ProposalServiceUtil.getBeanIdentifier();
        }

        if (_methodName449.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes449, parameterTypes)) {
            ProposalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName454.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes454, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersionFirstIndex(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName455.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes455, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersionIndex(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName456.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes456, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersions(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Long) arguments[2]).longValue(),
                ((Integer) arguments[3]).intValue(),
                ((Integer) arguments[4]).intValue());
        }

        if (_methodName457.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes457, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersions(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName458.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes458, parameterTypes)) {
            return ProposalServiceUtil.getProposalContestSections(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue(),
                ((Long) arguments[2]).longValue());
        }

        throw new UnsupportedOperationException();
    }
}
