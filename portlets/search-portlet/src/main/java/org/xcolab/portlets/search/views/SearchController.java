package org.xcolab.portlets.search.views;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.xcolab.portlets.search.SearchBean;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.io.UnsupportedEncodingException;

/**
 * Created by johannes on 12/6/15.
 */
@Controller
@RequestMapping("view")
public class SearchController {

    @RenderMapping
    public String showCategories(PortletRequest request, PortletResponse response, Model model,
                                 @RequestParam(required = false) String searchPhrase, @RequestParam(required = false) String searchLocation)
            throws SystemException, PortalException, UnsupportedEncodingException {

        model.addAttribute("searchBean", new SearchBean(searchPhrase, searchLocation));

        return "search";
    }
}
