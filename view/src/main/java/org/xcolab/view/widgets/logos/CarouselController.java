package org.xcolab.view.widgets.logos;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.widgets.AbstractWidgetController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
//@GetMapping("/carouselwidget")
@RequestMapping(CarouselController.BASE_URL)
public class CarouselController extends AbstractWidgetController<CarouselPreferences> {

    public static final String BASE_URL = "/widgets/logos";

    protected CarouselController() {
        super(BASE_URL, CarouselPreferences::new);
    }

    @GetMapping(AbstractWidgetController.PREFERENCES_URL_PATH)
    public String showPreferences(HttpServletResponse response, Model model, Member member,
            @RequestParam(required = false) String preferenceId,
            @RequestParam(defaultValue = "en") String language) {
        return showPreferencesInternal(response, model,  member, preferenceId, language,
                "/carouselwidget/editPreferences");
    }


    @PostMapping(AbstractWidgetController.PREFERENCES_URL_PATH)
    public String savePreferences(HttpServletRequest request, HttpServletResponse response,
            Member member, CarouselPreferences preferences) {
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
        return "carouselwidget/showCarousel";
    }

}
