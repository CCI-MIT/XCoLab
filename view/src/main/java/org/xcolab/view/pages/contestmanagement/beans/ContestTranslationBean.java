package org.xcolab.view.pages.contestmanagement.beans;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestTranslation;
import org.xcolab.commons.i18n.I18nUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ContestTranslationBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<ContestTranslation> translations;

    public ContestTranslationBean() {
    }

    public ContestTranslationBean(Contest contest) {
        ContestClient contestClient = ContestClient.fromNamespace(contest.getServiceNamespace());
        final Map<String, ContestTranslation> translations =
                contestClient.getTranslationsForContestId(contest.getContestPK())
                .stream()
                .collect(Collectors.toMap(ContestTranslation::getLang, t -> t));

        for (String lang : I18nUtils.getAllLanguages()) {
            translations.computeIfAbsent(lang, k -> {
                ContestTranslation translation = new ContestTranslation();
                translation.setLang(lang);
                translation.setContestId(contest.getContestPK());
                return translation;
            });
        }
        this.translations = new ArrayList<>(translations.values());
    }

    public List<ContestTranslation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<ContestTranslation> translations) {
        this.translations = translations;
    }

    public void persist(Contest contest) {
        ContestClient contestClient = ContestClient.fromNamespace(contest.getServiceNamespace());
        translations.stream()
                .filter(translation -> !StringUtils.isAllEmpty(translation.getContestName(),
                        translation.getContestShortName(), translation.getContestDescription()))
                .map(translation -> {
                    translation.setContestId(contest.getContestPK());
                    return translation;
                })
                .forEach(contestClient::saveTranslation);
    }
}
