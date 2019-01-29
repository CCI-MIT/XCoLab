package org.xcolab.view.widgets.logos;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.view.widgets.AbstractWidgetController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(CarouselController.BASE_URL)
public class CarouselController extends AbstractWidgetController<CarouselPreferences> {

    private static final String VIEW_BASE_PATH = "/widgets/logos";

    public static final String BASE_URL = "/widgets/logos";

    protected CarouselController() {
        super(BASE_URL, CarouselPreferences::new);
    }

    @GetMapping(AbstractWidgetController.PREFERENCES_URL_PATH)
    public String showPreferences(HttpServletResponse response, Model model, UserWrapper member,
            @RequestParam(required = false) String preferenceId,
            @RequestParam(defaultValue = "en") String language) {
        return showPreferencesInternal(response, model, member, preferenceId, language,
                VIEW_BASE_PATH + "/editPreferences");
    }


    @PostMapping(AbstractWidgetController.PREFERENCES_URL_PATH)
    public String savePreferences(HttpServletRequest request, HttpServletResponse response,
            UserWrapper member, CarouselPreferences preferences) {
        List<LogoElement> logos = new ArrayList<>();
        for (LogoElement logoElement : preferences.getLogos()) {
            if (!logoElement.getImageUrl().isEmpty() && !logoElement.getRemove()) {
                logos.add(logoElement);
            }
        }
        if (logos.size() > preferences.getLogosCount()) {
            logos = logos.subList(0, preferences.getLogosCount());
        }
        preferences.setLogos(logos);
        return savePreferencesInternal(request, response, member, preferences);
    }

    @GetMapping
    public String showCarousel(Model model, @RequestParam(required = false) String preferenceId) {
        model.addAttribute("preferences", getPreferences(preferenceId));
        return VIEW_BASE_PATH + "/showCarousel";
    }

}
