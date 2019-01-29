package org.xcolab.client.contest.proposals;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.pojo.wrapper.ProposalAttribute;
import org.xcolab.client.contest.pojo.wrapper.ProposalAttributeHelperDataDto;
import org.xcolab.client.contest.pojo.wrapper.ProposalUnversionedAttribute;
import org.xcolab.client.contest.pojo.wrapper.ProposalUnversionedAttributeHelperDataDto;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.exceptions.ProposalAttributeNotFoundException;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

@FeignClient("xcolab-contest-service")
public interface IProposalAttributeClient {

    default ProposalAttribute getProposalAttribute(Long proposalId, String name,
            Long additionalId) {
        return getProposalAttributes(proposalId, name, additionalId, null).stream().findFirst()
                .orElse(null);
    }

    default ProposalAttribute getProposalAttribute(Long proposalId, Integer version, String name,
            Long additionalId) {
        return getProposalAttributes(proposalId, name, additionalId, version).stream().findFirst()
                .orElse(null);
    }

    @GetMapping("/proposalAttributes/{proposalAttributeId}")
    ProposalAttribute getProposalAttribute(
            @PathVariable("proposalAttributeId") Long proposalAttributeId)
            throws ProposalAttributeNotFoundException;

    @DeleteMapping("/proposalAttributes/{id}")
    boolean deleteProposalAttribute(@PathVariable("id") Long id);

    @PutMapping("/proposalAttributes")
    boolean updateProposalAttribute(@RequestBody ProposalAttribute proposalAttribute);

    @GetMapping("/proposalAttributes")
    List<ProposalAttribute> getProposalAttributes(
            @RequestParam(value = "proposalId", required = false) Long proposalId,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "additionalId", required = false) Long additionalId,
            @RequestParam(value = "version", required = false) Integer version);

    default List<ProposalAttribute> getAllProposalAttributes(Long proposalId) {
        return getProposalAttributes(proposalId, null, null, null);
    }

    default List<ProposalAttribute> getAllProposalAttributes(Long proposalId, Integer version) {
        return getProposalAttributes(proposalId, null, null, version);
    }

    default List<ProposalAttribute> getAllProposalAttributes(Long proposalId, String name,
            Long additionalId) {
        return getProposalAttributes(proposalId, name, additionalId, null);
    }

    @GetMapping("/proposals/{proposalId}/versions/{version}/attributeHelper")
    ProposalAttributeHelperDataDto getProposalAttributeHelperData(
            @PathVariable("proposalId") Long proposalId, @PathVariable("version") Integer version);

    @GetMapping("/proposals/{proposalId}/attributeHelper")
    ProposalUnversionedAttributeHelperDataDto getProposalUnversionedAttributeHelperData(
            @PathVariable("proposalId") Long proposalId);

    default void invalidateProposalAttibuteCache(ProposalWrapper proposal) {
        ServiceRequestUtils.invalidateCache(CacheKeys.withClass(ProposalWrapper.class)
                        .withParameter("proposalId", proposal.getId())
                        .withParameter("version", proposal.getVersion()).asList(),
                CacheName.PROPOSAL_DETAILS);
    }

    @PostMapping("/proposalAttributes/setProposalAttribute")
    ProposalAttribute setProposalAttribute(@RequestBody ProposalAttribute proposalAttribute,
            @RequestParam("authorUserId") Long authorUserId);

    default ProposalAttribute setProposalAttribute(Long userId, Long proposalId, String name,
            Long additionalId, String stringValue, Long numericValue, Double doubleValue,
            Integer version) {
        ProposalAttribute proposalAttribute = new ProposalAttribute();
        proposalAttribute.setProposalId(proposalId);
        proposalAttribute.setName(name);
        proposalAttribute.setAdditionalId(additionalId);
        proposalAttribute.setStringValue(stringValue);
        proposalAttribute.setNumericValue(numericValue);
        proposalAttribute.setRealValue(doubleValue);
        proposalAttribute.setVersion(version);
        return setProposalAttribute(proposalAttribute, userId);
    }

    default ProposalAttribute setProposalAttribute(Long userId, Long proposalId, String name,
            Long additionalId, String stringValue, Integer version) {
        return setProposalAttribute(userId, proposalId, name, additionalId, stringValue,
                null, null, version);
    }

    default ProposalAttribute setProposalAttribute(Long userId, Long proposalId, String name,
            Long additionalId, Long numericValue, Integer version) {
        return setProposalAttribute(userId, proposalId, name, additionalId, null,
                numericValue, null, version);
    }

    @DeleteMapping("/proposalUnversionedAttributes/{id}")
    boolean deleteProposalUnversionedAttribute(@PathVariable("id") Long id);

    @GetMapping("/proposalUnversionedAttributes")
    List<ProposalUnversionedAttribute> getProposalUnversionedAttributesByProposalId(
            @RequestParam(value = "proposalId", required = false) Long proposalId);

    default void createOrUpdateUnversionedStringAttribute(Long proposalId, String attributeName,
            long authorUserId, String attributeValue) {
        createOrUpdateUnversionedAttribute(proposalId, attributeName, authorUserId, null,
                attributeValue, null);
    }

    default void createOrUpdateUnversionedDoubleAttribute(Long proposalId, String attributeName,
            long authorUserId, double attributeValue) {
        createOrUpdateUnversionedAttribute(proposalId, attributeName, authorUserId, null,
                null, attributeValue);
    }

    default void createOrUpdateUnversionedLongAttribute(Long proposalId, String attributeName,
            long authorUserId, long attributeValue) {
        createOrUpdateUnversionedAttribute(proposalId, attributeName, authorUserId, attributeValue,
                null, null);
    }

    default void createOrUpdateUnversionedAttribute(Long proposalId, String attributeName,
            long authorUserId, Long longValue, String stringValue, Double doubleValue) {
        ProposalUnversionedAttribute pua;
        try {
            pua = getProposalUnversionedAttribute(proposalId, attributeName);
            pua.setFirstAuthorUserId(authorUserId);
            pua.setNumericValue(longValue);
            pua.setStringValue(stringValue);
            pua.setRealValue(doubleValue);
            updateProposalUnversionedAttribute(pua);

        } catch (EntityNotFoundException e) {
            pua = new ProposalUnversionedAttribute();
            pua.setFirstAuthorUserId(authorUserId);
            pua.setName(attributeName);
            pua.setNumericValue(longValue);
            pua.setStringValue(stringValue);
            pua.setRealValue(doubleValue);
            pua.setProposalId(proposalId);
            createProposalUnversionedAttribute(pua);
        }
    }

    @PostMapping("/proposalUnversionedAttributes")
    ProposalUnversionedAttribute createProposalUnversionedAttribute(
            @RequestBody ProposalUnversionedAttribute proposalUnversionedAttribute);

    @GetMapping("/proposalUnversionedAttributes/getByProposalIdName")
    ProposalUnversionedAttribute getProposalUnversionedAttribute(
            @RequestParam(value = "proposalId", required = false) Long proposalId,
            @RequestParam(value = "name", required = false) String name)
            throws EntityNotFoundException;

    @PutMapping("/proposalUnversionedAttributes")
    boolean updateProposalUnversionedAttribute(
            @RequestBody ProposalUnversionedAttribute proposalUnversionedAttribute);
}
