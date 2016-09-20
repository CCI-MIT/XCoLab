package org.xcolab.service.contest.domain.impacttemplatefocusarealist;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.xcolab.client.contest.pojo.ImpactTemplateFocusAreaList;
import org.xcolab.service.contest.exceptions.NotFoundException;

import static org.xcolab.model.Tables.IMPACT_TEMPLATE_FOCUS_AREA_LIST;


public class ImpactTemplateFocusAreaListDaoImpl implements ImpactTemplateFocusAreaListDao {

    @Autowired
    private DSLContext dslContext;

    public ImpactTemplateFocusAreaList get(Long focusAreaListId) throws NotFoundException {

        final Record record = this.dslContext.selectFrom(IMPACT_TEMPLATE_FOCUS_AREA_LIST)
                .where(IMPACT_TEMPLATE_FOCUS_AREA_LIST.FOCUS_AREA_LIST_ID.eq(focusAreaListId))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("ImpactTemplateFocusAreaList with id " + focusAreaListId + " does not exist");
        }
        return record.into(ImpactTemplateFocusAreaList.class);

    }
}
