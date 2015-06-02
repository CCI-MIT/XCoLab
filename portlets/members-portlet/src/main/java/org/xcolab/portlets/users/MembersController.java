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
import org.xcolab.portlets.users.utils.MemberCategory;
import org.xcolab.portlets.users.utils.MemberItem;

import java.util.*;

@Controller
@RequestMapping("view")
public class MembersController {

    private int USERS_PER_PAGE = 20;

    @RequestMapping
    public String showUsers(PortletRequest request, PortletResponse response, SortFilterPage sortFilterPage, @RequestParam(value = "page", required = false) Long pageParam, @RequestParam(value="memberCategory", required = false) String memberCategoryParam, Model model) throws SystemException, PortalException {

        int page=1;
        if (pageParam!=null)
            page = pageParam.intValue();

        int startPage = 1;
        if (page - 5 > 0)
            startPage=page - 5;

        int firstUser = 0;
        if (page > 1)
            firstUser = (page-1) * USERS_PER_PAGE;

        int endUser = firstUser+USERS_PER_PAGE;

        List<User_> dBUsers = null;

        String filter = "%";
        String filterParam = request.getParameter("filter");
        if (filterParam!=null) {
            filter = "%" + filterParam + "%";
            sortFilterPage.setFilter(filterParam);
        }
        int usersCount;
        int pagesCount;
        int endPage;

        List<MemberItem> users = new ArrayList<MemberItem>();

        if (memberCategoryParam==null || memberCategoryParam.compareTo("")==0) {

            if (filterParam!=null){
                usersCount = User_LocalServiceUtil.getUsersSortedByScreenNameAsc(0, Integer.MAX_VALUE, filter).size();
            }
            else
                usersCount = UserLocalServiceUtil.getUsersCount();

            if (sortFilterPage.getSortColumn() != null) {

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

                request.getPortletSession().setAttribute("previousSortColumn", sortFilterPage.getSortColumn());
                request.getPortletSession().setAttribute("previousSortOrder", sortFilterPage.isSortAscending());
            }
            else {
                dBUsers = User_LocalServiceUtil.getUsersSortedByActivityCountDesc(firstUser, endUser, filter);
                sortFilterPage.setSortColumn("ACTIVITY");
                sortFilterPage.setSortAscending(false);
            }

            for (User_ user : dBUsers)
            {
                MemberItem memberItem = new MemberItem(user,memberCategoryParam);
                if (memberItem.getCategory()!= MemberCategory.STAFF)
                    users.add(memberItem);
                else
                    usersCount--;
            }

            //Pagination

            pagesCount = (int)Math.ceil((double)usersCount/(double)USERS_PER_PAGE);

            endPage = pagesCount;
            if (startPage + 10<pagesCount)
                endPage=startPage+10;
        }
        else {

            String memberCategoryFilter="%" + memberCategoryParam + "%";

            //Pagination

            usersCount = User_LocalServiceUtil.getUsersSortedByScreenNameAscFilteredByCategory(0, Integer.MAX_VALUE, filter, memberCategoryFilter).size();
            pagesCount = (int)Math.ceil((double)usersCount/(double)USERS_PER_PAGE);
            endPage = pagesCount;
            if (startPage + 10<pagesCount)
                endPage=startPage+10;

            //Filtering by category

            if (sortFilterPage.getSortColumn()!=null)
                switch (sortFilterPage.getSortColumn())
                {
                    case "USER_NAME":
                        if (sortFilterPage.isSortAscending())
                            dBUsers = User_LocalServiceUtil.getUsersSortedByScreenNameAscFilteredByCategory(firstUser, endUser, filter, memberCategoryFilter);
                        else
                            dBUsers = User_LocalServiceUtil.getUsersSortedByScreenNameDescFilteredByCategory(firstUser, endUser, filter, memberCategoryFilter);
                        break;

                    case "ACTIVITY":
                        if (sortFilterPage.isSortAscending())
                            dBUsers = User_LocalServiceUtil.getUsersSortedByActivityCountAscFilteredByCategory(firstUser, endUser, filter, memberCategoryFilter);
                        else
                            dBUsers = User_LocalServiceUtil.getUsersSortedByActivityCountDescFilteredByCategory(firstUser, endUser, filter, memberCategoryFilter);
                        break;

                    case "CATEGORY":
                        if (sortFilterPage.isSortAscending())
                            dBUsers = User_LocalServiceUtil.getUsersSortedByScreenNameAscFilteredByCategory(firstUser,endUser,filter,memberCategoryFilter);
                        else
                            dBUsers = User_LocalServiceUtil.getUsersSortedByScreenNameDescFilteredByCategory(firstUser,endUser,filter,memberCategoryFilter);
                        break;

                    case "MEMBER_SINCE":
                        if (sortFilterPage.isSortAscending())
                            dBUsers = User_LocalServiceUtil.getUsersSortedByMemberSinceAscFilteredByCategory(firstUser,endUser,filter,memberCategoryFilter);
                        else
                            dBUsers = User_LocalServiceUtil.getUsersSortedByMemberSinceDescFilteredByCategory(firstUser,endUser,filter,memberCategoryFilter);
                        break;
                }
            else {
                dBUsers = User_LocalServiceUtil.getUsersSortedByActivityCountDescFilteredByCategory(firstUser, endUser, filter, memberCategoryFilter);
                sortFilterPage.setSortColumn("ACTIVITY");
                sortFilterPage.setSortAscending(false);
            }

            for (User_ user : dBUsers)
            {
                MemberItem memberItem = new MemberItem(user,memberCategoryParam);
                users.add(memberItem);
            }

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