package org.xcolab.portlets.search.items;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestType;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestTypeLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.search.pojo.SearchPojo;

import java.io.IOException;

public class ContestSearchItem extends AbstractSearchItem {
    private final static String[] TITLE_FIELDS = {"title"};
    private final static String[] CONTENT_FIELDS = {"content"};

    private SearchPojo searchPojo;
    private String searchQuery;

    private Contest contest;

    @Override
    public void init(SearchPojo pojo, String searchQuery) {
        this.searchPojo = pojo;
        this.searchQuery = searchQuery;
        try{
            contest = ContestLocalServiceUtil.getContest(searchPojo.getClassPrimaryKey());
        }catch(SystemException | PortalException ignored){

        }
    }

    @Override
    public String getPrintName() {
        try {
            final long contestTypeId =
                    ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.getLongValue();
            final ContestType contestType = ContestTypeLocalServiceUtil
                    .getContestType(contestTypeId);
            return contestType.getContestNamePlural();
        } catch (PortalException | SystemException e) {
            return "Contests";
        }
    }

    @Override
    public String getTitle(){
        return highlight(contest.getContestName(),searchQuery);
    }

    @Override
    public String getLinkUrl() {
            return ContestLocalServiceUtil.getContestLinkUrl(contest);
    }

    @Override
    public String getContent() {
        String content = highlight(contest.getContestDescription(),searchQuery);
        return content.substring(0, Math.min(content.length(), MAX_CONTENT_LENGTH)) + " ...";
    }


}
