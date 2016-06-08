package org.xcolab.service.flagging.domain.reportTarget;

import org.xcolab.model.tables.pojos.ReportTarget;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

public interface ReportTargetDao {
    List<ReportTarget> findByGiven(PaginationHelper paginationHelper);
    ReportTarget get(String type, String reason);
    boolean update(ReportTarget reportTarget);
    ReportTarget create(ReportTarget reportTarget);
}
