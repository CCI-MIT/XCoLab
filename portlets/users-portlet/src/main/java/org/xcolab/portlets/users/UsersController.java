package org.xcolab.portlets.users;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.Cookie;

import com.ext.portlet.model.User_;
import com.ext.portlet.service.Role_LocalService;
import com.ext.portlet.service.StaffMemberServiceUtil;
import com.ext.portlet.service.User_LocalServiceUtil;
import com.ext.portlet.service.persistence.User_Finder;
import com.ext.portlet.service.persistence.User_Util;
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
        int startPage = page - 5 > 0?page - 5:1;
        int endPage = startPage + 10<pagesCount? startPage+10:pagesCount;

        if (page > 1)
            firstUser = ((page-1) * USERS_PER_PAGE)+1;

        int endUser = (firstUser+USERS_PER_PAGE)-1;

        List<User_> dBUsers = null;

        if (memberCategoryParam==null || memberCategoryParam.compareTo("")==0)
        if (sortFilterPage.getSortColumn()!=null)

            switch (sortFilterPage.getSortColumn()) {
                case "USER_NAME":
                    if (sortFilterPage.isSortAscending())
                        dBUsers = User_LocalServiceUtil.getUsersSortedByScreenNameAsc(firstUser, endUser);
                    else
                        dBUsers = User_LocalServiceUtil.getUsersSortedByScreenNameDesc(firstUser, endUser);
                    break;

                case "ACTIVITY":
                    Map<Long, Integer> activityCounts = ActivityUtil.getUsersActivityCount();
                    activityCounts = sortByComparator(activityCounts);
                    Object[] userIds = activityCounts.keySet().toArray();
                    dBUsers = new ArrayList<User_>();
                    if (sortFilterPage.isSortAscending()) {
                        for (int i = firstUser - 1; i <= endUser - 1; i++) {
                            Long userId = new Long(new Long(userIds[i].toString()));
                            User_ user_ = User_LocalServiceUtil.getUser_(userId);
                            dBUsers.add(user_);
                        }
                    } else {
                        for (int i = endUser - 1; i >= firstUser - 1; i--) {
                            Long userId = new Long(new Long(userIds[i].toString()));
                            User_ user_ = User_LocalServiceUtil.getUser_(userId);
                            dBUsers.add(user_);
                        }
                    }
                    break;

                case "CATEGORY":
                    if (sortFilterPage.isSortAscending())
                        dBUsers = User_LocalServiceUtil.getUsersSortedByRoleNameAsc(firstUser, endUser);
                    else
                        dBUsers = User_LocalServiceUtil.getUsersSortedByRoleNameDesc(firstUser, endUser);
                    break;

                case "MEMBER_SINCE":
                    if (sortFilterPage.isSortAscending())
                        dBUsers = User_LocalServiceUtil.getUsersSortedByMemberSinceAsc(firstUser, endUser);
                    else
                        dBUsers = User_LocalServiceUtil.getUsersSortedByMemberSinceDesc(firstUser, endUser);
                    break;
            }
        else {
            dBUsers = User_LocalServiceUtil.getUsersSortedByScreenNameAsc(firstUser, endUser);
            sortFilterPage.setSortColumn("USER_NAME");
            sortFilterPage.setSortAscending(true);
        }

        else {

            // Pagination

            usersCount = User_LocalServiceUtil.getUsersSortedByScreenNameAscFilteredByCategory(0,Integer.MAX_VALUE,memberCategoryParam).size();
            pagesCount = usersCount / USERS_PER_PAGE;
            endPage = startPage + 10<pagesCount? startPage+10:pagesCount;

            //Filtering by category

            if (sortFilterPage.getSortColumn()!=null)
                switch (sortFilterPage.getSortColumn())
                {
                    case "USER_NAME":
                        if (sortFilterPage.isSortAscending())
                            dBUsers = User_LocalServiceUtil.getUsersSortedByScreenNameAscFilteredByCategory(firstUser,endUser,memberCategoryParam);
                        else
                            dBUsers = User_LocalServiceUtil.getUsersSortedByScreenNameDescFilteredByCategory(firstUser,endUser,memberCategoryParam);
                        break;

                    case "ACTIVITY":
                        Map<Long,Integer> activityCounts = ActivityUtil.getUsersActivityCount();
                        activityCounts = sortByComparator (activityCounts);
                        Object[] userIds = activityCounts.keySet().toArray();
                        dBUsers = new ArrayList<User_>();
                        if (sortFilterPage.isSortAscending()) {
                            for (int i = firstUser-1; i <= endUser-1; i++) {
                                Long userId = new Long(new Long(userIds[i].toString()));
                                User_ user_ = User_LocalServiceUtil.getUser_(userId);
                                //TODO: Filter by category
                                dBUsers.add(user_);
                            }
                        }
                        else
                        {
                            for (int i = endUser-1; i >= firstUser-1; i--)
                            {
                                Long userId = new Long(new Long(userIds[i].toString()));
                                User_ user_ = User_LocalServiceUtil.getUser_(userId);
                                //TODO: Filter by category
                                dBUsers.add(user_);
                            }
                        }
                        break;

                    case "CATEGORY":
                        if (sortFilterPage.isSortAscending())
                            dBUsers = User_LocalServiceUtil.getUsersSortedByScreenNameAscFilteredByCategory(firstUser,endUser,memberCategoryParam);
                        else
                            dBUsers = User_LocalServiceUtil.getUsersSortedByScreenNameDescFilteredByCategory(firstUser,endUser,memberCategoryParam);
                        break;

                    case "MEMBER_SINCE":
                        if (sortFilterPage.isSortAscending())
                            dBUsers = User_LocalServiceUtil.getUsersSortedByMemberSinceAscFilteredByCategory(firstUser,endUser,memberCategoryParam);
                        else
                            dBUsers = User_LocalServiceUtil.getUsersSortedByMemberSinceDescFilteredByCategory(firstUser,endUser,memberCategoryParam);
                        break;
                }
            else {
                dBUsers = User_LocalServiceUtil.getUsersSortedByScreenNameAscFilteredByCategory(firstUser, endUser,memberCategoryParam);
                sortFilterPage.setSortColumn("USER_NAME");
                sortFilterPage.setSortAscending(true);
            }

        }

        List<UserItem> users = new ArrayList<UserItem>();
        for (User_ user : dBUsers)
        {
            UserItem userItem = new UserItem(user);
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

    private static Map<Long, Integer> sortByComparator(Map<Long, Integer> unsortMap) {

        // Convert Map to List
        List<Map.Entry<Long, Integer>> list =
                new LinkedList<Map.Entry<Long, Integer>>(unsortMap.entrySet());

        // Sort list with comparator, to compare the Map values
        Collections.sort(list, new Comparator<Map.Entry<Long, Integer>>() {
            public int compare(Map.Entry<Long, Integer> o1,
                               Map.Entry<Long, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // Convert sorted map back to a Map
        Map<Long, Integer> sortedMap = new LinkedHashMap<Long, Integer>();
        for (Iterator<Map.Entry<Long, Integer>> it = list.iterator(); it.hasNext();) {
            Map.Entry<Long, Integer> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

}