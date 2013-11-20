package com.ext.portlet.service.base;

import com.ext.portlet.service.ProposalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProposalServiceClpInvoker {
    private String _methodName462;
    private String[] _methodParameterTypes462;
    private String _methodName463;
    private String[] _methodParameterTypes463;
    private String _methodName468;
    private String[] _methodParameterTypes468;
    private String _methodName469;
    private String[] _methodParameterTypes469;

    public ProposalServiceClpInvoker() {
        _methodName462 = "getBeanIdentifier";

        _methodParameterTypes462 = new String[] {  };

        _methodName463 = "setBeanIdentifier";

        _methodParameterTypes463 = new String[] { "java.lang.String" };

        _methodName468 = "getProposalVersions";

        _methodParameterTypes468 = new String[] { "long", "long", "int", "int" };

        _methodName469 = "getProposalVersions";

        _methodParameterTypes469 = new String[] { "long", "int", "int" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName462.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes462, parameterTypes)) {
            return ProposalServiceUtil.getBeanIdentifier();
        }

        if (_methodName463.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes463, parameterTypes)) {
            ProposalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName468.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes468, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersions(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Integer) arguments[2]).intValue(),
                ((Integer) arguments[3]).intValue());
        }

        if (_methodName469.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes469, parameterTypes)) {
            return ProposalServiceUtil.getProposalVersions(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        throw new UnsupportedOperationException();
    }
}
