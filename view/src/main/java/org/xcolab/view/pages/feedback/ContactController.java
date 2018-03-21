package org.xcolab.view.pages.feedback;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.contents.ContentsClient;
import org.xcolab.client.contents.exceptions.ContentNotFoundException;
import org.xcolab.client.contents.pojo.ContentPage;
import org.xcolab.client.emails.EmailClient;
import org.xcolab.view.errors.ErrorText;
import org.xcolab.view.util.entity.ReCaptchaUtils;
import org.xcolab.commons.servlet.flash.AlertMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class ContactController {

    private static final String CONTACT_VIEW_NAME = "/feedback/contactForm";

    public ContactController() {
    }

    @GetMapping("/feedback")
    public String showContact(HttpServletRequest request, Model model) {

        try {
            final ContentPage feedbackPage = ContentsClient.getContentPage("feedback");
            model.addAttribute("feedbackPage", feedbackPage);
            if (!model.containsAttribute("contactBean")) {
                model.addAttribute("contactBean", new ContactBean());
            }

        } catch (ContentNotFoundException e) {
            return ErrorText.NOT_FOUND.flashAndReturnView(request);
        }

        return CONTACT_VIEW_NAME;
    }

    @ModelAttribute("recaptchaDataSiteKey")
    public String getRecaptchaDataSiteKey() {
        return PlatformAttributeKey.GOOGLE_RECAPTCHA_SITE_KEY.get();
    }

    @PostMapping("/feedback")
    public String sendMessage(HttpServletRequest request, HttpServletResponse response, Model model,
            @Valid ContactBean contactBean, BindingResult result,
            @RequestParam(required = false) String redirect) throws IOException {

        Locale locale = LocaleContextHolder.getLocale();
        ContactPreferences contactPreferences = new ContactPreferences(null, locale.getLanguage());

        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        boolean captchaValid = ReCaptchaUtils.verify(gRecaptchaResponse,
                ConfigurationAttributeKey.GOOGLE_RECAPTCHA_SITE_SECRET_KEY.get());

        if (result.hasErrors() || !captchaValid) {
            if (!captchaValid) {
                result.addError(new ObjectError("contactBean", "Please complete the captcha"));
            }
            model.addAttribute("hasError", true);
            return showContact(request, model);
        }

        String messageSubject = applyFilters(contactPreferences.getMessageSubject(), contactBean);
        String messageBody = applyFilters(contactPreferences.getMessageFormat(), contactBean);

        String[] recipients = contactPreferences.getRecipientsArray();
        List<String> addressTo = new ArrayList<>();

        Collections.addAll(addressTo, recipients);

        final String fromAddress = ConfigurationAttributeKey.ADMIN_FROM_EMAIL.get();
        final String fromName = ConfigurationAttributeKey.COLAB_NAME.get();
        EmailClient.sendEmail(fromAddress, fromName, addressTo, messageSubject, messageBody, false,
                contactBean.getEmail(), null, null);

        AlertMessage.success("Message sent!").flash(request);
        return showContact(request, model);
    }


    private String applyFilters(String msg, ContactBean contactBean) {
        msg = msg.replaceAll("USER_NAME", contactBean.getName());
        msg = msg.replaceAll("USER_EMAIL", contactBean.getEmail());
        msg = msg.replaceAll("USER_MESSAGE", contactBean.getMessage());
        return msg;
    }
}
