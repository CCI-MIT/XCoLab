package org.xcolab.service.flagging.domain.reportTarget;

import org.xcolab.client.flagging.pojo.IReportTarget;
import org.xcolab.model.tables.pojos.ReportTarget;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.util.enums.flagging.TargetType;

import java.util.List;

public interface ReportTargetDao {
    List<IReportTarget> findByGiven(PaginationHelper paginationHelper, TargetType type);
    IReportTarget get(long reportTargetId);
    IReportTarget get(String type, String reason);

    boolean update(IReportTarget reportTarget);
    IReportTarget create(IReportTarget reportTarget);
    boolean delete(long reportTargetId);
}
