package org.xcolab.proposals.events.handlers.social;

import org.xcolab.proposals.events.handlers.BaseEventHandler;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portlet.social.service.SocialActivityLocalService;

public class BaseProposalSocialActivityEventHandler extends BaseEventHandler {

    @BeanReference(type = SocialActivityLocalService.class)
    protected SocialActivityLocalService socialActivityService;
    
    
}
