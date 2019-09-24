package org.xcolab.service.contest.domain.impactdefaultseries;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.contest.pojo.IImpactDefaultSeries;
import org.xcolab.client.contest.pojo.tables.pojos.ImpactDefaultSeries;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.IMPACT_DEFAULT_SERIES;

@Repository
public class ImpactDefaultSeriesDaoImpl implements ImpactDefaultSeriesDao {

    @Autowired
    private DSLContext dslContext;

    public IImpactDefaultSeries get(Long seriesId) throws NotFoundException {

        final Record record =  this.dslContext.selectFrom(IMPACT_DEFAULT_SERIES)
                .where(IMPACT_DEFAULT_SERIES.SERIES_ID.eq(seriesId))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("ImpactDefaultSeries with id " + seriesId + " does not exist");
        }
        return record.into(ImpactDefaultSeries.class);

    }
    @Override
    public List<IImpactDefaultSeries> findByGiven(Long focusAreaId, String name) {
        final SelectQuery<Record> query = dslContext.select()
                .from(IMPACT_DEFAULT_SERIES).getQuery();

        if (focusAreaId != null) {
            query.addConditions(IMPACT_DEFAULT_SERIES.FOCUS_AREA_ID.eq(focusAreaId));
        }
        if (name != null) {
            query.addConditions(IMPACT_DEFAULT_SERIES.NAME.eq(name));
        }

        return query.fetchInto(ImpactDefaultSeries.class);
    }
}
