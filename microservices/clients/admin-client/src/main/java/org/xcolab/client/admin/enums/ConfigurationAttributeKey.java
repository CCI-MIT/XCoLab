package org.xcolab.client.admin.enums;

import org.xcolab.util.attributes.AttributeGetter;

import java.util.Collections;
import java.util.List;

public final class ConfigurationAttributeKey {

    //Main CoLab configuration
    public static final AttributeGetter<String> ADMIN_EMAIL =
            ConfigurationAttributes.newStringAttribute("ADMIN_EMAIL")
                    .withCache().build();
    public static final AttributeGetter<String> ADMIN_FROM_EMAIL =
            ConfigurationAttributes.newStringAttribute("ADMIN_FROM_EMAIL")
                    .withCache().build();
    public static final AttributeGetter<String> COLAB_NAME =
            ConfigurationAttributes.newStringAttribute("COLAB_NAME")
                    .withCache().build();
    public static final AttributeGetter<String> COLAB_SHORT_NAME =
            ConfigurationAttributes.newStringAttribute("COLAB_SHORT_NAME")
                    .withCache().build();
    public static final AttributeGetter<String> COLAB_URL =
            ConfigurationAttributes.newStringAttribute("COLAB_URL")
                    .withCache().build();

    public static final AttributeGetter<Long> DEFAULT_CONTEST_TYPE_ID =
            ConfigurationAttributes.newLongAttribute("DEFAULT_CONTEST_TYPE_ID")
                    .withCache().build();
    public static final AttributeGetter<String> DEFAULT_TIME_ZONE_ID =
            ConfigurationAttributes.newStringAttribute("DEFAULT_TIME_ZONE_ID")
                    .withCache().build();
    public static final AttributeGetter<String> GOOGLE_ANALYTICS_KEY =
            ConfigurationAttributes.newStringAttribute("GOOGLE_ANALYTICS_KEY")
                    .withCache().build();


    //SSO Configuration
    public static final AttributeGetter<String> GOOGLE_AUTH_CLIENT_ID =
            ConfigurationAttributes.newStringAttribute("GOOGLE_AUTH_CLIENT_ID")
                    .build();
    public static final AttributeGetter<String> GOOGLE_AUTH_CLIENT_SECRET =
            ConfigurationAttributes.newStringAttribute("GOOGLE_AUTH_CLIENT_SECRET")
                    .build();
    public static final AttributeGetter<String> FACEBOOK_APPLICATION_ID =
            ConfigurationAttributes.newStringAttribute("FACEBOOK_APPLICATION_ID")
                    .build();
    public static final AttributeGetter<String> FACEBOOK_APPLICATION_SECRET =
            ConfigurationAttributes.newStringAttribute("FACEBOOK_APPLICATION_SECRET")
                    .build();
    public static final AttributeGetter<Boolean> FACEBOOK_VERIFIED_REQUIRED =
            ConfigurationAttributes.newBooleanAttribute("FACEBOOK_VERIFIED_REQUIRED")
                    .build();


    //MyEmma configuration
    public static final AttributeGetter<Boolean> IS_MY_EMMA_ACTIVE =
            ConfigurationAttributes.newBooleanAttribute("IS_MY_EMMA_ACTIVE")
                    .build();
    public static final AttributeGetter<String> MY_EMMA_ACCOUNT_ID =
            ConfigurationAttributes.newStringAttribute("MY_EMMA_ACCOUNT_ID")
                    .build();
    public static final AttributeGetter<String> MY_EMMA_GROUP_ID =
            ConfigurationAttributes.newStringAttribute("MY_EMMA_GROUP_ID")
                    .build();
    public static final AttributeGetter<String> MY_EMMA_PUBLIC_API_KEY =
            ConfigurationAttributes.newStringAttribute("MY_EMMA_PUBLIC_API_KEY")
                    .build();
    public static final AttributeGetter<String> MY_EMMA_PRIVATE_API_KEY =
            ConfigurationAttributes.newStringAttribute("MY_EMMA_PRIVATE_API_KEY")
                    .build();


    //Image upload help messages
    public static final AttributeGetter<String> IMAGE_UPLOAD_EXTERNAL_SERVICE_URL =
            ConfigurationAttributes.newStringAttribute("IMAGE_UPLOAD_EXTERNAL_SERVICE_URL")
                    .build();
    public static final AttributeGetter<String> IMAGE_UPLOAD_HELP_TEXT =
            ConfigurationAttributes.newStringAttribute("IMAGE_UPLOAD_HELP_TEXT")
                    .build();

    //Shared CoLab configuration
    public static final AttributeGetter<Boolean> IS_SHARED_COLAB =
            ConfigurationAttributes.newBooleanAttribute("IS_SHARED_COLAB")
                    .defaultValue(false).build();
    public static final AttributeGetter<String> SHARED_COLAB_PORT =
            ConfigurationAttributes.newStringAttribute("SHARED_COLAB_PORT")
                    .defaultValue("8080").build();
    public static final AttributeGetter<String> SHARED_COLAB_LOCATION =
            ConfigurationAttributes.newStringAttribute("SHARED_COLAB_LOCATION")
                    .defaultValue("localhost").build();
    public static final AttributeGetter<String> PARTNER_COLAB_NAME =
            ConfigurationAttributes.newStringAttribute("PARTNER_COLAB_NAME")
                    .build();
    public static final AttributeGetter<String> PARTNER_COLAB_LOCATION =
            ConfigurationAttributes.newStringAttribute("PARTNER_COLAB_LOCATION")
                    .build();
    public static final AttributeGetter<String> PARTNER_COLAB_PORT =
            ConfigurationAttributes.newStringAttribute("PARTNER_COLAB_PORT")
                    .build();


    //Social media share text
    public static final AttributeGetter<String> OPEN_GRAPH_SHARE_TITLE =
            ConfigurationAttributes.newStringAttribute("OPEN_GRAPH_SHARE_TITLE")
                    .build();
    public static final AttributeGetter<String> OPEN_GRAPH_SHARE_DESCRIPTION =
            ConfigurationAttributes.newStringAttribute("OPEN_GRAPH_SHARE_DESCRIPTION")
                    .build();


    //Impact tab configuration
    public static final AttributeGetter<Boolean> IMPACT_TAB_IS_ACTIVE =
            ConfigurationAttributes.newBooleanAttribute("IMPACT_TAB_IS_ACTIVE")
                    .defaultValue(false).build();
    public static final AttributeGetter<List<Long>> IMPACT_TAB_EXCLUDED_ONTOLOGY_TERM_IDS =
            ConfigurationAttributes.newIdListAttribute("IMPACT_TAB_EXCLUDED_ONTOLOGY_TERM_IDS")
                    .defaultValue(Collections.<Long>emptyList()).build();


    //Misc features
    public static final AttributeGetter<String> LOGIN_INFO_MESSAGE =
            ConfigurationAttributes.newStringAttribute("LOGIN_INFO_MESSAGE")
                    .defaultValue("").build();
    public static final AttributeGetter<String> MEMBERS_DEFAULT_SORT_COLUMN =
            ConfigurationAttributes.newStringAttribute("MEMBERS_DEFAULT_SORT_COLUMN")
                    .defaultValue("").build();

    //Misc feature flags
    public static final AttributeGetter<Boolean> BETA_RIBBON_SHOW =
            ConfigurationAttributes.newBooleanAttribute("BETA_RIBBON_SHOW")
                    .build();
    public static final AttributeGetter<Boolean> SHOW_SEARCH_MENU_ITEM =
            ConfigurationAttributes.newBooleanAttribute("SHOW_SEARCH_MENU_ITEM")
                    .defaultValue(true)
                    .build();
    public static final AttributeGetter<Boolean> SHOW_SHARE_BUTTONS =
            ConfigurationAttributes.newBooleanAttribute("SHOW_SHARE_BUTTONS")
                    .defaultValue(true)
                    .build();
    public static final AttributeGetter<Boolean> PUBLISH_JUDGING_RESULTS =
            ConfigurationAttributes.newBooleanAttribute("PUBLISH_JUDGING_RESULTS")
                    .build();
    public static final AttributeGetter<Boolean> IS_POINTS_ACTIVE =
            ConfigurationAttributes.newBooleanAttribute("IS_POINTS_ACTIVE")
                    .build();
    public static final AttributeGetter<Boolean> FLAGGING_ALLOW_MEMBERS =
            ConfigurationAttributes.newBooleanAttribute("FLAGGING_ALLOW_MEMBERS")
                    .build();
    public static final AttributeGetter<Boolean> SHOW_CONTEST_COUNTDOWN =
            ConfigurationAttributes.newBooleanAttribute("SHOW_CONTEST_COUNTDOWN")
                    .build();
    public static final AttributeGetter<Boolean> FILTER_PROFANITY =
            ConfigurationAttributes.newBooleanAttribute("FILTER_PROFANITY")
                    .build();
    public static final AttributeGetter<Boolean> SHOW_CONTESTS_DISPLAY_OPTIONS =
            ConfigurationAttributes.newBooleanAttribute("SHOW_CONTESTS_DISPLAY_OPTIONS")
                    .build();
    public static final AttributeGetter<Boolean> GENERATE_SCREEN_NAME =
            ConfigurationAttributes.newBooleanAttribute("GENERATE_SCREEN_NAME")
                    .build();


    //Configuration of Solve's header bar
    public static final AttributeGetter<Boolean> MIT_HEADER_BAR_SHOW =
            ConfigurationAttributes.newBooleanAttribute("MIT_HEADER_BAR_SHOW")
                    .defaultValue(false).build();
    public static final AttributeGetter<String> MIT_HEADER_BAR_LINK_TEXT =
            ConfigurationAttributes.newStringAttribute("MIT_HEADER_BAR_LINK_TEXT")
                    .defaultValue("").build();
    public static final AttributeGetter<String> MIT_HEADER_BAR_LINK_URL =
            ConfigurationAttributes.newStringAttribute("MIT_HEADER_BAR_LINK_URL")
                    .defaultValue("").build();
}
