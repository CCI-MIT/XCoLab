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
    private String _methodName422;
    private String[] _methodParameterTypes422;
    private String _methodName423;
    private String[] _methodParameterTypes423;
    private String _methodName428;
    private String[] _methodParameterTypes428;
    private String _methodName429;
    private String[] _methodParameterTypes429;
    private String _methodName430;
    private String[] _methodParameterTypes430;
    private String _methodName431;
    private String[] _methodParameterTypes431;
    private String _methodName432;
    private String[] _methodParameterTypes432;
    private String _methodName433;
    private String[] _methodParameterTypes433;
    private String _methodName434;
    private String[] _methodParameterTypes434;
    private String _methodName435;
    private String[] _methodParameterTypes435;
    private String _methodName436;
    private String[] _methodParameterTypes436;
    private String _methodName437;
    private String[] _methodParameterTypes437;
    private String _methodName438;
    private String[] _methodParameterTypes438;
    private String _methodName439;
    private String[] _methodParameterTypes439;
    private String _methodName440;
    private String[] _methodParameterTypes440;
    private String _methodName441;
    private String[] _methodParameterTypes441;
    private String _methodName442;
    private String[] _methodParameterTypes442;

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

        _methodName422 = "getBeanIdentifier";

        _methodParameterTypes422 = new String[] {  };

        _methodName423 = "setBeanIdentifier";

        _methodParameterTypes423 = new String[] { "java.lang.String" };

        _methodName428 = "findByParentId";

        _methodParameterTypes428 = new String[] { "java.lang.Long" };

        _methodName429 = "findByParentIdSpaceId";

        _methodParameterTypes429 = new String[] {
                "java.lang.Long", "java.lang.Long"
            };

        _methodName430 = "findByOntologyTermName";

        _methodParameterTypes430 = new String[] { "java.lang.String" };

        _methodName431 = "createTerm";

        _methodParameterTypes431 = new String[] {
                "java.lang.Long", "java.lang.String", "java.lang.Long",
                "java.lang.String"
            };

        _methodName432 = "countChildTerms";

        _methodParameterTypes432 = new String[] { "java.lang.Long" };

        _methodName433 = "clearClassTags";

        _methodParameterTypes433 = new String[] {
                "java.lang.Class", "java.lang.Long"
            };

        _methodName434 = "store";

        _methodParameterTypes434 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
            };

        _methodName435 = "getParent";

        _methodParameterTypes435 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
            };

        _methodName436 = "getChildTermsCount";

        _methodParameterTypes436 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
            };

        _methodName437 = "getChildTerms";

        _methodParameterTypes437 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
            };

        _methodName438 = "getAllDescendantTerms";

        _methodParameterTypes438 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
            };

        _methodName439 = "getSpace";

        _methodParameterTypes439 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
            };

        _methodName440 = "tagClass";

        _methodParameterTypes440 = new String[] {
                "com.ext.portlet.model.OntologyTerm", "java.lang.Class",
                "java.lang.Long"
            };

        _methodName441 = "findTagedIdsForClass";

        _methodParameterTypes441 = new String[] {
                "com.ext.portlet.model.OntologyTerm", "java.lang.Class"
            };

        _methodName442 = "isAnyOntologyTermOfFocusAreaIdADescendantOfOntologyTermId";

        _methodParameterTypes442 = new String[] {
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

        if (_methodName422.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes422, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName423.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes423, parameterTypes)) {
            OntologyTermLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName428.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes428, parameterTypes)) {
            return OntologyTermLocalServiceUtil.findByParentId((java.lang.Long) arguments[0]);
        }

        if (_methodName429.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes429, parameterTypes)) {
            return OntologyTermLocalServiceUtil.findByParentIdSpaceId((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1]);
        }

        if (_methodName430.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes430, parameterTypes)) {
            return OntologyTermLocalServiceUtil.findByOntologyTermName((java.lang.String) arguments[0]);
        }

        if (_methodName431.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes431, parameterTypes)) {
            return OntologyTermLocalServiceUtil.createTerm((java.lang.Long) arguments[0],
                (java.lang.String) arguments[1], (java.lang.Long) arguments[2],
                (java.lang.String) arguments[3]);
        }

        if (_methodName432.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes432, parameterTypes)) {
            return OntologyTermLocalServiceUtil.countChildTerms((java.lang.Long) arguments[0]);
        }

        if (_methodName433.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes433, parameterTypes)) {
            OntologyTermLocalServiceUtil.clearClassTags((java.lang.Class) arguments[0],
                (java.lang.Long) arguments[1]);

            return null;
        }

        if (_methodName434.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes434, parameterTypes)) {
            OntologyTermLocalServiceUtil.store((com.ext.portlet.model.OntologyTerm) arguments[0]);

            return null;
        }

        if (_methodName435.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes435, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getParent((com.ext.portlet.model.OntologyTerm) arguments[0]);
        }

        if (_methodName436.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes436, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getChildTermsCount((com.ext.portlet.model.OntologyTerm) arguments[0]);
        }

        if (_methodName437.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes437, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getChildTerms((com.ext.portlet.model.OntologyTerm) arguments[0]);
        }

        if (_methodName438.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes438, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getAllDescendantTerms((com.ext.portlet.model.OntologyTerm) arguments[0]);
        }

        if (_methodName439.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes439, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getSpace((com.ext.portlet.model.OntologyTerm) arguments[0]);
        }

        if (_methodName440.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes440, parameterTypes)) {
            OntologyTermLocalServiceUtil.tagClass((com.ext.portlet.model.OntologyTerm) arguments[0],
                (java.lang.Class) arguments[1], (java.lang.Long) arguments[2]);

            return null;
        }

        if (_methodName441.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes441, parameterTypes)) {
            return OntologyTermLocalServiceUtil.findTagedIdsForClass((com.ext.portlet.model.OntologyTerm) arguments[0],
                (java.lang.Class) arguments[1]);
        }

        if (_methodName442.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes442, parameterTypes)) {
            return OntologyTermLocalServiceUtil.isAnyOntologyTermOfFocusAreaIdADescendantOfOntologyTermId((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1]);
        }

        throw new UnsupportedOperationException();
    }
}
