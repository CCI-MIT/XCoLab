package com.ext.portlet.service.base;

import com.ext.portlet.service.MessageRecipientStatusLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MessageRecipientStatusLocalServiceClpInvoker {
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
    private String _methodName620;
    private String[] _methodParameterTypes620;
    private String _methodName621;
    private String[] _methodParameterTypes621;
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
    private String _methodName633;
    private String[] _methodParameterTypes633;
    private String _methodName634;
    private String[] _methodParameterTypes634;

    public MessageRecipientStatusLocalServiceClpInvoker() {
        _methodName0 = "addMessageRecipientStatus";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.MessageRecipientStatus"
            };

        _methodName1 = "createMessageRecipientStatus";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deleteMessageRecipientStatus";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deleteMessageRecipientStatus";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.MessageRecipientStatus"
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

        _methodName10 = "fetchMessageRecipientStatus";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getMessageRecipientStatus";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getMessageRecipientStatuses";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getMessageRecipientStatusesCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateMessageRecipientStatus";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.MessageRecipientStatus"
            };

        _methodName620 = "getBeanIdentifier";

        _methodParameterTypes620 = new String[] {  };

        _methodName621 = "setBeanIdentifier";

        _methodParameterTypes621 = new String[] { "java.lang.String" };

        _methodName626 = "countByMessageId";

        _methodParameterTypes626 = new String[] { "long" };

        _methodName627 = "findByMessageId";

        _methodParameterTypes627 = new String[] { "long", "int", "int" };

        _methodName628 = "countArchivedMessagesForUser";

        _methodParameterTypes628 = new String[] { "long" };

        _methodName629 = "findArchivedMessagesForUser";

        _methodParameterTypes629 = new String[] { "long", "int", "int" };

        _methodName630 = "countInboxMessagesForUser";

        _methodParameterTypes630 = new String[] { "long" };

        _methodName631 = "findInboxMessagesForUser";

        _methodParameterTypes631 = new String[] { "long", "int", "int" };

        _methodName632 = "findByMessageRecipient";

        _methodParameterTypes632 = new String[] { "long", "long" };

        _methodName633 = "countUnreadMessages";

        _methodParameterTypes633 = new String[] { "long" };

        _methodName634 = "didReceiveJudgeCommentForProposal";

        _methodParameterTypes634 = new String[] {
                "com.ext.portlet.model.Proposal",
                "com.liferay.portal.model.User"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.addMessageRecipientStatus((com.ext.portlet.model.MessageRecipientStatus) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.createMessageRecipientStatus(((Long) arguments[0]).longValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.deleteMessageRecipientStatus(((Long) arguments[0]).longValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.deleteMessageRecipientStatus((com.ext.portlet.model.MessageRecipientStatus) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.fetchMessageRecipientStatus(((Long) arguments[0]).longValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.getMessageRecipientStatus(((Long) arguments[0]).longValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.getMessageRecipientStatuses(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.getMessageRecipientStatusesCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.updateMessageRecipientStatus((com.ext.portlet.model.MessageRecipientStatus) arguments[0]);
        }

        if (_methodName620.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes620, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName621.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes621, parameterTypes)) {
            MessageRecipientStatusLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName626.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes626, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.countByMessageId(((Long) arguments[0]).longValue());
        }

        if (_methodName627.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes627, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.findByMessageId(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName628.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes628, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.countArchivedMessagesForUser(((Long) arguments[0]).longValue());
        }

        if (_methodName629.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes629, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.findArchivedMessagesForUser(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName630.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes630, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.countInboxMessagesForUser(((Long) arguments[0]).longValue());
        }

        if (_methodName631.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes631, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.findInboxMessagesForUser(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName632.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes632, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.findByMessageRecipient(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName633.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes633, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.countUnreadMessages(((Long) arguments[0]).longValue());
        }

        if (_methodName634.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes634, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.didReceiveJudgeCommentForProposal((com.ext.portlet.model.Proposal) arguments[0],
                (com.liferay.portal.model.User) arguments[1]);
        }

        throw new UnsupportedOperationException();
    }
}
