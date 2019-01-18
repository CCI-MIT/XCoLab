package org.xcolab.view.pages.contestmanagement.beans;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.IContestTranslation;
import org.xcolab.client.contest.pojo.tables.pojos.ContestTranslation;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.util.i18n.I18nUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ContestTranslationBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<IContestTranslation> translations;

    public ContestTranslationBean() {
    }

    public ContestTranslationBean(ContestWrapper contest) {
        ContestClient contestClient = ContestClientUtil.getClient();
        final Map<String, IContestTranslation> translations =
                contestClient.getTranslationsForContestId(contest.getId())
                .stream()
                .collect(Collectors.toMap(IContestTranslation::getLang, t -> t));

        for (String lang : I18nUtils.getAllLanguages()) {
            translations.computeIfAbsent(lang, k -> {
                IContestTranslation translation = new ContestTranslation();
                translation.setLang(lang);
                translation.setContestId(contest.getId());
                return translation;
            });
        }
        this.translations = new ArrayList<>(translations.values());
    }

    public List<IContestTranslation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<IContestTranslation> translations) {
        this.translations = translations;
    }

    public void persist(ContestWrapper contest) {
        ContestClient contestClient = ContestClientUtil.getClient();
        translations.stream()
                .filter(translation -> !StringUtils.isAllEmpty(translation.getQuestion(),
                        translation.getTitle(), translation.getDescription()))
                .map(translation -> {
                    translation.setContestId(contest.getId());
                    return translation;
                })
                .forEach(contestClient::saveTranslation);
    }
}
