package org.xcolab.service.moderation.domain.reportTarget;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.moderation.pojo.IReportTarget;
import org.xcolab.model.tables.records.ReportTargetRecord;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.util.enums.moderation.TargetType;

import org.xcolab.client.moderation.pojo.tables.pojos.ReportTarget;

import java.util.List;

import static org.xcolab.model.Tables.REPORT_TARGET;

@Repository
public class ReportTargetDaoImpl implements ReportTargetDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public List<IReportTarget> findByGiven(PaginationHelper paginationHelper, TargetType type) {
        final SelectQuery<Record> query = dslContext.select()
                .from(REPORT_TARGET)
                .getQuery();

        if (type != null) {
            query.addConditions(REPORT_TARGET.TYPE.eq(type.name()));
        }
        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getCount());
        return query.fetchInto(ReportTarget.class);
    }

    @Override
    public IReportTarget get(long reportTargetId) {
        final Record record = dslContext.select()
                .from(REPORT_TARGET)
                .where(REPORT_TARGET.ID.eq(reportTargetId))
                .fetchOne();
        if (record == null) {
            return null;
        }
        return record.into(ReportTarget.class);
    }

    @Override
    public IReportTarget get(String type, String reason) {
        final Record record = dslContext.select()
                .from(REPORT_TARGET)
                .where(REPORT_TARGET.TYPE.eq(type).and(REPORT_TARGET.REASON.eq(reason)))
                .fetchOne();
        if (record == null) {
            return null;
        }
        return record.into(ReportTarget.class);
    }

    @Override
    public boolean update(IReportTarget reportTarget) {
        return dslContext
                .update(REPORT_TARGET)
                .set(REPORT_TARGET.TYPE, reportTarget.getType())
                .set(REPORT_TARGET.REASON, reportTarget.getReason())
                .set(REPORT_TARGET.NOTIFICATION_THRESHOLD, reportTarget.getNotificationThreshold())
                .set(REPORT_TARGET.SCREENING_THRESHOLD, reportTarget.getScreeningThreshold())
                .where(REPORT_TARGET.ID.eq(reportTarget.getId()))
                .execute() > 0;
    }

    @Override
    public IReportTarget create(IReportTarget reportTarget) {
        final ReportTargetRecord record = dslContext.insertInto(REPORT_TARGET)
                .set(REPORT_TARGET.TYPE, reportTarget.getType())
                .set(REPORT_TARGET.REASON, reportTarget.getReason())
                .set(REPORT_TARGET.NOTIFICATION_THRESHOLD, reportTarget.getNotificationThreshold())
                .set(REPORT_TARGET.SCREENING_THRESHOLD, reportTarget.getScreeningThreshold())
                .returning(REPORT_TARGET.ID)
                .fetchOne();
        if (record != null) {
            reportTarget.setId(record.getId());
            return reportTarget;
        }
        return null;
    }

    @Override
    public boolean delete(long reportTargetId) {
        return dslContext.deleteFrom(REPORT_TARGET)
                .where(REPORT_TARGET.ID.eq(reportTargetId))
                .execute() > 0;
    }
}
