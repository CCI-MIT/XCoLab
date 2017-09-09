package org.xcolab.view.widgets.logos;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CarouselController {

    @GetMapping("/carouselwidget")
    public String showCarousel(HttpServletRequest request, HttpServletResponse response,
            Model model, @RequestParam(required = false) String preferenceId) {
        Locale locale = LocaleContextHolder.getLocale();
        CarouselPreferences carouselPreferences =
                new CarouselPreferences(preferenceId, locale.getLanguage());

        model.addAttribute("carouselPreferences", carouselPreferences);
        return "carouselwidget/showCarousel";
    }

}
