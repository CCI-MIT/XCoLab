package org.xcolab.service.contest.domain.impacttemplatemaxfocusarea;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.contest.pojo.IImpactTemplateMaxFocusArea;
import org.xcolab.client.contest.pojo.tables.pojos.ImpactTemplateMaxFocusArea;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.IMPACT_TEMPLATE_MAX_FOCUS_AREA;

@Repository
public class ImpactTemplateMaxFocusAreaDaoImpl implements ImpactTemplateMaxFocusAreaDao {

    private final DSLContext dslContext;

    @Autowired
    public ImpactTemplateMaxFocusAreaDaoImpl(DSLContext dslContext) {this.dslContext = dslContext;}

    @Override
    public IImpactTemplateMaxFocusArea getByFocusAreaListId(Long id) throws NotFoundException {

        final Record record = this.dslContext.selectFrom(IMPACT_TEMPLATE_MAX_FOCUS_AREA)
                .where(IMPACT_TEMPLATE_MAX_FOCUS_AREA.FOCUS_AREA_LIST_ID.eq(id))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("ImpactTemplateMaxFocusArea with id " + id + " does not exist");
        }
        return record.into(ImpactTemplateMaxFocusArea.class);
    }

    @Override
    public List<IImpactTemplateMaxFocusArea> findByGiven(Long focusAreaListId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(IMPACT_TEMPLATE_MAX_FOCUS_AREA).getQuery();

        if (focusAreaListId != null) {
            query.addConditions(IMPACT_TEMPLATE_MAX_FOCUS_AREA.FOCUS_AREA_LIST_ID.eq(focusAreaListId));

        }
        return query.fetchInto(ImpactTemplateMaxFocusArea.class);
    }

}
