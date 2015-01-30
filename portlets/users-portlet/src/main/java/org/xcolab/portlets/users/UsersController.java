package org.xcolab.portlets.users;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import com.ext.portlet.model.User_;
import com.ext.portlet.service.User_LocalServiceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.commons.beans.SortFilterPage;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.xcolab.portlets.users.utils.UserItem;

import java.util.*;

@Controller
@RequestMapping("view")
public class UsersController {

    private int USERS_PER_PAGE = 20;

    @RequestMapping
    public String showUsers(PortletRequest request, PortletResponse response, SortFilterPage sortFilterPage, @RequestParam(value = "page", required = false) Long pageParam, @RequestParam(value="memberCategory", required = false) String memberCategoryParam, Model model) throws SystemException, PortalException {

        //Pagination

        int page=1;
        if (pageParam!=null)
            page = pageParam.intValue();
        int firstUser = 1;

        int usersCount = UserLocalServiceUtil.getUsersCount();
        int pagesCount = usersCount / USERS_PER_PAGE;
        int startPage = 1;
        if (page - 5 > 0)
        startPage=page - 5;
        int endPage = pagesCount;
        if (startPage + 10<pagesCount)
            endPage=startPage+10;

        if (page > 1)
            firstUser = ((page-1) * USERS_PER_PAGE)+1;

        int endUser = (firstUser+USERS_PER_PAGE)-1;

        List<User_> dBUsers = null;

        String filter = "%";
        String filterParam = request.getParameter("filter");
        if (filterParam!=null){
            filter = "%"+filterParam+"%";
            sortFilterPage.setFilter(filterParam);
        }

        if (memberCategoryParam==null || memberCategoryParam.compareTo("")==0)
        if (sortFilterPage.getSortColumn()!=null)

            switch (sortFilterPage.getSortColumn()) {
                case "USER_NAME":
                    if (sortFilterPage.isSortAscending())
                        dBUsers = User_LocalServiceUtil.getUsersSortedByScreenNameAsc(firstUser, endUser, filter);
                    else
                        dBUsers = User_LocalServiceUtil.getUsersSortedByScreenNameDesc(firstUser, endUser, filter);
                    break;

                case "ACTIVITY":
                    if (sortFilterPage.isSortAscending())
                        dBUsers = User_LocalServiceUtil.getUsersSortedByActivityCountAsc(firstUser, endUser, filter);
                    else
                        dBUsers = User_LocalServiceUtil.getUsersSortedByActivityCountDesc(firstUser, endUser, filter);
                    break;

                case "CATEGORY":
                    if (sortFilterPage.isSortAscending())
                        dBUsers = User_LocalServiceUtil.getUsersSortedByRoleNameAsc(firstUser, endUser, filter);
                    else
                        dBUsers = User_LocalServiceUtil.getUsersSortedByRoleNameDesc(firstUser, endUser, filter);
                    break;

                case "MEMBER_SINCE":
                    if (sortFilterPage.isSortAscending())
                        dBUsers = User_LocalServiceUtil.getUsersSortedByMemberSinceAsc(firstUser, endUser, filter);
                    else
                        dBUsers = User_LocalServiceUtil.getUsersSortedByMemberSinceDesc(firstUser, endUser, filter);
                    break;
            }
        else {
            dBUsers = User_LocalServiceUtil.getUsersSortedByScreenNameAsc(firstUser, endUser, filter);
            sortFilterPage.setSortColumn("USER_NAME");
            sortFilterPage.setSortAscending(true);
        }

        else {

            // Pagination

            usersCount = User_LocalServiceUtil.getUsersSortedByScreenNameAscFilteredByCategory(0, Integer.MAX_VALUE, filter, memberCategoryParam).size();
            pagesCount = usersCount / USERS_PER_PAGE;
            endPage = startPage + 10<pagesCount? startPage+10:pagesCount;

            //Filtering by category

            if (sortFilterPage.getSortColumn()!=null)
                switch (sortFilterPage.getSortColumn())
                {
                    case "USER_NAME":
                        if (sortFilterPage.isSortAscending())
                            dBUsers = User_LocalServiceUtil.getUsersSortedByScreenNameAscFilteredByCategory(firstUser, endUser, filter, memberCategoryParam);
                        else
                            dBUsers = User_LocalServiceUtil.getUsersSortedByScreenNameDescFilteredByCategory(firstUser, endUser, filter, memberCategoryParam);
                        break;

                    case "ACTIVITY":
                        if (sortFilterPage.isSortAscending())
                            dBUsers = User_LocalServiceUtil.getUsersSortedByActivityCountAscFilteredByCategory(firstUser, endUser, filter, memberCategoryParam);
                        else
                            dBUsers = User_LocalServiceUtil.getUsersSortedByActivityCountDescFilteredByCategory(firstUser, endUser, filter, memberCategoryParam);
                        break;

                    case "CATEGORY":
                        if (sortFilterPage.isSortAscending())
                            dBUsers = User_LocalServiceUtil.getUsersSortedByScreenNameAscFilteredByCategory(firstUser,endUser,filter,memberCategoryParam);
                        else
                            dBUsers = User_LocalServiceUtil.getUsersSortedByScreenNameDescFilteredByCategory(firstUser,endUser,filter,memberCategoryParam);
                        break;

                    case "MEMBER_SINCE":
                        if (sortFilterPage.isSortAscending())
                            dBUsers = User_LocalServiceUtil.getUsersSortedByMemberSinceAscFilteredByCategory(firstUser,endUser,filter,memberCategoryParam);
                        else
                            dBUsers = User_LocalServiceUtil.getUsersSortedByMemberSinceDescFilteredByCategory(firstUser,endUser,filter,memberCategoryParam);
                        break;
                }
            else {
                dBUsers = User_LocalServiceUtil.getUsersSortedByScreenNameAscFilteredByCategory(firstUser, endUser, filter, memberCategoryParam);
                sortFilterPage.setSortColumn("USER_NAME");
                sortFilterPage.setSortAscending(true);
            }

        }

        List<UserItem> users = new ArrayList<UserItem>();
        for (User_ user : dBUsers)
        {
            UserItem userItem = new UserItem(user,memberCategoryParam);
            users.add(userItem);
        }

        model.addAttribute("pageNumber", page);
        model.addAttribute("pagesCount", pagesCount);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("sortFilterPage", sortFilterPage);
        model.addAttribute("users", users);
        model.addAttribute("usersCount", usersCount);
        model.addAttribute("memberCategory", memberCategoryParam);

        return "users";

    }

}