package org.xcolab.client.contest.pojo.wrapper;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.contest.pojo.tables.pojos.PointType;
import org.xcolab.client.contest.proposals.PointsClientUtil;
import org.xcolab.client.contest.proposals.enums.points.DistributionStrategy;
import org.xcolab.client.contest.proposals.enums.points.ReceiverLimitationStrategy;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.ArrayList;
import java.util.List;

public class PointTypeWrapper extends PointType {

    public static final TypeProvider<PointTypeWrapper> TYPES =
            new TypeProvider<>(PointTypeWrapper.class,
                    new ParameterizedTypeReference<List<PointTypeWrapper>>() {});

    private List<PointTypeWrapper> children;

    private double percentageOfTotal = 1.0;

    public PointTypeWrapper() {}

    public PointTypeWrapper(PointTypeWrapper value) {
        super(value);
        initChildren();
    }

    public PointTypeWrapper(
            Long id,
            Long parentpointtypeid,
            Double percentageofparent,
            String distributionstrategy,
            String receiverlimitationstrategy,
            String name,
            Long sort
    ) {
        super(id, parentpointtypeid, percentageofparent, distributionstrategy,
                receiverlimitationstrategy, name, sort);
        initChildren();
    }

    public PointTypeWrapper(PointType abstractPointType) {
        super(abstractPointType);
        initChildren();
    }

    public PointTypeWrapper(PointTypeWrapper pointType, double parentPercentageOfTotal) {
        this(pointType);
        this.percentageOfTotal = parentPercentageOfTotal * pointType.getPercentageOfParent();
        initChildren();

    }

    private void initChildren(){
        if (this.getId() != null) {
            List<PointTypeWrapper> unwrappedChildren =
                    PointsClientUtil.getClient().getChildrenOfPointType(this.getId());
            this.children = new ArrayList<>();
            for (PointTypeWrapper child : unwrappedChildren) {
                this.children.add(new PointTypeWrapper(child, this.percentageOfTotal));
            }
        }
    }

    public List<PointTypeWrapper> getChildren() {
        return children;
    }

    //DEAL WITH IT
    public DistributionStrategy getDistributionStrategyz() {
        return DistributionStrategy.valueOf(this.getDistributionStrategy());
    }

    public ReceiverLimitationStrategy getReceiverLimitationStrategyz() {
        return ReceiverLimitationStrategy.valueOf(this.getReceiverLimitationStrategy());
    }

    public double getPercentageOfTotal() {
        return percentageOfTotal;
    }

    public void setPercentageOfTotal(double percentageOfTotal) {
        this.percentageOfTotal = percentageOfTotal;
    }
}
