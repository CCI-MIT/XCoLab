package org.xcolab.client.contest.pojo;

public interface IPointType {

    Long getId();

    void setId(Long id);

    Long getParentPointTypeId();

    void setParentPointTypeId(Long parentPointTypeId);

    Double getPercentageOfParent();

    void setPercentageOfParent(Double percentageOfParent);

    String getDistributionStrategy();

    void setDistributionStrategy(String distributionStrategy);

    String getReceiverLimitationStrategy();

    void setReceiverLimitationStrategy(String receiverLimitationStrategy);

    String getName();

    void setName(String name);

    Long getSortOrder();

    void setSortOrder(Long sortOrder);
}
