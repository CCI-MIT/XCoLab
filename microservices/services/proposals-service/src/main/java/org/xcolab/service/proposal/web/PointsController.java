package org.xcolab.service.proposal.web;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.model.tables.pojos.PointType;
import org.xcolab.service.proposal.domain.pointsdistributionconfiguration.PointsDistributionConfigurationDao;
import org.xcolab.model.tables.pojos.PointsDistributionConfiguration;
import org.xcolab.service.proposal.domain.pointtype.PointTypeDao;
import org.xcolab.service.proposal.exceptions.NotFoundException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
public class PointsController {

    @Autowired
    private PointsDistributionConfigurationDao pointsDistributionConfigurationDao;

    @Autowired
    private PointTypeDao pointTypeDao;

    @RequestMapping(value = "/pointsDistributionConfigurations", method = RequestMethod.POST)
    public PointsDistributionConfiguration createPointsDistributionConfiguration(@RequestBody PointsDistributionConfiguration pointsDistributionConfiguration) {
        pointsDistributionConfiguration.setCreateDate(new Timestamp(new Date().getTime()));
        return this.pointsDistributionConfigurationDao.create(pointsDistributionConfiguration);
    }

    @RequestMapping(value = "/pointsDistributionConfigurations", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<PointsDistributionConfiguration> getPointsDistributionConfigurations(
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
    @RequestMapping(value = "/pointsDistributionConfigurations/{id_}", method = RequestMethod.PUT)
    public boolean updatePointsDistributionConfiguration(@RequestBody PointsDistributionConfiguration pointsDistributionConfiguration,
                                                         @PathVariable("id_") Long id_) throws NotFoundException {

        if (id_ == null || id_ == 0 || pointsDistributionConfigurationDao.get(id_) == null) {
            throw new NotFoundException("No PointsDistributionConfiguration with id " + id_);
        } else {
            return pointsDistributionConfigurationDao.update(pointsDistributionConfiguration);
        }
    }
    @RequestMapping(value = "/pointsDistributionConfigurations/getByTargetPlanSectionDefinitionId", method = RequestMethod.GET)
    public PointsDistributionConfiguration getPointsDistributionConfiguration(@RequestParam("targetPlanSectionDefinitionId") Long targetPlanSectionDefinitionId) throws NotFoundException {
        if (targetPlanSectionDefinitionId == null || targetPlanSectionDefinitionId == 0) {
            throw new NotFoundException("No PointsDistributionConfiguration with the id given");
        } else {
            return pointsDistributionConfigurationDao.getByPlanSectionDefinitionId(targetPlanSectionDefinitionId);
        }
    }
    @RequestMapping(value = "/pointsDistributionConfigurations/{id_}", method = RequestMethod.DELETE)
    public String deletePointsDistributionConfiguration(@PathVariable("id_") Long id_)
            throws NotFoundException {

        if (id_ == null || id_ == 0) {
            throw new NotFoundException("No PointsDistributionConfiguration with id given");
        } else {
            PointsDistributionConfiguration pointsDistributionConfiguration = this.pointsDistributionConfigurationDao.get(id_);
            if (pointsDistributionConfiguration != null) {
                this.pointsDistributionConfigurationDao.delete(pointsDistributionConfiguration.getId_());
                return "PointsDistributionConfiguration deleted successfully";
            } else {
                throw new NotFoundException("No PointsDistributionConfiguration with id given");
            }
        }
    }



    @RequestMapping(value = "/pointTypes/{pointTypeId}", method = RequestMethod.GET)
    public PointType getPointType(@PathVariable("pointTypeId") Long pointTypeId) throws NotFoundException {
        if (pointTypeId == null || pointTypeId == 0) {
            throw new NotFoundException("No pointTypeId given");
        } else {
            return pointTypeDao.get(pointTypeId);
        }
    }

    @RequestMapping(value = "/pointTypes", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<PointType> getPointTypes(
            @RequestParam(required = false) Long parentPointTypeId
    ) {
        return pointTypeDao.findByGiven(parentPointTypeId);
    }


}
