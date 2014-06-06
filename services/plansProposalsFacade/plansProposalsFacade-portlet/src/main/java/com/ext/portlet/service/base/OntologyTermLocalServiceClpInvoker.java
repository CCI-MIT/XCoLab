package com.ext.portlet.service.base;

import com.ext.portlet.service.OntologyTermLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class OntologyTermLocalServiceClpInvoker {
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
    private String _methodName529;
    private String[] _methodParameterTypes529;
    private String _methodName530;
    private String[] _methodParameterTypes530;
    private String _methodName531;
    private String[] _methodParameterTypes531;
    private String _methodName532;
    private String[] _methodParameterTypes532;

    public OntologyTermLocalServiceClpInvoker() {
        _methodName0 = "addOntologyTerm";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
            };

        _methodName1 = "createOntologyTerm";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deleteOntologyTerm";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deleteOntologyTerm";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
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

        _methodName10 = "fetchOntologyTerm";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getOntologyTerm";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getOntologyTerms";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getOntologyTermsCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateOntologyTerm";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
            };

        _methodName514 = "getBeanIdentifier";

        _methodParameterTypes514 = new String[] {  };

        _methodName515 = "setBeanIdentifier";

        _methodParameterTypes515 = new String[] { "java.lang.String" };

        _methodName520 = "findByParentId";

        _methodParameterTypes520 = new String[] { "java.lang.Long" };

        _methodName521 = "findByParentIdSpaceId";

        _methodParameterTypes521 = new String[] {
                "java.lang.Long", "java.lang.Long"
            };

        _methodName522 = "createTerm";

        _methodParameterTypes522 = new String[] {
                "java.lang.Long", "java.lang.String", "java.lang.Long",
                "java.lang.String"
            };

        _methodName523 = "countChildTerms";

        _methodParameterTypes523 = new String[] { "java.lang.Long" };

        _methodName524 = "clearClassTags";

        _methodParameterTypes524 = new String[] {
                "java.lang.Class", "java.lang.Long"
            };

        _methodName525 = "store";

        _methodParameterTypes525 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
            };

        _methodName526 = "getParent";

        _methodParameterTypes526 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
            };

        _methodName527 = "getChildTermsCount";

        _methodParameterTypes527 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
            };

        _methodName528 = "getChildTerms";

        _methodParameterTypes528 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
            };

        _methodName529 = "getAllDescendantTerms";

        _methodParameterTypes529 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
            };

        _methodName530 = "getSpace";

        _methodParameterTypes530 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
            };

        _methodName531 = "tagClass";

        _methodParameterTypes531 = new String[] {
                "com.ext.portlet.model.OntologyTerm", "java.lang.Class",
                "java.lang.Long"
            };

        _methodName532 = "findTagedIdsForClass";

        _methodParameterTypes532 = new String[] {
                "com.ext.portlet.model.OntologyTerm", "java.lang.Class"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return OntologyTermLocalServiceUtil.addOntologyTerm((com.ext.portlet.model.OntologyTerm) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return OntologyTermLocalServiceUtil.createOntologyTerm(((Long) arguments[0]).longValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return OntologyTermLocalServiceUtil.deleteOntologyTerm(((Long) arguments[0]).longValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return OntologyTermLocalServiceUtil.deleteOntologyTerm((com.ext.portlet.model.OntologyTerm) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return OntologyTermLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return OntologyTermLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return OntologyTermLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return OntologyTermLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return OntologyTermLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return OntologyTermLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return OntologyTermLocalServiceUtil.fetchOntologyTerm(((Long) arguments[0]).longValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getOntologyTerm(((Long) arguments[0]).longValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getOntologyTerms(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getOntologyTermsCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return OntologyTermLocalServiceUtil.updateOntologyTerm((com.ext.portlet.model.OntologyTerm) arguments[0]);
        }

        if (_methodName514.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes514, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName515.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes515, parameterTypes)) {
            OntologyTermLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName520.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes520, parameterTypes)) {
            return OntologyTermLocalServiceUtil.findByParentId((java.lang.Long) arguments[0]);
        }

        if (_methodName521.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes521, parameterTypes)) {
            return OntologyTermLocalServiceUtil.findByParentIdSpaceId((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1]);
        }

        if (_methodName522.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes522, parameterTypes)) {
            return OntologyTermLocalServiceUtil.createTerm((java.lang.Long) arguments[0],
                (java.lang.String) arguments[1], (java.lang.Long) arguments[2],
                (java.lang.String) arguments[3]);
        }

        if (_methodName523.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes523, parameterTypes)) {
            return OntologyTermLocalServiceUtil.countChildTerms((java.lang.Long) arguments[0]);
        }

        if (_methodName524.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes524, parameterTypes)) {
            OntologyTermLocalServiceUtil.clearClassTags((java.lang.Class) arguments[0],
                (java.lang.Long) arguments[1]);

            return null;
        }

        if (_methodName525.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes525, parameterTypes)) {
            OntologyTermLocalServiceUtil.store((com.ext.portlet.model.OntologyTerm) arguments[0]);

            return null;
        }

        if (_methodName526.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes526, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getParent((com.ext.portlet.model.OntologyTerm) arguments[0]);
        }

        if (_methodName527.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes527, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getChildTermsCount((com.ext.portlet.model.OntologyTerm) arguments[0]);
        }

        if (_methodName528.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes528, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getChildTerms((com.ext.portlet.model.OntologyTerm) arguments[0]);
        }

        if (_methodName529.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes529, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getAllDescendantTerms((com.ext.portlet.model.OntologyTerm) arguments[0]);
        }

        if (_methodName530.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes530, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getSpace((com.ext.portlet.model.OntologyTerm) arguments[0]);
        }

        if (_methodName531.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes531, parameterTypes)) {
            OntologyTermLocalServiceUtil.tagClass((com.ext.portlet.model.OntologyTerm) arguments[0],
                (java.lang.Class) arguments[1], (java.lang.Long) arguments[2]);

            return null;
        }

        if (_methodName532.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes532, parameterTypes)) {
            return OntologyTermLocalServiceUtil.findTagedIdsForClass((com.ext.portlet.model.OntologyTerm) arguments[0],
                (java.lang.Class) arguments[1]);
        }

        throw new UnsupportedOperationException();
    }
}
