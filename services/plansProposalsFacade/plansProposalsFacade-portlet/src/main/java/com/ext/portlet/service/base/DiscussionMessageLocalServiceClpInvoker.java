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
    private String _methodName658;
    private String[] _methodParameterTypes658;
    private String _methodName659;
    private String[] _methodParameterTypes659;
    private String _methodName664;
    private String[] _methodParameterTypes664;
    private String _methodName665;
    private String[] _methodParameterTypes665;
    private String _methodName666;
    private String[] _methodParameterTypes666;
    private String _methodName667;
    private String[] _methodParameterTypes667;
    private String _methodName668;
    private String[] _methodParameterTypes668;
    private String _methodName669;
    private String[] _methodParameterTypes669;
    private String _methodName670;
    private String[] _methodParameterTypes670;
    private String _methodName671;
    private String[] _methodParameterTypes671;
    private String _methodName672;
    private String[] _methodParameterTypes672;
    private String _methodName673;
    private String[] _methodParameterTypes673;
    private String _methodName674;
    private String[] _methodParameterTypes674;
    private String _methodName675;
    private String[] _methodParameterTypes675;
    private String _methodName676;
    private String[] _methodParameterTypes676;
    private String _methodName677;
    private String[] _methodParameterTypes677;
    private String _methodName678;
    private String[] _methodParameterTypes678;
    private String _methodName679;
    private String[] _methodParameterTypes679;
    private String _methodName680;
    private String[] _methodParameterTypes680;
    private String _methodName681;
    private String[] _methodParameterTypes681;
    private String _methodName682;
    private String[] _methodParameterTypes682;
    private String _methodName683;
    private String[] _methodParameterTypes683;
    private String _methodName684;
    private String[] _methodParameterTypes684;
    private String _methodName685;
    private String[] _methodParameterTypes685;
    private String _methodName686;
    private String[] _methodParameterTypes686;
    private String _methodName687;
    private String[] _methodParameterTypes687;
    private String _methodName688;
    private String[] _methodParameterTypes688;

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

        _methodName658 = "getBeanIdentifier";

        _methodParameterTypes658 = new String[] {  };

        _methodName659 = "setBeanIdentifier";

        _methodParameterTypes659 = new String[] { "java.lang.String" };

        _methodName664 = "getThreadsByCategory";

        _methodParameterTypes664 = new String[] { "long" };

        _methodName665 = "getThreadMessages";

        _methodParameterTypes665 = new String[] { "long" };

        _methodName666 = "getThreadMessagesCount";

        _methodParameterTypes666 = new String[] { "long" };

        _methodName667 = "getThreadByThreadId";

        _methodParameterTypes667 = new String[] { "long" };

        _methodName668 = "addThread";

        _methodParameterTypes668 = new String[] {
                "long", "long", "java.lang.String", "java.lang.String",
                "com.liferay.portal.model.User"
            };

        _methodName669 = "addMessage";

        _methodParameterTypes669 = new String[] {
                "long", "long", "long", "java.lang.String", "java.lang.String",
                "com.liferay.portal.model.User"
            };

        _methodName670 = "search";

        _methodParameterTypes670 = new String[] { "java.lang.String", "long" };

        _methodName671 = "getMessageByMessageId";

        _methodParameterTypes671 = new String[] { "long" };

        _methodName672 = "reIndex";

        _methodParameterTypes672 = new String[] {  };

        _methodName673 = "reIndex";

        _methodParameterTypes673 = new String[] { "long" };

        _methodName674 = "getThreadMessages";

        _methodParameterTypes674 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName675 = "getThreadMessagesCount";

        _methodParameterTypes675 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName676 = "store";

        _methodParameterTypes676 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName677 = "addThreadMessage";

        _methodParameterTypes677 = new String[] {
                "com.ext.portlet.model.DiscussionMessage", "java.lang.String",
                "java.lang.String", "com.liferay.portal.model.User"
            };

        _methodName678 = "getAuthor";

        _methodParameterTypes678 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName679 = "getLastActivityAuthor";

        _methodParameterTypes679 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName680 = "delete";

        _methodParameterTypes680 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName681 = "update";

        _methodParameterTypes681 = new String[] {
                "com.ext.portlet.model.DiscussionMessage", "java.lang.String",
                "java.lang.String"
            };

        _methodName682 = "getCategory";

        _methodParameterTypes682 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName683 = "getCategoryGroup";

        _methodParameterTypes683 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName684 = "getThread";

        _methodParameterTypes684 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName685 = "getFlags";

        _methodParameterTypes685 = new String[] {
                "com.ext.portlet.model.DiscussionMessage"
            };

        _methodName686 = "addFlag";

        _methodParameterTypes686 = new String[] {
                "com.ext.portlet.model.DiscussionMessage", "java.lang.String",
                "java.lang.String", "com.liferay.portal.model.User"
            };

        _methodName687 = "removeFlag";

        _methodParameterTypes687 = new String[] {
                "com.ext.portlet.model.DiscussionMessage", "java.lang.String"
            };

        _methodName688 = "hasFlag";

        _methodParameterTypes688 = new String[] { "long", "java.lang.String" };
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

        if (_methodName658.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes658, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName659.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes659, parameterTypes)) {
            DiscussionMessageLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName664.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes664, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getThreadsByCategory(((Long) arguments[0]).longValue());
        }

        if (_methodName665.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes665, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getThreadMessages(((Long) arguments[0]).longValue());
        }

        if (_methodName666.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes666, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getThreadMessagesCount(((Long) arguments[0]).longValue());
        }

        if (_methodName667.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes667, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getThreadByThreadId(((Long) arguments[0]).longValue());
        }

        if (_methodName668.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes668, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.addThread(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2],
                (java.lang.String) arguments[3],
                (com.liferay.portal.model.User) arguments[4]);
        }

        if (_methodName669.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes669, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.addMessage(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Long) arguments[2]).longValue(),
                (java.lang.String) arguments[3],
                (java.lang.String) arguments[4],
                (com.liferay.portal.model.User) arguments[5]);
        }

        if (_methodName670.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes670, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.search((java.lang.String) arguments[0],
                ((Long) arguments[1]).longValue());
        }

        if (_methodName671.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes671, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getMessageByMessageId(((Long) arguments[0]).longValue());
        }

        if (_methodName672.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes672, parameterTypes)) {
            DiscussionMessageLocalServiceUtil.reIndex();

            return null;
        }

        if (_methodName673.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes673, parameterTypes)) {
            DiscussionMessageLocalServiceUtil.reIndex(((Long) arguments[0]).longValue());

            return null;
        }

        if (_methodName674.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes674, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getThreadMessages((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName675.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes675, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getThreadMessagesCount((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName676.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes676, parameterTypes)) {
            DiscussionMessageLocalServiceUtil.store((com.ext.portlet.model.DiscussionMessage) arguments[0]);

            return null;
        }

        if (_methodName677.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes677, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.addThreadMessage((com.ext.portlet.model.DiscussionMessage) arguments[0],
                (java.lang.String) arguments[1],
                (java.lang.String) arguments[2],
                (com.liferay.portal.model.User) arguments[3]);
        }

        if (_methodName678.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes678, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getAuthor((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName679.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes679, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getLastActivityAuthor((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName680.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes680, parameterTypes)) {
            DiscussionMessageLocalServiceUtil.delete((com.ext.portlet.model.DiscussionMessage) arguments[0]);

            return null;
        }

        if (_methodName681.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes681, parameterTypes)) {
            DiscussionMessageLocalServiceUtil.update((com.ext.portlet.model.DiscussionMessage) arguments[0],
                (java.lang.String) arguments[1], (java.lang.String) arguments[2]);

            return null;
        }

        if (_methodName682.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes682, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getCategory((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName683.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes683, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getCategoryGroup((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName684.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes684, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getThread((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName685.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes685, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.getFlags((com.ext.portlet.model.DiscussionMessage) arguments[0]);
        }

        if (_methodName686.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes686, parameterTypes)) {
            DiscussionMessageLocalServiceUtil.addFlag((com.ext.portlet.model.DiscussionMessage) arguments[0],
                (java.lang.String) arguments[1],
                (java.lang.String) arguments[2],
                (com.liferay.portal.model.User) arguments[3]);

            return null;
        }

        if (_methodName687.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes687, parameterTypes)) {
            DiscussionMessageLocalServiceUtil.removeFlag((com.ext.portlet.model.DiscussionMessage) arguments[0],
                (java.lang.String) arguments[1]);

            return null;
        }

        if (_methodName688.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes688, parameterTypes)) {
            return DiscussionMessageLocalServiceUtil.hasFlag(((Long) arguments[0]).longValue(),
                (java.lang.String) arguments[1]);
        }

        throw new UnsupportedOperationException();
    }
}
