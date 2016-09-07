package org.xcolab.service.proposal.domain.plantemplate;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.PlanTemplate;
import org.xcolab.service.proposal.exceptions.NotFoundException;

import static org.xcolab.model.Tables.PLAN_TEMPLATE;

@Repository
public class PlanTemplateDaoImpl implements PlanTemplateDao {


    @Autowired
    private DSLContext dslContext;

    @Override
    public PlanTemplate get(Long id_) throws NotFoundException {

        final Record record = this.dslContext.selectFrom(PLAN_TEMPLATE)
                .where(PLAN_TEMPLATE.ID_.eq(id_))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("PlanTemplate with id " + id_ + " does not exist");
        }
        return record.into(PlanTemplate.class);

    }
}
