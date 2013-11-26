package com.ext.portlet.Activity;

import java.io.Serializable;

public interface FeedEntryWithMailInfo extends Serializable {
    
    String getMailSubject();
    String getMailBody();

}
