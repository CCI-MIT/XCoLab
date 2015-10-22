package com.ext.portlet.service.base;

import com.ext.portlet.service.ActivitySubscriptionLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ActivitySubscriptionLocalServiceClpInvoker {
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
    private String _methodName492;
    private String[] _methodParameterTypes492;
    private String _methodName493;
    private String[] _methodParameterTypes493;
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
    private String _methodName509;
    private String[] _methodParameterTypes509;
    private String _methodName510;
    private String[] _methodParameterTypes510;
    private String _methodName511;
    private String[] _methodParameterTypes511;
    private String _methodName512;
    private String[] _methodParameterTypes512;
    private String _methodName513;
    private String[] _methodParameterTypes513;
    private String _methodName514;
    private String[] _methodParameterTypes514;
    private String _methodName515;
    private String[] _methodParameterTypes515;
    private String _methodName516;
    private String[] _methodParameterTypes516;
    private String _methodName517;
    private String[] _methodParameterTypes517;
    private String _methodName518;
    private String[] _methodParameterTypes518;

    public ActivitySubscriptionLocalServiceClpInvoker() {
        _methodName0 = "addActivitySubscription";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.ActivitySubscription"
            };

        _methodName1 = "createActivitySubscription";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deleteActivitySubscription";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deleteActivitySubscription";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.ActivitySubscription"
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

        _methodName10 = "fetchActivitySubscription";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getActivitySubscription";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getActivitySubscriptions";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getActivitySubscriptionsCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateActivitySubscription";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.ActivitySubscription"
            };

        _methodName492 = "getBeanIdentifier";

        _methodParameterTypes492 = new String[] {  };

        _methodName493 = "setBeanIdentifier";

        _methodParameterTypes493 = new String[] { "java.lang.String" };

        _methodName499 = "getActivitySubscriptions";

        _methodParameterTypes499 = new String[] {
                "java.lang.Class", "java.lang.Long", "java.lang.Integer",
                "java.lang.String"
            };

        _methodName500 = "findByUser";

        _methodParameterTypes500 = new String[] { "java.lang.Long" };

        _methodName501 = "isSubscribed";

        _methodParameterTypes501 = new String[] {
                "java.lang.Long", "java.lang.Long", "java.lang.Long",
                "java.lang.Integer", "java.lang.String"
            };

        _methodName502 = "isSubscribed";

        _methodParameterTypes502 = new String[] {
                "java.lang.Long", "java.lang.Class", "java.lang.Long",
                "java.lang.Integer", "java.lang.String"
            };

        _methodName503 = "deleteSubscription";

        _methodParameterTypes503 = new String[] {
                "java.lang.Long", "java.lang.Long", "java.lang.Long",
                "java.lang.Integer", "java.lang.String"
            };

        _methodName504 = "deleteSubscription";

        _methodParameterTypes504 = new String[] {
                "java.lang.Long", "java.lang.Long", "java.lang.Long",
                "java.lang.Integer", "java.lang.String", "boolean"
            };

        _methodName505 = "deleteSubscription";

        _methodParameterTypes505 = new String[] {
                "java.lang.Long", "java.lang.Class", "java.lang.Long",
                "java.lang.Integer", "java.lang.String", "boolean"
            };

        _methodName506 = "deleteSubscription";

        _methodParameterTypes506 = new String[] {
                "java.lang.Long", "java.lang.Class", "java.lang.Long",
                "java.lang.Integer", "java.lang.String"
            };

        _methodName507 = "addSubscription";

        _methodParameterTypes507 = new String[] {
                "java.lang.Long", "java.lang.Long", "java.lang.Integer",
                "java.lang.String", "java.lang.Long"
            };

        _methodName508 = "addSubscription";

        _methodParameterTypes508 = new String[] {
                "java.lang.Long", "java.lang.Long", "java.lang.Integer",
                "java.lang.String", "java.lang.Long", "boolean"
            };

        _methodName509 = "addSubscription";

        _methodParameterTypes509 = new String[] {
                "java.lang.Class", "java.lang.Long", "java.lang.Integer",
                "java.lang.String", "java.lang.Long"
            };

        _methodName510 = "addSubscription";

        _methodParameterTypes510 = new String[] {
                "java.lang.Class", "java.lang.Long", "java.lang.Integer",
                "java.lang.String", "java.lang.Long", "boolean"
            };

        _methodName511 = "getActivities";

        _methodParameterTypes511 = new String[] { "java.lang.Long", "int", "int" };

        _methodName512 = "store";

        _methodParameterTypes512 = new String[] {
                "com.ext.portlet.model.ActivitySubscription"
            };

        _methodName513 = "getName";

        _methodParameterTypes513 = new String[] {
                "com.ext.portlet.model.ActivitySubscription"
            };

        _methodName514 = "getSubscriptionType";

        _methodParameterTypes514 = new String[] {
                "com.ext.portlet.model.ActivitySubscription"
            };

        _methodName515 = "delete";

        _methodParameterTypes515 = new String[] {
                "com.ext.portlet.model.ActivitySubscription"
            };

        _methodName516 = "sendEmailNotifications";

        _methodParameterTypes516 = new String[] {
                "com.liferay.portal.service.ServiceContext"
            };

        _methodName517 = "getSubscribedUsers";

        _methodParameterTypes517 = new String[] { "java.lang.Class", "long" };

        _methodName518 = "getSubscribedUsers";

        _methodParameterTypes518 = new String[] { "long", "long" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.addActivitySubscription((com.ext.portlet.model.ActivitySubscription) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.createActivitySubscription(((Long) arguments[0]).longValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.deleteActivitySubscription(((Long) arguments[0]).longValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.deleteActivitySubscription((com.ext.portlet.model.ActivitySubscription) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.fetchActivitySubscription(((Long) arguments[0]).longValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.getActivitySubscription(((Long) arguments[0]).longValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.getActivitySubscriptions(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.getActivitySubscriptionsCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.updateActivitySubscription((com.ext.portlet.model.ActivitySubscription) arguments[0]);
        }

        if (_methodName492.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes492, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName493.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes493, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName499.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes499, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.getActivitySubscriptions((java.lang.Class) arguments[0],
                (java.lang.Long) arguments[1],
                (java.lang.Integer) arguments[2],
                (java.lang.String) arguments[3]);
        }

        if (_methodName500.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes500, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.findByUser((java.lang.Long) arguments[0]);
        }

        if (_methodName501.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes501, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.isSubscribed((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1], (java.lang.Long) arguments[2],
                (java.lang.Integer) arguments[3],
                (java.lang.String) arguments[4]);
        }

        if (_methodName502.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes502, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.isSubscribed((java.lang.Long) arguments[0],
                (java.lang.Class) arguments[1], (java.lang.Long) arguments[2],
                (java.lang.Integer) arguments[3],
                (java.lang.String) arguments[4]);
        }

        if (_methodName503.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes503, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.deleteSubscription((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1], (java.lang.Long) arguments[2],
                (java.lang.Integer) arguments[3],
                (java.lang.String) arguments[4]);

            return null;
        }

        if (_methodName504.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes504, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.deleteSubscription((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1], (java.lang.Long) arguments[2],
                (java.lang.Integer) arguments[3],
                (java.lang.String) arguments[4],
                ((Boolean) arguments[5]).booleanValue());

            return null;
        }

        if (_methodName505.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes505, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.deleteSubscription((java.lang.Long) arguments[0],
                (java.lang.Class) arguments[1], (java.lang.Long) arguments[2],
                (java.lang.Integer) arguments[3],
                (java.lang.String) arguments[4],
                ((Boolean) arguments[5]).booleanValue());

            return null;
        }

        if (_methodName506.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes506, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.deleteSubscription((java.lang.Long) arguments[0],
                (java.lang.Class) arguments[1], (java.lang.Long) arguments[2],
                (java.lang.Integer) arguments[3],
                (java.lang.String) arguments[4]);

            return null;
        }

        if (_methodName507.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes507, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.addSubscription((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1],
                (java.lang.Integer) arguments[2],
                (java.lang.String) arguments[3], (java.lang.Long) arguments[4]);

            return null;
        }

        if (_methodName508.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes508, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.addSubscription((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1],
                (java.lang.Integer) arguments[2],
                (java.lang.String) arguments[3], (java.lang.Long) arguments[4],
                ((Boolean) arguments[5]).booleanValue());

            return null;
        }

        if (_methodName509.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes509, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.addSubscription((java.lang.Class) arguments[0],
                (java.lang.Long) arguments[1],
                (java.lang.Integer) arguments[2],
                (java.lang.String) arguments[3], (java.lang.Long) arguments[4]);

            return null;
        }

        if (_methodName510.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes510, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.addSubscription((java.lang.Class) arguments[0],
                (java.lang.Long) arguments[1],
                (java.lang.Integer) arguments[2],
                (java.lang.String) arguments[3], (java.lang.Long) arguments[4],
                ((Boolean) arguments[5]).booleanValue());

            return null;
        }

        if (_methodName511.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes511, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.getActivities((java.lang.Long) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName512.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes512, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.store((com.ext.portlet.model.ActivitySubscription) arguments[0]);

            return null;
        }

        if (_methodName513.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes513, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.getName((com.ext.portlet.model.ActivitySubscription) arguments[0]);
        }

        if (_methodName514.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes514, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.getSubscriptionType((com.ext.portlet.model.ActivitySubscription) arguments[0]);
        }

        if (_methodName515.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes515, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.delete((com.ext.portlet.model.ActivitySubscription) arguments[0]);

            return null;
        }

        if (_methodName516.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes516, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.sendEmailNotifications((com.liferay.portal.service.ServiceContext) arguments[0]);

            return null;
        }

        if (_methodName517.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes517, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.getSubscribedUsers((java.lang.Class) arguments[0],
                ((Long) arguments[1]).longValue());
        }

        if (_methodName518.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes518, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.getSubscribedUsers(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        throw new UnsupportedOperationException();
    }
}
