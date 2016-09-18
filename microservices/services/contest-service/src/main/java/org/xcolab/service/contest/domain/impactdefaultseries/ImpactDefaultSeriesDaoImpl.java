package org.xcolab.service.contest.domain.impactdefaultseries;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.ImpactDefaultSeries;
import org.xcolab.service.contest.exceptions.NotFoundException;

import static org.xcolab.model.Tables.IMPACT_DEFAULT_SERIES;

@Repository
public class ImpactDefaultSeriesDaoImpl implements ImpactDefaultSeriesDao {

    @Autowired
    private DSLContext dslContext;

    public ImpactDefaultSeries get(Long seriesId) throws NotFoundException {

        final Record record =  this.dslContext.selectFrom(IMPACT_DEFAULT_SERIES)
                .where(IMPACT_DEFAULT_SERIES.SERIES_ID.eq(seriesId))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("ImpactDefaultSeries with id " + seriesId + " does not exist");
        }
        return record.into(ImpactDefaultSeries.class);

    }
}
