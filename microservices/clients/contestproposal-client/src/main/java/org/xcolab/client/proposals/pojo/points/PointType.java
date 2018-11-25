package org.xcolab.client.proposals.pojo.points;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.proposals.PointsClientUtil;
import org.xcolab.client.proposals.enums.points.DistributionStrategy;
import org.xcolab.client.proposals.enums.points.ReceiverLimitationStrategy;
import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.ArrayList;
import java.util.List;

public class PointType extends AbstractPointType {

    public static final TypeProvider<PointType> TYPES =
            new TypeProvider<>(PointType.class,
                    new ParameterizedTypeReference<List<PointType>>() {});

    private List<PointType> children;

    private double percentageOfTotal = 1.0;

    public PointType() {}

    public PointType(PointType value) {
        super(value);
        initChildren();
    }

    public PointType(
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

    public PointType(AbstractPointType abstractPointType, ServiceNamespace serviceNamespace) {
        super(abstractPointType);
        initChildren();
    }

    public PointType(PointType pointType, double parentPercentageOfTotal) {
        this(pointType);
        this.percentageOfTotal = parentPercentageOfTotal * pointType.getPercentageOfParent();
        initChildren();

    }

    private void initChildren(){
        if (this.getId() != null) {
            List<PointType> unwrappedChildren =
                    PointsClientUtil.getClient().getChildrenOfPointType(this.getId());
            this.children = new ArrayList<>();
            for (PointType child : unwrappedChildren) {
                this.children.add(new PointType(child, this.percentageOfTotal));
            }
        }
    }

    public List<PointType> getChildren() {
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
