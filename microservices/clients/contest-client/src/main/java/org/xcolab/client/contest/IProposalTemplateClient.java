package org.xcolab.client.contest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.pojo.IProposalTemplate;
import org.xcolab.client.contest.pojo.IProposalTemplateSection;
import org.xcolab.client.contest.pojo.wrapper.ProposalTemplateSectionDefinitionWrapper;
import org.xcolab.util.http.client.RestResource1;

import java.util.List;

@FeignClient("xcolab-contest-service")
public interface IProposalTemplateClient {

    @GetMapping("/proposalTemplates/{proposalTemplateId}")
    IProposalTemplate getProposalTemplate(
            @PathVariable("proposalTemplateId") Long proposalTemplateId);

    @GetMapping("/proposalTemplates")
    List<IProposalTemplate> getProposalTemplates();

    @DeleteMapping("/proposalTemplates/{id}")
    boolean deleteProposalTemplate(@PathVariable("id") Long id);

    @PostMapping("/proposalTemplates")
    IProposalTemplate createProposalTemplate(@RequestBody IProposalTemplate proposalTemplate);

    @PutMapping("/proposalTemplates")
    boolean updateProposalTemplate(@RequestBody IProposalTemplate proposalTemplate);

    @GetMapping("/proposalTemplateSectionDefinitions/{proposalTemplateSectionDefinitionId}")
    ProposalTemplateSectionDefinitionWrapper getProposalTemplateSectionDefinition(
            @PathVariable("proposalTemplateSectionDefinitionId")
                    Long proposalTemplateSectionDefinitionId);

    @PutMapping("/proposalTemplateSectionDefinitions")
    boolean updateProposalTemplateSectionDefinition(@RequestBody
            ProposalTemplateSectionDefinitionWrapper proposalTemplateSectionDefinition);

    @PostMapping("/proposalTemplateSectionDefinitions")
    ProposalTemplateSectionDefinitionWrapper createProposalTemplateSectionDefinition(@RequestBody
            ProposalTemplateSectionDefinitionWrapper proposalTemplateSectionDefinition);

    @GetMapping("/proposalTemplateSectionDefinitions")
    List<ProposalTemplateSectionDefinitionWrapper> getProposalTemplateSectionDefinitionByProposalTemplateId(
            @RequestParam(value = "proposalTemplateId", required = false) Long proposalTemplateId,
            @RequestParam(value = "weight", required = false, defaultValue = "false")
                    Boolean weight);

    @DeleteMapping("/proposalTemplateSectionDefinitions/{id}")
    boolean deleteProposalTemplateSectionDefinition(@PathVariable("id") Long id);

    @DeleteMapping("/proposalTemplateSections/deleteProposalTemplateSection")
    boolean deleteProposalTemplateSection(
            @RequestParam("proposalTemplateId") Long proposalTemplateId,
            @RequestParam("proposalTemplateSectionDefinitionId")
                    Long proposalTemplateSectionDefinitionId);

    default List<IProposalTemplateSection> getProposalTemplateSectionsByProposalTemplateId(
            Long proposalTemplateId) {
        return getProposalTemplateSections(proposalTemplateId, null);
    }

    @PostMapping("/proposalTemplateSections")
    IProposalTemplateSection createProposalTemplateSection(
            @RequestBody IProposalTemplateSection proposalTemplateSection);

    @PostMapping("/proposalTemplateSections/updateTemplateSection")
    boolean updateProposalTemplateSection(
            @RequestBody IProposalTemplateSection proposalTemplateSection);

    default List<IProposalTemplateSection> getProposalTemplateSectionsBySectionDefinitionId(
            long sectionDefinitionId) {
        return getProposalTemplateSections(null, sectionDefinitionId);
    }

    @GetMapping("/proposalTemplateSections")
    List<IProposalTemplateSection> getProposalTemplateSections(
            @RequestParam(value = "proposalTemplateId", required = false) Long proposalTemplateId,
            @RequestParam(value = "planSectionId", required = false) Long planSectionId);

    default IProposalTemplateSection getProposalTemplateSection(Long proposalTemplateId,
            Long sectionDefinitionId) {
        return getProposalTemplateSections(proposalTemplateId, sectionDefinitionId).stream()
                .findFirst().orElse(null);
    }
}
