package org.xcolab.service.contest.domain.impacttemplateseries;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.contest.pojo.IImpactTemplateSeries;
import org.xcolab.client.contest.pojo.tables.pojos.ImpactTemplateSeries;
import org.xcolab.service.contest.exceptions.NotFoundException;

import static org.xcolab.model.Tables.IMPACT_TEMPLATE_SERIES;

@Repository
public class ImpactTemplateSeriesDaoImpl implements ImpactTemplateSeriesDao {

    private final DSLContext dslContext;

    @Autowired
    public ImpactTemplateSeriesDaoImpl(DSLContext dslContext) {this.dslContext = dslContext;}

    @Override
    public IImpactTemplateSeries get(Long seriesId) throws NotFoundException {

        final Record record =  this.dslContext.selectFrom(IMPACT_TEMPLATE_SERIES)
                .where(IMPACT_TEMPLATE_SERIES.SERIES_ID.eq(seriesId))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("ImpactTemplateSeries with id " + seriesId + " does not exist");
        }
        return record.into(ImpactTemplateSeries.class);

    }
}
