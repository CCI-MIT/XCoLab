package com.ext.portlet.service.base;

import com.ext.portlet.service.AnalyticsUserEventLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AnalyticsUserEventLocalServiceClpInvoker {
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
    private String _methodName552;
    private String[] _methodParameterTypes552;
    private String _methodName553;
    private String[] _methodParameterTypes553;
    private String _methodName558;
    private String[] _methodParameterTypes558;
    private String _methodName559;
    private String[] _methodParameterTypes559;

    public AnalyticsUserEventLocalServiceClpInvoker() {
        _methodName0 = "addAnalyticsUserEvent";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.AnalyticsUserEvent"
            };

        _methodName1 = "createAnalyticsUserEvent";

        _methodParameterTypes1 = new String[] {
                "com.ext.portlet.service.persistence.AnalyticsUserEventPK"
            };

        _methodName2 = "deleteAnalyticsUserEvent";

        _methodParameterTypes2 = new String[] {
                "com.ext.portlet.service.persistence.AnalyticsUserEventPK"
            };

        _methodName3 = "deleteAnalyticsUserEvent";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.AnalyticsUserEvent"
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

        _methodName10 = "fetchAnalyticsUserEvent";

        _methodParameterTypes10 = new String[] {
                "com.ext.portlet.service.persistence.AnalyticsUserEventPK"
            };

        _methodName11 = "getAnalyticsUserEvent";

        _methodParameterTypes11 = new String[] {
                "com.ext.portlet.service.persistence.AnalyticsUserEventPK"
            };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getAnalyticsUserEvents";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getAnalyticsUserEventsCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateAnalyticsUserEvent";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.AnalyticsUserEvent"
            };

        _methodName552 = "getBeanIdentifier";

        _methodParameterTypes552 = new String[] {  };

        _methodName553 = "setBeanIdentifier";

        _methodParameterTypes553 = new String[] { "java.lang.String" };

        _methodName558 = "eventExists";

        _methodParameterTypes558 = new String[] { "long", "java.lang.String" };

        _methodName559 = "createEvent";

        _methodParameterTypes559 = new String[] {
                "long", "java.lang.String", "java.lang.String",
                "java.lang.String", "java.lang.String", "int"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return AnalyticsUserEventLocalServiceUtil.addAnalyticsUserEvent((com.ext.portlet.model.AnalyticsUserEvent) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return AnalyticsUserEventLocalServiceUtil.createAnalyticsUserEvent((com.ext.portlet.service.persistence.AnalyticsUserEventPK) arguments[0]);
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return AnalyticsUserEventLocalServiceUtil.deleteAnalyticsUserEvent((com.ext.portlet.service.persistence.AnalyticsUserEventPK) arguments[0]);
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return AnalyticsUserEventLocalServiceUtil.deleteAnalyticsUserEvent((com.ext.portlet.model.AnalyticsUserEvent) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return AnalyticsUserEventLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return AnalyticsUserEventLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return AnalyticsUserEventLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return AnalyticsUserEventLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return AnalyticsUserEventLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return AnalyticsUserEventLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return AnalyticsUserEventLocalServiceUtil.fetchAnalyticsUserEvent((com.ext.portlet.service.persistence.AnalyticsUserEventPK) arguments[0]);
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return AnalyticsUserEventLocalServiceUtil.getAnalyticsUserEvent((com.ext.portlet.service.persistence.AnalyticsUserEventPK) arguments[0]);
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return AnalyticsUserEventLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return AnalyticsUserEventLocalServiceUtil.getAnalyticsUserEvents(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return AnalyticsUserEventLocalServiceUtil.getAnalyticsUserEventsCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return AnalyticsUserEventLocalServiceUtil.updateAnalyticsUserEvent((com.ext.portlet.model.AnalyticsUserEvent) arguments[0]);
        }

        if (_methodName552.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes552, parameterTypes)) {
            return AnalyticsUserEventLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName553.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes553, parameterTypes)) {
            AnalyticsUserEventLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName558.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes558, parameterTypes)) {
            return AnalyticsUserEventLocalServiceUtil.eventExists(((Long) arguments[0]).longValue(),
                (java.lang.String) arguments[1]);
        }

        if (_methodName559.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes559, parameterTypes)) {
            return AnalyticsUserEventLocalServiceUtil.createEvent(((Long) arguments[0]).longValue(),
                (java.lang.String) arguments[1],
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                (java.lang.String) arguments[4],
                ((Integer) arguments[5]).intValue());
        }

        throw new UnsupportedOperationException();
    }
}
