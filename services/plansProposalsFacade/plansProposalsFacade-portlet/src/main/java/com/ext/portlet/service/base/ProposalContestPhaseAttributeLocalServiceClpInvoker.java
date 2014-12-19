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
    private String _methodName576;
    private String[] _methodParameterTypes576;
    private String _methodName577;
    private String[] _methodParameterTypes577;
    private String _methodName582;
    private String[] _methodParameterTypes582;
    private String _methodName583;
    private String[] _methodParameterTypes583;
    private String _methodName584;
    private String[] _methodParameterTypes584;
    private String _methodName585;
    private String[] _methodParameterTypes585;
    private String _methodName586;
    private String[] _methodParameterTypes586;
    private String _methodName587;
    private String[] _methodParameterTypes587;
    private String _methodName588;
    private String[] _methodParameterTypes588;
    private String _methodName589;
    private String[] _methodParameterTypes589;
    private String _methodName590;
    private String[] _methodParameterTypes590;
    private String _methodName591;
    private String[] _methodParameterTypes591;
    private String _methodName592;
    private String[] _methodParameterTypes592;
    private String _methodName593;
    private String[] _methodParameterTypes593;
    private String _methodName594;
    private String[] _methodParameterTypes594;
    private String _methodName595;
    private String[] _methodParameterTypes595;

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

        _methodName576 = "getBeanIdentifier";

        _methodParameterTypes576 = new String[] {  };

        _methodName577 = "setBeanIdentifier";

        _methodParameterTypes577 = new String[] { "java.lang.String" };

        _methodName582 = "isAttributeSetAndTrue";

        _methodParameterTypes582 = new String[] {
                "long", "long", "java.lang.String", "long"
            };

        _methodName583 = "persistAttribute";

        _methodParameterTypes583 = new String[] {
                "long", "long", "java.lang.String", "long", "long"
            };

        _methodName584 = "persistAttribute";

        _methodParameterTypes584 = new String[] {
                "long", "long", "java.lang.String", "long", "java.lang.String"
            };

        _methodName585 = "persistSelectedJudgesAttribute";

        _methodParameterTypes585 = new String[] { "long", "long", "java.util.List" };

        _methodName586 = "getOrCreateAttribute";

        _methodParameterTypes586 = new String[] {
                "long", "long", "java.lang.String", "long"
            };

        _methodName587 = "getProposalContestPhaseAttributes";

        _methodParameterTypes587 = new String[] { "long", "long" };

        _methodName588 = "getProposalContestPhaseAttribute";

        _methodParameterTypes588 = new String[] {
                "long", "long", "java.lang.String", "long"
            };

        _methodName589 = "getProposalContestPhaseAttribute";

        _methodParameterTypes589 = new String[] {
                "long", "long", "java.lang.String"
            };

        _methodName590 = "getAllContestPhaseAttributes";

        _methodParameterTypes590 = new String[] { "long" };

        _methodName591 = "setProposalContestPhaseAttribute";

        _methodParameterTypes591 = new String[] {
                "long", "long", "java.lang.String", "long"
            };

        _methodName592 = "setProposalContestPhaseAttribute";

        _methodParameterTypes592 = new String[] {
                "long", "long", "java.lang.String", "java.lang.String"
            };

        _methodName593 = "setProposalContestPhaseAttribute";

        _methodParameterTypes593 = new String[] {
                "long", "long", "java.lang.String", "double"
            };

        _methodName594 = "setProposalContestPhaseAttribute";

        _methodParameterTypes594 = new String[] {
                "long", "long", "java.lang.String", "long", "java.lang.String",
                "double"
            };

        _methodName595 = "deleteProposalContestPhaseAttribute";

        _methodParameterTypes595 = new String[] {
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

        if (_methodName576.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes576, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName577.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes577, parameterTypes)) {
            ProposalContestPhaseAttributeLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName582.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes582, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.isAttributeSetAndTrue(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2],
                ((Long) arguments[3]).longValue());
        }

        if (_methodName583.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes583, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.persistAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2],
                ((Long) arguments[3]).longValue(),
                ((Long) arguments[4]).longValue());
        }

        if (_methodName584.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes584, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.persistAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2],
                ((Long) arguments[3]).longValue(),
                (java.lang.String) arguments[4]);
        }

        if (_methodName585.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes585, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.persistSelectedJudgesAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.util.List<java.lang.Long>) arguments[2]);
        }

        if (_methodName586.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes586, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.getOrCreateAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2],
                ((Long) arguments[3]).longValue());
        }

        if (_methodName587.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes587, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttributes(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName588.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes588, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2],
                ((Long) arguments[3]).longValue());
        }

        if (_methodName589.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes589, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName590.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes590, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.getAllContestPhaseAttributes(((Long) arguments[0]).longValue());
        }

        if (_methodName591.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes591, parameterTypes)) {
            ProposalContestPhaseAttributeLocalServiceUtil.setProposalContestPhaseAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2],
                ((Long) arguments[3]).longValue());

            return null;
        }

        if (_methodName592.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes592, parameterTypes)) {
            ProposalContestPhaseAttributeLocalServiceUtil.setProposalContestPhaseAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);

            return null;
        }

        if (_methodName593.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes593, parameterTypes)) {
            ProposalContestPhaseAttributeLocalServiceUtil.setProposalContestPhaseAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2],
                ((Double) arguments[3]).doubleValue());

            return null;
        }

        if (_methodName594.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes594, parameterTypes)) {
            ProposalContestPhaseAttributeLocalServiceUtil.setProposalContestPhaseAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2],
                ((Long) arguments[3]).longValue(),
                (java.lang.String) arguments[4],
                ((Double) arguments[5]).doubleValue());

            return null;
        }

        if (_methodName595.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes595, parameterTypes)) {
            ProposalContestPhaseAttributeLocalServiceUtil.deleteProposalContestPhaseAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
