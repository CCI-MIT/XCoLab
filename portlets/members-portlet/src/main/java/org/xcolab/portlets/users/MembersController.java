package org.xcolab.portlets.users;

import com.ext.portlet.service.MemberCategoryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.commons.beans.SortFilterPage;
import org.xcolab.enums.ConfigurationAttributeKey;
import org.xcolab.pojo.User_;
import org.xcolab.portlets.users.utils.MemberItem;
import org.xcolab.service.client.MembersClient;

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
                            @RequestParam(value = "memberCategory", required = false) String memberCategoryParam,
                            Model model)
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


        String filterParam = request.getParameter("filter");
        if (filterParam != null) {
            sortFilterPage.setFilter(filterParam);
        }

        List<MemberItem> users = new ArrayList<>();

        final String sortColumn = sortFilterPage.getSortColumn() != null ? sortFilterPage.getSortColumn() : "";

        int usersCount = MembersClient.countMembers(memberCategoryParam, filterParam);

        if(sortColumn == null || sortColumn.isEmpty()){
            sortFilterPage.setSortColumn("POINTS");
            sortFilterPage.setSortAscending(false);
        }
        List<User_> dbUsersMicro = MembersClient.listMembers(memberCategoryParam, filterParam, sortColumn,
                sortFilterPage.isSortAscending(), firstUser, endUser);


        request.getPortletSession().setAttribute("previousSortColumn", sortColumn);
        request.getPortletSession().setAttribute("previousSortOrder", sortFilterPage.isSortAscending());

        for (User_ user : dbUsersMicro) {
            MemberItem memberItem = new MemberItem(user, memberCategoryParam);
            users.add(memberItem);
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

        model.addAttribute("colabName", ConfigurationAttributeKey.COLAB_NAME.getStringValue());
        model.addAttribute("colabShortName", ConfigurationAttributeKey.COLAB_SHORT_NAME.getStringValue());
        return "users";
    }
}