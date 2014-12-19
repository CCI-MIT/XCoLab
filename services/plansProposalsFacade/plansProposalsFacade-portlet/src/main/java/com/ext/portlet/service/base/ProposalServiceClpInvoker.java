package com.ext.portlet.service.base;

import com.ext.portlet.service.ProposalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProposalServiceClpInvoker {
    private String _methodName560;
    private String[] _methodParameterTypes560;
    private String _methodName561;
    private String[] _methodParameterTypes561;
    private String _methodName566;
    private String[] _methodParameterTypes566;
    private String _methodName567;
    private String[] _methodParameterTypes567;
    private String _methodName568;
    private String[] _methodParameterTypes568;
    private String _methodName569;
    private String[] _methodParameterTypes569;
    private String _methodName570;
    private String[] _methodParameterTypes570;

    public ProposalServiceClpInvoker() {
        _methodName560 = "getBeanIdentifier";

        _methodParameterTypes560 = new String[] {  };

        _methodName561 = "setBeanIdentifier";

        _methodParameterTypes561 = new String[] { "java.lang.String" };

        _methodName566 = "getProposalVersionFirstIndex";

        _methodParameterTypes566 = new String[] { "long", "long" };

        _methodName567 = "getProposalVersionIndex";

        _methodParameterTypes567 = new String[] { "long", "long" };

        _methodName568 = "getProposalVersions";

        _methodParameterTypes568 = new String[] {
                "long", "long", "long", "int", "int"
            };

        _methodName569 = "getProposalVersions";

        _methodParameterTypes569 = new String[] { "long", "int", "int" };

        _methodName570 = "getProposalContestSections";

        _methodParameterTypes570 = new String[] { "long", "int", "long" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName560.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes560, parameterTypes)) {
            return ProposalServiceUtil.getBeanIdentifier();
        }

        if (_methodName561.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes561, parameterTypes)) {
            ProposalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName566.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes566, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersionFirstIndex(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName567.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes567, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersionIndex(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName568.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes568, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersions(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Long) arguments[2]).longValue(),
                ((Integer) arguments[3]).intValue(),
                ((Integer) arguments[4]).intValue());
        }

        if (_methodName569.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes569, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersions(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName570.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes570, parameterTypes)) {
            return ProposalServiceUtil.getProposalContestSections(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue(),
                ((Long) arguments[2]).longValue());
        }

        throw new UnsupportedOperationException();
    }
}
