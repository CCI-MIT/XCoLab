package org.xcolab.view.theme;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolverChain;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.util.enums.theme.ColabTheme;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class ThemeResourceResolver extends PathResourceResolver {

    @Override
    protected Resource resolveResourceInternal(HttpServletRequest request,
            String requestPath,
            List<? extends Resource> locations, ResourceResolverChain chain) {
        Resource themedResource = super.resolveResourceInternal(request,
                requestPath, getOverrideResourceLocations(), chain);
        if (themedResource != null) {
            return themedResource;
        }
        return super.resolveResourceInternal(request, requestPath, locations, chain);
    }

    private List<Resource> getOverrideResourceLocations() {
        ColabTheme activeTheme = ConfigurationAttributeKey.ACTIVE_THEME.get();
        final ClassPathResource overrideLocation =
                new ClassPathResource(activeTheme.getOverrideImagePath() + "/");
        return Collections.singletonList(overrideLocation);
    }
}
