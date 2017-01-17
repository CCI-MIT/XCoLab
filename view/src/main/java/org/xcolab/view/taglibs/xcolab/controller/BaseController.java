package org.xcolab.view.taglibs.xcolab.controller;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;


public class BaseController {

    public BaseController() {
        super();
    }

    protected void setSeoTexts(HttpServletRequest request, String pageTitle,
            String pageSubtitle, String pageDescription) {



        //TODO: liferay internal - needs to be removed after transition
        if (StringUtils.isNotBlank(pageTitle)) {
          //  PortalUtil.setPageTitle(pageTitle, httpRequest);
        }

        if (StringUtils.isNotBlank(pageDescription)) {
            //PortalUtil.setPageDescription(pageDescription, httpRequest);
        }

        if (StringUtils.isNotBlank(pageSubtitle)) {
            //PortalUtil.setPageSubtitle(pageSubtitle, httpRequest);
        }
    }

}