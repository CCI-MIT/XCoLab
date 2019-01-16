package org.xcolab.client.admin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.admin.pojo.IContestTypeAttribute;
import org.xcolab.util.enums.Plurality;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@FeignClient("xcolab-admin-service")
public interface IContestTypeClient {

    @GetMapping("/contestTypeAttributes")
    List<IContestTypeAttribute> listContestTypeAttributes();

    @GetMapping("/contestTypeAttributes/{attributeName}")
    Optional<IContestTypeAttribute> getContestTypeAttribute(
            @PathVariable("attributeName") String attributeName,
            @RequestParam("additionalId") Long additionalId,
            @RequestParam(value = "locale", required = false) String locale);

    @PostMapping("/contestTypeAttributes")
    IContestTypeAttribute createContestTypeAttribute(
            @RequestBody IContestTypeAttribute contestTypeAttribute);

    @PutMapping("/contestTypeAttributes")
    boolean updateContestTypeAttribute(@RequestBody IContestTypeAttribute contestTypeAttribute);

    default List<ContestType> getActiveContestTypes() {
        final List<ContestType> contestTypes = getAllContestTypes();
        return contestTypes.stream()
                .filter(ContestType::isActive)
                .collect(Collectors.toList());
    }

    default List<ContestType> getAllContestTypes() {
        return listContestTypeAttributes().stream()
                .map(IContestTypeAttribute::getAdditionalId)
                .distinct()
                .map(ContestType::new)
                .collect(Collectors.toList());
    }

    default ContestType getContestType(long id) {
        return new ContestType(id);
    }

    default ContestType getContestType(long id, String language) {
        return new ContestType(id, language);
    }

    default String getProposalNames(List<Long> contestTypeIds, String plurality,
            String conjunction) {
        return getJoinedNameString(contestTypeIds, true, plurality, conjunction);
    }

    default String getContestNames(List<Long> contestTypeIds,
            String plurality, String conjunction) {
        return getJoinedNameString(contestTypeIds, false, plurality, conjunction);
    }

    default String getJoinedNameString(List<Long> contestTypeIds, boolean isProposal,
            String plurality, String conjuction) {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<Long> iterator = contestTypeIds.iterator();
        int currentWord = 1, totalWords = contestTypeIds.size();
        while (iterator.hasNext()) {
            ContestType contestType = getContestType(iterator.next());
            if (currentWord > 1) {
                if (currentWord == totalWords) {
                    stringBuilder.append(String.format(" %s ", conjuction));
                } else {
                    stringBuilder.append(", ");
                }
            }
            if (isProposal) {
                if (plurality.equals(Plurality.SINGULAR.name())) {
                    stringBuilder.append(contestType.getProposalName());
                } else {
                    stringBuilder.append(contestType.getProposalNamePlural());
                }
            } else {
                if (plurality.equals(Plurality.SINGULAR.name())) {
                    stringBuilder.append(contestType.getContestName());
                } else {
                    stringBuilder.append(contestType.getContestNamePlural());
                }
            }
            currentWord++;
        }
        return stringBuilder.toString();
    }
}
