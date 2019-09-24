package org.xcolab.client.contest.proposals;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.exceptions.PointDistributionNotFoundException;
import org.xcolab.client.contest.pojo.IPointsDistributionConfiguration;
import org.xcolab.client.contest.pojo.wrapper.PointTypeWrapper;

import java.util.List;

@FeignClient("xcolab-contest-service")
public interface IPointsClient {

    @PostMapping("/pointsDistributionConfigurations")
    IPointsDistributionConfiguration createPointsDistributionConfiguration(@RequestBody
            IPointsDistributionConfiguration pointsDistributionConfiguration);

    @GetMapping("/pointsDistributionConfigurations/{targetProposalTemplateSectionDefinitionId}")
    IPointsDistributionConfiguration getPointsDistributionConfigurationByTargetProposalTemplateSectionDefinitionId(
            @PathVariable("targetProposalTemplateSectionDefinitionId")
                    Long targetProposalTemplateSectionDefinitionId) throws PointDistributionNotFoundException;

    @PutMapping("/pointsDistributionConfigurations")
    boolean updatePointsDistributionConfiguration(
            @RequestBody IPointsDistributionConfiguration pointsDistributionConfiguration);

    @GetMapping("/pointsDistributionConfigurations")
    List<IPointsDistributionConfiguration> getPointsDistributionByProposalIdPointTypeId(
            @RequestParam(value = "proposalId", required = false) Long proposalId,
            @RequestParam(value = "pointTypeId", required = false) Long pointTypeId);

    @DeleteMapping("/pointsDistributionConfigurations/{id}")
    boolean deletePointsDistributionConfiguration(@PathVariable("id") Long id);

    @GetMapping("/pointsDistributionConfigurations/verifyDistributionConfigurationsForProposalId")
    boolean verifyDistributionConfigurationsForProposalId(
            @RequestParam("proposalId") Long proposalId);

    @DeleteMapping("/pointsDistributionConfigurations/removeByProposalId")
    boolean deletePointsDistributionConfigurationByProposalId(
            @RequestParam("proposalId") Long proposalId);

    @GetMapping("/pointTypes/{pointTypeId}")
    PointTypeWrapper getPointType(@PathVariable("pointTypeId") Long pointTypeId);

    @GetMapping("/pointTypes")
    List<PointTypeWrapper> getPointTypes(
            @RequestParam(value = "parentPointTypeId", required = false) Long parentPointTypeId);

    default List<PointTypeWrapper> getAllPointTypes() {
        return getPointTypes(null);
    }

    default IPointsDistributionConfiguration getPointsDistributionConfigurationByTargetProposalTemplateSectionDefinitionIdOrNull(Long templateSection){
        try{
            return getPointsDistributionConfigurationByTargetProposalTemplateSectionDefinitionId(templateSection);
        } catch (PointDistributionNotFoundException e) {
            return null;
        }

    }
}
