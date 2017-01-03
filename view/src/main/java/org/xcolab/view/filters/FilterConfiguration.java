package org.xcolab.view.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.xcolab.view.filters.emails.ActivitySubscriptionEmailFilter;
import org.xcolab.view.filters.errorreporting.ErrorReportingFilter;
import org.xcolab.view.filters.files.FileUploadFilter;
import org.xcolab.view.filters.files.ImageDisplayFilter;
import org.xcolab.view.filters.filtering.ProfanityFilteringFilter;
import org.xcolab.view.filters.flagging.FlaggingReportFilter;
import org.xcolab.view.filters.impersonation.ImpersonationFilter;
import org.xcolab.view.filters.membership.ProposalMembershipInvitationResponseFilter;
import org.xcolab.view.filters.tracking.UserTrackingServlet;

import javax.servlet.Filter;

@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean activitySubscriptionEmail() {
        return createRegistration(new ActivitySubscriptionEmailFilter(), "/emails/sendNotifications");
    }

    @Bean
    public FilterRegistrationBean errorReporting() {
        return createRegistration(new ErrorReportingFilter(),"/c/reportError");
    }

    @Bean
    public FilterRegistrationBean fileUpload() {
        return createRegistration(new FileUploadFilter(),"/fileUpload");
    }

    @Bean
    public FilterRegistrationBean imageDisplay() {
        return createRegistration(new ImageDisplayFilter(),"/image/*");
    }

    @Bean
    public FilterRegistrationBean profanityFiltering() {
        return createRegistration(new ProfanityFilteringFilter(),"/profanityfiltering/*");
    }

    @Bean
    public FilterRegistrationBean flaggingReport() {
        return createRegistration(new FlaggingReportFilter(),"/flagging/report");
    }

    @Bean
    public FilterRegistrationBean impersonation() {
        return createRegistration(new ImpersonationFilter(),"/impersonate");
    }

    @Bean
    public FilterRegistrationBean proposalMembershipInvitation() {
        return createRegistration(new ProposalMembershipInvitationResponseFilter(),
                "/membershipRequests/reply");
    }

    private FilterRegistrationBean createRegistration(Filter filter, String urlPattern) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.addUrlPatterns(urlPattern);
        return registration;
    }

    @Bean
    public ServletRegistrationBean userTracking() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        registrationBean.setServlet(new UserTrackingServlet());
        registrationBean.addUrlMappings("/trackVisitor");
        return registrationBean;
    }
}
