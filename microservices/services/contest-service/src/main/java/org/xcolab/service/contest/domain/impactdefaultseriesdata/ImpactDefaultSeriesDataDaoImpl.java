package org.xcolab.service.contest.domain.impactdefaultseriesdata;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.ImpactDefaultSeriesData;

import java.util.List;
import static org.xcolab.model.Tables.IMPACT_DEFAULT_SERIES_DATA;

@Repository
public class ImpactDefaultSeriesDataDaoImpl implements ImpactDefaultSeriesDataDao {



    @Autowired
    private DSLContext dslContext;

    public List<ImpactDefaultSeriesData> findByGiven(Long seriesId, Integer year) {
        final SelectQuery<Record> query = dslContext.select()
                .from(IMPACT_DEFAULT_SERIES_DATA).getQuery();

        if (seriesId != null) {
            query.addConditions(IMPACT_DEFAULT_SERIES_DATA.SERIES_ID.eq(seriesId));
        }
        if (seriesId != null) {
            query.addConditions(IMPACT_DEFAULT_SERIES_DATA.YEAR.eq(year));
        }
        return query.fetchInto(ImpactDefaultSeriesData.class);
    }

}
