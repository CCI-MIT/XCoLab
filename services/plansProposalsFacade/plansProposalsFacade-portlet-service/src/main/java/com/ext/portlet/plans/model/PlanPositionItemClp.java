package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.PlanPositionItemLocalServiceUtil;
import com.ext.portlet.plans.service.persistence.PlanPositionItemPK;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class PlanPositionItemClp extends BaseModelImpl<PlanPositionItem>
    implements PlanPositionItem {
    private Long _planPositionsId;
    private Long _positionId;

    public PlanPositionItemClp() {
    }

    public Class<?> getModelClass() {
        return PlanPositionItem.class;
    }

    public String getModelClassName() {
        return PlanPositionItem.class.getName();
    }

    public PlanPositionItemPK getPrimaryKey() {
        return new PlanPositionItemPK(_planPositionsId, _positionId);
    }

    public void setPrimaryKey(PlanPositionItemPK primaryKey) {
        setPlanPositionsId(primaryKey.planPositionsId);
        setPositionId(primaryKey.positionId);
    }

    public Serializable getPrimaryKeyObj() {
        return new PlanPositionItemPK(_planPositionsId, _positionId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((PlanPositionItemPK) primaryKeyObj);
    }

    public Long getPlanPositionsId() {
        return _planPositionsId;
    }

    public void setPlanPositionsId(Long planPositionsId) {
        _planPositionsId = planPositionsId;
    }

    public Long getPositionId() {
        return _positionId;
    }

    public void setPositionId(Long positionId) {
        _positionId = positionId;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanPositionItemLocalServiceUtil.addPlanPositionItem(this);
        } else {
            PlanPositionItemLocalServiceUtil.updatePlanPositionItem(this);
        }
    }

    @Override
    public PlanPositionItem toEscapedModel() {
        return (PlanPositionItem) Proxy.newProxyInstance(PlanPositionItem.class.getClassLoader(),
            new Class[] { PlanPositionItem.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanPositionItemClp clone = new PlanPositionItemClp();

        clone.setPlanPositionsId(getPlanPositionsId());
        clone.setPositionId(getPositionId());

        return clone;
    }

    public int compareTo(PlanPositionItem planPositionItem) {
        PlanPositionItemPK primaryKey = planPositionItem.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanPositionItemClp planPositionItem = null;

        try {
            planPositionItem = (PlanPositionItemClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        PlanPositionItemPK primaryKey = planPositionItem.getPrimaryKey();

        if (getPrimaryKey().equals(primaryKey)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getPrimaryKey().hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(5);

        sb.append("{planPositionsId=");
        sb.append(getPlanPositionsId());
        sb.append(", positionId=");
        sb.append(getPositionId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(10);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlanPositionItem");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planPositionsId</column-name><column-value><![CDATA[");
        sb.append(getPlanPositionsId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>positionId</column-name><column-value><![CDATA[");
        sb.append(getPositionId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
