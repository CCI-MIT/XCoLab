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
    private String _methodName350;
    private String[] _methodParameterTypes350;
    private String _methodName351;
    private String[] _methodParameterTypes351;
    private String _methodName356;
    private String[] _methodParameterTypes356;
    private String _methodName357;
    private String[] _methodParameterTypes357;
    private String _methodName358;
    private String[] _methodParameterTypes358;
    private String _methodName359;
    private String[] _methodParameterTypes359;
    private String _methodName360;
    private String[] _methodParameterTypes360;
    private String _methodName361;
    private String[] _methodParameterTypes361;
    private String _methodName362;
    private String[] _methodParameterTypes362;
    private String _methodName363;
    private String[] _methodParameterTypes363;
    private String _methodName364;
    private String[] _methodParameterTypes364;
    private String _methodName365;
    private String[] _methodParameterTypes365;
    private String _methodName366;
    private String[] _methodParameterTypes366;
    private String _methodName367;
    private String[] _methodParameterTypes367;
    private String _methodName368;
    private String[] _methodParameterTypes368;
    private String _methodName369;
    private String[] _methodParameterTypes369;
    private String _methodName370;
    private String[] _methodParameterTypes370;

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

        _methodName350 = "getBeanIdentifier";

        _methodParameterTypes350 = new String[] {  };

        _methodName351 = "setBeanIdentifier";

        _methodParameterTypes351 = new String[] { "java.lang.String" };

        _methodName356 = "findByParentId";

        _methodParameterTypes356 = new String[] { "java.lang.Long" };

        _methodName357 = "findByParentIdSpaceId";

        _methodParameterTypes357 = new String[] {
                "java.lang.Long", "java.lang.Long"
            };

        _methodName358 = "findByOntologyTermName";

        _methodParameterTypes358 = new String[] { "java.lang.String" };

        _methodName359 = "createTerm";

        _methodParameterTypes359 = new String[] {
                "java.lang.Long", "java.lang.String", "java.lang.Long",
                "java.lang.String"
            };

        _methodName360 = "countChildTerms";

        _methodParameterTypes360 = new String[] { "java.lang.Long" };

        _methodName361 = "clearClassTags";

        _methodParameterTypes361 = new String[] {
                "java.lang.Class", "java.lang.Long"
            };

        _methodName362 = "store";

        _methodParameterTypes362 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
            };

        _methodName363 = "getParent";

        _methodParameterTypes363 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
            };

        _methodName364 = "getChildTermsCount";

        _methodParameterTypes364 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
            };

        _methodName365 = "getChildTerms";

        _methodParameterTypes365 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
            };

        _methodName366 = "getAllDescendantTerms";

        _methodParameterTypes366 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
            };

        _methodName367 = "getSpace";

        _methodParameterTypes367 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
            };

        _methodName368 = "tagClass";

        _methodParameterTypes368 = new String[] {
                "com.ext.portlet.model.OntologyTerm", "java.lang.Class",
                "java.lang.Long"
            };

        _methodName369 = "findTagedIdsForClass";

        _methodParameterTypes369 = new String[] {
                "com.ext.portlet.model.OntologyTerm", "java.lang.Class"
            };

        _methodName370 = "isAnyOntologyTermOfFocusAreaIdADescendantOfOntologyTermId";

        _methodParameterTypes370 = new String[] {
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

        if (_methodName350.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes350, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName351.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes351, parameterTypes)) {
            OntologyTermLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName356.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes356, parameterTypes)) {
            return OntologyTermLocalServiceUtil.findByParentId((java.lang.Long) arguments[0]);
        }

        if (_methodName357.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes357, parameterTypes)) {
            return OntologyTermLocalServiceUtil.findByParentIdSpaceId((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1]);
        }

        if (_methodName358.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes358, parameterTypes)) {
            return OntologyTermLocalServiceUtil.findByOntologyTermName((java.lang.String) arguments[0]);
        }

        if (_methodName359.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes359, parameterTypes)) {
            return OntologyTermLocalServiceUtil.createTerm((java.lang.Long) arguments[0],
                (java.lang.String) arguments[1], (java.lang.Long) arguments[2],
                (java.lang.String) arguments[3]);
        }

        if (_methodName360.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes360, parameterTypes)) {
            return OntologyTermLocalServiceUtil.countChildTerms((java.lang.Long) arguments[0]);
        }

        if (_methodName361.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes361, parameterTypes)) {
            OntologyTermLocalServiceUtil.clearClassTags((java.lang.Class) arguments[0],
                (java.lang.Long) arguments[1]);

            return null;
        }

        if (_methodName362.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes362, parameterTypes)) {
            OntologyTermLocalServiceUtil.store((com.ext.portlet.model.OntologyTerm) arguments[0]);

            return null;
        }

        if (_methodName363.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes363, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getParent((com.ext.portlet.model.OntologyTerm) arguments[0]);
        }

        if (_methodName364.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes364, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getChildTermsCount((com.ext.portlet.model.OntologyTerm) arguments[0]);
        }

        if (_methodName365.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes365, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getChildTerms((com.ext.portlet.model.OntologyTerm) arguments[0]);
        }

        if (_methodName366.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes366, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getAllDescendantTerms((com.ext.portlet.model.OntologyTerm) arguments[0]);
        }

        if (_methodName367.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes367, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getSpace((com.ext.portlet.model.OntologyTerm) arguments[0]);
        }

        if (_methodName368.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes368, parameterTypes)) {
            OntologyTermLocalServiceUtil.tagClass((com.ext.portlet.model.OntologyTerm) arguments[0],
                (java.lang.Class) arguments[1], (java.lang.Long) arguments[2]);

            return null;
        }

        if (_methodName369.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes369, parameterTypes)) {
            return OntologyTermLocalServiceUtil.findTagedIdsForClass((com.ext.portlet.model.OntologyTerm) arguments[0],
                (java.lang.Class) arguments[1]);
        }

        if (_methodName370.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes370, parameterTypes)) {
            return OntologyTermLocalServiceUtil.isAnyOntologyTermOfFocusAreaIdADescendantOfOntologyTermId((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1]);
        }

        throw new UnsupportedOperationException();
    }
}
