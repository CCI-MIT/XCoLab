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
    private String _methodName602;
    private String[] _methodParameterTypes602;
    private String _methodName603;
    private String[] _methodParameterTypes603;
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
    private String _methodName615;
    private String[] _methodParameterTypes615;
    private String _methodName616;
    private String[] _methodParameterTypes616;
    private String _methodName617;
    private String[] _methodParameterTypes617;
    private String _methodName618;
    private String[] _methodParameterTypes618;
    private String _methodName619;
    private String[] _methodParameterTypes619;
    private String _methodName620;
    private String[] _methodParameterTypes620;
    private String _methodName621;
    private String[] _methodParameterTypes621;
    private String _methodName622;
    private String[] _methodParameterTypes622;
    private String _methodName623;
    private String[] _methodParameterTypes623;
    private String _methodName624;
    private String[] _methodParameterTypes624;
    private String _methodName625;
    private String[] _methodParameterTypes625;
    private String _methodName626;
    private String[] _methodParameterTypes626;
    private String _methodName627;
    private String[] _methodParameterTypes627;
    private String _methodName628;
    private String[] _methodParameterTypes628;
    private String _methodName629;
    private String[] _methodParameterTypes629;
    private String _methodName630;
    private String[] _methodParameterTypes630;
    private String _methodName631;
    private String[] _methodParameterTypes631;
    private String _methodName632;
    private String[] _methodParameterTypes632;

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

        _methodName602 = "getBeanIdentifier";

        _methodParameterTypes602 = new String[] {  };

        _methodName603 = "setBeanIdentifier";

        _methodParameterTypes603 = new String[] { "java.lang.String" };

        _methodName608 = "getThreadsByCategory";

        _methodParameterTypes608 = new String[] { "long" };

        _methodName609 = "getThreadMessages";

        _methodParameterTypes609 = new String[] { "long" };

        _methodName610 = "getThreadMessagesCount";

        _methodParameterTypes610 = new String[] { "long" };

        _methodName611 = "getThreadByThreadId";

        _methodParameterTypes611 = new String[] { "long" };

        _methodName612 = "addThread";

        _methodParameterTypes612 = new String[] {
                "long", "long", "java.lang.String", "java.lang.String",
                "com.liferay.portal.model.User"
            };

        _methodName613 = "addMessage";

        _methodParameterTypes613 = new String[] {
                "long", "long", "long", "java.lang.String", "java.lang.String",
                "com.liferay.portal.model.User"
            };

        _methodName614 = "search";

        _methodParameterTypes614 = new String[] { "java.lang.String", "long" };

        _methodName615 = "getMessageByMessageId";

        _methodParameterTypes615 = new String[] { "long" };

        _methodName616 = "reIndex";

        _methodParameterTypes616 = new String[] {  };

        _methodName617 = "reIndex";

        _methodParameterTypes617 = new String[] { "long" };

        _methodName618 = "getThreadMessages";

        _methodParameterTypes618 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName619 = "getThreadMessagesCount";

        _methodParameterTypes619 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName620 = "store";

        _methodParameterTypes620 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName621 = "addThreadMessage";

        _methodParameterTypes621 = new String[] {
                "com.ext.portlet.model.DiscussionMessage", "java.lang.String",
                "java.lang.String", "com.liferay.portal.model.User"
            };

        _methodName622 = "getAuthor";

        _methodParameterTypes622 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName623 = "getLastActivityAuthor";

        _methodParameterTypes623 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName624 = "delete";

        _methodParameterTypes624 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName625 = "update";

        _methodParameterTypes625 = new String[] {
                "com.ext.portlet.model.DiscussionMessage", "java.lang.String",
                "java.lang.String"
            };

        _methodName626 = "getCategory";

        _methodParameterTypes626 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName627 = "getCategoryGroup";

        _methodParameterTypes627 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName628 = "getThread";

        _methodParameterTypes628 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName629 = "getFlags";

        _methodParameterTypes629 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName630 = "addFlag";

        _methodParameterTypes630 = new String[] {
                "com.ext.portlet.model.DiscussionMessage", "java.lang.String",
                "java.lang.String", "com.liferay.portal.model.User"
            };

        _methodName631 = "removeFlag";

        _methodParameterTypes631 = new String[] {
                "com.ext.portlet.model.DiscussionMessage", "java.lang.String"
            };

        _methodName632 = "hasFlag";

        _methodParameterTypes632 = new String[] { "long", "java.lang.String" };
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

        if (_methodName602.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes602, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName603.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes603, parameterTypes)) {
            DiscussionMessageLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName608.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes608, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getThreadsByCategory(((Long) arguments[0]).longValue());
        }

        if (_methodName609.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes609, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getThreadMessages(((Long) arguments[0]).longValue());
        }

        if (_methodName610.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes610, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getThreadMessagesCount(((Long) arguments[0]).longValue());
        }

        if (_methodName611.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes611, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getThreadByThreadId(((Long) arguments[0]).longValue());
        }

        if (_methodName612.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes612, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.addThread(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                (com.liferay.portal.model.User) arguments[4]);
        }

        if (_methodName613.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes613, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.addMessage(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Long) arguments[2]).longValue(),
                (java.lang.String) arguments[3],
                (java.lang.String) arguments[4],
                (com.liferay.portal.model.User) arguments[5]);
        }

        if (_methodName614.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes614, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.search((java.lang.String) arguments[0],
                ((Long) arguments[1]).longValue());
        }

        if (_methodName615.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes615, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getMessageByMessageId(((Long) arguments[0]).longValue());
        }

        if (_methodName616.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes616, parameterTypes)) {
            DiscussionMessageLocalServiceUtil.reIndex();

            return null;
        }

        if (_methodName617.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes617, parameterTypes)) {
            DiscussionMessageLocalServiceUtil.reIndex(((Long) arguments[0]).longValue());

            return null;
        }

        if (_methodName618.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes618, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getThreadMessages((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName619.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes619, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getThreadMessagesCount((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName620.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes620, parameterTypes)) {
            DiscussionMessageLocalServiceUtil.store((com.ext.portlet.model.DiscussionMessage) arguments[0]);

            return null;
        }

        if (_methodName621.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes621, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.addThreadMessage((com.ext.portlet.model.DiscussionMessage) arguments[0],
                (java.lang.String) arguments[1],
                (java.lang.String) arguments[2],
                (com.liferay.portal.model.User) arguments[3]);
        }

        if (_methodName622.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes622, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getAuthor((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName623.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes623, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getLastActivityAuthor((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName624.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes624, parameterTypes)) {
            DiscussionMessageLocalServiceUtil.delete((com.ext.portlet.model.DiscussionMessage) arguments[0]);

            return null;
        }

        if (_methodName625.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes625, parameterTypes)) {
            DiscussionMessageLocalServiceUtil.update((com.ext.portlet.model.DiscussionMessage) arguments[0],
                (java.lang.String) arguments[1], (java.lang.String) arguments[2]);

            return null;
        }

        if (_methodName626.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes626, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getCategory((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName627.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes627, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getCategoryGroup((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName628.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes628, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getThread((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName629.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes629, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getFlags((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName630.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes630, parameterTypes)) {
            DiscussionMessageLocalServiceUtil.addFlag((com.ext.portlet.model.DiscussionMessage) arguments[0],
                (java.lang.String) arguments[1],
                (java.lang.String) arguments[2],
                (com.liferay.portal.model.User) arguments[3]);

            return null;
        }

        if (_methodName631.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes631, parameterTypes)) {
            DiscussionMessageLocalServiceUtil.removeFlag((com.ext.portlet.model.DiscussionMessage) arguments[0],
                (java.lang.String) arguments[1]);

            return null;
        }

        if (_methodName632.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes632, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.hasFlag(((Long) arguments[0]).longValue(),
                (java.lang.String) arguments[1]);
        }

        throw new UnsupportedOperationException();
    }
}
