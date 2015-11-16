package com.ext.portlet.service.base;

import com.ext.portlet.service.MessageLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MessageLocalServiceClpInvoker {
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
    private String _methodName498;
    private String[] _methodParameterTypes498;
    private String _methodName499;
    private String[] _methodParameterTypes499;
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

    public MessageLocalServiceClpInvoker() {
        _methodName0 = "addMessage";

        _methodParameterTypes0 = new String[] { "com.ext.portlet.model.Message" };

        _methodName1 = "createMessage";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deleteMessage";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deleteMessage";

        _methodParameterTypes3 = new String[] { "com.ext.portlet.model.Message" };

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

        _methodName10 = "fetchMessage";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getMessage";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getMessages";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getMessagesCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateMessage";

        _methodParameterTypes15 = new String[] { "com.ext.portlet.model.Message" };

        _methodName498 = "getBeanIdentifier";

        _methodParameterTypes498 = new String[] {  };

        _methodName499 = "setBeanIdentifier";

        _methodParameterTypes499 = new String[] { "java.lang.String" };

        _methodName504 = "countSentMessage";

        _methodParameterTypes504 = new String[] { "long" };

        _methodName505 = "findSentMessages";

        _methodParameterTypes505 = new String[] { "long", "int", "int" };

        _methodName506 = "getRecipients";

        _methodParameterTypes506 = new String[] { "com.ext.portlet.model.Message" };

        _methodName507 = "hasReciever";

        _methodParameterTypes507 = new String[] {
                "com.ext.portlet.model.Message", "long"
            };

        _methodName508 = "isOpened";

        _methodParameterTypes508 = new String[] {
                "com.ext.portlet.model.Message", "long"
            };

        _methodName509 = "setOpened";

        _methodParameterTypes509 = new String[] {
                "com.ext.portlet.model.Message", "long"
            };

        _methodName510 = "isArchived";

        _methodParameterTypes510 = new String[] {
                "com.ext.portlet.model.Message", "long"
            };

        _methodName511 = "setArchived";

        _methodParameterTypes511 = new String[] {
                "com.ext.portlet.model.Message", "long"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return MessageLocalServiceUtil.addMessage((com.ext.portlet.model.Message) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return MessageLocalServiceUtil.createMessage(((Long) arguments[0]).longValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return MessageLocalServiceUtil.deleteMessage(((Long) arguments[0]).longValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return MessageLocalServiceUtil.deleteMessage((com.ext.portlet.model.Message) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return MessageLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return MessageLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return MessageLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return MessageLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return MessageLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return MessageLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return MessageLocalServiceUtil.fetchMessage(((Long) arguments[0]).longValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return MessageLocalServiceUtil.getMessage(((Long) arguments[0]).longValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return MessageLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return MessageLocalServiceUtil.getMessages(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return MessageLocalServiceUtil.getMessagesCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return MessageLocalServiceUtil.updateMessage((com.ext.portlet.model.Message) arguments[0]);
        }

        if (_methodName498.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes498, parameterTypes)) {
            return MessageLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName499.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes499, parameterTypes)) {
            MessageLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName504.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes504, parameterTypes)) {
            return MessageLocalServiceUtil.countSentMessage(((Long) arguments[0]).longValue());
        }

        if (_methodName505.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes505, parameterTypes)) {
            return MessageLocalServiceUtil.findSentMessages(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName506.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes506, parameterTypes)) {
            return MessageLocalServiceUtil.getRecipients((com.ext.portlet.model.Message) arguments[0]);
        }

        if (_methodName507.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes507, parameterTypes)) {
            return MessageLocalServiceUtil.hasReciever((com.ext.portlet.model.Message) arguments[0],
                ((Long) arguments[1]).longValue());
        }

        if (_methodName508.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes508, parameterTypes)) {
            return MessageLocalServiceUtil.isOpened((com.ext.portlet.model.Message) arguments[0],
                ((Long) arguments[1]).longValue());
        }

        if (_methodName509.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes509, parameterTypes)) {
            MessageLocalServiceUtil.setOpened((com.ext.portlet.model.Message) arguments[0],
                ((Long) arguments[1]).longValue());

            return null;
        }

        if (_methodName510.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes510, parameterTypes)) {
            return MessageLocalServiceUtil.isArchived((com.ext.portlet.model.Message) arguments[0],
                ((Long) arguments[1]).longValue());
        }

        if (_methodName511.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes511, parameterTypes)) {
            MessageLocalServiceUtil.setArchived((com.ext.portlet.model.Message) arguments[0],
                ((Long) arguments[1]).longValue());

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
