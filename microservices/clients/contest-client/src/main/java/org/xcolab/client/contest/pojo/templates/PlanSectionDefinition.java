package org.xcolab.client.contest.pojo.templates;

import org.xcolab.util.http.client.RestService;

import java.util.ArrayList;
import java.util.List;

public class PlanSectionDefinition extends AbstractPlanSectionDefinition {

    public PlanSectionDefinition() {}

    public PlanSectionDefinition(PlanSectionDefinition value) {
        super(value);
    }

    public PlanSectionDefinition(AbstractPlanSectionDefinition abstractPlanSectionDefinition,
            RestService restService) {
        super(abstractPlanSectionDefinition);
    }

    public List<Long> getAdditionalIdsAsList() {
        List<Long> longIds = new ArrayList<>();
        final String stringOfStringIds = this.getAdditionalIds();
        if (stringOfStringIds != null) {
            String[] stringIds = stringOfStringIds.split(",");
            for (String stringId : stringIds) {
                if (!stringId.isEmpty()) {
                    try {
                        longIds.add(Long.parseLong(stringId));
                    } catch (NumberFormatException e) {
                    }
                }
            }
        }
        return longIds;
    }
}
