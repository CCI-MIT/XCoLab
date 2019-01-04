package org.xcolab.service.tracking.domain.trackedvisit;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.tracking.pojo.ITrackedVisit;
import org.xcolab.model.tables.records.TrackedVisitRecord;

import static org.xcolab.model.tables.TrackedVisitTable.TRACKED_VISIT;

@Repository
public class TrackedVisitDaoImpl implements TrackedVisitDao {

    private final DSLContext dslContext;

    @Autowired
    public TrackedVisitDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public ITrackedVisit create(ITrackedVisit trackedVisit) {
        final TrackedVisitRecord record = dslContext.insertInto(TRACKED_VISIT)
                .set(TRACKED_VISIT.VISITOR_UUID, trackedVisit.getVisitorUuid())
                .set(TRACKED_VISIT.URL, trackedVisit.getUrl())
                .set(TRACKED_VISIT.IP, trackedVisit.getIp())
                .set(TRACKED_VISIT.BROWSER, trackedVisit.getBrowser())
                .set(TRACKED_VISIT.REFERER, trackedVisit.getReferer())
                .set(TRACKED_VISIT.HEADERS, trackedVisit.getHeaders())
                .set(TRACKED_VISIT.CITY, trackedVisit.getCity())
                .set(TRACKED_VISIT.COUNTRY, trackedVisit.getCountry())
                .set(TRACKED_VISIT.CREATED_AT, DSL.currentTimestamp())
                .returning(TRACKED_VISIT.ID)
                .fetchOne();

        if (record == null) {
            throw new IllegalStateException("Could not get inserted id");
        }
        trackedVisit.setId(record.getValue(TRACKED_VISIT.ID));
        return trackedVisit;
    }
}
