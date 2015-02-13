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
    private String _methodName588;
    private String[] _methodParameterTypes588;
    private String _methodName589;
    private String[] _methodParameterTypes589;
    private String _methodName595;
    private String[] _methodParameterTypes595;
    private String _methodName596;
    private String[] _methodParameterTypes596;
    private String _methodName597;
    private String[] _methodParameterTypes597;
    private String _methodName598;
    private String[] _methodParameterTypes598;
    private String _methodName599;
    private String[] _methodParameterTypes599;
    private String _methodName600;
    private String[] _methodParameterTypes600;
    private String _methodName601;
    private String[] _methodParameterTypes601;
    private String _methodName602;
    private String[] _methodParameterTypes602;
    private String _methodName603;
    private String[] _methodParameterTypes603;
    private String _methodName604;
    private String[] _methodParameterTypes604;
    private String _methodName605;
    private String[] _methodParameterTypes605;
    private String _methodName606;
    private String[] _methodParameterTypes606;
    private String _methodName607;
    private String[] _methodParameterTypes607;
    private String _methodName608;
    private String[] _methodParameterTypes608;
    private String _methodName609;
    private String[] _methodParameterTypes609;
    private String _methodName610;
    private String[] _methodParameterTypes610;
    private String _methodName611;
    private String[] _methodParameterTypes611;
    private String _methodName612;
    private String[] _methodParameterTypes612;
    private String _methodName613;
    private String[] _methodParameterTypes613;
    private String _methodName614;
    private String[] _methodParameterTypes614;

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

        _methodName588 = "getBeanIdentifier";

        _methodParameterTypes588 = new String[] {  };

        _methodName589 = "setBeanIdentifier";

        _methodParameterTypes589 = new String[] { "java.lang.String" };

        _methodName595 = "getActivitySubscriptions";

        _methodParameterTypes595 = new String[] {
                "java.lang.Class", "java.lang.Long", "java.lang.Integer",
                "java.lang.String"
            };

        _methodName596 = "findByUser";

        _methodParameterTypes596 = new String[] { "java.lang.Long" };

        _methodName597 = "isSubscribed";

        _methodParameterTypes597 = new String[] {
                "java.lang.Long", "java.lang.Long", "java.lang.Long",
                "java.lang.Integer", "java.lang.String"
            };

        _methodName598 = "isSubscribed";

        _methodParameterTypes598 = new String[] {
                "java.lang.Long", "java.lang.Class", "java.lang.Long",
                "java.lang.Integer", "java.lang.String"
            };

        _methodName599 = "deleteSubscription";

        _methodParameterTypes599 = new String[] {
                "java.lang.Long", "java.lang.Long", "java.lang.Long",
                "java.lang.Integer", "java.lang.String"
            };

        _methodName600 = "deleteSubscription";

        _methodParameterTypes600 = new String[] {
                "java.lang.Long", "java.lang.Long", "java.lang.Long",
                "java.lang.Integer", "java.lang.String", "boolean"
            };

        _methodName601 = "deleteSubscription";

        _methodParameterTypes601 = new String[] {
                "java.lang.Long", "java.lang.Class", "java.lang.Long",
                "java.lang.Integer", "java.lang.String", "boolean"
            };

        _methodName602 = "deleteSubscription";

        _methodParameterTypes602 = new String[] {
                "java.lang.Long", "java.lang.Class", "java.lang.Long",
                "java.lang.Integer", "java.lang.String"
            };

        _methodName603 = "addSubscription";

        _methodParameterTypes603 = new String[] {
                "java.lang.Long", "java.lang.Long", "java.lang.Integer",
                "java.lang.String", "java.lang.Long"
            };

        _methodName604 = "addSubscription";

        _methodParameterTypes604 = new String[] {
                "java.lang.Long", "java.lang.Long", "java.lang.Integer",
                "java.lang.String", "java.lang.Long", "boolean"
            };

        _methodName605 = "addSubscription";

        _methodParameterTypes605 = new String[] {
                "java.lang.Class", "java.lang.Long", "java.lang.Integer",
                "java.lang.String", "java.lang.Long"
            };

        _methodName606 = "addSubscription";

        _methodParameterTypes606 = new String[] {
                "java.lang.Class", "java.lang.Long", "java.lang.Integer",
                "java.lang.String", "java.lang.Long", "boolean"
            };

        _methodName607 = "getActivities";

        _methodParameterTypes607 = new String[] { "java.lang.Long", "int", "int" };

        _methodName608 = "store";

        _methodParameterTypes608 = new String[] {
                "com.ext.portlet.model.ActivitySubscription"
            };

        _methodName609 = "getName";

        _methodParameterTypes609 = new String[] {
                "com.ext.portlet.model.ActivitySubscription"
            };

        _methodName610 = "getSubscriptionType";

        _methodParameterTypes610 = new String[] {
                "com.ext.portlet.model.ActivitySubscription"
            };

        _methodName611 = "delete";

        _methodParameterTypes611 = new String[] {
                "com.ext.portlet.model.ActivitySubscription"
            };

        _methodName612 = "sendEmailNotifications";

        _methodParameterTypes612 = new String[] {
                "com.liferay.portal.service.ServiceContext"
            };

        _methodName613 = "getSubscribedUsers";

        _methodParameterTypes613 = new String[] { "java.lang.Class", "long" };

        _methodName614 = "getSubscribedUsers";

        _methodParameterTypes614 = new String[] { "long", "long" };
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

        if (_methodName588.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes588, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName589.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes589, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName595.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes595, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.getActivitySubscriptions((java.lang.Class) arguments[0],
                (java.lang.Long) arguments[1],
                (java.lang.Integer) arguments[2],
                (java.lang.String) arguments[3]);
        }

        if (_methodName596.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes596, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.findByUser((java.lang.Long) arguments[0]);
        }

        if (_methodName597.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes597, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.isSubscribed((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1], (java.lang.Long) arguments[2],
                (java.lang.Integer) arguments[3],
                (java.lang.String) arguments[4]);
        }

        if (_methodName598.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes598, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.isSubscribed((java.lang.Long) arguments[0],
                (java.lang.Class) arguments[1], (java.lang.Long) arguments[2],
                (java.lang.Integer) arguments[3],
                (java.lang.String) arguments[4]);
        }

        if (_methodName599.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes599, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.deleteSubscription((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1], (java.lang.Long) arguments[2],
                (java.lang.Integer) arguments[3],
                (java.lang.String) arguments[4]);

            return null;
        }

        if (_methodName600.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes600, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.deleteSubscription((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1], (java.lang.Long) arguments[2],
                (java.lang.Integer) arguments[3],
                (java.lang.String) arguments[4],
                ((Boolean) arguments[5]).booleanValue());

            return null;
        }

        if (_methodName601.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes601, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.deleteSubscription((java.lang.Long) arguments[0],
                (java.lang.Class) arguments[1], (java.lang.Long) arguments[2],
                (java.lang.Integer) arguments[3],
                (java.lang.String) arguments[4],
                ((Boolean) arguments[5]).booleanValue());

            return null;
        }

        if (_methodName602.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes602, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.deleteSubscription((java.lang.Long) arguments[0],
                (java.lang.Class) arguments[1], (java.lang.Long) arguments[2],
                (java.lang.Integer) arguments[3],
                (java.lang.String) arguments[4]);

            return null;
        }

        if (_methodName603.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes603, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.addSubscription((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1],
                (java.lang.Integer) arguments[2],
                (java.lang.String) arguments[3], (java.lang.Long) arguments[4]);

            return null;
        }

        if (_methodName604.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes604, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.addSubscription((java.lang.Long) arguments[0],
                (java.lang.Long) arguments[1],
                (java.lang.Integer) arguments[2],
                (java.lang.String) arguments[3], (java.lang.Long) arguments[4],
                ((Boolean) arguments[5]).booleanValue());

            return null;
        }

        if (_methodName605.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes605, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.addSubscription((java.lang.Class) arguments[0],
                (java.lang.Long) arguments[1],
                (java.lang.Integer) arguments[2],
                (java.lang.String) arguments[3], (java.lang.Long) arguments[4]);

            return null;
        }

        if (_methodName606.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes606, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.addSubscription((java.lang.Class) arguments[0],
                (java.lang.Long) arguments[1],
                (java.lang.Integer) arguments[2],
                (java.lang.String) arguments[3], (java.lang.Long) arguments[4],
                ((Boolean) arguments[5]).booleanValue());

            return null;
        }

        if (_methodName607.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes607, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.getActivities((java.lang.Long) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName608.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes608, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.store((com.ext.portlet.model.ActivitySubscription) arguments[0]);

            return null;
        }

        if (_methodName609.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes609, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.getName((com.ext.portlet.model.ActivitySubscription) arguments[0]);
        }

        if (_methodName610.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes610, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.getSubscriptionType((com.ext.portlet.model.ActivitySubscription) arguments[0]);
        }

        if (_methodName611.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes611, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.delete((com.ext.portlet.model.ActivitySubscription) arguments[0]);

            return null;
        }

        if (_methodName612.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes612, parameterTypes)) {
            ActivitySubscriptionLocalServiceUtil.sendEmailNotifications((com.liferay.portal.service.ServiceContext) arguments[0]);

            return null;
        }

        if (_methodName613.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes613, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.getSubscribedUsers((java.lang.Class) arguments[0],
                ((Long) arguments[1]).longValue());
        }

        if (_methodName614.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes614, parameterTypes)) {
            return ActivitySubscriptionLocalServiceUtil.getSubscribedUsers(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        throw new UnsupportedOperationException();
    }
}
