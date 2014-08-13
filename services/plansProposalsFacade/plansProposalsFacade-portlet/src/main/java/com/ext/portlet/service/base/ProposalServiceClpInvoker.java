package com.ext.portlet.service.base;

import com.ext.portlet.service.ProposalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProposalServiceClpInvoker {
    private String _methodName536;
    private String[] _methodParameterTypes536;
    private String _methodName537;
    private String[] _methodParameterTypes537;
    private String _methodName542;
    private String[] _methodParameterTypes542;
    private String _methodName543;
    private String[] _methodParameterTypes543;
    private String _methodName544;
    private String[] _methodParameterTypes544;
    private String _methodName545;
    private String[] _methodParameterTypes545;
    private String _methodName546;
    private String[] _methodParameterTypes546;

    public ProposalServiceClpInvoker() {
        _methodName536 = "getBeanIdentifier";

        _methodParameterTypes536 = new String[] {  };

        _methodName537 = "setBeanIdentifier";

        _methodParameterTypes537 = new String[] { "java.lang.String" };

        _methodName542 = "getProposalVersionFirstIndex";

        _methodParameterTypes542 = new String[] { "long", "long" };

        _methodName543 = "getProposalVersionIndex";

        _methodParameterTypes543 = new String[] { "long", "long" };

        _methodName544 = "getProposalVersions";

        _methodParameterTypes544 = new String[] { "long", "long", "int", "int" };

        _methodName545 = "getProposalVersions";

        _methodParameterTypes545 = new String[] { "long", "int", "int" };

        _methodName546 = "getProposalContestSections";

        _methodParameterTypes546 = new String[] { "long", "int", "long" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName536.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes536, parameterTypes)) {
            return ProposalServiceUtil.getBeanIdentifier();
        }

        if (_methodName537.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes537, parameterTypes)) {
            ProposalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName542.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes542, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersionFirstIndex(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName543.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes543, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersionIndex(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName544.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes544, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersions(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Integer) arguments[2]).intValue(),
                ((Integer) arguments[3]).intValue());
        }

        if (_methodName545.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes545, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersions(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName546.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes546, parameterTypes)) {
            return ProposalServiceUtil.getProposalContestSections(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue(),
                ((Long) arguments[2]).longValue());
        }

        throw new UnsupportedOperationException();
    }
}
