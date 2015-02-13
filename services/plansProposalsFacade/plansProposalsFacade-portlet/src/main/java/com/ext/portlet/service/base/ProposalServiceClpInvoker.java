package com.ext.portlet.service.base;

import com.ext.portlet.service.ProposalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProposalServiceClpInvoker {
    private String _methodName572;
    private String[] _methodParameterTypes572;
    private String _methodName573;
    private String[] _methodParameterTypes573;
    private String _methodName578;
    private String[] _methodParameterTypes578;
    private String _methodName579;
    private String[] _methodParameterTypes579;
    private String _methodName580;
    private String[] _methodParameterTypes580;
    private String _methodName581;
    private String[] _methodParameterTypes581;
    private String _methodName582;
    private String[] _methodParameterTypes582;

    public ProposalServiceClpInvoker() {
        _methodName572 = "getBeanIdentifier";

        _methodParameterTypes572 = new String[] {  };

        _methodName573 = "setBeanIdentifier";

        _methodParameterTypes573 = new String[] { "java.lang.String" };

        _methodName578 = "getProposalVersionFirstIndex";

        _methodParameterTypes578 = new String[] { "long", "long" };

        _methodName579 = "getProposalVersionIndex";

        _methodParameterTypes579 = new String[] { "long", "long" };

        _methodName580 = "getProposalVersions";

        _methodParameterTypes580 = new String[] {
                "long", "long", "long", "int", "int"
            };

        _methodName581 = "getProposalVersions";

        _methodParameterTypes581 = new String[] { "long", "int", "int" };

        _methodName582 = "getProposalContestSections";

        _methodParameterTypes582 = new String[] { "long", "int", "long" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName572.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes572, parameterTypes)) {
            return ProposalServiceUtil.getBeanIdentifier();
        }

        if (_methodName573.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes573, parameterTypes)) {
            ProposalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName578.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes578, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersionFirstIndex(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName579.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes579, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersionIndex(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName580.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes580, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersions(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Long) arguments[2]).longValue(),
                ((Integer) arguments[3]).intValue(),
                ((Integer) arguments[4]).intValue());
        }

        if (_methodName581.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes581, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersions(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName582.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes582, parameterTypes)) {
            return ProposalServiceUtil.getProposalContestSections(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue(),
                ((Long) arguments[2]).longValue());
        }

        throw new UnsupportedOperationException();
    }
}
