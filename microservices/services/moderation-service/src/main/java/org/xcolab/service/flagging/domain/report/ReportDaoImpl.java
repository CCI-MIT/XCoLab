package org.xcolab.service.flagging.domain.report;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record9;
import org.jooq.SelectQuery;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.Report;
import org.xcolab.model.tables.records.ReportRecord;
import org.xcolab.service.flagging.wrappers.AggregatedReport;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.commons.SortColumn;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import static org.xcolab.model.Tables.REPORT;

@Repository
public class ReportDaoImpl implements ReportDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public List<Report> findByGiven(PaginationHelper paginationHelper, Long reporterMemberId,
            Long managerMemberId, String targetType, Long targetId, Long targetAdditionalId,
            String managerAction) {
        final SelectQuery<Record> query = dslContext.select()
                .from(REPORT)
                .getQuery();

        filterConditions(reporterMemberId, managerMemberId, targetType, targetId,
                targetAdditionalId, managerAction, query);

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
        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getCount());
        return query.fetchInto(Report.class);
    }

    @Override
    public List<AggregatedReport> findAggregatedByGiven(PaginationHelper paginationHelper,
            Long reporterMemberId, Long managerMemberId, String targetType, Long targetId,
            Long targetAdditionalId, String managerAction) {

        final Field<Long> firstReportId = DSL.min(REPORT.REPORT_ID).as("firstReportId");
        final Field<BigDecimal> aggregatedWeight = DSL.sum(REPORT.WEIGHT).as("aggregatedWeight");
        final Field<Timestamp> firstReportDate = DSL.min(REPORT.CREATE_DATE).as("firstReportDate");
        final Field<Timestamp> lastReportDate = DSL.max(REPORT.CREATE_DATE).as("lastReportDate");
        final Field<Integer> count = DSL.count().as("count");

        final SelectQuery<Record9<
                String, String, Long, Long, Long, BigDecimal, Timestamp, Timestamp, Integer>>
                query = dslContext
                .select(REPORT.TARGET_TYPE, REPORT.REASON, REPORT.TARGET_ID,
                        REPORT.TARGET_ADDITIONAL_ID, firstReportId, aggregatedWeight,
                        firstReportDate, lastReportDate, count)
                .from(REPORT)
                .groupBy(REPORT.TARGET_TYPE, REPORT.REASON,
                        REPORT.TARGET_ID, REPORT.TARGET_ADDITIONAL_ID)
                .getQuery();

        filterConditions(reporterMemberId, managerMemberId, targetType, targetId,
                targetAdditionalId, managerAction, query);

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
                case "firstReportDate":
                    query.addOrderBy(sortColumn.isAscending()
                            ? firstReportDate.asc() : firstReportDate.desc());
                    break;
                case "lastReportDate":
                    query.addOrderBy(sortColumn.isAscending()
                            ? lastReportDate.asc() : lastReportDate.desc());
                    break;
                case "aggregatedWeight":
                    query.addOrderBy(sortColumn.isAscending()
                            ? aggregatedWeight.asc() : aggregatedWeight.desc());
                    break;
                case "count":
                    query.addOrderBy(sortColumn.isAscending()
                            ? count.asc() : count.desc());
                    break;
            }
        }
        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getCount());
        return query.fetchInto(AggregatedReport.class);
    }

    @Override
    public int countByGiven(Long reporterMemberId, Long managerMemberId, String targetType,
            Long targetId, Long targetAdditionalId, String managerAction) {
        final SelectQuery<Record1<Integer>> query = dslContext.select(DSL.countDistinct(
                        REPORT.TARGET_TYPE, REPORT.TARGET_ID, REPORT.TARGET_ADDITIONAL_ID))
                .from(REPORT)
                .getQuery();

        filterConditions(reporterMemberId, managerMemberId, targetType, targetId,
                targetAdditionalId, managerAction, query);

        return query.fetchOne().into(Integer.class);
    }

    @Override
    public int countAggregatedByGiven(Long reporterMemberId, Long managerMemberId,
            String targetType, Long targetId, Long targetAdditionalId, String managerAction) {
        final SelectQuery<Record1<Integer>> query = dslContext.select(DSL.countDistinct(
                REPORT.TARGET_TYPE, REPORT.TARGET_ID, REPORT.TARGET_ADDITIONAL_ID))
                .from(REPORT)
                .groupBy(REPORT.TARGET_TYPE, REPORT.REASON,
                        REPORT.TARGET_ID, REPORT.TARGET_ADDITIONAL_ID)
                .getQuery();

        filterConditions(reporterMemberId, managerMemberId, targetType, targetId,
                targetAdditionalId, managerAction, query);

        return query.fetchOne().into(Integer.class);
    }

    private void filterConditions(Long reporterMemberId, Long managerMemberId, String targetType,
            Long targetId, Long targetAdditionalId, String managerAction,
            SelectQuery<? extends Record> query) {
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
        if (targetAdditionalId != null) {
            query.addConditions(REPORT.TARGET_ADDITIONAL_ID.eq(targetAdditionalId));
        }
        if (managerAction != null) {
            query.addConditions(REPORT.MANAGER_ACTION.eq(managerAction));
        }
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
                .set(REPORT.TARGET_ADDITIONAL_ID, report.getTargetAdditionalId())
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
                .set(REPORT.TARGET_ADDITIONAL_ID, report.getTargetAdditionalId())
                .set(REPORT.REASON, report.getReason())
                .set(REPORT.WEIGHT, report.getWeight())
                .set(REPORT.COMMENT, report.getComment())
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
        final Record1<BigDecimal> record = dslContext
                .select(DSL.sum(REPORT.WEIGHT))
                .from(REPORT)
                .where(REPORT.TARGET_TYPE.eq(targetType)
                        .and(REPORT.TARGET_ID.eq(targetId))
                        .and(REPORT.TARGET_ADDITIONAL_ID.eq(targetAdditionalId)))
                .fetchOne();
        if (record == null) {
            return 0;
        }
        return record.into(Integer.class);
    }
}
