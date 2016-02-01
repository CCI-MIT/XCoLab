package org.xcolab.portlets.users;

import com.ext.portlet.service.MemberCategoryLocalServiceUtil;
import com.ext.portlet.service.Xcolab_UserLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.commons.beans.SortFilterPage;
import org.xcolab.enums.MemberRole;
import org.xcolab.portlets.users.utils.MemberItem;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("view")
public class MembersController {

    private static final int USERS_PER_PAGE = 20;

    @RequestMapping
    public String showUsers(PortletRequest request, PortletResponse response, SortFilterPage sortFilterPage,
                            @RequestParam(value = "page", required = false) Long pageParam,
                            @RequestParam(value = "memberCategory", required = false) String memberCategoryParam, Model model)
            throws SystemException, PortalException {
        int page = 1;
        if (pageParam != null) {
            page = pageParam.intValue();
        }

        int startPage = 1;
        if (page - 5 > 0) {
            startPage = page - 5;
        }

        int firstUser = 0;
        if (page > 1) {
            firstUser = (page - 1) * USERS_PER_PAGE;
        }

        int endUser = firstUser + USERS_PER_PAGE;

        String filter = "%";
        String filterParam = request.getParameter("filter");
        if (filterParam != null) {
            filter = "%" + filterParam + "%";
            sortFilterPage.setFilter(filterParam);
        }
        int usersCount;

        List<MemberItem> users = new ArrayList<>();

        final String sortColumn = sortFilterPage.getSortColumn() != null ? sortFilterPage.getSortColumn() : "";
        List<User> dBUsers;
        if (memberCategoryParam == null || memberCategoryParam.compareTo("") == 0) {

            if (filterParam != null){
                usersCount = Xcolab_UserLocalServiceUtil.getUsersSortedByScreenName(0, Integer.MAX_VALUE,
                        filter, true).size();
            } else {
                usersCount = UserLocalServiceUtil.getUsersCount();
            }

            switch (sortColumn) {
                case "USER_NAME":
                    dBUsers = Xcolab_UserLocalServiceUtil.getUsersSortedByScreenName(firstUser, endUser,
                            filter, sortFilterPage.isSortAscending());
                    break;

                case "POINTS":
                    dBUsers = Xcolab_UserLocalServiceUtil.getUsersSortedByPoints(firstUser, endUser,
                            filter, sortFilterPage.isSortAscending());
                    break;

                case "ACTIVITY":
                    dBUsers = Xcolab_UserLocalServiceUtil.getUsersSortedByActivityCount(firstUser, endUser,
                            filter, sortFilterPage.isSortAscending());
                    break;

                case "CATEGORY":
                    dBUsers = Xcolab_UserLocalServiceUtil.getUsersSortedByRoleName(firstUser, endUser,
                            filter, sortFilterPage.isSortAscending());
                    break;

                case "MEMBER_SINCE":
                    dBUsers = Xcolab_UserLocalServiceUtil.getUsersSortedByMemberSince(firstUser, endUser,
                            filter, sortFilterPage.isSortAscending());
                    break;
                default:
                    sortFilterPage.setSortColumn("POINTS");
                    sortFilterPage.setSortAscending(false);
                    dBUsers = Xcolab_UserLocalServiceUtil.getUsersSortedByActivityCount(firstUser, endUser,
                            filter, sortFilterPage.isSortAscending());

            }
            request.getPortletSession().setAttribute("previousSortColumn", sortColumn);
            request.getPortletSession().setAttribute("previousSortOrder", sortFilterPage.isSortAscending());

            for (User user : dBUsers) {
                MemberItem memberItem = new MemberItem(user, memberCategoryParam);
                if (memberItem.getMemberRole() != MemberRole.STAFF) {
                    users.add(memberItem);
                } else {
                    usersCount--;
                }
            }
        } else { //Filtering by category
            String memberCategoryFilter="%" + memberCategoryParam + "%";

            //Pagination
            usersCount = Xcolab_UserLocalServiceUtil.getUsersSortedByScreenNameFilteredByCategory(0, Integer.MAX_VALUE, filter, memberCategoryFilter, true).size();

            switch (sortColumn) {
                case "UserNAME":
                    dBUsers = Xcolab_UserLocalServiceUtil.getUsersSortedByScreenNameFilteredByCategory(firstUser,
                            endUser, filter, memberCategoryFilter, sortFilterPage.isSortAscending());
                    break;
                case "POINTS":
                    dBUsers = Xcolab_UserLocalServiceUtil.getUsersSortedByPointsFilteredByCategory(firstUser,
                            endUser, filter, memberCategoryFilter, sortFilterPage.isSortAscending());
                    break;
                case "ACTIVITY":
                    dBUsers = Xcolab_UserLocalServiceUtil.getUsersSortedByActivityCountFilteredByCategory(firstUser,
                            endUser, filter, memberCategoryFilter, sortFilterPage.isSortAscending());
                    break;
                case "CATEGORY":
                    dBUsers = Xcolab_UserLocalServiceUtil.getUsersSortedByScreenNameFilteredByCategory(firstUser,
                            endUser, filter, memberCategoryFilter, sortFilterPage.isSortAscending());
                    break;
                case "MEMBER_SINCE":
                    dBUsers = Xcolab_UserLocalServiceUtil.getUsersSortedByMemberSinceFilteredByCategory(firstUser,
                            endUser, filter, memberCategoryFilter, sortFilterPage.isSortAscending());
                    break;
                default:
                    dBUsers = Xcolab_UserLocalServiceUtil.getUsersSortedByActivityCountFilteredByCategory(firstUser,
                            endUser, filter, memberCategoryFilter, false);
                    sortFilterPage.setSortColumn("ACTIVITY");
                    sortFilterPage.setSortAscending(false);
            }

            for (User user : dBUsers) {
                MemberItem memberItem = new MemberItem(user, memberCategoryParam);
                users.add(memberItem);
            }
        }

        int pagesCount = (int) Math.ceil(usersCount / (double) USERS_PER_PAGE);
        int endPage = pagesCount;
        if (startPage + 10 < pagesCount) {
            endPage = startPage + 10;
        }

        model.addAttribute("pageNumber", page);
        model.addAttribute("pagesCount", pagesCount);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("sortFilterPage", sortFilterPage);
        model.addAttribute("users", users);
        model.addAttribute("usersCount", usersCount);
        model.addAttribute("memberCategory", memberCategoryParam);
        model.addAttribute("memberCategories", MemberCategoryLocalServiceUtil
                .getVisibleMemberCategories());

        return "users";
    }
}