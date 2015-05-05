package com.ext.portlet.service.base;

import com.ext.portlet.service.ProposalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProposalServiceClpInvoker {
    private String _methodName642;
    private String[] _methodParameterTypes642;
    private String _methodName643;
    private String[] _methodParameterTypes643;
    private String _methodName648;
    private String[] _methodParameterTypes648;
    private String _methodName649;
    private String[] _methodParameterTypes649;
    private String _methodName650;
    private String[] _methodParameterTypes650;
    private String _methodName651;
    private String[] _methodParameterTypes651;
    private String _methodName652;
    private String[] _methodParameterTypes652;

    public ProposalServiceClpInvoker() {
        _methodName642 = "getBeanIdentifier";

        _methodParameterTypes642 = new String[] {  };

        _methodName643 = "setBeanIdentifier";

        _methodParameterTypes643 = new String[] { "java.lang.String" };

        _methodName648 = "getProposalVersionFirstIndex";

        _methodParameterTypes648 = new String[] { "long", "long" };

        _methodName649 = "getProposalVersionIndex";

        _methodParameterTypes649 = new String[] { "long", "long" };

        _methodName650 = "getProposalVersions";

        _methodParameterTypes650 = new String[] {
                "long", "long", "long", "int", "int"
            };

        _methodName651 = "getProposalVersions";

        _methodParameterTypes651 = new String[] { "long", "int", "int" };

        _methodName652 = "getProposalContestSections";

        _methodParameterTypes652 = new String[] { "long", "int", "long" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName642.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes642, parameterTypes)) {
            return ProposalServiceUtil.getBeanIdentifier();
        }

        if (_methodName643.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes643, parameterTypes)) {
            ProposalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName648.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes648, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersionFirstIndex(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName649.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes649, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersionIndex(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName650.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes650, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersions(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Long) arguments[2]).longValue(),
                ((Integer) arguments[3]).intValue(),
                ((Integer) arguments[4]).intValue());
        }

        if (_methodName651.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes651, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersions(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName652.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes652, parameterTypes)) {
            return ProposalServiceUtil.getProposalContestSections(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue(),
                ((Long) arguments[2]).longValue());
        }

        throw new UnsupportedOperationException();
    }
}
