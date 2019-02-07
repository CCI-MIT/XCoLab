package org.xcolab.service.contest.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.contest.exceptions.PointDistributionNotFoundException;
import org.xcolab.client.contest.pojo.IPointsDistributionConfiguration;
import org.xcolab.client.contest.pojo.wrapper.PointTypeWrapper;
import org.xcolab.client.contest.proposals.IPointsClient;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.contest.proposal.domain.pointsdistributionconfiguration.PointsDistributionConfigurationDao;
import org.xcolab.service.contest.proposal.domain.pointtype.PointTypeDao;
import org.xcolab.service.contest.proposal.service.pointsdistributionconfiguration.PointsDistributionConfigurationService;
import org.xcolab.util.http.exceptions.RuntimeEntityNotFoundException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
public class PointsController implements IPointsClient {

    private PointsDistributionConfigurationDao pointsDistributionConfigurationDao;
    private PointTypeDao pointTypeDao;

    private PointsDistributionConfigurationService pointsDistributionConfigurationService;

    @Autowired
    public PointsController(
            PointsDistributionConfigurationDao pointsDistributionConfigurationDao,
            PointTypeDao pointTypeDao,
            PointsDistributionConfigurationService pointsDistributionConfigurationService) {
        this.pointsDistributionConfigurationDao = pointsDistributionConfigurationDao;
        this.pointTypeDao = pointTypeDao;
        this.pointsDistributionConfigurationService = pointsDistributionConfigurationService;
    }

    @Override
    @PostMapping("/pointsDistributionConfigurations")
    public IPointsDistributionConfiguration createPointsDistributionConfiguration(
            @RequestBody IPointsDistributionConfiguration pointsDistributionConfiguration) {
        pointsDistributionConfiguration.setCreatedAt(new Timestamp(new Date().getTime()));
        return this.pointsDistributionConfigurationDao.create(pointsDistributionConfiguration);
    }

    @Override
    @GetMapping("/pointsDistributionConfigurations")
    public List<IPointsDistributionConfiguration> getPointsDistributionByProposalIdPointTypeId(
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) Long pointTypeId) {
        return pointsDistributionConfigurationDao.findByGiven(proposalId, pointTypeId);
    }

    @Override
    @DeleteMapping("/pointsDistributionConfigurations/removeByProposalId")
    public boolean deletePointsDistributionConfigurationByProposalId(
            @RequestParam Long proposalId) {
        if (proposalId == null || proposalId == 0) {
            throw new RuntimeEntityNotFoundException(
                    "PointsDistributionConfiguration not found with id " + proposalId);
        } else {
            this.pointsDistributionConfigurationDao.deleteByProposalId(proposalId);
            return true;
        }
    }

    @Override
    @GetMapping("/pointsDistributionConfigurations/verifyDistributionConfigurationsForProposalId")
    public boolean verifyDistributionConfigurationsForProposalId(@RequestParam Long proposalId) {
        if (proposalId == null || proposalId == 0) {
            throw new RuntimeEntityNotFoundException(
                    "PointsDistributionConfiguration not found with id " + proposalId);
        } else {
            this.pointsDistributionConfigurationService
                    .verifyDistributionConfigurationsForProposalId(proposalId);
            return true;
        }
    }

    @Override
    @PutMapping("/pointsDistributionConfigurations")
    public boolean updatePointsDistributionConfiguration(
            @RequestBody IPointsDistributionConfiguration pointsDistributionConfiguration) {
        Long id = pointsDistributionConfiguration.getId();
        try {
            if (!(id == null || id == 0 || pointsDistributionConfigurationDao.get(id) == null)) {
                return pointsDistributionConfigurationDao.update(pointsDistributionConfiguration);
            }
        } catch (NotFoundException e) {}
        throw new RuntimeEntityNotFoundException("PointsDistributionConfiguration with id " + id);
    }

    @Override
    @GetMapping("/pointsDistributionConfigurations/{targetProposalTemplateSectionDefinitionId}")

    public IPointsDistributionConfiguration getPointsDistributionConfigurationByTargetProposalTemplateSectionDefinitionId(
            @PathVariable("targetProposalTemplateSectionDefinitionId")
                    Long targetProposalTemplateSectionDefinitionId) throws PointDistributionNotFoundException {
        if (targetProposalTemplateSectionDefinitionId == null
                || targetProposalTemplateSectionDefinitionId == 0) {
            throw new PointDistributionNotFoundException(
                    "PointsDistributionConfiguration not found with the id "
                            + targetProposalTemplateSectionDefinitionId);
        }
        try {
            return pointsDistributionConfigurationDao.getByProposalTemplateSectionDefinitionId(
                    targetProposalTemplateSectionDefinitionId);
        } catch (NotFoundException e) {}
        throw new PointDistributionNotFoundException(
                "PointsDistributionConfiguration not found with the id "
                        + targetProposalTemplateSectionDefinitionId);
    }

    @Override
    @DeleteMapping("/pointsDistributionConfigurations/{id}")
    public boolean deletePointsDistributionConfiguration(@PathVariable Long id) {
        if (id == null || id == 0) {
            throw new RuntimeEntityNotFoundException(
                    "PointsDistributionConfiguration not found with id " + id);
        } else {
            try {
                IPointsDistributionConfiguration pointsDistributionConfiguration =
                        this.pointsDistributionConfigurationDao.get(id);
                if (pointsDistributionConfiguration != null) {
                    this.pointsDistributionConfigurationDao
                            .delete(pointsDistributionConfiguration.getId());
                    return true;
                }
            } catch (NotFoundException e) {}
            throw new RuntimeEntityNotFoundException(
                    "PointsDistributionConfiguration not found with id " + id);
        }
    }

    @Override
    @GetMapping("/pointTypes/{pointTypeId}")
    public PointTypeWrapper getPointType(@PathVariable Long pointTypeId) {
        if (pointTypeId == null || pointTypeId == 0) {
            throw new RuntimeEntityNotFoundException("pointTypeId not given");
        }
        try {
            return pointTypeDao.get(pointTypeId);
        } catch (NotFoundException e) {
            throw new RuntimeEntityNotFoundException("PointType not found with id " + pointTypeId);
        }
    }

    @Override
    @GetMapping("/pointTypes")
    public List<PointTypeWrapper> getPointTypes(
            @RequestParam(required = false) Long parentPointTypeId) {
        return pointTypeDao.findByGiven(parentPointTypeId);
    }
}
