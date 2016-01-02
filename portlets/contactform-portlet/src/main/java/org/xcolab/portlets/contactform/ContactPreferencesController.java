package org.xcolab.portlets.contactform;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.portlet.ActionRequest;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ValidatorException;
import java.io.IOException;

@Controller
@RequestMapping("edit")
public class ContactPreferencesController {
	
    @RequestMapping
    public String showFeed(RenderRequest request, RenderResponse response, Model model) {
    	model.addAttribute("contactPreferences", new ContactPreferences(request));
        
        return "edit";
    }
	

    @RequestMapping(params = {"action=savePreferences"})
    public void savePreferences(ActionRequest request, ActionRequest response, Model model, ContactPreferences preferences) throws ReadOnlyException, ValidatorException, IOException {;
        preferences.store(request.getPreferences());
	}

}
