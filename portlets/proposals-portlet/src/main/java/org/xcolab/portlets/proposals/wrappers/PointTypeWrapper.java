package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.model.PointType;
import com.ext.portlet.service.PointTypeLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import org.xcolab.points.DistributionStrategy;
import org.xcolab.points.ReceiverLimitationStrategy;

import java.util.ArrayList;
import java.util.List;

public class PointTypeWrapper {
    PointType pointType;
    List<PointTypeWrapper> children;

    double percentageOfTotal;

    public PointTypeWrapper(PointType pointType) throws SystemException {
        this(pointType, 1.0);
    }

    public PointTypeWrapper(PointType pointType, double parentPercentageOfTotal) throws SystemException {
        this.percentageOfTotal = parentPercentageOfTotal * pointType.getPercentageOfParent();
        this.pointType = pointType;
        List<PointType> unwrappedChildren = PointTypeLocalServiceUtil.getChildrenOfPointType(pointType.getId());
        this.children = new ArrayList<PointTypeWrapper>();
        for (PointType child: unwrappedChildren) {
            this.children.add(new PointTypeWrapper(child, this.percentageOfTotal));
        }
    }

    public List<PointTypeWrapper> getChildren() {
        return children;
    }

    public Long getId() {
        return pointType.getId();
    }

    public PointType getPointType() {
        return pointType;
    }

    public double getPercentageOfParent() {
        return pointType.getPercentageOfParent();
    }

    public DistributionStrategy getDistributionStrategy() {
        return DistributionStrategy.valueOf(pointType.getDistributionStrategy());
    }

    public ReceiverLimitationStrategy getReceiverLimitationStrategy() {
        return ReceiverLimitationStrategy.valueOf(pointType.getReceiverLimitationStrategy());
    }

    public double getPercentageOfTotal() {
        return percentageOfTotal;
    }

    public void setPercentageOfTotal(double percentageOfTotal) {
        this.percentageOfTotal = percentageOfTotal;
    }

}
