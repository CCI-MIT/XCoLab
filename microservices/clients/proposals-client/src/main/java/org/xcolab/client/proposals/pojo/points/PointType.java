package org.xcolab.client.proposals.pojo.points;

import org.xcolab.util.http.client.RestService;

public class PointType extends AbstractPointType {

    public PointType() {}

    public PointType(PointType value) {
        super(value);
    }

    public PointType(
            Long id_,
            Long parentpointtypeid,
            Double percentageofparent,
            String distributionstrategy,
            String receiverlimitationstrategy,
            String name,
            Long sort
    ) {
        super(id_, parentpointtypeid, percentageofparent, distributionstrategy,
                receiverlimitationstrategy, name, sort);
    }

    public PointType(AbstractPointType abstractPointType, RestService restService) {
        super(abstractPointType);
    }
}
