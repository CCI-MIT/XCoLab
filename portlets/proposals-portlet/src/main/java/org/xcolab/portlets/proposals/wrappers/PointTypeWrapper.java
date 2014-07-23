package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.model.*;
import com.ext.portlet.service.*;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

import java.util.*;

public class PointTypeWrapper {
    PointType pointType;
    List<PointTypeWrapper> children;

    public PointTypeWrapper(PointType pointType) throws SystemException {
        this.pointType = pointType;
        List<PointType> unwrappedChildren = PointTypeLocalServiceUtil.getChildrenOfPointType(pointType.getId());
        this.children = new ArrayList<PointTypeWrapper>();
        for (PointType child: unwrappedChildren) {
            this.children.add(new PointTypeWrapper(child));
        }
    }

    public List<PointTypeWrapper> getChildren() {
        return children;
    }


    public PointType getPointType() {
        return pointType;
    }

    public double getPercentageOfParent() {
        return pointType.getPercentageOfParent();
    }

    public String getDistributionStrategy() {
        return pointType.getDistributionStrategy();
    }

    public String getReceiverLimitationStrategy() {
        return pointType.getReceiverLimitationStrategy();
    }

}
