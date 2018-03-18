package org.xcolab.client.admin;

import org.xcolab.client.admin.exceptions.ContestTypeAttributeNotFoundException;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.admin.pojo.ContestTypeAttribute;
import org.xcolab.commons.enums.Plurality;
import org.xcolab.commons.http.caching.CacheName;
import org.xcolab.commons.http.client.RestResource;
import org.xcolab.commons.http.client.RestResource1;

import org.xcolab.commons.http.exceptions.EntityNotFoundException;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public final class ContestTypeClient {

    private static final RestResource<ContestTypeAttribute, String> contestTypeAttributeResource =
            new RestResource1<>(AdminResource.CONTEST_TYPE_ATTRIBUTE, ContestTypeAttribute.TYPES);

    private ContestTypeClient() {
    }

    public static ContestTypeAttribute getContestTypeAttribute(String name,
            long contestTypeId, String locale) {

        try {
            return contestTypeAttributeResource.get(name)
                    .optionalQueryParam("additionalId", contestTypeId)
                    .optionalQueryParam("locale", locale)
                    .withCache(CacheName.CONFIGURATION)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            throw new ContestTypeAttributeNotFoundException(name, contestTypeId, locale);
        }
    }

    public static ContestTypeAttribute createContestTypeAttribute(
            ContestTypeAttribute contestTypeAttribute) {
        return contestTypeAttributeResource.create(contestTypeAttribute).execute();
    }

    public static boolean updateContestTypeAttribute(
            ContestTypeAttribute contestTypeAttribute) {
        return contestTypeAttributeResource.update(contestTypeAttribute,
                contestTypeAttribute.getName())
                .cacheName(CacheName.CONFIGURATION)
                .execute();
    }

    public static List<ContestType> getActiveContestTypes() {
        final List<ContestType> contestTypes = getAllContestTypes();
        return contestTypes.stream()
                .filter(ContestType::isActive)
                .collect(Collectors.toList());
    }

    public static List<ContestType> getAllContestTypes() {
        return listContestTypeAttributes().stream()
                .map(ContestTypeAttribute::getAdditionalId)
                .distinct()
                .map(ContestType::new)
                .collect(Collectors.toList());
    }

    public static List<ContestTypeAttribute> listContestTypeAttributes() {
        return contestTypeAttributeResource.list()
                .withCache(CacheName.CONFIGURATION)
                .execute();
    }

    public static ContestType getContestType(long id) {
        return new ContestType(id);
    }

    public static ContestType getContestType(long id, String language) {
        return new ContestType(id, language);
    }

    public static String getProposalNames(List<Long> contestTypeIds, String plurality,
            String conjunction) {
        return getJoinedNameString(contestTypeIds, true, plurality, conjunction);
    }

    public static String getContestNames(List<Long> contestTypeIds,
            String plurality, String conjunction) {
        return getJoinedNameString(contestTypeIds, false, plurality, conjunction);
    }

    private static String getJoinedNameString(List<Long> contestTypeIds, boolean isProposal,
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
