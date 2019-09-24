package org.xcolab.client.contest.pojo.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.xcolab.client.contest.pojo.tables.pojos.PointType;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.contest.proposals.enums.points.DistributionStrategy;
import org.xcolab.client.contest.proposals.enums.points.ReceiverLimitationStrategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PointTypeWrapper extends PointType implements Serializable {

    private List<PointTypeWrapper> children;

    private double percentageOfTotal = 1.0;

    public PointTypeWrapper() {}

    public PointTypeWrapper(PointTypeWrapper value) {
        super(value);
        initChildren();
    }

    public PointTypeWrapper(Long id, Long parentpointtypeid, Double percentageofparent,
            String distributionstrategy, String receiverlimitationstrategy, String name,
            Long sort) {
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

    @JsonIgnore
    private void initChildren() {
        if (this.getId() != null) {
            List<PointTypeWrapper> unwrappedChildren =
                    StaticProposalContext.getPointsClient().getPointTypes(this.getId());
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
    @JsonIgnore
    public DistributionStrategy getDistributionStrategyz() {
        return DistributionStrategy.valueOf(this.getDistributionStrategy());
    }

    @JsonIgnore
    public ReceiverLimitationStrategy getReceiverLimitationStrategyz() {
        return ReceiverLimitationStrategy.valueOf(this.getReceiverLimitationStrategy());
    }

    @JsonIgnore
    public double getPercentageOfTotal() {
        return percentageOfTotal;
    }

    @JsonIgnore
    public void setPercentageOfTotal(double percentageOfTotal) {
        this.percentageOfTotal = percentageOfTotal;
    }
}
