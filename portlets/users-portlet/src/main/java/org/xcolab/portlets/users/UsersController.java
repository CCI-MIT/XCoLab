package org.xcolab.portlets.users;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.Cookie;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.commons.beans.SortFilterPage;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.PortletDisplay;
import com.liferay.portal.theme.ThemeDisplay;

import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.ext.portlet.Activity.ActivityUtil;
import org.xcolab.portlets.users.utils.UserItem;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("view")
public class UsersController {

    private int USERS_PER_PAGE = 20;

    @RequestMapping
    public String showUsers(PortletRequest request, PortletResponse response, SortFilterPage sortFilterPage, @RequestParam(value = "page", required = false) Long pageParam, Model model) throws SystemException, PortalException {

        //Pagination

        int page=1;
        if (pageParam!=null)
            page = pageParam.intValue();

        //int pagesCount = (int)Math.ceil((double)UserLocalServiceUtil.getUsersCount() / (double)USERS_PER_PAGE);
        int pagesCount = UserLocalServiceUtil.getUsersCount() / USERS_PER_PAGE;
        int startPage = page - 5 > 0?page - 5:1;
        int endPage = startPage + 10<pagesCount? startPage+10:pagesCount;


        int firstUser = 1;
        if (page > 1)
            firstUser = ((page-1) * USERS_PER_PAGE)+1;

        int endUser = (firstUser+USERS_PER_PAGE)-1;

        List<User> liferayUsers = UserLocalServiceUtil.getUsers(firstUser,endUser);
        List<UserItem> users = new ArrayList<UserItem>();
        for (User liferayUser : liferayUsers)
        {
            UserItem userItem = new UserItem(liferayUser);
            users.add(userItem);
        }

        model.addAttribute("pageNumber", page);
        model.addAttribute("pagesCount", pagesCount);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("sortFilterPage", sortFilterPage);
        model.addAttribute("users", users);

        return "users";

    }

}