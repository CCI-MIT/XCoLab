package org.xcolab.service.proposal.service.pointsdistributionconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.xcolab.model.tables.pojos.PointType;
import org.xcolab.model.tables.pojos.PointsDistributionConfiguration;
import org.xcolab.service.proposal.domain.pointsdistributionconfiguration.PointsDistributionConfigurationDao;
import org.xcolab.service.proposal.domain.pointtype.PointTypeDao;
import org.xcolab.service.proposal.exceptions.NotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PointsDistributionConfigurationService {

    private final PointsDistributionConfigurationDao pointsDistributionConfigurationDao;
    private final PointTypeDao pointTypeDao;

    @Autowired
    public PointsDistributionConfigurationService(PointsDistributionConfigurationDao pointsDistributionConfigurationDao, PointTypeDao pointTypeDao) {
        this.pointsDistributionConfigurationDao = pointsDistributionConfigurationDao;
        this.pointTypeDao = pointTypeDao;
    }

    public void verifyDistributionConfigurationsForProposalId(long proposalId) {
        Map<Long, List<PointsDistributionConfiguration>> pdcsByPointTypeId = new HashMap<>();
        for (org.xcolab.model.tables.pojos.PointsDistributionConfiguration pdc : pointsDistributionConfigurationDao.findByGiven(proposalId, null)) {
            List<PointsDistributionConfiguration> pdcs = EntityGroupingUtil.getInnerListOrCreate(pdc.getPointTypeId(), pdcsByPointTypeId);
            pdcs.add(pdc);
        }

        for (Map.Entry<Long, List<PointsDistributionConfiguration>> entry : pdcsByPointTypeId.entrySet()) {
            final long pointTypeId = entry.getKey();
            final List<PointsDistributionConfiguration> pdcs = entry.getValue();


            try {
                PointType pointType = pointTypeDao.get(pointTypeId);
                if (ReceiverLimitationStrategy.valueOf(pointType.getReceiverLimitationStrategy())
                        .equals(ReceiverLimitationStrategy.ANY_TEAM_MEMBER)) {
                    verifyTeamMemberships(proposalId, pointTypeId, pdcs);
                }

                double sum = 0;
                for (PointsDistributionConfiguration pdc : pdcs) {
                    sum += pdc.getPercentage();
                }

                if (Math.abs(sum - 1.0) > EQUALITY_EPSILON) {
                    _log.warn(String.format("Fixing PointsDistributionConfiguration for proposal %d pointType %d: sum is %f (should be 1)",
                            proposalId, pointTypeId, sum));
                    double scaleFactor = 1.0 / sum;
                    for (PointsDistributionConfiguration pdc : pdcs) {
                        pdc.setPercentage(pdc.getPercentage() * scaleFactor);
                        pdc.persist();
                    }
                }
            }catch (NotFoundException ignored){

            }
        }
    }
}
