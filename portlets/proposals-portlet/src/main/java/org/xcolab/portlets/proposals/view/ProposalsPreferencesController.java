package org.xcolab.portlets.proposals.view;

import java.io.IOException;

import javax.portlet.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.portlets.proposals.wrappers.ProposalsPreferencesWrapper;

@Controller
@RequestMapping("edit")
public class ProposalsPreferencesController {

    @RequestMapping
    public String showPreferences(RenderRequest request, RenderResponse response, Model model) {
        model.addAttribute("prefs", new ProposalsPreferencesWrapper(request));
        return "preferences";
    }
    

    @RequestMapping(params = "action=save")
    public void showPreferences(ActionRequest request, ActionResponse response, Model model, ProposalsPreferencesWrapper preferences) 
            throws ReadOnlyException, ValidatorException, IOException {

        preferences.store(request);
    }
}
