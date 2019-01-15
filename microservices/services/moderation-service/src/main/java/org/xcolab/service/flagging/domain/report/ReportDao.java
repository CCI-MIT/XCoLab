package org.xcolab.service.flagging.domain.report;

import org.xcolab.client.flagging.pojo.AggregatedReport;
import org.xcolab.client.flagging.pojo.IReport;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

public interface ReportDao {
    List<IReport> findByGiven(PaginationHelper paginationHelper, Long reporteruserId,
            Long manageruserId, String targetType, Long targetId, Long targetAdditionalId,
            String managerAction);
    List<AggregatedReport> findAggregatedByGiven(
            PaginationHelper paginationHelper, Long reporteruserId,
            Long manageruserId, String targetType, Long targetId, Long targetAdditionalId,
            String managerAction);

    IReport get(long reportId);
    boolean update(IReport report);
    IReport create(IReport report);

    int getTotalWeight(String targetType, long targetId, long targetAdditionalId);

    int countByGiven(Long reporteruserId, Long manageruserId, String targetType, Long targetId,
            Long targetAdditionalId, String managerAction);
    int countAggregatedByGiven(Long reporteruserId, Long manageruserId,
            String targetType, Long targetId, Long targetAdditionalId, String managerAction);
}
