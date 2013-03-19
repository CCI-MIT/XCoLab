package org.xcolab.portlets.search;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.util.Version;
import org.climatecollaboratorium.facelets.discussions.activity.NavigationUrl;

import com.ext.portlet.NoSuchDiscussionMessageException;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.model.PlanItem;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;

public enum SearchItemType {
    
    PLAN("Proposals", new String[] {"entryClassName", "com.ext.portlet.model.PlanItem" }, new String[] { "content", "title", "pitch", "sections" },
            new String[] { "title" }, new String[] { "content", "pitch", "sections" }, new URLCreator() {
                public String getUrl(Document doc) {
                    String idStr = doc.get(Field.ENTRY_CLASS_PK);
                    try {
                    	Long id = Long.parseLong(idStr);
                    	PlanItem p = PlanItemLocalServiceUtil.getPlan(id);
                    	return "/web/guest/plans/-/plans/contestId/" + PlanItemLocalServiceUtil.getContest(p).getContestPK() + "/planId/" + id; 
                    }
                    catch (Exception e) {
                    	// ignore
                    }
                    
                    return "/web/guest/plans";
                }
     }),
     CONTEST("Contests", new String[] {"entryClassName", "com.ext.portlet.model.Contest" }, new String[] { "content", "title" },
             new String[] { "title" }, new String[] { "content" }, new URLCreator() {
                 public String getUrl(Document doc) {
                     String idStr = doc.get(Field.ENTRY_CLASS_PK);
                     try {
                     	Long id = Long.parseLong(idStr);
                     	Contest c = ContestLocalServiceUtil.getContest(id);
                     	return "/web/guest/plans/-/plans/contestId/" + id; 
                     }
                     catch (Exception e) {
                     	// ignore
                     }
                     
                     return "/web/guest/plans";
                 }
            }),            

    USER("Users", new String[] { "entryClassName", "com.liferay.portal.model.User" }, new String[] { "screenName", "firstName", "lastName" },
            new String[] { "screenName" }, new String[] { "firstName", "lastName" }, new URLCreator() {
                public String getUrl(Document doc) {
                    String id = doc.get(Field.USER_ID);
                    return "/web/guest/member/-/member/userId/" + id;
                }

            }
    ), 
    
    CONTENT("Content", new String[] { "entryClassName", "com.liferay.portlet.wiki.model.* OR com.liferay.portlet.journal.model.JournalArticle" }, new String[] { "title",
            "content" }, new String[] { "title" }, new String[] { "content" }, new URLCreator() {

        @Override
        public String getUrl(Document doc) {
            String title = doc.get("title");
            try {
            	String pageUrl = doc.get("PAGE_URL"); 
            	if (pageUrl != null && pageUrl.length() > 0) {
            		return "/web/guest" + pageUrl;
            	}
                return "/web/guest/resources/-/wiki/Main/" + URLEncoder.encode(title, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return "";
            }

        }
    }),

    BLOG("News", new String[] {"entryClassName", "com.liferay.portlet.blogs.model.*" }, new String[] { "title", "content" },
            new String[] { "title" }, new String[] { "content" }, new URLCreator() {

                private static final String BLOG_URL_FORMAT = "/c/blogs/find_entry?redirect=/web/guest/community&noSuchEntryRedirect=/web/guest/community&entryId=%1$s";

                @Override
                public String getUrl(Document doc) {
                    String id = doc.get(Field.ENTRY_CLASS_PK);
                    return String.format(BLOG_URL_FORMAT, id);
                }
            }),
            /*
     ARTICLE("Page", new String[] {"entryClassName", "com.liferay.portlet.journal.model.*" }, new String[] { "title", "content" },
    		
    		new String[] { "title" }, new String[] { "content" }, new URLCreator() {

    	 		private static final String BLOG_URL_FORMAT = "/c/blogs/find_entry?redirect=/web/guest/community&noSuchEntryRedirect=/web/guest/community&entryId=%1$s";

                        @Override
                        public String getUrl(Document doc) {
                        	System.out.println(doc.getFields());
                        	
                        	
                            String id = doc.get(Field.ENTRY_CLASS_PK);
                            return String.format(BLOG_URL_FORMAT, id);
                        }
            }),
*/
    DISCUSSION("Discussions", new String[] { "entryClassName", "com.ext.portlet.model.DiscussionMessage" }, new String[] { "title",
            "content" }, new String[] { "title" }, new String[] { "content" }, new URLCreator() {

        private static final String DISCUSSION_URL_FORMAT = "alert('this is discussion');";

        @Override
        public String getUrl(Document doc) {
            String idStr = doc.get(Field.ENTRY_CLASS_PK);
            Long messageId = Long.parseLong(idStr);
            DiscussionMessage msg;
            try {
                msg = DiscussionMessageLocalServiceUtil.getMessageByMessageId(messageId);

                Long threadId = msg.getThreadId();
                if (threadId == 0) {
                    threadId = messageId;
                }

                DiscussionCategoryGroup categoryGroup = DiscussionMessageLocalServiceUtil.getCategoryGroup(msg);

                NavigationUrl navUrl = new NavigationUrl(categoryGroup.getUrl());
                return navUrl
                        .getUrlWithParameters("discussion", "pageType", "THREAD", "threadId", String.valueOf(threadId))
                        .toString();
            } catch (NoSuchDiscussionMessageException e) {
                _log.error("Can't create url for message: " + messageId, e);
            } catch (SystemException e) {
                _log.error("Can't create url for message: " + messageId, e);
            } catch (PortalException e) {
                _log.error("Can't create url for message: " + messageId, e);
            }
            return "";
        }
    })
    /*,
    
    DEBATE_ITEM("Debates", new String[] { "entryClassName", "com.ext.portlet.debaterevision.model.*" }, new String[] { "content", "title" },
            new String[] { "title" }, new String[] { "content" }, new URLCreator() {
        private final static String DEBATE_ITEM_URL_ACTIVE_FORMAT = "/web/guest/plans#plans=contests:active,subview:issues;debate=debateId:%s,itemId:%s";
        private final static String DEBATE_ITEM_URL_PAST_FORMAT = "/web/guest/plans#plans=contests:past,subview:issues;debate=debateId:%s,itemId:%s";
                public String getUrl(Document doc) {
                    
                    String debateId = doc.get("debateId");
                    String itemId = doc.get(Field.ENTRY_CLASS_PK);

                    // check if debate item is part of
                    Contest activeContest;
                    try {
                        activeContest = ContestLocalServiceUtil.getContestByActiveFlag(true);
                        Set<Long> debateIds = new HashSet<Long>(activeContest.getDebatesIds());
                        if (debateIds.contains(Long.parseLong(debateId))) {
                            return String.format(DEBATE_ITEM_URL_ACTIVE_FORMAT, debateId, itemId);
                        }
                    } catch (NoSuchContestException e) {
                        _log.error("Can't find active contest", e);
                    } catch (SystemException e) {
                        _log.error("Error when retrieving contest and its debates", e);
                    }
                    return String.format(DEBATE_ITEM_URL_PAST_FORMAT, debateId, itemId);

                }
            }
    )*/
    ;
    
    
    

    private static final String HTML_CLEAN_UP_REGEXP = "<[^>]*>";
    private static final Log _log = LogFactoryUtil.getLog(SearchItemType.class);

    private final String[] determinatorFieldValue;
    private final String[] searchFields;
    private final String[] titleFields;
    private final String[] contentFields;
    private final String searchInDescription;
    private final URLCreator urlCreator;
    private final static int MAX_CONTENT_LENGTH = 255;

    SearchItemType(String searchInDescription, String[] determinatorInfo, String[] searchFields, String[] titleFields, String[] contentFields,
            URLCreator urlCreator) {
        if (determinatorInfo.length != 2) {
            throw new IllegalArgumentException("Determinator info table needs to have 2 values");
        }
        this.determinatorFieldValue = determinatorInfo;
        this.searchFields = searchFields;
        this.titleFields = titleFields;
        this.urlCreator = urlCreator;
        this.contentFields = contentFields;
        this.searchInDescription = searchInDescription;
    }

    public String getUrl(Document doc) {
        return urlCreator.getUrl(doc);
    }

    public String getTitle(Document doc, Highlighter highlighter) throws IOException, InvalidTokenOffsetsException {
        return concatFields(titleFields, doc, highlighter);
    }

    public String getQuery(String userQuery) {
        StringBuilder sb = new StringBuilder();
        // first add determinator fields query
        sb.append("(");

        sb.append(determinatorFieldValue[0]);
        sb.append(":(");
        sb.append(determinatorFieldValue[1]);
        sb.append(") ");

        if (userQuery != null && userQuery.length() > 0) {
            sb.append(" AND (");
            boolean addSeparator = false;
            for (String field : searchFields) {
                if (addSeparator) {
                    sb.append(" OR ");
                }
                sb.append(field);
                sb.append(":(");
                sb.append(userQuery);
                sb.append(") ");
                addSeparator = true;
            }
            sb.append(")");
        }
        sb.append(")");

        return sb.toString();
    }

    public String getContent(Document doc, Highlighter highlighter) throws IOException, InvalidTokenOffsetsException {
        String content = concatFields(contentFields, doc, highlighter);
        return content.substring(0, Math.min(content.length(), MAX_CONTENT_LENGTH)) + " ...";
    }

    private String concatFields(String[] fields, Document doc, Highlighter highlighter) throws IOException, InvalidTokenOffsetsException {
        StringBuilder sb = new StringBuilder();
        boolean addSeparator = false;
        for (String field : fields) {
            if (addSeparator) {
                sb.append(" ");
            }
            sb.append(doc.get(field));
            addSeparator = true;
        }

        String concatenated = sb.toString().replaceAll(HTML_CLEAN_UP_REGEXP, "");
        String highlighted = highlighter.getBestFragment(new StandardAnalyzer(Version.LUCENE_34), fields[0], concatenated);

        if (highlighted == null || highlighted.trim().length() == 0) {
            return concatenated;
        }
        return highlighted;

    }

    public boolean isOfGivenType(Document doc) {
    	String detFieldVal = doc.get(determinatorFieldValue[0]);
    	if (detFieldVal != null) { 
    		String[] detFieldVals = determinatorFieldValue[1].split(" ");
    		for (String fv: detFieldVals) {
    			if (detFieldVal.matches(fv)) return true;
    		}
    	}
    	return false;
    }

    private abstract interface URLCreator {
        String getUrl(Document doc);
    }
    
    public String getSearchInDescription() {
        return searchInDescription;
    }
    
    public String getPrintName() {
        return searchInDescription;
    }
    
    public String getName() {
        return name();
    }

}
