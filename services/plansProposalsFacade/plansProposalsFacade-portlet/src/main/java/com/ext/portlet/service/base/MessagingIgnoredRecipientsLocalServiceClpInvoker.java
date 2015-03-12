package com.ext.portlet.service.base;

import com.ext.portlet.service.MessagingIgnoredRecipientsLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MessagingIgnoredRecipientsLocalServiceClpInvoker {
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
    private String _methodName624;
    private String[] _methodParameterTypes624;
    private String _methodName625;
    private String[] _methodParameterTypes625;
    private String _methodName630;
    private String[] _methodParameterTypes630;
    private String _methodName631;
    private String[] _methodParameterTypes631;

    public MessagingIgnoredRecipientsLocalServiceClpInvoker() {
        _methodName0 = "addMessagingIgnoredRecipients";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.MessagingIgnoredRecipients"
            };

        _methodName1 = "createMessagingIgnoredRecipients";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deleteMessagingIgnoredRecipients";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deleteMessagingIgnoredRecipients";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.MessagingIgnoredRecipients"
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

        _methodName10 = "fetchMessagingIgnoredRecipients";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getMessagingIgnoredRecipients";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getMessagingIgnoredRecipientses";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getMessagingIgnoredRecipientsesCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateMessagingIgnoredRecipients";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.MessagingIgnoredRecipients"
            };

        _methodName624 = "getBeanIdentifier";

        _methodParameterTypes624 = new String[] {  };

        _methodName625 = "setBeanIdentifier";

        _methodParameterTypes625 = new String[] { "java.lang.String" };

        _methodName630 = "findByUserId";

        _methodParameterTypes630 = new String[] { "java.lang.Long" };

        _methodName631 = "findByEmail";

        _methodParameterTypes631 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return MessagingIgnoredRecipientsLocalServiceUtil.addMessagingIgnoredRecipients((com.ext.portlet.model.MessagingIgnoredRecipients) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return MessagingIgnoredRecipientsLocalServiceUtil.createMessagingIgnoredRecipients(((Long) arguments[0]).longValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return MessagingIgnoredRecipientsLocalServiceUtil.deleteMessagingIgnoredRecipients(((Long) arguments[0]).longValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return MessagingIgnoredRecipientsLocalServiceUtil.deleteMessagingIgnoredRecipients((com.ext.portlet.model.MessagingIgnoredRecipients) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return MessagingIgnoredRecipientsLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return MessagingIgnoredRecipientsLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return MessagingIgnoredRecipientsLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return MessagingIgnoredRecipientsLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return MessagingIgnoredRecipientsLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return MessagingIgnoredRecipientsLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return MessagingIgnoredRecipientsLocalServiceUtil.fetchMessagingIgnoredRecipients(((Long) arguments[0]).longValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return MessagingIgnoredRecipientsLocalServiceUtil.getMessagingIgnoredRecipients(((Long) arguments[0]).longValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return MessagingIgnoredRecipientsLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return MessagingIgnoredRecipientsLocalServiceUtil.getMessagingIgnoredRecipientses(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return MessagingIgnoredRecipientsLocalServiceUtil.getMessagingIgnoredRecipientsesCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return MessagingIgnoredRecipientsLocalServiceUtil.updateMessagingIgnoredRecipients((com.ext.portlet.model.MessagingIgnoredRecipients) arguments[0]);
        }

        if (_methodName624.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes624, parameterTypes)) {
            return MessagingIgnoredRecipientsLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName625.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes625, parameterTypes)) {
            MessagingIgnoredRecipientsLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName630.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes630, parameterTypes)) {
            return MessagingIgnoredRecipientsLocalServiceUtil.findByUserId((java.lang.Long) arguments[0]);
        }

        if (_methodName631.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes631, parameterTypes)) {
            return MessagingIgnoredRecipientsLocalServiceUtil.findByEmail((java.lang.String) arguments[0]);
        }

        throw new UnsupportedOperationException();
    }
}
