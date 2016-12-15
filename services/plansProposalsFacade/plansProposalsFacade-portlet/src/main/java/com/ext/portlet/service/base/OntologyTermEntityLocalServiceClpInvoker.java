package com.ext.portlet.service.base;

import com.ext.portlet.service.OntologyTermEntityLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class OntologyTermEntityLocalServiceClpInvoker {
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
    private String _methodName262;
    private String[] _methodParameterTypes262;
    private String _methodName263;
    private String[] _methodParameterTypes263;
    private String _methodName268;
    private String[] _methodParameterTypes268;
    private String _methodName269;
    private String[] _methodParameterTypes269;
    private String _methodName270;
    private String[] _methodParameterTypes270;

    public OntologyTermEntityLocalServiceClpInvoker() {
        _methodName0 = "addOntologyTermEntity";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.OntologyTermEntity"
            };

        _methodName1 = "createOntologyTermEntity";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deleteOntologyTermEntity";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deleteOntologyTermEntity";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.OntologyTermEntity"
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

        _methodName10 = "fetchOntologyTermEntity";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getOntologyTermEntity";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getOntologyTermEntities";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getOntologyTermEntitiesCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateOntologyTermEntity";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.OntologyTermEntity"
            };

        _methodName262 = "getBeanIdentifier";

        _methodParameterTypes262 = new String[] {  };

        _methodName263 = "setBeanIdentifier";

        _methodParameterTypes263 = new String[] { "java.lang.String" };

        _methodName268 = "findTagedIdsForClass";

        _methodParameterTypes268 = new String[] {
                "java.lang.Long", "java.lang.Class"
            };

        _methodName269 = "store";

        _methodParameterTypes269 = new String[] {
                "com.ext.portlet.model.OntologyTermEntity"
            };

        _methodName270 = "remove";

        _methodParameterTypes270 = new String[] {
                "com.ext.portlet.model.OntologyTermEntity"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return OntologyTermEntityLocalServiceUtil.addOntologyTermEntity((com.ext.portlet.model.OntologyTermEntity) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return OntologyTermEntityLocalServiceUtil.createOntologyTermEntity(((Long) arguments[0]).longValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return OntologyTermEntityLocalServiceUtil.deleteOntologyTermEntity(((Long) arguments[0]).longValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return OntologyTermEntityLocalServiceUtil.deleteOntologyTermEntity((com.ext.portlet.model.OntologyTermEntity) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return OntologyTermEntityLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return OntologyTermEntityLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return OntologyTermEntityLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return OntologyTermEntityLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return OntologyTermEntityLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return OntologyTermEntityLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return OntologyTermEntityLocalServiceUtil.fetchOntologyTermEntity(((Long) arguments[0]).longValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return OntologyTermEntityLocalServiceUtil.getOntologyTermEntity(((Long) arguments[0]).longValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return OntologyTermEntityLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return OntologyTermEntityLocalServiceUtil.getOntologyTermEntities(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return OntologyTermEntityLocalServiceUtil.getOntologyTermEntitiesCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return OntologyTermEntityLocalServiceUtil.updateOntologyTermEntity((com.ext.portlet.model.OntologyTermEntity) arguments[0]);
        }

        if (_methodName262.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes262, parameterTypes)) {
            return OntologyTermEntityLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName263.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes263, parameterTypes)) {
            OntologyTermEntityLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName268.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes268, parameterTypes)) {
            return OntologyTermEntityLocalServiceUtil.findTagedIdsForClass((java.lang.Long) arguments[0],
                (java.lang.Class) arguments[1]);
        }

        if (_methodName269.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes269, parameterTypes)) {
            OntologyTermEntityLocalServiceUtil.store((com.ext.portlet.model.OntologyTermEntity) arguments[0]);

            return null;
        }

        if (_methodName270.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes270, parameterTypes)) {
            OntologyTermEntityLocalServiceUtil.remove((com.ext.portlet.model.OntologyTermEntity) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
