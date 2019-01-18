package org.xcolab.service.moderation.domain.reportTarget;

import org.xcolab.client.moderation.pojo.IReportTarget;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.util.enums.moderation.TargetType;

import java.util.List;

public interface ReportTargetDao {

    List<IReportTarget> findByGiven(PaginationHelper paginationHelper, TargetType type);

    IReportTarget get(long reportTargetId);

    IReportTarget get(String type, String reason);

    boolean update(IReportTarget reportTarget);

    IReportTarget create(IReportTarget reportTarget);

    boolean delete(long reportTargetId);
}
