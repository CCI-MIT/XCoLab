package org.xcolab.service.contest.domain.plantemplate;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.PlanTemplate;
import org.xcolab.model.tables.records.PlanTemplateRecord;
import org.xcolab.service.contest.exceptions.NotFoundException;


import java.util.List;

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

    @Override
    public PlanTemplate create(PlanTemplate planTemplate) {

        PlanTemplateRecord ret = this.dslContext.insertInto(PLAN_TEMPLATE)
                .set(PLAN_TEMPLATE.NAME, planTemplate.getName())
                .set(PLAN_TEMPLATE.BASE_TEMPLATE_ID, planTemplate.getBaseTemplateId())
                .set(PLAN_TEMPLATE.IMPACT_SERIES_TEMPLATE_ID, planTemplate.getImpactSeriesTemplateId())
                .set(PLAN_TEMPLATE.FOCUS_AREA_LIST_TEMPLATE_ID, planTemplate.getFocusAreaListTemplateId())
                .returning(PLAN_TEMPLATE.ID_)
                .fetchOne();
        if (ret != null) {
            planTemplate.setId_(ret.getValue(PLAN_TEMPLATE.ID_));
            return planTemplate;
        } else {
            return null;
        }

    }

    @Override
    public int delete(Long id_) {
        return dslContext.deleteFrom(PLAN_TEMPLATE)
                .where(PLAN_TEMPLATE.ID_.eq(id_))
                .execute();
    }

    @Override
    public boolean update(PlanTemplate planTemplate) {
        return dslContext.update(PLAN_TEMPLATE)
                .set(PLAN_TEMPLATE.NAME, planTemplate.getName())
                .set(PLAN_TEMPLATE.BASE_TEMPLATE_ID, planTemplate.getBaseTemplateId())
                .set(PLAN_TEMPLATE.IMPACT_SERIES_TEMPLATE_ID, planTemplate.getImpactSeriesTemplateId())
                .set(PLAN_TEMPLATE.FOCUS_AREA_LIST_TEMPLATE_ID, planTemplate.getFocusAreaListTemplateId())
                .where(PLAN_TEMPLATE.ID_.eq(planTemplate.getId_()))
                .execute() > 0;
    }

    @Override
    public List<PlanTemplate> findByGiven() {
        final SelectQuery<Record> query = dslContext.select()
                .from(PLAN_TEMPLATE).getQuery();


        return query.fetchInto(PlanTemplate.class);
    }




}
