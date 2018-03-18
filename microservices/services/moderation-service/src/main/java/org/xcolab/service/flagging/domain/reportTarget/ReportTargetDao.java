package org.xcolab.service.flagging.domain.reportTarget;

import org.xcolab.model.tables.pojos.ReportTarget;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.commons.enums.flagging.TargetType;

import java.util.List;

public interface ReportTargetDao {
    List<ReportTarget> findByGiven(PaginationHelper paginationHelper, TargetType type );
    ReportTarget get(long reportTargetId);
    ReportTarget get(String type, String reason);

    boolean update(ReportTarget reportTarget);
    ReportTarget create(ReportTarget reportTarget);
    boolean delete(long reportTargetId);
}
