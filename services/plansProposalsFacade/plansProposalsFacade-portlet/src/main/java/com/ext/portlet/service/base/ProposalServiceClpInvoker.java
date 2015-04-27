package com.ext.portlet.service.base;

import com.ext.portlet.service.ProposalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProposalServiceClpInvoker {
    private String _methodName610;
    private String[] _methodParameterTypes610;
    private String _methodName611;
    private String[] _methodParameterTypes611;
    private String _methodName616;
    private String[] _methodParameterTypes616;
    private String _methodName617;
    private String[] _methodParameterTypes617;
    private String _methodName618;
    private String[] _methodParameterTypes618;
    private String _methodName619;
    private String[] _methodParameterTypes619;
    private String _methodName620;
    private String[] _methodParameterTypes620;

    public ProposalServiceClpInvoker() {
        _methodName610 = "getBeanIdentifier";

        _methodParameterTypes610 = new String[] {  };

        _methodName611 = "setBeanIdentifier";

        _methodParameterTypes611 = new String[] { "java.lang.String" };

        _methodName616 = "getProposalVersionFirstIndex";

        _methodParameterTypes616 = new String[] { "long", "long" };

        _methodName617 = "getProposalVersionIndex";

        _methodParameterTypes617 = new String[] { "long", "long" };

        _methodName618 = "getProposalVersions";

        _methodParameterTypes618 = new String[] {
                "long", "long", "long", "int", "int"
            };

        _methodName619 = "getProposalVersions";

        _methodParameterTypes619 = new String[] { "long", "int", "int" };

        _methodName620 = "getProposalContestSections";

        _methodParameterTypes620 = new String[] { "long", "int", "long" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName610.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes610, parameterTypes)) {
            return ProposalServiceUtil.getBeanIdentifier();
        }

        if (_methodName611.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes611, parameterTypes)) {
            ProposalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName616.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes616, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersionFirstIndex(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName617.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes617, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersionIndex(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName618.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes618, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersions(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Long) arguments[2]).longValue(),
                ((Integer) arguments[3]).intValue(),
                ((Integer) arguments[4]).intValue());
        }

        if (_methodName619.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes619, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersions(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName620.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes620, parameterTypes)) {
            return ProposalServiceUtil.getProposalContestSections(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue(),
                ((Long) arguments[2]).longValue());
        }

        throw new UnsupportedOperationException();
    }
}
