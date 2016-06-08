package org.xcolab.service.flagging.domain.report;

import org.xcolab.model.tables.pojos.Report;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

public interface ReportDao {
    List<Report> findByGiven(PaginationHelper paginationHelper, Long reporterMemberId,
            Long managerMemberId, String targetType, Long targetId, String managerAction);

    Report get(long reportId);
    boolean update(Report report);
    Report create(Report report);
}
