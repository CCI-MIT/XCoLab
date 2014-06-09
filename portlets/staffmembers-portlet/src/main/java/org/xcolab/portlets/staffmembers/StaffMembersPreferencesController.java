package org.xcolab.portlets.staffmembers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.portlet.*;
import java.io.IOException;

@Controller
@RequestMapping("edit")
public class StaffMembersPreferencesController {
	
    @RequestMapping
    public String showStaffMembers(RenderRequest request, RenderResponse response, Model model) {
    	model.addAttribute("staffMembersPreferences", new StaffMembersPreferences(request));
        
        return "edit";
    }
	

    @RequestMapping(params = {"action=savePreferences"})
    public void savePreferences(ActionRequest request, ActionRequest response, Model model, StaffMembersPreferences preferences) throws ReadOnlyException, ValidatorException, IOException {
    	preferences.store(request);
	}

}
