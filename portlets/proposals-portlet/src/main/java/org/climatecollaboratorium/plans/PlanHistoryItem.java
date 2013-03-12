package org.climatecollaboratorium.plans;

import java.util.Date;

import com.ext.portlet.model.PlanItem;
import com.ext.portlet.plans.UpdateType;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

public class PlanHistoryItem {
	private PlanItem plan;
	private String updateType;
	private User updateAuthor;

	public PlanHistoryItem(PlanItem plan) throws PortalException,
			SystemException {
		this.plan = plan;
		updateAuthor = PlanItemLocalServiceUtil.getUpdateAuthor(plan);
		updateType = plan.getUpdateType();
	}

	public String getUpdateType() {
		return UpdateType.valueOf(plan.getUpdateType()).description();
	}

	public User getUpdateAuthor() {
		return updateAuthor;
	}

	public Date getUpdateDate() {
		return plan.getUpdated();
	}
}