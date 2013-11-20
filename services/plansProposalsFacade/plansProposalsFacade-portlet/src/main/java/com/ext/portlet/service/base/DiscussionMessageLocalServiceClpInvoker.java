package com.ext.portlet.service.base;

import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DiscussionMessageLocalServiceClpInvoker {
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
    private String _methodName478;
    private String[] _methodParameterTypes478;
    private String _methodName479;
    private String[] _methodParameterTypes479;
    private String _methodName484;
    private String[] _methodParameterTypes484;
    private String _methodName485;
    private String[] _methodParameterTypes485;
    private String _methodName486;
    private String[] _methodParameterTypes486;
    private String _methodName487;
    private String[] _methodParameterTypes487;
    private String _methodName488;
    private String[] _methodParameterTypes488;
    private String _methodName489;
    private String[] _methodParameterTypes489;
    private String _methodName490;
    private String[] _methodParameterTypes490;
    private String _methodName491;
    private String[] _methodParameterTypes491;
    private String _methodName492;
    private String[] _methodParameterTypes492;
    private String _methodName493;
    private String[] _methodParameterTypes493;
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

    public DiscussionMessageLocalServiceClpInvoker() {
        _methodName0 = "addDiscussionMessage";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName1 = "createDiscussionMessage";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deleteDiscussionMessage";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deleteDiscussionMessage";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
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

        _methodName10 = "fetchDiscussionMessage";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getDiscussionMessage";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getDiscussionMessages";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getDiscussionMessagesCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateDiscussionMessage";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName478 = "getBeanIdentifier";

        _methodParameterTypes478 = new String[] {  };

        _methodName479 = "setBeanIdentifier";

        _methodParameterTypes479 = new String[] { "java.lang.String" };

        _methodName484 = "getThreadsByCategory";

        _methodParameterTypes484 = new String[] { "long" };

        _methodName485 = "getThreadMessages";

        _methodParameterTypes485 = new String[] { "long" };

        _methodName486 = "getThreadMessagesCount";

        _methodParameterTypes486 = new String[] { "long" };

        _methodName487 = "getThreadByThreadId";

        _methodParameterTypes487 = new String[] { "long" };

        _methodName488 = "addThread";

        _methodParameterTypes488 = new String[] {
                "long", "long", "java.lang.String", "java.lang.String",
                "com.liferay.portal.model.User"
            };

        _methodName489 = "addMessage";

        _methodParameterTypes489 = new String[] {
                "long", "long", "long", "java.lang.String", "java.lang.String",
                "com.liferay.portal.model.User"
            };

        _methodName490 = "search";

        _methodParameterTypes490 = new String[] { "java.lang.String", "long" };

        _methodName491 = "getMessageByMessageId";

        _methodParameterTypes491 = new String[] { "long" };

        _methodName492 = "reIndex";

        _methodParameterTypes492 = new String[] {  };

        _methodName493 = "reIndex";

        _methodParameterTypes493 = new String[] { "long" };

        _methodName494 = "getThreadMessages";

        _methodParameterTypes494 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName495 = "getThreadMessagesCount";

        _methodParameterTypes495 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName496 = "store";

        _methodParameterTypes496 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName497 = "addThreadMessage";

        _methodParameterTypes497 = new String[] {
                "com.ext.portlet.model.DiscussionMessage", "java.lang.String",
                "java.lang.String", "com.liferay.portal.model.User"
            };

        _methodName498 = "getAuthor";

        _methodParameterTypes498 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName499 = "getLastActivityAuthor";

        _methodParameterTypes499 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName500 = "delete";

        _methodParameterTypes500 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName501 = "update";

        _methodParameterTypes501 = new String[] {
                "com.ext.portlet.model.DiscussionMessage", "java.lang.String",
                "java.lang.String"
            };

        _methodName502 = "getCategory";

        _methodParameterTypes502 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName503 = "getCategoryGroup";

        _methodParameterTypes503 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName504 = "getThread";

        _methodParameterTypes504 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName505 = "getFlags";

        _methodParameterTypes505 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName506 = "addFlag";

        _methodParameterTypes506 = new String[] {
                "com.ext.portlet.model.DiscussionMessage", "java.lang.String",
                "java.lang.String", "com.liferay.portal.model.User"
            };

        _methodName507 = "removeFlag";

        _methodParameterTypes507 = new String[] {
                "com.ext.portlet.model.DiscussionMessage", "java.lang.String"
            };

        _methodName508 = "hasFlag";

        _methodParameterTypes508 = new String[] { "long", "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.addDiscussionMessage((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.createDiscussionMessage(((Long) arguments[0]).longValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.deleteDiscussionMessage(((Long) arguments[0]).longValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.deleteDiscussionMessage((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.fetchDiscussionMessage(((Long) arguments[0]).longValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getDiscussionMessage(((Long) arguments[0]).longValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getDiscussionMessages(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getDiscussionMessagesCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.updateDiscussionMessage((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName478.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes478, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName479.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes479, parameterTypes)) {
            DiscussionMessageLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName484.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes484, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getThreadsByCategory(((Long) arguments[0]).longValue());
        }

        if (_methodName485.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes485, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getThreadMessages(((Long) arguments[0]).longValue());
        }

        if (_methodName486.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes486, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getThreadMessagesCount(((Long) arguments[0]).longValue());
        }

        if (_methodName487.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes487, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getThreadByThreadId(((Long) arguments[0]).longValue());
        }

        if (_methodName488.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes488, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.addThread(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                (com.liferay.portal.model.User) arguments[4]);
        }

        if (_methodName489.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes489, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.addMessage(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Long) arguments[2]).longValue(),
                (java.lang.String) arguments[3],
                (java.lang.String) arguments[4],
                (com.liferay.portal.model.User) arguments[5]);
        }

        if (_methodName490.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes490, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.search((java.lang.String) arguments[0],
                ((Long) arguments[1]).longValue());
        }

        if (_methodName491.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes491, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getMessageByMessageId(((Long) arguments[0]).longValue());
        }

        if (_methodName492.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes492, parameterTypes)) {
            DiscussionMessageLocalServiceUtil.reIndex();

            return null;
        }

        if (_methodName493.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes493, parameterTypes)) {
            DiscussionMessageLocalServiceUtil.reIndex(((Long) arguments[0]).longValue());

            return null;
        }

        if (_methodName494.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes494, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getThreadMessages((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName495.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes495, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getThreadMessagesCount((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName496.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes496, parameterTypes)) {
            DiscussionMessageLocalServiceUtil.store((com.ext.portlet.model.DiscussionMessage) arguments[0]);

            return null;
        }

        if (_methodName497.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes497, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.addThreadMessage((com.ext.portlet.model.DiscussionMessage) arguments[0],
                (java.lang.String) arguments[1],
                (java.lang.String) arguments[2],
                (com.liferay.portal.model.User) arguments[3]);
        }

        if (_methodName498.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes498, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getAuthor((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName499.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes499, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getLastActivityAuthor((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName500.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes500, parameterTypes)) {
            DiscussionMessageLocalServiceUtil.delete((com.ext.portlet.model.DiscussionMessage) arguments[0]);

            return null;
        }

        if (_methodName501.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes501, parameterTypes)) {
            DiscussionMessageLocalServiceUtil.update((com.ext.portlet.model.DiscussionMessage) arguments[0],
                (java.lang.String) arguments[1], (java.lang.String) arguments[2]);

            return null;
        }

        if (_methodName502.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes502, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getCategory((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName503.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes503, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getCategoryGroup((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName504.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes504, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getThread((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName505.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes505, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getFlags((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName506.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes506, parameterTypes)) {
            DiscussionMessageLocalServiceUtil.addFlag((com.ext.portlet.model.DiscussionMessage) arguments[0],
                (java.lang.String) arguments[1],
                (java.lang.String) arguments[2],
                (com.liferay.portal.model.User) arguments[3]);

            return null;
        }

        if (_methodName507.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes507, parameterTypes)) {
            DiscussionMessageLocalServiceUtil.removeFlag((com.ext.portlet.model.DiscussionMessage) arguments[0],
                (java.lang.String) arguments[1]);

            return null;
        }

        if (_methodName508.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes508, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.hasFlag(((Long) arguments[0]).longValue(),
                (java.lang.String) arguments[1]);
        }

        throw new UnsupportedOperationException();
    }
}
