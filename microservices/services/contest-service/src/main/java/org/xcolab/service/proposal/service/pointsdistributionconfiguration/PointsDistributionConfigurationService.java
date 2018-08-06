package org.xcolab.service.proposal.service.pointsdistributionconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.model.tables.pojos.PointType;
import org.xcolab.model.tables.pojos.PointsDistributionConfiguration;
import org.xcolab.service.proposal.domain.pointsdistributionconfiguration
        .PointsDistributionConfigurationDao;
import org.xcolab.service.proposal.domain.pointtype.PointTypeDao;
import org.xcolab.service.proposal.enums.ReceiverLimitationStrategy;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.proposal.service.proposal.ProposalService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class PointsDistributionConfigurationService {

    private final PointsDistributionConfigurationDao pointsDistributionConfigurationDao;
    private final PointTypeDao pointTypeDao;
    private final ProposalService proposalService;

    private static final double EQUALITY_EPSILON = 0.00000001d;

    @Autowired
    public PointsDistributionConfigurationService(PointsDistributionConfigurationDao pointsDistributionConfigurationDao, PointTypeDao pointTypeDao, ProposalService proposalService) {
        this.pointsDistributionConfigurationDao = pointsDistributionConfigurationDao;
        this.pointTypeDao = pointTypeDao;
        this.proposalService = proposalService;
    }

    public PointsDistributionConfiguration getPointsDistributionConfiguration(long planSectionDefinitionId){
        PointsDistributionConfiguration config = null;
        try{
            config = pointsDistributionConfigurationDao.getByProposalTemplateSectionDefinitionId(planSectionDefinitionId);
        } catch(NotFoundException ignored) {}
        return config;
    }

    public List<PointsDistributionConfiguration> getPointsDistributionConfiguration(long proposalId, long pointTypeId) {
            return pointsDistributionConfigurationDao.findByGiven(proposalId, pointTypeId);
    }

    public void verifyDistributionConfigurationsForProposalId(long proposalId) {
        Map<Long, List<PointsDistributionConfiguration>> pdcsByPointTypeId = new HashMap<>();
        for (PointsDistributionConfiguration pdc : pointsDistributionConfigurationDao.findByGiven(proposalId, null)) {
            List<PointsDistributionConfiguration> pdcs = pdcsByPointTypeId
                    .computeIfAbsent(pdc.getPointTypeId(), k -> new ArrayList<>());
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
                    //_log.warn(String.format("Fixing PointsDistributionConfiguration for proposal %d pointType %d: sum is %f (should be 1)",
                    //       proposalId, pointTypeId, sum));
                    double scaleFactor = 1.0 / sum;
                    for (PointsDistributionConfiguration pdc : pdcs) {
                        pdc.setPercentage(pdc.getPercentage() * scaleFactor);
                        pointsDistributionConfigurationDao.update(pdc);
                    }
                }
            } catch (NotFoundException ignored) {

            }
        }
    }

    private void verifyTeamMemberships(long proposalId, long pointTypeId, List<PointsDistributionConfiguration> pdcs) {

        try {
            Set<Long> userIds = new HashSet<>();
            Set<Long> missinguserIds = new HashSet<>();
            for (Member user : proposalService.getProposalMembers(proposalId)) {
                userIds.add(user.getId());
                missinguserIds.add(user.getId());
            }

            for (PointsDistributionConfiguration pdc : pdcs) {
                if (userIds.contains(pdc.getTargetUserId())) {
                    missinguserIds.remove(pdc.getTargetUserId());
                } else {
                    pointsDistributionConfigurationDao.delete(pdc.getId());
                    //_log.info(String.format("Removing PointsDistributionConfiguration non-team member %d for proposal %d pointType %d.",
                    //       pdc.getTargetUserId(), proposalId, pointTypeId));
                }
            }

            for (long userId : missinguserIds) {
                addDistributionConfiguration(proposalId, pointTypeId, userId, 0L, 1.0 / userIds.size(), 0L);
                // _log.info(String.format("Adding missing PointsDistributionConfiguration for team member %d for proposal %d pointType %d.",
                //        userId, proposalId, pointTypeId));
            }
        }catch (ProposalNotFoundException ignored){

        }
    }

    public PointsDistributionConfiguration addDistributionConfiguration(long proposalId, long pointTypeId,
                                                                        Long targetUserId, Long targetSubProposalId,
                                                                        double percentage, long creator) {


        PointsDistributionConfiguration model = new PointsDistributionConfiguration();

        model.setProposalId(proposalId);
        model.setPointTypeId(pointTypeId);
        if (targetUserId != null) {
            model.setTargetUserId(targetUserId);
        }
        if (targetSubProposalId != null) {
            model.setTargetSubProposalId(targetSubProposalId);
        }
        model.setPercentage(percentage);
        model.setAuthorUserId(creator);
        model.setCreatedAt(new Timestamp((new Date()).getTime()));

        model = pointsDistributionConfigurationDao.create(model);

        return model;
    }
}
