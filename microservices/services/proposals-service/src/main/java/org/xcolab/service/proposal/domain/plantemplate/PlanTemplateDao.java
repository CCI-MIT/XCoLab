package org.xcolab.service.proposal.domain.plantemplate;

import org.xcolab.model.tables.pojos.PlanTemplate;
import org.xcolab.service.proposal.exceptions.NotFoundException;

public interface PlanTemplateDao {

    PlanTemplate get(Long id_) throws NotFoundException;
}
