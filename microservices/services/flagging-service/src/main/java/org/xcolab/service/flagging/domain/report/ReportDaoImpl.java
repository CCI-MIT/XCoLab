package org.xcolab.service.flagging.domain.report;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectQuery;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.Report;
import org.xcolab.model.tables.records.ReportRecord;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.service.utils.PaginationHelper.SortColumn;

import java.util.List;

import static org.xcolab.model.Tables.REPORT;

@Repository
public class ReportDaoImpl implements ReportDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public List<Report> findByGiven(PaginationHelper paginationHelper, Long reporterMemberId,
            Long managerMemberId, String targetType, Long targetId, String managerAction) {
        final SelectQuery<Record> query = dslContext.select()
                .from(REPORT)
                .getQuery();

        if (reporterMemberId != null) {
            query.addConditions(REPORT.REPORTER_MEMBER_ID.eq(reporterMemberId));
        }
        if (managerMemberId != null) {
            query.addConditions(REPORT.MANAGER_MEMBER_ID.eq(managerMemberId));
        }
        if (targetType != null) {
            query.addConditions(REPORT.TARGET_TYPE.eq(targetType));
        }
        if (targetId != null) {
            query.addConditions(REPORT.TARGET_ID.eq(targetId));
        }
        if (managerAction != null) {
            query.addConditions(REPORT.MANAGER_ACTION.eq(managerAction));
        }

        for (SortColumn sortColumn : paginationHelper.getSortColumns()) {
            switch (sortColumn.getColumnName()) {
                case "targetType":
                    query.addOrderBy(sortColumn.isAscending()
                            ? REPORT.TARGET_TYPE.asc() : REPORT.TARGET_TYPE.desc());
                    break;
                case "managerMemberId":
                    query.addOrderBy(sortColumn.isAscending()
                            ? REPORT.MANAGER_MEMBER_ID.asc() : REPORT.MANAGER_MEMBER_ID.desc());
                    break;
                case "managerAction":
                    query.addOrderBy(sortColumn.isAscending()
                            ? REPORT.MANAGER_ACTION.asc() : REPORT.MANAGER_ACTION.desc());
                    break;
                case "createDate":
                    query.addOrderBy(sortColumn.isAscending()
                            ? REPORT.CREATE_DATE.asc() : REPORT.CREATE_DATE.desc());
                    break;
            }
        }
        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getLimitRecord());
        return query.fetchInto(Report.class);
    }

    @Override
    public int countByGiven(Long reporterMemberId, Long managerMemberId, String targetType,
            Long targetId, String managerAction) {
        final SelectQuery<Record1<Integer>> query = dslContext.select(DSL.countDistinct(
                        REPORT.TARGET_TYPE, REPORT.TARGET_ID, REPORT.TARGET_ADDITIONAL_ID))
                .from(REPORT)
                .getQuery();

        if (reporterMemberId != null) {
            query.addConditions(REPORT.REPORTER_MEMBER_ID.eq(reporterMemberId));
        }
        if (managerMemberId != null) {
            query.addConditions(REPORT.MANAGER_MEMBER_ID.eq(managerMemberId));
        }
        if (targetType != null) {
            query.addConditions(REPORT.TARGET_TYPE.eq(targetType));
        }
        if (targetId != null) {
            query.addConditions(REPORT.TARGET_ID.eq(targetId));
        }
        if (managerAction != null) {
            query.addConditions(REPORT.MANAGER_ACTION.eq(managerAction));
        }

        return query.fetchOne().into(Integer.class);
    }

    @Override
    public Report get(long reportId) {
        final Record record = dslContext.select()
                .from(REPORT)
                .where(REPORT.REPORT_ID.eq(reportId))
                .fetchOne();
        if (record == null) {
            return null;
        }
        return record.into(Report.class);
    }

    @Override
    public boolean update(Report report) {
        return dslContext
                .update(REPORT)
                .set(REPORT.REPORTER_MEMBER_ID, report.getReporterMemberId())
                .set(REPORT.TARGET_TYPE, report.getTargetType())
                .set(REPORT.TARGET_ID, report.getTargetId())
                .set(REPORT.REASON, report.getReason())
                .set(REPORT.WEIGHT, report.getWeight())
                .set(REPORT.MANAGER_ACTION, report.getManagerAction())
                .set(REPORT.MANAGER_MEMBER_ID, report.getManagerMemberId())
                .set(REPORT.MANAGER_ACTION_DATE, report.getManagerActionDate())
                .set(REPORT.CREATE_DATE, report.getCreateDate())
                .where(REPORT.REPORT_ID.eq(report.getReportId()))
                .execute() > 0;
    }

    @Override
    public Report create(Report report) {
        final ReportRecord record = dslContext.insertInto(REPORT)
                .set(REPORT.REPORTER_MEMBER_ID, report.getReporterMemberId())
                .set(REPORT.TARGET_TYPE, report.getTargetType())
                .set(REPORT.TARGET_ID, report.getTargetId())
                .set(REPORT.REASON, report.getReason())
                .set(REPORT.WEIGHT, report.getWeight())
                .set(REPORT.MANAGER_ACTION, report.getManagerAction())
                .set(REPORT.MANAGER_MEMBER_ID, report.getManagerMemberId())
                .set(REPORT.MANAGER_ACTION_DATE, report.getManagerActionDate())
                .set(REPORT.CREATE_DATE, report.getCreateDate())
                .returning(REPORT.REPORT_ID)
                .fetchOne();
        if (record != null) {
            report.setReportId(record.getReportId());
            return report;
        }
        return null;
    }

    @Override
    public int getTotalWeight(String targetType, long targetId, long targetAdditionalId) {
        return dslContext
                .select(DSL.sum(REPORT.WEIGHT))
                .from(REPORT)
                .where(REPORT.TARGET_TYPE.eq(targetType)
                    .and(REPORT.TARGET_ID.eq(targetId))
                    .and(REPORT.TARGET_ADDITIONAL_ID.eq(targetAdditionalId)))
                .fetchOne().into(Integer.class);
    }
}
