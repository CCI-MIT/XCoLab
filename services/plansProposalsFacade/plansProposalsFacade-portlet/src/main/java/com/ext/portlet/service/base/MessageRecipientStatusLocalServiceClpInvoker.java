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
    private String _methodName492;
    private String[] _methodParameterTypes492;
    private String _methodName493;
    private String[] _methodParameterTypes493;
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

        _methodName492 = "getBeanIdentifier";

        _methodParameterTypes492 = new String[] {  };

        _methodName493 = "setBeanIdentifier";

        _methodParameterTypes493 = new String[] { "java.lang.String" };

        _methodName498 = "countByMessageId";

        _methodParameterTypes498 = new String[] { "long" };

        _methodName499 = "findByMessageId";

        _methodParameterTypes499 = new String[] { "long", "int", "int" };

        _methodName500 = "countArchivedMessagesForUser";

        _methodParameterTypes500 = new String[] { "long" };

        _methodName501 = "findArchivedMessagesForUser";

        _methodParameterTypes501 = new String[] { "long", "int", "int" };

        _methodName502 = "countInboxMessagesForUser";

        _methodParameterTypes502 = new String[] { "long" };

        _methodName503 = "findInboxMessagesForUser";

        _methodParameterTypes503 = new String[] { "long", "int", "int" };

        _methodName504 = "findByMessageRecipient";

        _methodParameterTypes504 = new String[] { "long", "long" };

        _methodName505 = "countUnreadMessages";

        _methodParameterTypes505 = new String[] { "long" };

        _methodName506 = "didReceiveJudgeCommentForProposal";

        _methodParameterTypes506 = new String[] {
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

        if (_methodName492.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes492, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName493.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes493, parameterTypes)) {
            MessageRecipientStatusLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName498.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes498, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.countByMessageId(((Long) arguments[0]).longValue());
        }

        if (_methodName499.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes499, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.findByMessageId(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName500.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes500, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.countArchivedMessagesForUser(((Long) arguments[0]).longValue());
        }

        if (_methodName501.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes501, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.findArchivedMessagesForUser(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName502.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes502, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.countInboxMessagesForUser(((Long) arguments[0]).longValue());
        }

        if (_methodName503.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes503, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.findInboxMessagesForUser(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName504.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes504, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.findByMessageRecipient(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName505.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes505, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.countUnreadMessages(((Long) arguments[0]).longValue());
        }

        if (_methodName506.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes506, parameterTypes)) {
            return MessageRecipientStatusLocalServiceUtil.didReceiveJudgeCommentForProposal((com.ext.portlet.model.Proposal) arguments[0],
                (com.liferay.portal.model.User) arguments[1]);
        }

        throw new UnsupportedOperationException();
    }
}
