package com.ext.portlet.service.base;

import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProposalContestPhaseAttributeLocalServiceClpInvoker {
    private String _methodName0;
    private String[] _methodParameterTypes0;
    private String _methodName1;
    private String[] _methodParameterTypes1;
    private String _methodName2;
    private String[] _methodParameterTypes2;
    private String _methodName3;
    private String[] _methodParameterTypes3;
    private String _methodName4;
    private String[] _methodParameterTypes4;
    private String _methodName5;
    private String[] _methodParameterTypes5;
    private String _methodName6;
    private String[] _methodParameterTypes6;
    private String _methodName7;
    private String[] _methodParameterTypes7;
    private String _methodName8;
    private String[] _methodParameterTypes8;
    private String _methodName9;
    private String[] _methodParameterTypes9;
    private String _methodName10;
    private String[] _methodParameterTypes10;
    private String _methodName11;
    private String[] _methodParameterTypes11;
    private String _methodName12;
    private String[] _methodParameterTypes12;
    private String _methodName13;
    private String[] _methodParameterTypes13;
    private String _methodName14;
    private String[] _methodParameterTypes14;
    private String _methodName15;
    private String[] _methodParameterTypes15;
    private String _methodName514;
    private String[] _methodParameterTypes514;
    private String _methodName515;
    private String[] _methodParameterTypes515;
    private String _methodName520;
    private String[] _methodParameterTypes520;
    private String _methodName521;
    private String[] _methodParameterTypes521;
    private String _methodName522;
    private String[] _methodParameterTypes522;
    private String _methodName523;
    private String[] _methodParameterTypes523;
    private String _methodName524;
    private String[] _methodParameterTypes524;
    private String _methodName525;
    private String[] _methodParameterTypes525;
    private String _methodName526;
    private String[] _methodParameterTypes526;
    private String _methodName527;
    private String[] _methodParameterTypes527;
    private String _methodName528;
    private String[] _methodParameterTypes528;

    public ProposalContestPhaseAttributeLocalServiceClpInvoker() {
        _methodName0 = "addProposalContestPhaseAttribute";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.ProposalContestPhaseAttribute"
            };

        _methodName1 = "createProposalContestPhaseAttribute";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deleteProposalContestPhaseAttribute";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deleteProposalContestPhaseAttribute";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.ProposalContestPhaseAttribute"
            };

        _methodName4 = "dynamicQuery";

        _methodParameterTypes4 = new String[] {  };

        _methodName5 = "dynamicQuery";

        _methodParameterTypes5 = new String[] {
                "com.liferay.portal.kernel.dao.orm.DynamicQuery"
            };

        _methodName6 = "dynamicQuery";

        _methodParameterTypes6 = new String[] {
                "com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int"
            };

        _methodName7 = "dynamicQuery";

        _methodParameterTypes7 = new String[] {
                "com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int",
                "com.liferay.portal.kernel.util.OrderByComparator"
            };

        _methodName8 = "dynamicQueryCount";

        _methodParameterTypes8 = new String[] {
                "com.liferay.portal.kernel.dao.orm.DynamicQuery"
            };

        _methodName9 = "dynamicQueryCount";

        _methodParameterTypes9 = new String[] {
                "com.liferay.portal.kernel.dao.orm.DynamicQuery",
                "com.liferay.portal.kernel.dao.orm.Projection"
            };

        _methodName10 = "fetchProposalContestPhaseAttribute";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getProposalContestPhaseAttribute";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getProposalContestPhaseAttributes";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getProposalContestPhaseAttributesCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateProposalContestPhaseAttribute";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.ProposalContestPhaseAttribute"
            };

        _methodName514 = "getBeanIdentifier";

        _methodParameterTypes514 = new String[] {  };

        _methodName515 = "setBeanIdentifier";

        _methodParameterTypes515 = new String[] { "java.lang.String" };

        _methodName520 = "getProposalContestPhaseAttributes";

        _methodParameterTypes520 = new String[] { "long", "long" };

        _methodName521 = "getProposalContestPhaseAttribute";

        _methodParameterTypes521 = new String[] {
                "long", "long", "java.lang.String", "long"
            };

        _methodName522 = "getProposalContestPhaseAttribute";

        _methodParameterTypes522 = new String[] {
                "long", "long", "java.lang.String"
            };

        _methodName523 = "getAllContestPhaseAttributes";

        _methodParameterTypes523 = new String[] { "long" };

        _methodName524 = "setProposalContestPhaseAttribute";

        _methodParameterTypes524 = new String[] {
                "long", "long", "java.lang.String", "long"
            };

        _methodName525 = "setProposalContestPhaseAttribute";

        _methodParameterTypes525 = new String[] {
                "long", "long", "java.lang.String", "java.lang.String"
            };

        _methodName526 = "setProposalContestPhaseAttribute";

        _methodParameterTypes526 = new String[] {
                "long", "long", "java.lang.String", "double"
            };

        _methodName527 = "setProposalContestPhaseAttribute";

        _methodParameterTypes527 = new String[] {
                "long", "long", "java.lang.String", "long", "java.lang.String",
                "double"
            };

        _methodName528 = "deleteProposalContestPhaseAttribute";

        _methodParameterTypes528 = new String[] {
                "long", "long", "java.lang.String"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.addProposalContestPhaseAttribute((com.ext.portlet.model.ProposalContestPhaseAttribute) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.createProposalContestPhaseAttribute(((Long) arguments[0]).longValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.deleteProposalContestPhaseAttribute(((Long) arguments[0]).longValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.deleteProposalContestPhaseAttribute((com.ext.portlet.model.ProposalContestPhaseAttribute) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.fetchProposalContestPhaseAttribute(((Long) arguments[0]).longValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(((Long) arguments[0]).longValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttributes(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttributesCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.updateProposalContestPhaseAttribute((com.ext.portlet.model.ProposalContestPhaseAttribute) arguments[0]);
        }

        if (_methodName514.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes514, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName515.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes515, parameterTypes)) {
            ProposalContestPhaseAttributeLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName520.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes520, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttributes(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName521.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes521, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2],
                ((Long) arguments[3]).longValue());
        }

        if (_methodName522.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes522, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName523.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes523, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.getAllContestPhaseAttributes(((Long) arguments[0]).longValue());
        }

        if (_methodName524.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes524, parameterTypes)) {
            ProposalContestPhaseAttributeLocalServiceUtil.setProposalContestPhaseAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2],
                ((Long) arguments[3]).longValue());

            return null;
        }

        if (_methodName525.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes525, parameterTypes)) {
            ProposalContestPhaseAttributeLocalServiceUtil.setProposalContestPhaseAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);

            return null;
        }

        if (_methodName526.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes526, parameterTypes)) {
            ProposalContestPhaseAttributeLocalServiceUtil.setProposalContestPhaseAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2],
                ((Double) arguments[3]).doubleValue());

            return null;
        }

        if (_methodName527.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes527, parameterTypes)) {
            ProposalContestPhaseAttributeLocalServiceUtil.setProposalContestPhaseAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2],
                ((Long) arguments[3]).longValue(),
                (java.lang.String) arguments[4],
                ((Double) arguments[5]).doubleValue());

            return null;
        }

        if (_methodName528.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes528, parameterTypes)) {
            ProposalContestPhaseAttributeLocalServiceUtil.deleteProposalContestPhaseAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
