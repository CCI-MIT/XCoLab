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
    private String _methodName540;
    private String[] _methodParameterTypes540;
    private String _methodName541;
    private String[] _methodParameterTypes541;
    private String _methodName546;
    private String[] _methodParameterTypes546;
    private String _methodName547;
    private String[] _methodParameterTypes547;
    private String _methodName548;
    private String[] _methodParameterTypes548;
    private String _methodName549;
    private String[] _methodParameterTypes549;
    private String _methodName550;
    private String[] _methodParameterTypes550;
    private String _methodName551;
    private String[] _methodParameterTypes551;
    private String _methodName552;
    private String[] _methodParameterTypes552;
    private String _methodName553;
    private String[] _methodParameterTypes553;
    private String _methodName554;
    private String[] _methodParameterTypes554;
    private String _methodName555;
    private String[] _methodParameterTypes555;
    private String _methodName556;
    private String[] _methodParameterTypes556;
    private String _methodName557;
    private String[] _methodParameterTypes557;
    private String _methodName558;
    private String[] _methodParameterTypes558;
    private String _methodName559;
    private String[] _methodParameterTypes559;

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

        _methodName540 = "getBeanIdentifier";

        _methodParameterTypes540 = new String[] {  };

        _methodName541 = "setBeanIdentifier";

        _methodParameterTypes541 = new String[] { "java.lang.String" };

        _methodName546 = "isAttributeSetAndTrue";

        _methodParameterTypes546 = new String[] {
                "long", "long", "java.lang.String", "long"
            };

        _methodName547 = "persistAttribute";

        _methodParameterTypes547 = new String[] {
                "long", "long", "java.lang.String", "long", "long"
            };

        _methodName548 = "persistAttribute";

        _methodParameterTypes548 = new String[] {
                "long", "long", "java.lang.String", "long", "java.lang.String"
            };

        _methodName549 = "persistSelectedJudgesAttribute";

        _methodParameterTypes549 = new String[] { "long", "long", "java.util.List" };

        _methodName550 = "getOrCreateAttribute";

        _methodParameterTypes550 = new String[] {
                "long", "long", "java.lang.String", "long"
            };

        _methodName551 = "getProposalContestPhaseAttributes";

        _methodParameterTypes551 = new String[] { "long", "long" };

        _methodName552 = "getProposalContestPhaseAttribute";

        _methodParameterTypes552 = new String[] {
                "long", "long", "java.lang.String", "long"
            };

        _methodName553 = "getProposalContestPhaseAttribute";

        _methodParameterTypes553 = new String[] {
                "long", "long", "java.lang.String"
            };

        _methodName554 = "getAllContestPhaseAttributes";

        _methodParameterTypes554 = new String[] { "long" };

        _methodName555 = "setProposalContestPhaseAttribute";

        _methodParameterTypes555 = new String[] {
                "long", "long", "java.lang.String", "long"
            };

        _methodName556 = "setProposalContestPhaseAttribute";

        _methodParameterTypes556 = new String[] {
                "long", "long", "java.lang.String", "java.lang.String"
            };

        _methodName557 = "setProposalContestPhaseAttribute";

        _methodParameterTypes557 = new String[] {
                "long", "long", "java.lang.String", "double"
            };

        _methodName558 = "setProposalContestPhaseAttribute";

        _methodParameterTypes558 = new String[] {
                "long", "long", "java.lang.String", "long", "java.lang.String",
                "double"
            };

        _methodName559 = "deleteProposalContestPhaseAttribute";

        _methodParameterTypes559 = new String[] {
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

        if (_methodName540.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes540, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName541.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes541, parameterTypes)) {
            ProposalContestPhaseAttributeLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName546.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes546, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.isAttributeSetAndTrue(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2],
                ((Long) arguments[3]).longValue());
        }

        if (_methodName547.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes547, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.persistAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2],
                ((Long) arguments[3]).longValue(),
                ((Long) arguments[4]).longValue());
        }

        if (_methodName548.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes548, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.persistAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2],
                ((Long) arguments[3]).longValue(),
                (java.lang.String) arguments[4]);
        }

        if (_methodName549.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes549, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.persistSelectedJudgesAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.util.List<java.lang.Long>) arguments[2]);
        }

        if (_methodName550.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes550, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.getOrCreateAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2],
                ((Long) arguments[3]).longValue());
        }

        if (_methodName551.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes551, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttributes(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName552.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes552, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2],
                ((Long) arguments[3]).longValue());
        }

        if (_methodName553.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes553, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName554.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes554, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.getAllContestPhaseAttributes(((Long) arguments[0]).longValue());
        }

        if (_methodName555.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes555, parameterTypes)) {
            ProposalContestPhaseAttributeLocalServiceUtil.setProposalContestPhaseAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2],
                ((Long) arguments[3]).longValue());

            return null;
        }

        if (_methodName556.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes556, parameterTypes)) {
            ProposalContestPhaseAttributeLocalServiceUtil.setProposalContestPhaseAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);

            return null;
        }

        if (_methodName557.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes557, parameterTypes)) {
            ProposalContestPhaseAttributeLocalServiceUtil.setProposalContestPhaseAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2],
                ((Double) arguments[3]).doubleValue());

            return null;
        }

        if (_methodName558.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes558, parameterTypes)) {
            ProposalContestPhaseAttributeLocalServiceUtil.setProposalContestPhaseAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2],
                ((Long) arguments[3]).longValue(),
                (java.lang.String) arguments[4],
                ((Double) arguments[5]).doubleValue());

            return null;
        }

        if (_methodName559.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes559, parameterTypes)) {
            ProposalContestPhaseAttributeLocalServiceUtil.deleteProposalContestPhaseAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
