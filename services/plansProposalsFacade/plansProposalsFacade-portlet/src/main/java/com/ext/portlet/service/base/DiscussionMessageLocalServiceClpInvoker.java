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
    private String _methodName540;
    private String[] _methodParameterTypes540;
    private String _methodName541;
    private String[] _methodParameterTypes541;
    private String _methodName546;
    private String[] _methodParameterTypes546;
    private String _methodName547;
    private String[] _methodParameterTypes547;
    private String _methodName548;
    private String[] _methodParameterTypes548;
    private String _methodName549;
    private String[] _methodParameterTypes549;
    private String _methodName550;
    private String[] _methodParameterTypes550;
    private String _methodName551;
    private String[] _methodParameterTypes551;
    private String _methodName552;
    private String[] _methodParameterTypes552;
    private String _methodName553;
    private String[] _methodParameterTypes553;
    private String _methodName554;
    private String[] _methodParameterTypes554;
    private String _methodName555;
    private String[] _methodParameterTypes555;
    private String _methodName556;
    private String[] _methodParameterTypes556;
    private String _methodName557;
    private String[] _methodParameterTypes557;
    private String _methodName558;
    private String[] _methodParameterTypes558;
    private String _methodName559;
    private String[] _methodParameterTypes559;
    private String _methodName560;
    private String[] _methodParameterTypes560;
    private String _methodName561;
    private String[] _methodParameterTypes561;
    private String _methodName562;
    private String[] _methodParameterTypes562;
    private String _methodName563;
    private String[] _methodParameterTypes563;
    private String _methodName564;
    private String[] _methodParameterTypes564;
    private String _methodName565;
    private String[] _methodParameterTypes565;
    private String _methodName566;
    private String[] _methodParameterTypes566;
    private String _methodName567;
    private String[] _methodParameterTypes567;
    private String _methodName568;
    private String[] _methodParameterTypes568;
    private String _methodName569;
    private String[] _methodParameterTypes569;
    private String _methodName570;
    private String[] _methodParameterTypes570;

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

        _methodName540 = "getBeanIdentifier";

        _methodParameterTypes540 = new String[] {  };

        _methodName541 = "setBeanIdentifier";

        _methodParameterTypes541 = new String[] { "java.lang.String" };

        _methodName546 = "getThreadsByCategory";

        _methodParameterTypes546 = new String[] { "long" };

        _methodName547 = "getThreadMessages";

        _methodParameterTypes547 = new String[] { "long" };

        _methodName548 = "getThreadMessagesCount";

        _methodParameterTypes548 = new String[] { "long" };

        _methodName549 = "getThreadByThreadId";

        _methodParameterTypes549 = new String[] { "long" };

        _methodName550 = "addThread";

        _methodParameterTypes550 = new String[] {
                "long", "long", "java.lang.String", "java.lang.String",
                "com.liferay.portal.model.User"
            };

        _methodName551 = "addMessage";

        _methodParameterTypes551 = new String[] {
                "long", "long", "long", "java.lang.String", "java.lang.String",
                "com.liferay.portal.model.User"
            };

        _methodName552 = "search";

        _methodParameterTypes552 = new String[] { "java.lang.String", "long" };

        _methodName553 = "getMessageByMessageId";

        _methodParameterTypes553 = new String[] { "long" };

        _methodName554 = "reIndex";

        _methodParameterTypes554 = new String[] {  };

        _methodName555 = "reIndex";

        _methodParameterTypes555 = new String[] { "long" };

        _methodName556 = "getThreadMessages";

        _methodParameterTypes556 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName557 = "getThreadMessagesCount";

        _methodParameterTypes557 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName558 = "store";

        _methodParameterTypes558 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName559 = "addThreadMessage";

        _methodParameterTypes559 = new String[] {
                "com.ext.portlet.model.DiscussionMessage", "java.lang.String",
                "java.lang.String", "com.liferay.portal.model.User"
            };

        _methodName560 = "getAuthor";

        _methodParameterTypes560 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName561 = "getLastActivityAuthor";

        _methodParameterTypes561 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName562 = "delete";

        _methodParameterTypes562 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName563 = "update";

        _methodParameterTypes563 = new String[] {
                "com.ext.portlet.model.DiscussionMessage", "java.lang.String",
                "java.lang.String"
            };

        _methodName564 = "getCategory";

        _methodParameterTypes564 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName565 = "getCategoryGroup";

        _methodParameterTypes565 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName566 = "getThread";

        _methodParameterTypes566 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName567 = "getFlags";

        _methodParameterTypes567 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName568 = "addFlag";

        _methodParameterTypes568 = new String[] {
                "com.ext.portlet.model.DiscussionMessage", "java.lang.String",
                "java.lang.String", "com.liferay.portal.model.User"
            };

        _methodName569 = "removeFlag";

        _methodParameterTypes569 = new String[] {
                "com.ext.portlet.model.DiscussionMessage", "java.lang.String"
            };

        _methodName570 = "hasFlag";

        _methodParameterTypes570 = new String[] { "long", "java.lang.String" };
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

        if (_methodName540.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes540, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName541.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes541, parameterTypes)) {
            DiscussionMessageLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName546.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes546, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getThreadsByCategory(((Long) arguments[0]).longValue());
        }

        if (_methodName547.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes547, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getThreadMessages(((Long) arguments[0]).longValue());
        }

        if (_methodName548.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes548, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getThreadMessagesCount(((Long) arguments[0]).longValue());
        }

        if (_methodName549.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes549, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getThreadByThreadId(((Long) arguments[0]).longValue());
        }

        if (_methodName550.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes550, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.addThread(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                (com.liferay.portal.model.User) arguments[4]);
        }

        if (_methodName551.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes551, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.addMessage(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Long) arguments[2]).longValue(),
                (java.lang.String) arguments[3],
                (java.lang.String) arguments[4],
                (com.liferay.portal.model.User) arguments[5]);
        }

        if (_methodName552.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes552, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.search((java.lang.String) arguments[0],
                ((Long) arguments[1]).longValue());
        }

        if (_methodName553.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes553, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getMessageByMessageId(((Long) arguments[0]).longValue());
        }

        if (_methodName554.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes554, parameterTypes)) {
            DiscussionMessageLocalServiceUtil.reIndex();

            return null;
        }

        if (_methodName555.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes555, parameterTypes)) {
            DiscussionMessageLocalServiceUtil.reIndex(((Long) arguments[0]).longValue());

            return null;
        }

        if (_methodName556.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes556, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getThreadMessages((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName557.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes557, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getThreadMessagesCount((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName558.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes558, parameterTypes)) {
            DiscussionMessageLocalServiceUtil.store((com.ext.portlet.model.DiscussionMessage) arguments[0]);

            return null;
        }

        if (_methodName559.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes559, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.addThreadMessage((com.ext.portlet.model.DiscussionMessage) arguments[0],
                (java.lang.String) arguments[1],
                (java.lang.String) arguments[2],
                (com.liferay.portal.model.User) arguments[3]);
        }

        if (_methodName560.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes560, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getAuthor((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName561.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes561, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getLastActivityAuthor((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName562.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes562, parameterTypes)) {
            DiscussionMessageLocalServiceUtil.delete((com.ext.portlet.model.DiscussionMessage) arguments[0]);

            return null;
        }

        if (_methodName563.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes563, parameterTypes)) {
            DiscussionMessageLocalServiceUtil.update((com.ext.portlet.model.DiscussionMessage) arguments[0],
                (java.lang.String) arguments[1], (java.lang.String) arguments[2]);

            return null;
        }

        if (_methodName564.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes564, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getCategory((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName565.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes565, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getCategoryGroup((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName566.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes566, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getThread((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName567.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes567, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getFlags((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName568.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes568, parameterTypes)) {
            DiscussionMessageLocalServiceUtil.addFlag((com.ext.portlet.model.DiscussionMessage) arguments[0],
                (java.lang.String) arguments[1],
                (java.lang.String) arguments[2],
                (com.liferay.portal.model.User) arguments[3]);

            return null;
        }

        if (_methodName569.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes569, parameterTypes)) {
            DiscussionMessageLocalServiceUtil.removeFlag((com.ext.portlet.model.DiscussionMessage) arguments[0],
                (java.lang.String) arguments[1]);

            return null;
        }

        if (_methodName570.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes570, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.hasFlag(((Long) arguments[0]).longValue(),
                (java.lang.String) arguments[1]);
        }

        throw new UnsupportedOperationException();
    }
}
