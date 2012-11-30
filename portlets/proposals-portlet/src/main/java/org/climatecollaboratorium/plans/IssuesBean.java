package org.climatecollaboratorium.plans;

import java.util.Map;

import org.climatecollaboratorium.navigation.NavigationEvent;


import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class IssuesBean {
    private Long debateId;
    private Long itemId;
    
    private static final String DEBATE_ID_PARAM = "debateId";
    private static final String ITEM_ID_PARAM = "issueId";
    
    private final static Log _log = LogFactoryUtil.getLog(IssuesBean.class);
    
    public void init(NavigationEvent event) {
        Map<String, String> params = event.getParameters("debate");
        debateId = null;
        itemId = null;
        if (params == null) {
            return;
        }
        if (params.containsKey(DEBATE_ID_PARAM)) {
            try {
                debateId = Long.parseLong(params.get(DEBATE_ID_PARAM)); 
            }
            catch (NumberFormatException e) {
                _log.warn("Can't parse debateId: " + params.get(DEBATE_ID_PARAM));
            }
        }
        if (params.containsKey(ITEM_ID_PARAM)) {
            try {
                itemId = Long.parseLong(params.get(ITEM_ID_PARAM));
            }
            catch (NumberFormatException e) {
                _log.warn("Can't parse itemId: " + params.get(ITEM_ID_PARAM));   
            }
        }
    }
    
    public boolean isDebateDetailsPage() {
        return debateId != null && debateId > 0;
    }
    
    public Long getDebateId() {
        return debateId;
    }
    
    public Long getItemId() {
        return itemId;
    }
}