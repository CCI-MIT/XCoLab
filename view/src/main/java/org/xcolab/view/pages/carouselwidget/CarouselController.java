package org.xcolab.view.pages.carouselwidget;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger _log = LoggerFactory.getLogger(CarouselController.class);

    public CarouselController() {
    }

    @GetMapping("/carouselwidget")
    public String showCarousel(@RequestParam(required = false) String preferenceId, HttpServletRequest request, HttpServletResponse response, Model model)  {
        Locale locale = LocaleContextHolder.getLocale();
        CarouselPreferences carouselPreferences = new CarouselPreferences(preferenceId,locale.getLanguage());

        model.addAttribute("carouselPreferences", carouselPreferences);
        return "carouselwidget/showCarousel";
    }

}
