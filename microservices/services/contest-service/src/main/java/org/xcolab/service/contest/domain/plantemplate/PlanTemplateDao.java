package org.xcolab.service.contest.domain.plantemplate;

import org.xcolab.model.tables.pojos.PlanTemplate;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;


public interface PlanTemplateDao {

    PlanTemplate get(Long id_) throws NotFoundException;
    boolean update(PlanTemplate planTemplate);
    PlanTemplate create(PlanTemplate planTemplate);
    List<PlanTemplate> findByGiven();
}
