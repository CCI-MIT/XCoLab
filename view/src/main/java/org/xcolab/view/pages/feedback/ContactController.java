package org.xcolab.view.pages.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.emails.EmailClient;
import org.xcolab.entity.utils.ReCaptchaUtils;
import org.xcolab.entity.utils.portlet.session.SessionErrors;
import org.xcolab.entity.utils.portlet.session.SessionMessages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class ContactController {

    public static final String CONTACT_VIEW_NAME = "feedback/contactForm";

    @Autowired
    private ContactPreferences contactPreferences;

    @Autowired
    private Validator validator;

    private String fromAddress = "no-reply@climatecolab.org";

    public ContactController() {
        fromAddress = ConfigurationAttributeKey.ADMIN_FROM_EMAIL.get();
    }

    //@InitBinder("contactBean")
    //public void initBinder(WebDataBinder binder) {
     //   binder.setValidator(validator);
    //}

    /**
     * Main view displayed when user enters page with contactform portlet
     */
    @GetMapping("/feedback")
    public String showContact(HttpServletRequest request, HttpServletResponse response, Model model) {
        contactPreferences = new ContactPreferences(request);
        model.addAttribute("contactBean", new ContactBean());

        return CONTACT_VIEW_NAME;
    }

    @ModelAttribute("recaptchaDataSiteKey")
    public String getRecaptchaDataSiteKey(){
        return ConfigurationAttributeKey.GOOGLE_RECAPTCHA_SITE_KEY.get();
    }


    @GetMapping("/feedback/error")
    public String contactError(HttpServletRequest request, Model model, @Valid ContactBean contactBean,
                               BindingResult result, @RequestParam(required = false) String redirect) {
        if (request.getParameter("recaptchaError") != null) {
            result.addError(new ObjectError("createUserBean", "Please complete the captcha"));
        }
        model.addAttribute("error", true);

        return CONTACT_VIEW_NAME;
    }

    @GetMapping("/feedback/success")
    public String sendMessageSuccess(HttpServletRequest request, Model model, @Valid ContactBean contactBean,
                                     BindingResult result, @RequestParam(required = false) String redirect) {
        if (request.getParameter("recaptchaError") != null) {
            result.addError(new ObjectError("createUserBean", "Invalid words in captcha field"));
        }
        model.addAttribute("contactBean", new ContactBean());
        model.addAttribute("success", true);

        return CONTACT_VIEW_NAME;
    }

    @PostMapping("/feedback/sendFeedback")
    public void sendMessage(HttpServletRequest request, Model model, HttpServletResponse response,
            @Valid ContactBean contactBean, BindingResult result,
            @RequestParam(required = false) String redirect) throws IOException {

        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        boolean captchaValid = ReCaptchaUtils.verify(gRecaptchaResponse,
                ConfigurationAttributeKey.GOOGLE_RECAPTCHA_SITE_SECRET_KEY.get());

        SessionErrors.clear(request);
        SessionMessages.clear(request);
        if (!result.hasErrors()) {

            if (!captchaValid) {
                SessionErrors.clear(request);

                response.sendRedirect("/feedback/error?recaptchaError=true");
            } else {


                String messageSubject = applyFilters(contactPreferences
                        .getMessageSubject(), contactBean);
                String messageBody = applyFilters(contactPreferences.getMessageFormat(), contactBean);



                String[] recipients = contactPreferences.getRecipientsArray();
                List<String> addressTo = new ArrayList<>();

                Collections.addAll(addressTo, recipients);


                EmailClient.sendEmail(fromAddress, addressTo , messageSubject,
                        messageBody, false, contactBean.getEmail());


                response.sendRedirect("/feedback/?success=true");
            }
        } else {
            response.sendRedirect("/feedback/error");
        }

    }


    private String applyFilters(String msg, ContactBean contactBean) {
        msg = msg.replaceAll("USER_NAME", contactBean.getName());
        msg = msg.replaceAll("USER_EMAIL", contactBean.getEmail());
        msg = msg.replaceAll("USER_MESSAGE", contactBean.getMessage());
        return msg;
    }

}
