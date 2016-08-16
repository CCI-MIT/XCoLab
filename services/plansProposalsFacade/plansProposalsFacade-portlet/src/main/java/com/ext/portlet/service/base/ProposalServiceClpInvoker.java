package com.ext.portlet.service.base;

import com.ext.portlet.service.ProposalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProposalServiceClpInvoker {
    private String _methodName382;
    private String[] _methodParameterTypes382;
    private String _methodName383;
    private String[] _methodParameterTypes383;
    private String _methodName388;
    private String[] _methodParameterTypes388;
    private String _methodName389;
    private String[] _methodParameterTypes389;
    private String _methodName390;
    private String[] _methodParameterTypes390;
    private String _methodName391;
    private String[] _methodParameterTypes391;
    private String _methodName392;
    private String[] _methodParameterTypes392;

    public ProposalServiceClpInvoker() {
        _methodName382 = "getBeanIdentifier";

        _methodParameterTypes382 = new String[] {  };

        _methodName383 = "setBeanIdentifier";

        _methodParameterTypes383 = new String[] { "java.lang.String" };

        _methodName388 = "getProposalVersionFirstIndex";

        _methodParameterTypes388 = new String[] { "long", "long" };

        _methodName389 = "getProposalVersionIndex";

        _methodParameterTypes389 = new String[] { "long", "long" };

        _methodName390 = "getProposalVersions";

        _methodParameterTypes390 = new String[] {
                "long", "long", "long", "int", "int"
            };

        _methodName391 = "getProposalVersions";

        _methodParameterTypes391 = new String[] { "long", "int", "int" };

        _methodName392 = "getProposalContestSections";

        _methodParameterTypes392 = new String[] { "long", "int", "long" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName382.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes382, parameterTypes)) {
            return ProposalServiceUtil.getBeanIdentifier();
        }

        if (_methodName383.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes383, parameterTypes)) {
            ProposalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName388.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes388, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersionFirstIndex(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName389.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes389, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersionIndex(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName390.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes390, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersions(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Long) arguments[2]).longValue(),
                ((Integer) arguments[3]).intValue(),
                ((Integer) arguments[4]).intValue());
        }

        if (_methodName391.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes391, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersions(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName392.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes392, parameterTypes)) {
            return ProposalServiceUtil.getProposalContestSections(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue(),
                ((Long) arguments[2]).longValue());
        }

        throw new UnsupportedOperationException();
    }
}
