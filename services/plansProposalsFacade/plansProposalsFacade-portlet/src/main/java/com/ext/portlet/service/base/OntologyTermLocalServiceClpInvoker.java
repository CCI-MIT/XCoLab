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
    private String _methodName488;
    private String[] _methodParameterTypes488;
    private String _methodName489;
    private String[] _methodParameterTypes489;
    private String _methodName494;
    private String[] _methodParameterTypes494;
    private String _methodName495;
    private String[] _methodParameterTypes495;
    private String _methodName496;
    private String[] _methodParameterTypes496;
    private String _methodName497;
    private String[] _methodParameterTypes497;
    private String _methodName498;
    private String[] _methodParameterTypes498;
    private String _methodName499;
    private String[] _methodParameterTypes499;
    private String _methodName500;
    private String[] _methodParameterTypes500;
    private String _methodName501;
    private String[] _methodParameterTypes501;
    private String _methodName502;
    private String[] _methodParameterTypes502;
    private String _methodName503;
    private String[] _methodParameterTypes503;
    private String _methodName504;
    private String[] _methodParameterTypes504;
    private String _methodName505;
    private String[] _methodParameterTypes505;
    private String _methodName506;
    private String[] _methodParameterTypes506;
    private String _methodName507;
    private String[] _methodParameterTypes507;
    private String _methodName508;
    private String[] _methodParameterTypes508;

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

        _methodName488 = "getBeanIdentifier";

        _methodParameterTypes488 = new String[] {  };

        _methodName489 = "setBeanIdentifier";

        _methodParameterTypes489 = new String[] { "java.lang.String" };

        _methodName494 = "findByParentId";

        _methodParameterTypes494 = new String[] { "java.lang.Long" };

        _methodName495 = "findByParentIdSpaceId";

        _methodParameterTypes495 = new String[] {
                "java.lang.Long", "java.lang.Long"
            };

        _methodName496 = "findByOntologyTermName";

        _methodParameterTypes496 = new String[] { "java.lang.String" };

        _methodName497 = "createTerm";

        _methodParameterTypes497 = new String[] {
                "java.lang.Long", "java.lang.String", "java.lang.Long",
                "java.lang.String"
            };

        _methodName498 = "countChildTerms";

        _methodParameterTypes498 = new String[] { "java.lang.Long" };

        _methodName499 = "clearClassTags";

        _methodParameterTypes499 = new String[] {
                "java.lang.Class", "java.lang.Long"
            };

        _methodName500 = "store";

        _methodParameterTypes500 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
            };

        _methodName501 = "getParent";

        _methodParameterTypes501 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
            };

        _methodName502 = "getChildTermsCount";

        _methodParameterTypes502 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
            };

        _methodName503 = "getChildTerms";

        _methodParameterTypes503 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
            };

        _methodName504 = "getAllDescendantTerms";

        _methodParameterTypes504 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
            };

        _methodName505 = "getSpace";

        _methodParameterTypes505 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
            };

        _methodName506 = "tagClass";

        _methodParameterTypes506 = new String[] {
                "com.ext.portlet.model.OntologyTerm", "java.lang.Class",
                "java.lang.Long"
            };

        _methodName507 = "findTagedIdsForClass";

        _methodParameterTypes507 = new String[] {
                "com.ext.portlet.model.OntologyTerm", "java.lang.Class"
            };

        _methodName508 = "isAnyOntologyTermOfFocusAreaIdADescendantOfOntologyTermId";

        _methodParameterTypes508 = new String[] {
                "java.lang.Long", "java.lang.Long"
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

        if (_methodName488.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes488, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName489.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes489, parameterTypes)) {
            OntologyTermLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName494.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes494, parameterTypes)) {
            return OntologyTermLocalServiceUtil.findByParentId((java.lang.Long) arguments[0]);
        }

        if (_methodName495.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes495, parameterTypes)) {
            return OntologyTermLocalServiceUtil.findByParentIdSpaceId((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1]);
        }

        if (_methodName496.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes496, parameterTypes)) {
            return OntologyTermLocalServiceUtil.findByOntologyTermName((java.lang.String) arguments[0]);
        }

        if (_methodName497.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes497, parameterTypes)) {
            return OntologyTermLocalServiceUtil.createTerm((java.lang.Long) arguments[0],
                (java.lang.String) arguments[1], (java.lang.Long) arguments[2],
                (java.lang.String) arguments[3]);
        }

        if (_methodName498.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes498, parameterTypes)) {
            return OntologyTermLocalServiceUtil.countChildTerms((java.lang.Long) arguments[0]);
        }

        if (_methodName499.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes499, parameterTypes)) {
            OntologyTermLocalServiceUtil.clearClassTags((java.lang.Class) arguments[0],
                (java.lang.Long) arguments[1]);

            return null;
        }

        if (_methodName500.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes500, parameterTypes)) {
            OntologyTermLocalServiceUtil.store((com.ext.portlet.model.OntologyTerm) arguments[0]);

            return null;
        }

        if (_methodName501.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes501, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getParent((com.ext.portlet.model.OntologyTerm) arguments[0]);
        }

        if (_methodName502.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes502, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getChildTermsCount((com.ext.portlet.model.OntologyTerm) arguments[0]);
        }

        if (_methodName503.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes503, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getChildTerms((com.ext.portlet.model.OntologyTerm) arguments[0]);
        }

        if (_methodName504.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes504, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getAllDescendantTerms((com.ext.portlet.model.OntologyTerm) arguments[0]);
        }

        if (_methodName505.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes505, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getSpace((com.ext.portlet.model.OntologyTerm) arguments[0]);
        }

        if (_methodName506.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes506, parameterTypes)) {
            OntologyTermLocalServiceUtil.tagClass((com.ext.portlet.model.OntologyTerm) arguments[0],
                (java.lang.Class) arguments[1], (java.lang.Long) arguments[2]);

            return null;
        }

        if (_methodName507.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes507, parameterTypes)) {
            return OntologyTermLocalServiceUtil.findTagedIdsForClass((com.ext.portlet.model.OntologyTerm) arguments[0],
                (java.lang.Class) arguments[1]);
        }

        if (_methodName508.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes508, parameterTypes)) {
            return OntologyTermLocalServiceUtil.isAnyOntologyTermOfFocusAreaIdADescendantOfOntologyTermId((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1]);
        }

        throw new UnsupportedOperationException();
    }
}
