package org.xcolab.service.flagging.domain.report;

import org.xcolab.model.tables.pojos.Report;
import org.xcolab.service.flagging.wrappers.AggregatedReport;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

public interface ReportDao {
    List<Report> findByGiven(PaginationHelper paginationHelper, Long reporterMemberId,
            Long managerMemberId, String targetType, Long targetId, Long targetAdditionalId,
            String managerAction);
    List<AggregatedReport> findAggregatedByGiven(
            PaginationHelper paginationHelper, Long reporterMemberId,
            Long managerMemberId, String targetType, Long targetId, Long targetAdditionalId,
            String managerAction);

    Report get(long reportId);
    boolean update(Report report);
    Report create(Report report);

    int getTotalWeight(String targetType, long targetId, long targetAdditionalId);

    int countByGiven(Long reporterMemberId, Long managerMemberId, String targetType, Long targetId,
            Long targetAdditionalId, String managerAction);
    int countAggregatedByGiven(Long reporterMemberId, Long managerMemberId,
            String targetType, Long targetId, Long targetAdditionalId, String managerAction);
}
