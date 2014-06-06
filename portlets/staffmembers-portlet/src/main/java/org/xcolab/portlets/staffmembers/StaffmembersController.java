package org.xcolab.portlets.staffmembers;

import com.ext.portlet.model.StaffMember;
import com.ext.portlet.service.StaffMemberLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.util.List;

@Controller
@RequestMapping("view")
public class StaffMembersController {
    @RequestMapping
    public String showStaffMembers(PortletRequest request, PortletResponse response, Model model) throws SystemException, PortalException {

        //_preferences = new StaffMembersPreferences(request);

        //TODO: use the categoryId from the preferences
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(StaffMember.class)
                .add(PropertyFactoryUtil.forName("categoryId").eq(new Long(1)));

        List<StaffMember> staffMembers = StaffMemberLocalServiceUtil.dynamicQuery(query);

        model.addAttribute("staffMembers", staffMembers);

        //return the name of the view to be rendered
        return "staffmembers";
    }
}
