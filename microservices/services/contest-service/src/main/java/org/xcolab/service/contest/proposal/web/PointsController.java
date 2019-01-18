package org.xcolab.service.contest.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.contest.pojo.IPointType;
import org.xcolab.client.contest.pojo.IPointsDistributionConfiguration;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.contest.proposal.domain.pointsdistributionconfiguration.PointsDistributionConfigurationDao;
import org.xcolab.service.contest.proposal.domain.pointtype.PointTypeDao;
import org.xcolab.service.contest.proposal.service.pointsdistributionconfiguration.PointsDistributionConfigurationService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
public class PointsController {

    @Autowired
    private PointsDistributionConfigurationDao pointsDistributionConfigurationDao;

    @Autowired
    private PointTypeDao pointTypeDao;

    @Autowired
    private PointsDistributionConfigurationService pointsDistributionConfigurationService;

    @RequestMapping(value = "/pointsDistributionConfigurations", method = RequestMethod.POST)
    public IPointsDistributionConfiguration createPointsDistributionConfiguration(@RequestBody
            IPointsDistributionConfiguration pointsDistributionConfiguration) {
        pointsDistributionConfiguration.setCreatedAt(new Timestamp(new Date().getTime()));
        return this.pointsDistributionConfigurationDao.create(pointsDistributionConfiguration);
    }

    @RequestMapping(value = "/pointsDistributionConfigurations", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<IPointsDistributionConfiguration> getPointsDistributionConfigurations(
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) Long pointTypeId
    ) {
        return pointsDistributionConfigurationDao.findByGiven(proposalId, pointTypeId);
    }

    @RequestMapping(value = "/pointsDistributionConfigurations/removeByProposalId", method = RequestMethod.DELETE)
    public String deletePointsDistributionConfigurationByProposalId(@RequestParam("proposalId") Long proposalId)
            throws NotFoundException {
        if (proposalId == null || proposalId == 0) {
            throw new NotFoundException("No PointsDistributionConfiguration with id given");
        } else {
            this.pointsDistributionConfigurationDao.deleteByProposalId(proposalId);
            return "PointsDistributionConfiguration deleted successfully";
        }
    }

    @RequestMapping(value = "/pointsDistributionConfigurations/verifyDistributionConfigurationsForProposalId", method = RequestMethod.GET)
    public String verifyDistributionConfigurationsForProposalId(@RequestParam("proposalId") Long proposalId)
            throws NotFoundException {

        if (proposalId == null || proposalId == 0) {
            throw new NotFoundException("No PointsDistributionConfiguration with id given");
        } else {
            this.pointsDistributionConfigurationService.verifyDistributionConfigurationsForProposalId(proposalId);
            return "PointsDistributionConfiguration checked successfully";
        }
    }

    @RequestMapping(value = "/pointsDistributionConfigurations/{id}", method = RequestMethod.PUT)
    public boolean updatePointsDistributionConfiguration(@RequestBody
            IPointsDistributionConfiguration pointsDistributionConfiguration,
            @PathVariable("id") Long id) throws NotFoundException {
        if (id == null || id == 0 || pointsDistributionConfigurationDao.get(id) == null) {
            throw new NotFoundException("No PointsDistributionConfiguration with id " + id);
        } else {
            return pointsDistributionConfigurationDao.update(pointsDistributionConfiguration);
        }
    }

    @RequestMapping(value = "/pointsDistributionConfigurations/getByTargetProposalTemplateSectionDefinitionId", method = RequestMethod.GET)
    public IPointsDistributionConfiguration getPointsDistributionConfiguration(@RequestParam("targetProposalTemplateSectionDefinitionId") Long targetProposalTemplateSectionDefinitionId) throws NotFoundException {
        if (targetProposalTemplateSectionDefinitionId == null || targetProposalTemplateSectionDefinitionId == 0) {
            throw new NotFoundException("No PointsDistributionConfiguration with the id given");
        } else {
            return pointsDistributionConfigurationDao.getByProposalTemplateSectionDefinitionId(targetProposalTemplateSectionDefinitionId);
        }
    }

    @RequestMapping(value = "/pointsDistributionConfigurations/{id}", method = RequestMethod.DELETE)
    public String deletePointsDistributionConfiguration(@PathVariable("id") Long id)
            throws NotFoundException {
        if (id == null || id == 0) {
            throw new NotFoundException("No PointsDistributionConfiguration with id given");
        } else {
            IPointsDistributionConfiguration pointsDistributionConfiguration = this.pointsDistributionConfigurationDao.get(id);
            if (pointsDistributionConfiguration != null) {
                this.pointsDistributionConfigurationDao.delete(pointsDistributionConfiguration.getId());
                return "PointsDistributionConfiguration deleted successfully";
            } else {
                throw new NotFoundException("No PointsDistributionConfiguration with id given");
            }
        }
    }

    @RequestMapping(value = "/pointTypes/{pointTypeId}", method = RequestMethod.GET)
    public IPointType getPointType(@PathVariable("pointTypeId") Long pointTypeId) throws NotFoundException {
        if (pointTypeId == null || pointTypeId == 0) {
            throw new NotFoundException("No pointTypeId given");
        } else {
            return pointTypeDao.get(pointTypeId);
        }
    }

    @RequestMapping(value = "/pointTypes", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<IPointType> getPointTypes(
            @RequestParam(required = false) Long parentPointTypeId
    ) {
        return pointTypeDao.findByGiven(parentPointTypeId);
    }
}
