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
    private String _methodName626;
    private String[] _methodParameterTypes626;
    private String _methodName627;
    private String[] _methodParameterTypes627;
    private String _methodName632;
    private String[] _methodParameterTypes632;
    private String _methodName633;
    private String[] _methodParameterTypes633;
    private String _methodName634;
    private String[] _methodParameterTypes634;
    private String _methodName635;
    private String[] _methodParameterTypes635;
    private String _methodName636;
    private String[] _methodParameterTypes636;
    private String _methodName637;
    private String[] _methodParameterTypes637;
    private String _methodName638;
    private String[] _methodParameterTypes638;
    private String _methodName639;
    private String[] _methodParameterTypes639;
    private String _methodName640;
    private String[] _methodParameterTypes640;
    private String _methodName641;
    private String[] _methodParameterTypes641;
    private String _methodName642;
    private String[] _methodParameterTypes642;
    private String _methodName643;
    private String[] _methodParameterTypes643;
    private String _methodName644;
    private String[] _methodParameterTypes644;
    private String _methodName645;
    private String[] _methodParameterTypes645;
    private String _methodName646;
    private String[] _methodParameterTypes646;

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

        _methodName626 = "getBeanIdentifier";

        _methodParameterTypes626 = new String[] {  };

        _methodName627 = "setBeanIdentifier";

        _methodParameterTypes627 = new String[] { "java.lang.String" };

        _methodName632 = "isAttributeSetAndTrue";

        _methodParameterTypes632 = new String[] {
                "long", "long", "java.lang.String", "long"
            };

        _methodName633 = "persistAttribute";

        _methodParameterTypes633 = new String[] {
                "long", "long", "java.lang.String", "long", "long"
            };

        _methodName634 = "persistAttribute";

        _methodParameterTypes634 = new String[] {
                "long", "long", "java.lang.String", "long", "java.lang.String"
            };

        _methodName635 = "persistSelectedJudgesAttribute";

        _methodParameterTypes635 = new String[] { "long", "long", "java.util.List" };

        _methodName636 = "getOrCreateAttribute";

        _methodParameterTypes636 = new String[] {
                "long", "long", "java.lang.String", "long"
            };

        _methodName637 = "getProposalContestPhaseAttributes";

        _methodParameterTypes637 = new String[] { "long", "long" };

        _methodName638 = "getProposalContestPhaseAttribute";

        _methodParameterTypes638 = new String[] {
                "long", "long", "java.lang.String", "long"
            };

        _methodName639 = "getProposalContestPhaseAttribute";

        _methodParameterTypes639 = new String[] {
                "long", "long", "java.lang.String"
            };

        _methodName640 = "getAllContestPhaseAttributes";

        _methodParameterTypes640 = new String[] { "long" };

        _methodName641 = "getAllContestPhaseProposalAttributes";

        _methodParameterTypes641 = new String[] { "long", "long" };

        _methodName642 = "setProposalContestPhaseAttribute";

        _methodParameterTypes642 = new String[] {
                "long", "long", "java.lang.String", "long"
            };

        _methodName643 = "setProposalContestPhaseAttribute";

        _methodParameterTypes643 = new String[] {
                "long", "long", "java.lang.String", "java.lang.String"
            };

        _methodName644 = "setProposalContestPhaseAttribute";

        _methodParameterTypes644 = new String[] {
                "long", "long", "java.lang.String", "double"
            };

        _methodName645 = "setProposalContestPhaseAttribute";

        _methodParameterTypes645 = new String[] {
                "long", "long", "java.lang.String", "long", "java.lang.String",
                "double"
            };

        _methodName646 = "deleteProposalContestPhaseAttribute";

        _methodParameterTypes646 = new String[] {
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

        if (_methodName626.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes626, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName627.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes627, parameterTypes)) {
            ProposalContestPhaseAttributeLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName632.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes632, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.isAttributeSetAndTrue(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2],
                ((Long) arguments[3]).longValue());
        }

        if (_methodName633.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes633, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.persistAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2],
                ((Long) arguments[3]).longValue(),
                ((Long) arguments[4]).longValue());
        }

        if (_methodName634.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes634, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.persistAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2],
                ((Long) arguments[3]).longValue(),
                (java.lang.String) arguments[4]);
        }

        if (_methodName635.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes635, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.persistSelectedJudgesAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.util.List<java.lang.Long>) arguments[2]);
        }

        if (_methodName636.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes636, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.getOrCreateAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2],
                ((Long) arguments[3]).longValue());
        }

        if (_methodName637.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes637, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttributes(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName638.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes638, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2],
                ((Long) arguments[3]).longValue());
        }

        if (_methodName639.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes639, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2]);
        }

        if (_methodName640.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes640, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.getAllContestPhaseAttributes(((Long) arguments[0]).longValue());
        }

        if (_methodName641.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes641, parameterTypes)) {
            return ProposalContestPhaseAttributeLocalServiceUtil.getAllContestPhaseProposalAttributes(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName642.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes642, parameterTypes)) {
            ProposalContestPhaseAttributeLocalServiceUtil.setProposalContestPhaseAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2],
                ((Long) arguments[3]).longValue());

            return null;
        }

        if (_methodName643.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes643, parameterTypes)) {
            ProposalContestPhaseAttributeLocalServiceUtil.setProposalContestPhaseAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2], (java.lang.String) arguments[3]);

            return null;
        }

        if (_methodName644.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes644, parameterTypes)) {
            ProposalContestPhaseAttributeLocalServiceUtil.setProposalContestPhaseAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2],
                ((Double) arguments[3]).doubleValue());

            return null;
        }

        if (_methodName645.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes645, parameterTypes)) {
            ProposalContestPhaseAttributeLocalServiceUtil.setProposalContestPhaseAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2],
                ((Long) arguments[3]).longValue(),
                (java.lang.String) arguments[4],
                ((Double) arguments[5]).doubleValue());

            return null;
        }

        if (_methodName646.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes646, parameterTypes)) {
            ProposalContestPhaseAttributeLocalServiceUtil.deleteProposalContestPhaseAttribute(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
