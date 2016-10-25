package org.xcolab.portlets.proposals.wrappers;

import org.xcolab.client.proposals.PointsClient;
import org.xcolab.client.proposals.pojo.points.PointType;
import org.xcolab.points.DistributionStrategy;
import org.xcolab.points.ReceiverLimitationStrategy;
import org.xcolab.portlets.proposals.utils.context.ProposalsContextUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;

public class PointTypeWrapper {
    PointType pointType;
    List<PointTypeWrapper> children;

    double percentageOfTotal;

    public PointTypeWrapper(PointType pointType, PortletRequest request) {
        this(pointType, 1.0, request);
    }

    public PointTypeWrapper(PointType pointType, double parentPercentageOfTotal, PortletRequest request) {
        this.percentageOfTotal = parentPercentageOfTotal * pointType.getPercentageOfParent();
        this.pointType = pointType;
        final PointsClient pointsClient = ProposalsContextUtil.getClients(request).getPointsClient();
        List<PointType> unwrappedChildren = pointsClient.getChildrenOfPointType(pointType.getId_());
        this.children = new ArrayList<>();
        for (PointType child: unwrappedChildren) {
            this.children.add(new PointTypeWrapper(child, this.percentageOfTotal, request));
        }
    }

    public List<PointTypeWrapper> getChildren() {
        return children;
    }

    public Long getId() {
        return pointType.getId_();
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
