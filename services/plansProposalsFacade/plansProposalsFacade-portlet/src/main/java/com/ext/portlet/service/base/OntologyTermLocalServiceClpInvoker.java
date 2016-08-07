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
    private String _methodName464;
    private String[] _methodParameterTypes464;
    private String _methodName465;
    private String[] _methodParameterTypes465;
    private String _methodName470;
    private String[] _methodParameterTypes470;
    private String _methodName471;
    private String[] _methodParameterTypes471;
    private String _methodName472;
    private String[] _methodParameterTypes472;
    private String _methodName473;
    private String[] _methodParameterTypes473;
    private String _methodName474;
    private String[] _methodParameterTypes474;
    private String _methodName475;
    private String[] _methodParameterTypes475;
    private String _methodName476;
    private String[] _methodParameterTypes476;
    private String _methodName477;
    private String[] _methodParameterTypes477;
    private String _methodName478;
    private String[] _methodParameterTypes478;
    private String _methodName479;
    private String[] _methodParameterTypes479;
    private String _methodName480;
    private String[] _methodParameterTypes480;
    private String _methodName481;
    private String[] _methodParameterTypes481;
    private String _methodName482;
    private String[] _methodParameterTypes482;
    private String _methodName483;
    private String[] _methodParameterTypes483;
    private String _methodName484;
    private String[] _methodParameterTypes484;

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

        _methodName464 = "getBeanIdentifier";

        _methodParameterTypes464 = new String[] {  };

        _methodName465 = "setBeanIdentifier";

        _methodParameterTypes465 = new String[] { "java.lang.String" };

        _methodName470 = "findByParentId";

        _methodParameterTypes470 = new String[] { "java.lang.Long" };

        _methodName471 = "findByParentIdSpaceId";

        _methodParameterTypes471 = new String[] {
                "java.lang.Long", "java.lang.Long"
            };

        _methodName472 = "findByOntologyTermName";

        _methodParameterTypes472 = new String[] { "java.lang.String" };

        _methodName473 = "createTerm";

        _methodParameterTypes473 = new String[] {
                "java.lang.Long", "java.lang.String", "java.lang.Long",
                "java.lang.String"
            };

        _methodName474 = "countChildTerms";

        _methodParameterTypes474 = new String[] { "java.lang.Long" };

        _methodName475 = "clearClassTags";

        _methodParameterTypes475 = new String[] {
                "java.lang.Class", "java.lang.Long"
            };

        _methodName476 = "store";

        _methodParameterTypes476 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
            };

        _methodName477 = "getParent";

        _methodParameterTypes477 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
            };

        _methodName478 = "getChildTermsCount";

        _methodParameterTypes478 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
            };

        _methodName479 = "getChildTerms";

        _methodParameterTypes479 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
            };

        _methodName480 = "getAllDescendantTerms";

        _methodParameterTypes480 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
            };

        _methodName481 = "getSpace";

        _methodParameterTypes481 = new String[] {
                "com.ext.portlet.model.OntologyTerm"
            };

        _methodName482 = "tagClass";

        _methodParameterTypes482 = new String[] {
                "com.ext.portlet.model.OntologyTerm", "java.lang.Class",
                "java.lang.Long"
            };

        _methodName483 = "findTagedIdsForClass";

        _methodParameterTypes483 = new String[] {
                "com.ext.portlet.model.OntologyTerm", "java.lang.Class"
            };

        _methodName484 = "isAnyOntologyTermOfFocusAreaIdADescendantOfOntologyTermId";

        _methodParameterTypes484 = new String[] {
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

        if (_methodName464.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes464, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName465.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes465, parameterTypes)) {
            OntologyTermLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName470.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes470, parameterTypes)) {
            return OntologyTermLocalServiceUtil.findByParentId((java.lang.Long) arguments[0]);
        }

        if (_methodName471.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes471, parameterTypes)) {
            return OntologyTermLocalServiceUtil.findByParentIdSpaceId((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1]);
        }

        if (_methodName472.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes472, parameterTypes)) {
            return OntologyTermLocalServiceUtil.findByOntologyTermName((java.lang.String) arguments[0]);
        }

        if (_methodName473.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes473, parameterTypes)) {
            return OntologyTermLocalServiceUtil.createTerm((java.lang.Long) arguments[0],
                (java.lang.String) arguments[1], (java.lang.Long) arguments[2],
                (java.lang.String) arguments[3]);
        }

        if (_methodName474.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes474, parameterTypes)) {
            return OntologyTermLocalServiceUtil.countChildTerms((java.lang.Long) arguments[0]);
        }

        if (_methodName475.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes475, parameterTypes)) {
            OntologyTermLocalServiceUtil.clearClassTags((java.lang.Class) arguments[0],
                (java.lang.Long) arguments[1]);

            return null;
        }

        if (_methodName476.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes476, parameterTypes)) {
            OntologyTermLocalServiceUtil.store((com.ext.portlet.model.OntologyTerm) arguments[0]);

            return null;
        }

        if (_methodName477.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes477, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getParent((com.ext.portlet.model.OntologyTerm) arguments[0]);
        }

        if (_methodName478.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes478, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getChildTermsCount((com.ext.portlet.model.OntologyTerm) arguments[0]);
        }

        if (_methodName479.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes479, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getChildTerms((com.ext.portlet.model.OntologyTerm) arguments[0]);
        }

        if (_methodName480.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes480, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getAllDescendantTerms((com.ext.portlet.model.OntologyTerm) arguments[0]);
        }

        if (_methodName481.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes481, parameterTypes)) {
            return OntologyTermLocalServiceUtil.getSpace((com.ext.portlet.model.OntologyTerm) arguments[0]);
        }

        if (_methodName482.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes482, parameterTypes)) {
            OntologyTermLocalServiceUtil.tagClass((com.ext.portlet.model.OntologyTerm) arguments[0],
                (java.lang.Class) arguments[1], (java.lang.Long) arguments[2]);

            return null;
        }

        if (_methodName483.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes483, parameterTypes)) {
            return OntologyTermLocalServiceUtil.findTagedIdsForClass((com.ext.portlet.model.OntologyTerm) arguments[0],
                (java.lang.Class) arguments[1]);
        }

        if (_methodName484.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes484, parameterTypes)) {
            return OntologyTermLocalServiceUtil.isAnyOntologyTermOfFocusAreaIdADescendantOfOntologyTermId((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1]);
        }

        throw new UnsupportedOperationException();
    }
}
