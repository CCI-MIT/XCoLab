package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ContestTypeLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;


public class ContestTypeClp extends BaseModelImpl<ContestType>
    implements ContestType {
    private long _id;
    private String _contestName;
    private String _contestNamePlural;
    private String _proposalName;
    private String _proposalNamePlural;
    private String _portletName;
    private String _portletUrl;
    private String _friendlyUrlStringContests;
    private String _friendlyUrlStringProposal;
    private String _menuItemName;
    private boolean _hasDiscussion;
    private long _suggestionContestId;
    private BaseModel<?> _contestTypeRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public ContestTypeClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ContestType.class;
    }

    @Override
    public String getModelClassName() {
        return ContestType.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _id;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _id;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("contestName", getContestName());
        attributes.put("contestNamePlural", getContestNamePlural());
        attributes.put("proposalName", getProposalName());
        attributes.put("proposalNamePlural", getProposalNamePlural());
        attributes.put("portletName", getPortletName());
        attributes.put("portletUrl", getPortletUrl());
        attributes.put("friendlyUrlStringContests",
            getFriendlyUrlStringContests());
        attributes.put("friendlyUrlStringProposal",
            getFriendlyUrlStringProposal());
        attributes.put("menuItemName", getMenuItemName());
        attributes.put("hasDiscussion", getHasDiscussion());
        attributes.put("suggestionContestId", getSuggestionContestId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        String contestName = (String) attributes.get("contestName");

        if (contestName != null) {
            setContestName(contestName);
        }

        String contestNamePlural = (String) attributes.get("contestNamePlural");

        if (contestNamePlural != null) {
            setContestNamePlural(contestNamePlural);
        }

        String proposalName = (String) attributes.get("proposalName");

        if (proposalName != null) {
            setProposalName(proposalName);
        }

        String proposalNamePlural = (String) attributes.get(
                "proposalNamePlural");

        if (proposalNamePlural != null) {
            setProposalNamePlural(proposalNamePlural);
        }

        String portletName = (String) attributes.get("portletName");

        if (portletName != null) {
            setPortletName(portletName);
        }

        String portletUrl = (String) attributes.get("portletUrl");

        if (portletUrl != null) {
            setPortletUrl(portletUrl);
        }

        String friendlyUrlStringContests = (String) attributes.get(
                "friendlyUrlStringContests");

        if (friendlyUrlStringContests != null) {
            setFriendlyUrlStringContests(friendlyUrlStringContests);
        }

        String friendlyUrlStringProposal = (String) attributes.get(
                "friendlyUrlStringProposal");

        if (friendlyUrlStringProposal != null) {
            setFriendlyUrlStringProposal(friendlyUrlStringProposal);
        }

        String menuItemName = (String) attributes.get("menuItemName");

        if (menuItemName != null) {
            setMenuItemName(menuItemName);
        }

        Boolean hasDiscussion = (Boolean) attributes.get("hasDiscussion");

        if (hasDiscussion != null) {
            setHasDiscussion(hasDiscussion);
        }

        Long suggestionContestId = (Long) attributes.get("suggestionContestId");

        if (suggestionContestId != null) {
            setSuggestionContestId(suggestionContestId);
        }
    }

    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;

        if (_contestTypeRemoteModel != null) {
            try {
                Class<?> clazz = _contestTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_contestTypeRemoteModel, id);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContestName() {
        return _contestName;
    }

    @Override
    public void setContestName(String contestName) {
        _contestName = contestName;

        if (_contestTypeRemoteModel != null) {
            try {
                Class<?> clazz = _contestTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setContestName", String.class);

                method.invoke(_contestTypeRemoteModel, contestName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContestNamePlural() {
        return _contestNamePlural;
    }

    @Override
    public void setContestNamePlural(String contestNamePlural) {
        _contestNamePlural = contestNamePlural;

        if (_contestTypeRemoteModel != null) {
            try {
                Class<?> clazz = _contestTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setContestNamePlural",
                        String.class);

                method.invoke(_contestTypeRemoteModel, contestNamePlural);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getProposalName() {
        return _proposalName;
    }

    @Override
    public void setProposalName(String proposalName) {
        _proposalName = proposalName;

        if (_contestTypeRemoteModel != null) {
            try {
                Class<?> clazz = _contestTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setProposalName", String.class);

                method.invoke(_contestTypeRemoteModel, proposalName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getProposalNamePlural() {
        return _proposalNamePlural;
    }

    @Override
    public void setProposalNamePlural(String proposalNamePlural) {
        _proposalNamePlural = proposalNamePlural;

        if (_contestTypeRemoteModel != null) {
            try {
                Class<?> clazz = _contestTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setProposalNamePlural",
                        String.class);

                method.invoke(_contestTypeRemoteModel, proposalNamePlural);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPortletName() {
        return _portletName;
    }

    @Override
    public void setPortletName(String portletName) {
        _portletName = portletName;

        if (_contestTypeRemoteModel != null) {
            try {
                Class<?> clazz = _contestTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setPortletName", String.class);

                method.invoke(_contestTypeRemoteModel, portletName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPortletUrl() {
        return _portletUrl;
    }

    @Override
    public void setPortletUrl(String portletUrl) {
        _portletUrl = portletUrl;

        if (_contestTypeRemoteModel != null) {
            try {
                Class<?> clazz = _contestTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setPortletUrl", String.class);

                method.invoke(_contestTypeRemoteModel, portletUrl);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFriendlyUrlStringContests() {
        return _friendlyUrlStringContests;
    }

    @Override
    public void setFriendlyUrlStringContests(String friendlyUrlStringContests) {
        _friendlyUrlStringContests = friendlyUrlStringContests;

        if (_contestTypeRemoteModel != null) {
            try {
                Class<?> clazz = _contestTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setFriendlyUrlStringContests",
                        String.class);

                method.invoke(_contestTypeRemoteModel, friendlyUrlStringContests);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFriendlyUrlStringProposal() {
        return _friendlyUrlStringProposal;
    }

    @Override
    public void setFriendlyUrlStringProposal(String friendlyUrlStringProposal) {
        _friendlyUrlStringProposal = friendlyUrlStringProposal;

        if (_contestTypeRemoteModel != null) {
            try {
                Class<?> clazz = _contestTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setFriendlyUrlStringProposal",
                        String.class);

                method.invoke(_contestTypeRemoteModel, friendlyUrlStringProposal);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getMenuItemName() {
        return _menuItemName;
    }

    @Override
    public void setMenuItemName(String menuItemName) {
        _menuItemName = menuItemName;

        if (_contestTypeRemoteModel != null) {
            try {
                Class<?> clazz = _contestTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setMenuItemName", String.class);

                method.invoke(_contestTypeRemoteModel, menuItemName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getHasDiscussion() {
        return _hasDiscussion;
    }

    @Override
    public boolean isHasDiscussion() {
        return _hasDiscussion;
    }

    @Override
    public void setHasDiscussion(boolean hasDiscussion) {
        _hasDiscussion = hasDiscussion;

        if (_contestTypeRemoteModel != null) {
            try {
                Class<?> clazz = _contestTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setHasDiscussion",
                        boolean.class);

                method.invoke(_contestTypeRemoteModel, hasDiscussion);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getSuggestionContestId() {
        return _suggestionContestId;
    }

    @Override
    public void setSuggestionContestId(long suggestionContestId) {
        _suggestionContestId = suggestionContestId;

        if (_contestTypeRemoteModel != null) {
            try {
                Class<?> clazz = _contestTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setSuggestionContestId",
                        long.class);

                method.invoke(_contestTypeRemoteModel, suggestionContestId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getContestTypeRemoteModel() {
        return _contestTypeRemoteModel;
    }

    public void setContestTypeRemoteModel(BaseModel<?> contestTypeRemoteModel) {
        _contestTypeRemoteModel = contestTypeRemoteModel;
    }

    public Object invokeOnRemoteModel(String methodName,
        Class<?>[] parameterTypes, Object[] parameterValues)
        throws Exception {
        Object[] remoteParameterValues = new Object[parameterValues.length];

        for (int i = 0; i < parameterValues.length; i++) {
            if (parameterValues[i] != null) {
                remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
            }
        }

        Class<?> remoteModelClass = _contestTypeRemoteModel.getClass();

        ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

        Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i].isPrimitive()) {
                remoteParameterTypes[i] = parameterTypes[i];
            } else {
                String parameterTypeName = parameterTypes[i].getName();

                remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
            }
        }

        Method method = remoteModelClass.getMethod(methodName,
                remoteParameterTypes);

        Object returnValue = method.invoke(_contestTypeRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ContestTypeLocalServiceUtil.addContestType(this);
        } else {
            ContestTypeLocalServiceUtil.updateContestType(this);
        }
    }

    @Override
    public ContestType toEscapedModel() {
        return (ContestType) ProxyUtil.newProxyInstance(ContestType.class.getClassLoader(),
            new Class[] { ContestType.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ContestTypeClp clone = new ContestTypeClp();

        clone.setId(getId());
        clone.setContestName(getContestName());
        clone.setContestNamePlural(getContestNamePlural());
        clone.setProposalName(getProposalName());
        clone.setProposalNamePlural(getProposalNamePlural());
        clone.setPortletName(getPortletName());
        clone.setPortletUrl(getPortletUrl());
        clone.setFriendlyUrlStringContests(getFriendlyUrlStringContests());
        clone.setFriendlyUrlStringProposal(getFriendlyUrlStringProposal());
        clone.setMenuItemName(getMenuItemName());
        clone.setHasDiscussion(getHasDiscussion());
        clone.setSuggestionContestId(getSuggestionContestId());

        return clone;
    }

    @Override
    public int compareTo(ContestType contestType) {
        long primaryKey = contestType.getPrimaryKey();

        if (getPrimaryKey() < primaryKey) {
            return -1;
        } else if (getPrimaryKey() > primaryKey) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ContestTypeClp)) {
            return false;
        }

        ContestTypeClp contestType = (ContestTypeClp) obj;

        long primaryKey = contestType.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    public Class<?> getClpSerializerClass() {
        return _clpSerializerClass;
    }

    @Override
    public int hashCode() {
        return (int) getPrimaryKey();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(25);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", contestName=");
        sb.append(getContestName());
        sb.append(", contestNamePlural=");
        sb.append(getContestNamePlural());
        sb.append(", proposalName=");
        sb.append(getProposalName());
        sb.append(", proposalNamePlural=");
        sb.append(getProposalNamePlural());
        sb.append(", portletName=");
        sb.append(getPortletName());
        sb.append(", portletUrl=");
        sb.append(getPortletUrl());
        sb.append(", friendlyUrlStringContests=");
        sb.append(getFriendlyUrlStringContests());
        sb.append(", friendlyUrlStringProposal=");
        sb.append(getFriendlyUrlStringProposal());
        sb.append(", menuItemName=");
        sb.append(getMenuItemName());
        sb.append(", hasDiscussion=");
        sb.append(getHasDiscussion());
        sb.append(", suggestionContestId=");
        sb.append(getSuggestionContestId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(40);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ContestType");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contestName</column-name><column-value><![CDATA[");
        sb.append(getContestName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contestNamePlural</column-name><column-value><![CDATA[");
        sb.append(getContestNamePlural());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>proposalName</column-name><column-value><![CDATA[");
        sb.append(getProposalName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>proposalNamePlural</column-name><column-value><![CDATA[");
        sb.append(getProposalNamePlural());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>portletName</column-name><column-value><![CDATA[");
        sb.append(getPortletName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>portletUrl</column-name><column-value><![CDATA[");
        sb.append(getPortletUrl());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>friendlyUrlStringContests</column-name><column-value><![CDATA[");
        sb.append(getFriendlyUrlStringContests());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>friendlyUrlStringProposal</column-name><column-value><![CDATA[");
        sb.append(getFriendlyUrlStringProposal());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>menuItemName</column-name><column-value><![CDATA[");
        sb.append(getMenuItemName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>hasDiscussion</column-name><column-value><![CDATA[");
        sb.append(getHasDiscussion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>suggestionContestId</column-name><column-value><![CDATA[");
        sb.append(getSuggestionContestId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
