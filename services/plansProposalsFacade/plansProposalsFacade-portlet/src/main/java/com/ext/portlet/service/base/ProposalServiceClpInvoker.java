package com.ext.portlet.service.base;

import com.ext.portlet.service.ProposalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProposalServiceClpInvoker {
    private String _methodName406;
    private String[] _methodParameterTypes406;
    private String _methodName407;
    private String[] _methodParameterTypes407;
    private String _methodName412;
    private String[] _methodParameterTypes412;
    private String _methodName413;
    private String[] _methodParameterTypes413;
    private String _methodName414;
    private String[] _methodParameterTypes414;
    private String _methodName415;
    private String[] _methodParameterTypes415;
    private String _methodName416;
    private String[] _methodParameterTypes416;

    public ProposalServiceClpInvoker() {
        _methodName406 = "getBeanIdentifier";

        _methodParameterTypes406 = new String[] {  };

        _methodName407 = "setBeanIdentifier";

        _methodParameterTypes407 = new String[] { "java.lang.String" };

        _methodName412 = "getProposalVersionFirstIndex";

        _methodParameterTypes412 = new String[] { "long", "long" };

        _methodName413 = "getProposalVersionIndex";

        _methodParameterTypes413 = new String[] { "long", "long" };

        _methodName414 = "getProposalVersions";

        _methodParameterTypes414 = new String[] {
                "long", "long", "long", "int", "int"
            };

        _methodName415 = "getProposalVersions";

        _methodParameterTypes415 = new String[] { "long", "int", "int" };

        _methodName416 = "getProposalContestSections";

        _methodParameterTypes416 = new String[] { "long", "int", "long" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName406.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes406, parameterTypes)) {
            return ProposalServiceUtil.getBeanIdentifier();
        }

        if (_methodName407.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes407, parameterTypes)) {
            ProposalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName412.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes412, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersionFirstIndex(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName413.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes413, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersionIndex(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName414.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes414, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersions(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Long) arguments[2]).longValue(),
                ((Integer) arguments[3]).intValue(),
                ((Integer) arguments[4]).intValue());
        }

        if (_methodName415.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes415, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersions(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName416.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes416, parameterTypes)) {
            return ProposalServiceUtil.getProposalContestSections(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue(),
                ((Long) arguments[2]).longValue());
        }

        throw new UnsupportedOperationException();
    }
}
