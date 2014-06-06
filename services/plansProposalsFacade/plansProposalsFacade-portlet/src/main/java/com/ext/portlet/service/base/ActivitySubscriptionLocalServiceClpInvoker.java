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
    private String _methodName533;
    private String[] _methodParameterTypes533;
    private String _methodName534;
    private String[] _methodParameterTypes534;
    private String _methodName535;
    private String[] _methodParameterTypes535;
    private String _methodName536;
    private String[] _methodParameterTypes536;
    private String _methodName537;
    private String[] _methodParameterTypes537;
    private String _methodName538;
    private String[] _methodParameterTypes538;
    private String _methodName539;
    private String[] _methodParameterTypes539;

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

        _methodName514 = "getBeanIdentifier";

        _methodParameterTypes514 = new String[] {  };

        _methodName515 = "setBeanIdentifier";

        _methodParameterTypes515 = new String[] { "java.lang.String" };

        _methodName520 = "getActivitySubscriptions";

        _methodParameterTypes520 = new String[] {
                "java.lang.Class", "java.lang.Long", "java.lang.Integer",
                "java.lang.String"
            };

        _methodName521 = "findByUser";

        _methodParameterTypes521 = new String[] { "java.lang.Long" };

        _methodName522 = "isSubscribed";

        _methodParameterTypes522 = new String[] {
                "java.lang.Long", "java.lang.Long", "java.lang.Long",
                "java.lang.Integer", "java.lang.String"
            };

        _methodName523 = "isSubscribed";

        _methodParameterTypes523 = new String[] {
                "java.lang.Long", "java.lang.Class", "java.lang.Long",
                "java.lang.Integer", "java.lang.String"
            };

        _methodName524 = "deleteSubscription";

        _methodParameterTypes524 = new String[] {
                "java.lang.Long", "java.lang.Long", "java.lang.Long",
                "java.lang.Integer", "java.lang.String"
            };

        _methodName525 = "deleteSubscription";

        _methodParameterTypes525 = new String[] {
                "java.lang.Long", "java.lang.Long", "java.lang.Long",
                "java.lang.Integer", "java.lang.String", "boolean"
            };

        _methodName526 = "deleteSubscription";

        _methodParameterTypes526 = new String[] {
                "java.lang.Long", "java.lang.Class", "java.lang.Long",
                "java.lang.Integer", "java.lang.String", "boolean"
            };

        _methodName527 = "deleteSubscription";

        _methodParameterTypes527 = new String[] {
                "java.lang.Long", "java.lang.Class", "java.lang.Long",
                "java.lang.Integer", "java.lang.String"
            };

        _methodName528 = "addSubscription";

        _methodParameterTypes528 = new String[] {
                "java.lang.Long", "java.lang.Long", "java.lang.Integer",
                "java.lang.String", "java.lang.Long"
            };

        _methodName529 = "addSubscription";

        _methodParameterTypes529 = new String[] {
                "java.lang.Long", "java.lang.Long", "java.lang.Integer",
                "java.lang.String", "java.lang.Long", "boolean"
            };

        _methodName530 = "addSubscription";

        _methodParameterTypes530 = new String[] {
                "java.lang.Class", "java.lang.Long", "java.lang.Integer",
                "java.lang.String", "java.lang.Long"
            };

        _methodName531 = "addSubscription";

        _methodParameterTypes531 = new String[] {
                "java.lang.Class", "java.lang.Long", "java.lang.Integer",
                "java.lang.String", "java.lang.Long", "boolean"
            };

        _methodName532 = "getActivities";

        _methodParameterTypes532 = new String[] { "java.lang.Long", "int", "int" };

        _methodName533 = "store";

        _methodParameterTypes533 = new String[] {
                "com.ext.portlet.model.ActivitySubscription"
            };

        _methodName534 = "getName";

        _methodParameterTypes534 = new String[] {
                "com.ext.portlet.model.ActivitySubscription"
            };

        _methodName535 = "getSubscriptionType";

        _methodParameterTypes535 = new String[] {
                "com.ext.portlet.model.ActivitySubscription"
            };

        _methodName536 = "delete";

        _methodParameterTypes536 = new String[] {
                "com.ext.portlet.model.ActivitySubscription"
            };

        _methodName537 = "sendEmailNotifications";

        _methodParameterTypes537 = new String[] {
                "com.liferay.portal.service.ServiceContext"
            };

        _methodName538 = "getSubscribedUsers";

        _methodParameterTypes538 = new String[] { "java.lang.Class", "long" };

        _methodName539 = "getSubscribedUsers";

        _methodParameterTypes539 = new String[] { "long", "long" };
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

        if (_methodName514.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes514, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName515.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes515, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName520.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes520, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.getActivitySubscriptions((java.lang.Class) arguments[0],
                (java.lang.Long) arguments[1],
                (java.lang.Integer) arguments[2],
                (java.lang.String) arguments[3]);
        }

        if (_methodName521.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes521, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.findByUser((java.lang.Long) arguments[0]);
        }

        if (_methodName522.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes522, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.isSubscribed((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1], (java.lang.Long) arguments[2],
                (java.lang.Integer) arguments[3],
                (java.lang.String) arguments[4]);
        }

        if (_methodName523.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes523, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.isSubscribed((java.lang.Long) arguments[0],
                (java.lang.Class) arguments[1], (java.lang.Long) arguments[2],
                (java.lang.Integer) arguments[3],
                (java.lang.String) arguments[4]);
        }

        if (_methodName524.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes524, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.deleteSubscription((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1], (java.lang.Long) arguments[2],
                (java.lang.Integer) arguments[3],
                (java.lang.String) arguments[4]);

            return null;
        }

        if (_methodName525.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes525, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.deleteSubscription((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1], (java.lang.Long) arguments[2],
                (java.lang.Integer) arguments[3],
                (java.lang.String) arguments[4],
                ((Boolean) arguments[5]).booleanValue());

            return null;
        }

        if (_methodName526.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes526, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.deleteSubscription((java.lang.Long) arguments[0],
                (java.lang.Class) arguments[1], (java.lang.Long) arguments[2],
                (java.lang.Integer) arguments[3],
                (java.lang.String) arguments[4],
                ((Boolean) arguments[5]).booleanValue());

            return null;
        }

        if (_methodName527.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes527, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.deleteSubscription((java.lang.Long) arguments[0],
                (java.lang.Class) arguments[1], (java.lang.Long) arguments[2],
                (java.lang.Integer) arguments[3],
                (java.lang.String) arguments[4]);

            return null;
        }

        if (_methodName528.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes528, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.addSubscription((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1],
                (java.lang.Integer) arguments[2],
                (java.lang.String) arguments[3], (java.lang.Long) arguments[4]);

            return null;
        }

        if (_methodName529.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes529, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.addSubscription((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1],
                (java.lang.Integer) arguments[2],
                (java.lang.String) arguments[3], (java.lang.Long) arguments[4],
                ((Boolean) arguments[5]).booleanValue());

            return null;
        }

        if (_methodName530.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes530, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.addSubscription((java.lang.Class) arguments[0],
                (java.lang.Long) arguments[1],
                (java.lang.Integer) arguments[2],
                (java.lang.String) arguments[3], (java.lang.Long) arguments[4]);

            return null;
        }

        if (_methodName531.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes531, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.addSubscription((java.lang.Class) arguments[0],
                (java.lang.Long) arguments[1],
                (java.lang.Integer) arguments[2],
                (java.lang.String) arguments[3], (java.lang.Long) arguments[4],
                ((Boolean) arguments[5]).booleanValue());

            return null;
        }

        if (_methodName532.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes532, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.getActivities((java.lang.Long) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName533.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes533, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.store((com.ext.portlet.model.ActivitySubscription) arguments[0]);

            return null;
        }

        if (_methodName534.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes534, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.getName((com.ext.portlet.model.ActivitySubscription) arguments[0]);
        }

        if (_methodName535.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes535, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.getSubscriptionType((com.ext.portlet.model.ActivitySubscription) arguments[0]);
        }

        if (_methodName536.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes536, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.delete((com.ext.portlet.model.ActivitySubscription) arguments[0]);

            return null;
        }

        if (_methodName537.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes537, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.sendEmailNotifications((com.liferay.portal.service.ServiceContext) arguments[0]);

            return null;
        }

        if (_methodName538.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes538, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.getSubscribedUsers((java.lang.Class) arguments[0],
                ((Long) arguments[1]).longValue());
        }

        if (_methodName539.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes539, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.getSubscribedUsers(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        throw new UnsupportedOperationException();
    }
}
