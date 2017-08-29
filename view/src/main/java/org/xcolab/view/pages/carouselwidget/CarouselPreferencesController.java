package org.xcolab.view.pages.carouselwidget;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.util.i18n.I18nUtils;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.util.entity.flash.AlertMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CarouselPreferencesController {

    @GetMapping("carouselwidget/editPreferences")
    public String showPreferences(HttpServletRequest request, HttpServletResponse response,
            Model model, Member member, @RequestParam(required = false) String preferenceId,
            @RequestParam(required = false) String language) {

        if (!PermissionsClient.canAdminAll(member)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        if (language == null || language.isEmpty()) {
            language = I18nUtils.DEFAULT_LANGUAGE;
        }

        CarouselPreferences carouselPreferences = new CarouselPreferences(preferenceId, language);
        model.addAttribute("carouselPreferences", carouselPreferences);
        return "carouselwidget/editPreferences";
    }


    @PostMapping("carouselwidget/savePreferences")
    public void savePreferences(HttpServletRequest request, HttpServletResponse response,
            Model model, CarouselPreferences carouselPreferences) throws IOException {
        List<LogoElement> logos = new ArrayList<>();
        for (LogoElement logoElement : carouselPreferences.getLogos()) {
            if (!logoElement.getImageUrl().isEmpty() && !logoElement.getRemove()) {
                logos.add(logoElement);
            }
        }
        if (logos.size() > carouselPreferences.getLogosCount()) {
            logos = logos.subList(0, carouselPreferences.getLogosCount());
        }
        carouselPreferences.setLogos(logos);
        carouselPreferences.submit();

        AlertMessage.success("Carousel widget preferences has been saved.").flash(request);
        response.sendRedirect("/carouselwidget/editPreferences?preferenceId=" + carouselPreferences
                .getPreferenceId());

    }

}
