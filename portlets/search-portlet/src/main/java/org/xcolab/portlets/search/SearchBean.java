package org.xcolab.portlets.search;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchException;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.portlets.search.paging.PageLinkWrapper;
import org.xcolab.portlets.search.paging.SearchDataPage;
import org.xcolab.util.html.HtmlUtil;

import java.io.IOException;
import java.io.Serializable;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class SearchBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private final static int PAGER_RANGE = 5;
    private final String searchLocation;
    private final int pageNumber;
    private String searchPhrase = "";
    private final SearchDataPage dataPage;

    public SearchBean(String searchPhrase, String searchLocation, Integer pageNumber)
            throws IOException, ParseException, org.apache.lucene.queryParser.ParseException, SystemException,
            SearchException, InvalidTokenOffsetsException {
        this.pageNumber = pageNumber == null || pageNumber < 1 ? 1 : pageNumber;
        this.searchLocation = searchLocation == null ? "" : searchLocation;
        if (StringUtils.isEmpty(searchPhrase)) {
            this.searchPhrase = "";
        } else {
            this.searchPhrase = HtmlUtil.cleanAll(URLDecoder.decode(searchPhrase, "UTF-8"));
        }

        this.dataPage = new SearchDataPage(pageNumber != null ? pageNumber : 1, searchPhrase, searchLocation);
    }

    public String getSearchPhrase() {
        return searchPhrase;
    }

    public List<SearchResultItem> getItems() {
        return dataPage.getItems();
    }

    public String getSearchLocation() {
        return searchLocation;
    }

    public SearchDataPage getDataPage() {
        return dataPage;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public SearchItemType[] getItemTypes() {
        return SearchItemType.values();
    }

    public SearchItemType getSelectedItemType() {
        if (StringUtils.isEmpty(searchLocation) || searchLocation.equals("FULL_SITE")) {
            return null;
        }
        return SearchItemType.valueOf(searchLocation);
    }

    public List<PageLinkWrapper> getPageLinks() {
        List<PageLinkWrapper> pageLinks = new ArrayList<>();
        pageLinks.add(new PageLinkWrapper("<< First", 1, searchPhrase, searchLocation));
        if (pageNumber > 1) {
            pageLinks.add(new PageLinkWrapper("< Previous", pageNumber - 1, searchPhrase, searchLocation));
        }
        for (int i = Math.max(1, pageNumber - PAGER_RANGE), stop =
             Math.min(dataPage.getNumberOfPages(), pageNumber + PAGER_RANGE); i <= stop; i++) {
            pageLinks.add(new PageLinkWrapper("", i, searchPhrase, searchLocation));
        }
        if (pageNumber < dataPage.getNumberOfPages()) {
            pageLinks.add(new PageLinkWrapper("Next >", pageNumber + 1, searchPhrase, searchLocation));
        }
        pageLinks.add(new PageLinkWrapper("Last >>", dataPage.getNumberOfPages(), searchPhrase, searchLocation));
        return pageLinks;
    }

    public boolean getShowBlogReference() {
        return !ConfigurationAttributeKey.BLOG_URL.get().isEmpty();
    }

    public String getSearchBlogURL() {
        return ConfigurationAttributeKey.BLOG_URL.get() + "/?submit=Search&s=" + getSearchPhrase();
    }
}
