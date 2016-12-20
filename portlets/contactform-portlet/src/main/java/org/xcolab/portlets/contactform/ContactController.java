package org.xcolab.portlets.contactform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.emails.EmailClient;
import org.xcolab.entity.utils.ReCaptchaUtils;
import org.xcolab.entity.utils.portlet.session.SessionErrors;
import org.xcolab.entity.utils.portlet.session.SessionMessages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("view")
public class ContactController {

    @Autowired
    private ContactPreferences contactPreferences;

    @Autowired
    private Validator validator;

    private String fromAddress = "no-reply@climatecolab.org";

    public ContactController() {
        fromAddress = ConfigurationAttributeKey.ADMIN_FROM_EMAIL.get();
    }

    @InitBinder("contactBean")
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    /**
     * Main view displayed when user enters page with contactform portlet
     */
    @RequestMapping
    public String showContact(PortletRequest request, PortletResponse response, Model model) {
        contactPreferences = new ContactPreferences(request);
        model.addAttribute("contactBean", new ContactBean());

        return "view";
    }

    @ModelAttribute("recaptchaDataSiteKey")
    public String getRecaptchaDataSiteKey(){
        return ConfigurationAttributeKey.GOOGLE_RECAPTCHA_SITE_KEY.get();
    }


    @RequestMapping(params = "error=true")
    public String contactError(PortletRequest request, Model model, @Valid ContactBean contactBean,
                               BindingResult result, @RequestParam(required = false) String redirect) {
        if (request.getParameter("recaptchaError") != null) {
            result.addError(new ObjectError("createUserBean", "Invalid words in captcha field"));
        }
        model.addAttribute("error", true);

        return "view";
    }

    @RequestMapping(params = "success=true")
    public String sendMessageSuccess(PortletRequest request, Model model, @Valid ContactBean contactBean,
                                     BindingResult result, @RequestParam(required = false) String redirect) {
        if (request.getParameter("recaptchaError") != null) {
            result.addError(new ObjectError("createUserBean", "Invalid words in captcha field"));
        }
        model.addAttribute("contactBean", new ContactBean());
        model.addAttribute("success", true);

        return "view";
    }

    @RequestMapping(params = "action=send")
    public void sendMessage(ActionRequest request, Model model, ActionResponse response,
            @Valid ContactBean contactBean, BindingResult result,
            @RequestParam(required = false) String redirect) {

        String gRecaptchaResponse = request
                .getParameter("g-recaptcha-response");
        boolean captchaValid = ReCaptchaUtils.verify(gRecaptchaResponse,ConfigurationAttributeKey.GOOGLE_RECAPTCHA_SITE_SECRET_KEY.get());


        SessionErrors.clear(request);
        SessionMessages.clear(request);
        if (!result.hasErrors()) {


            if (!captchaValid) {
                SessionErrors.clear(request);
                response.setRenderParameter("error", "true");
                response.setRenderParameter("recaptchaError", "true");
            } else {
                response.setRenderParameter("success", "true");

                String messageSubject = applyFilters(contactPreferences
                        .getMessageSubject(), contactBean);
                String messageBody = applyFilters(contactPreferences.getMessageFormat(), contactBean);



                String[] recipients = contactPreferences.getRecipientsArray();
                List<String> addressTo = new ArrayList<>();

                Collections.addAll(addressTo, recipients);


                EmailClient.sendEmail(fromAddress, addressTo , messageSubject,
                        messageBody, false, contactBean.getEmail());

                response.setRenderParameter("success", "true");
            }
        } else {
            response.setRenderParameter("error", "true");
        }

    }


    private String applyFilters(String msg, ContactBean contactBean) {
        msg = msg.replaceAll("USER_NAME", contactBean.getName());
        msg = msg.replaceAll("USER_EMAIL", contactBean.getEmail());
        msg = msg.replaceAll("USER_MESSAGE", contactBean.getMessage());
        return msg;
    }

}
