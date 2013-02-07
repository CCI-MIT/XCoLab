/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.xcolab.portlets.massmessaging.action;

import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xcolab.portlets.massmessaging.MessagingConstants;

import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;

public class ViewMessagingPanelAction extends BaseStrutsPortletAction {
    public ActionForward render(ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
            RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {
        
        
        return mapping.findForward(MessagingConstants.VIEW_FORWARD);
    }
}
