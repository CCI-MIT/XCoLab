package org.xcolab.view.theme;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.util.html.HtmlUtil;
import org.xcolab.view.util.MetaKeys;

import javax.servlet.http.HttpServletRequest;

public class MetaVariables {
    public String openGraphShareTitle;
    public String openGraphShareDescription;
    public String metaPageDescription;
    public String metaPageKeywords;

    public MetaVariables(HttpServletRequest request, I18nVariables i18NVariables) {
        this.openGraphShareTitle = ConfigurationAttributeKey.OPEN_GRAPH_SHARE_TITLE.get();
        this.openGraphShareDescription = ConfigurationAttributeKey.OPEN_GRAPH_SHARE_DESCRIPTION.get();

        final String metaDescriptionAttribute = (String) request.getAttribute(MetaKeys.DESCRIPTION.getAttributeName());
        if (StringUtils.isNotBlank(metaDescriptionAttribute)) {
            this.metaPageDescription = HtmlUtil.cleanAll(metaDescriptionAttribute);
        } else {
            this.metaPageDescription = ConfigurationAttributeKey.META_PAGE_DESCRIPTION.get(
                    i18NVariables.language);
        }
        this.metaPageKeywords = ConfigurationAttributeKey.META_PAGE_KEYWORDS.get();
    }
}
