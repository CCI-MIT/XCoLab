package com.ext.portlet.Activity;

import com.liferay.portlet.social.model.SocialActivityInterpreter;

public interface ICollabActivityInterpreter extends SocialActivityInterpreter {
    String getName(Long classNameId, Long classPK, Integer type, String extraData);
}
