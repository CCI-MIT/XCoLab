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
    private String _methodName638;
    private String[] _methodParameterTypes638;
    private String _methodName639;
    private String[] _methodParameterTypes639;
    private String _methodName645;
    private String[] _methodParameterTypes645;
    private String _methodName646;
    private String[] _methodParameterTypes646;
    private String _methodName647;
    private String[] _methodParameterTypes647;
    private String _methodName648;
    private String[] _methodParameterTypes648;
    private String _methodName649;
    private String[] _methodParameterTypes649;
    private String _methodName650;
    private String[] _methodParameterTypes650;
    private String _methodName651;
    private String[] _methodParameterTypes651;
    private String _methodName652;
    private String[] _methodParameterTypes652;
    private String _methodName653;
    private String[] _methodParameterTypes653;
    private String _methodName654;
    private String[] _methodParameterTypes654;
    private String _methodName655;
    private String[] _methodParameterTypes655;
    private String _methodName656;
    private String[] _methodParameterTypes656;
    private String _methodName657;
    private String[] _methodParameterTypes657;
    private String _methodName658;
    private String[] _methodParameterTypes658;
    private String _methodName659;
    private String[] _methodParameterTypes659;
    private String _methodName660;
    private String[] _methodParameterTypes660;
    private String _methodName661;
    private String[] _methodParameterTypes661;
    private String _methodName662;
    private String[] _methodParameterTypes662;
    private String _methodName663;
    private String[] _methodParameterTypes663;
    private String _methodName664;
    private String[] _methodParameterTypes664;

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

        _methodName638 = "getBeanIdentifier";

        _methodParameterTypes638 = new String[] {  };

        _methodName639 = "setBeanIdentifier";

        _methodParameterTypes639 = new String[] { "java.lang.String" };

        _methodName645 = "getActivitySubscriptions";

        _methodParameterTypes645 = new String[] {
                "java.lang.Class", "java.lang.Long", "java.lang.Integer",
                "java.lang.String"
            };

        _methodName646 = "findByUser";

        _methodParameterTypes646 = new String[] { "java.lang.Long" };

        _methodName647 = "isSubscribed";

        _methodParameterTypes647 = new String[] {
                "java.lang.Long", "java.lang.Long", "java.lang.Long",
                "java.lang.Integer", "java.lang.String"
            };

        _methodName648 = "isSubscribed";

        _methodParameterTypes648 = new String[] {
                "java.lang.Long", "java.lang.Class", "java.lang.Long",
                "java.lang.Integer", "java.lang.String"
            };

        _methodName649 = "deleteSubscription";

        _methodParameterTypes649 = new String[] {
                "java.lang.Long", "java.lang.Long", "java.lang.Long",
                "java.lang.Integer", "java.lang.String"
            };

        _methodName650 = "deleteSubscription";

        _methodParameterTypes650 = new String[] {
                "java.lang.Long", "java.lang.Long", "java.lang.Long",
                "java.lang.Integer", "java.lang.String", "boolean"
            };

        _methodName651 = "deleteSubscription";

        _methodParameterTypes651 = new String[] {
                "java.lang.Long", "java.lang.Class", "java.lang.Long",
                "java.lang.Integer", "java.lang.String", "boolean"
            };

        _methodName652 = "deleteSubscription";

        _methodParameterTypes652 = new String[] {
                "java.lang.Long", "java.lang.Class", "java.lang.Long",
                "java.lang.Integer", "java.lang.String"
            };

        _methodName653 = "addSubscription";

        _methodParameterTypes653 = new String[] {
                "java.lang.Long", "java.lang.Long", "java.lang.Integer",
                "java.lang.String", "java.lang.Long"
            };

        _methodName654 = "addSubscription";

        _methodParameterTypes654 = new String[] {
                "java.lang.Long", "java.lang.Long", "java.lang.Integer",
                "java.lang.String", "java.lang.Long", "boolean"
            };

        _methodName655 = "addSubscription";

        _methodParameterTypes655 = new String[] {
                "java.lang.Class", "java.lang.Long", "java.lang.Integer",
                "java.lang.String", "java.lang.Long"
            };

        _methodName656 = "addSubscription";

        _methodParameterTypes656 = new String[] {
                "java.lang.Class", "java.lang.Long", "java.lang.Integer",
                "java.lang.String", "java.lang.Long", "boolean"
            };

        _methodName657 = "getActivities";

        _methodParameterTypes657 = new String[] { "java.lang.Long", "int", "int" };

        _methodName658 = "store";

        _methodParameterTypes658 = new String[] {
                "com.ext.portlet.model.ActivitySubscription"
            };

        _methodName659 = "getName";

        _methodParameterTypes659 = new String[] {
                "com.ext.portlet.model.ActivitySubscription"
            };

        _methodName660 = "getSubscriptionType";

        _methodParameterTypes660 = new String[] {
                "com.ext.portlet.model.ActivitySubscription"
            };

        _methodName661 = "delete";

        _methodParameterTypes661 = new String[] {
                "com.ext.portlet.model.ActivitySubscription"
            };

        _methodName662 = "sendEmailNotifications";

        _methodParameterTypes662 = new String[] {
                "com.liferay.portal.service.ServiceContext"
            };

        _methodName663 = "getSubscribedUsers";

        _methodParameterTypes663 = new String[] { "java.lang.Class", "long" };

        _methodName664 = "getSubscribedUsers";

        _methodParameterTypes664 = new String[] { "long", "long" };
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

        if (_methodName638.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes638, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName639.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes639, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName645.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes645, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.getActivitySubscriptions((java.lang.Class) arguments[0],
                (java.lang.Long) arguments[1],
                (java.lang.Integer) arguments[2],
                (java.lang.String) arguments[3]);
        }

        if (_methodName646.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes646, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.findByUser((java.lang.Long) arguments[0]);
        }

        if (_methodName647.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes647, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.isSubscribed((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1], (java.lang.Long) arguments[2],
                (java.lang.Integer) arguments[3],
                (java.lang.String) arguments[4]);
        }

        if (_methodName648.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes648, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.isSubscribed((java.lang.Long) arguments[0],
                (java.lang.Class) arguments[1], (java.lang.Long) arguments[2],
                (java.lang.Integer) arguments[3],
                (java.lang.String) arguments[4]);
        }

        if (_methodName649.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes649, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.deleteSubscription((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1], (java.lang.Long) arguments[2],
                (java.lang.Integer) arguments[3],
                (java.lang.String) arguments[4]);

            return null;
        }

        if (_methodName650.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes650, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.deleteSubscription((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1], (java.lang.Long) arguments[2],
                (java.lang.Integer) arguments[3],
                (java.lang.String) arguments[4],
                ((Boolean) arguments[5]).booleanValue());

            return null;
        }

        if (_methodName651.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes651, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.deleteSubscription((java.lang.Long) arguments[0],
                (java.lang.Class) arguments[1], (java.lang.Long) arguments[2],
                (java.lang.Integer) arguments[3],
                (java.lang.String) arguments[4],
                ((Boolean) arguments[5]).booleanValue());

            return null;
        }

        if (_methodName652.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes652, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.deleteSubscription((java.lang.Long) arguments[0],
                (java.lang.Class) arguments[1], (java.lang.Long) arguments[2],
                (java.lang.Integer) arguments[3],
                (java.lang.String) arguments[4]);

            return null;
        }

        if (_methodName653.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes653, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.addSubscription((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1],
                (java.lang.Integer) arguments[2],
                (java.lang.String) arguments[3], (java.lang.Long) arguments[4]);

            return null;
        }

        if (_methodName654.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes654, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.addSubscription((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1],
                (java.lang.Integer) arguments[2],
                (java.lang.String) arguments[3], (java.lang.Long) arguments[4],
                ((Boolean) arguments[5]).booleanValue());

            return null;
        }

        if (_methodName655.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes655, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.addSubscription((java.lang.Class) arguments[0],
                (java.lang.Long) arguments[1],
                (java.lang.Integer) arguments[2],
                (java.lang.String) arguments[3], (java.lang.Long) arguments[4]);

            return null;
        }

        if (_methodName656.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes656, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.addSubscription((java.lang.Class) arguments[0],
                (java.lang.Long) arguments[1],
                (java.lang.Integer) arguments[2],
                (java.lang.String) arguments[3], (java.lang.Long) arguments[4],
                ((Boolean) arguments[5]).booleanValue());

            return null;
        }

        if (_methodName657.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes657, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.getActivities((java.lang.Long) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName658.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes658, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.store((com.ext.portlet.model.ActivitySubscription) arguments[0]);

            return null;
        }

        if (_methodName659.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes659, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.getName((com.ext.portlet.model.ActivitySubscription) arguments[0]);
        }

        if (_methodName660.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes660, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.getSubscriptionType((com.ext.portlet.model.ActivitySubscription) arguments[0]);
        }

        if (_methodName661.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes661, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.delete((com.ext.portlet.model.ActivitySubscription) arguments[0]);

            return null;
        }

        if (_methodName662.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes662, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.sendEmailNotifications((com.liferay.portal.service.ServiceContext) arguments[0]);

            return null;
        }

        if (_methodName663.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes663, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.getSubscribedUsers((java.lang.Class) arguments[0],
                ((Long) arguments[1]).longValue());
        }

        if (_methodName664.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes664, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.getSubscribedUsers(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        throw new UnsupportedOperationException();
    }
}
