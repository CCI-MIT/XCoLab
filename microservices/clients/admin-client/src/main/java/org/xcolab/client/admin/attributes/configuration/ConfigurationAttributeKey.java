package org.xcolab.client.admin.attributes.configuration;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.util.attributes.AttributeGetter;
import org.xcolab.util.attributes.i18n.LocalizableAttributeGetter;
import org.xcolab.util.attributes.transformers.AttributeTransformers;
import org.xcolab.util.attributes.wrappers.TransformedAttribute;
import org.xcolab.util.enums.theme.ColabTheme;

import java.util.Collections;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Pattern;

public final class ConfigurationAttributeKey {

    private static final String EMPTY_JAVASCRIPT_OBJECT = "{}";

    private ConfigurationAttributeKey() {
    }

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
    public static final AttributeGetter<String> COLAB_LONG_NAME =
            ConfigurationAttributes.newStringAttribute("COLAB_LONG_NAME")
                    .withCache()
                    .defaultValue(COLAB_NAME)
                    .build();
    public static final AttributeGetter<String> COLAB_SHORT_NAME =
            ConfigurationAttributes.newStringAttribute("COLAB_SHORT_NAME")
                    .withCache().build();

    public static final AttributeGetter<String> COLAB_URL_PRODUCTION =
            ConfigurationAttributes.newStringAttribute("COLAB_URL_PRODUCTION")
                    .map(AttributeTransformers.addDefaultScheme())
                    .defaultValue(PlatformAttributeKey.COLAB_URL)
                    .build();
    public static final AttributeGetter<String> BLOG_URL =
            ConfigurationAttributes.newStringAttribute("BLOG_URL")
                    .map(AttributeTransformers.addDefaultScheme())
                    .defaultValue("")
                    .build();
    public static final AttributeGetter<String> BLOG_ADMIN_URL =
            ConfigurationAttributes.newStringAttribute("BLOG_ADMIN_URL")
                    .map(AttributeTransformers.addDefaultScheme())
                    .defaultValue("")
                    .build();
    public static final AttributeGetter<ColabTheme> ACTIVE_THEME =
            ConfigurationAttributes.newEnumAttribute("ACTIVE_THEME", ColabTheme.class)
                    .withCache()
                    .build();
    public static final AttributeGetter<Long> WIKI_CONTENT_FOLDER_ID =
            ConfigurationAttributes.newLongAttribute("WIKI_CONTENT_FOLDER_ID")
                    .defaultValue(3L)
                    .withCache()
                    .build();

    public static final AttributeGetter<Boolean> REGISTRATION_IS_OPEN =
            ConfigurationAttributes.newBooleanAttribute("REGISTRATION_IS_OPEN")
                    .defaultValue(true)
                    .build();

    public static final AttributeGetter<Long> DEFAULT_CONTEST_TYPE_ID =
            ConfigurationAttributes.newLongAttribute("DEFAULT_CONTEST_TYPE_ID")
                    .defaultValue(0L)
                    .build();
    public static final AttributeGetter<Long> DEFAULT_CONTEST_SCHEDULE_ID =
            ConfigurationAttributes.newLongAttribute("DEFAULT_CONTEST_SCHEDULE_ID")
                    .defaultValue(0L)
                    .build();
    public static final AttributeGetter<Long> DEFAULT_CONTEST_TEMPLATE_ID =
            ConfigurationAttributes.newLongAttribute("DEFAULT_CONTEST_TEMPLATE_ID")
                    .defaultValue(0L)
                    .build();
    public static final AttributeGetter<String> DEFAULT_TIME_ZONE_ID =
            ConfigurationAttributes.newStringAttribute("DEFAULT_TIME_ZONE_ID")
                    .defaultValue(TimeZone.getDefault().getDisplayName())
                    .build();

    public static final AttributeGetter<String> TYPEKIT_KIT_ID =
            ConfigurationAttributes.newStringAttribute("TYPEKIT_KIT_ID")
                    .defaultValue("")
                    .build();

    public static final AttributeGetter<String> TYPEKIT_KIT_ID_LOCALHOST =
            ConfigurationAttributes.newStringAttribute("TYPEKIT_KIT_ID_LOCALHOST")
                    .defaultValue(TYPEKIT_KIT_ID)
                    .build();

    public static final AttributeGetter<String> PINGDOM_RUM_ID =
            ConfigurationAttributes.newStringAttribute("PINGDOM_RUM_ID")
                    .defaultValue("")
                    .build();

    public static final AttributeGetter<String> GOOGLE_ANALYTICS_KEY =
            ConfigurationAttributes.newStringAttribute("GOOGLE_ANALYTICS_KEY")
                    .defaultValue("")
                    .build();

    // Social media configuration
    public static final AttributeGetter<String> GOOGLE_URL =
            ConfigurationAttributes.newStringAttribute("GOOGLE_URL")
                    .defaultValue("")
                    .build();

    public static final AttributeGetter<String> GOOGLE_AUTH_CLIENT_ID =
            ConfigurationAttributes.newStringAttribute("GOOGLE_AUTH_CLIENT_ID")
                    .defaultValue("")
                    .build();
    public static final AttributeGetter<String> GOOGLE_AUTH_CLIENT_SECRET =
            ConfigurationAttributes.newStringAttribute("GOOGLE_AUTH_CLIENT_SECRET")
                    .build();
    public static final AttributeGetter<Boolean> GOOGLE_SSO_IS_ACTIVE =
            ConfigurationAttributes.newBooleanAttribute("GOOGLE_SSO_IS_ACTIVE")
                    .defaultValue(TransformedAttribute.of(GOOGLE_AUTH_CLIENT_ID, StringUtils::isNotEmpty))
                    .build();

    public static final AttributeGetter<String> GOOGLE_RECAPTCHA_SITE_KEY =
            ConfigurationAttributes.newStringAttribute("GOOGLE_RECAPTCHA_SITE_KEY")
                    .defaultValue("")
                    .build();
    public static final AttributeGetter<String> GOOGLE_RECAPTCHA_SITE_SECRET_KEY =
            ConfigurationAttributes.newStringAttribute("GOOGLE_RECAPTCHA_SITE_SECRET_KEY")
                    .build();
    public static final AttributeGetter<Boolean> GOOGLE_RECAPTCHA_IS_ACTIVE =
            ConfigurationAttributes.newBooleanAttribute("GOOGLE_RECAPTCHA_IS_ACTIVE")
                    .defaultValue(TransformedAttribute.of(GOOGLE_RECAPTCHA_SITE_KEY, StringUtils::isNotEmpty))
                    .build();

    public static final AttributeGetter<String> FACEBOOK_URL =
            ConfigurationAttributes.newStringAttribute("FACEBOOK_URL")
                    .defaultValue("")
                    .build();
    public static final AttributeGetter<String> FACEBOOK_APPLICATION_ID =
            ConfigurationAttributes.newStringAttribute("FACEBOOK_APPLICATION_ID")
                    .defaultValue("")
                    .build();
    public static final AttributeGetter<String> FACEBOOK_APPLICATION_SECRET =
            ConfigurationAttributes.newStringAttribute("FACEBOOK_APPLICATION_SECRET")
                    .build();
    public static final AttributeGetter<Boolean> FACEBOOK_SSO_IS_ACTIVE =
            ConfigurationAttributes.newBooleanAttribute("FACEBOOK_SSO_IS_ACTIVE")
                    .defaultValue(TransformedAttribute.of(FACEBOOK_APPLICATION_ID, StringUtils::isNotEmpty))
                    .build();
    public static final AttributeGetter<Boolean> FACEBOOK_VERIFIED_REQUIRED =
            ConfigurationAttributes.newBooleanAttribute("FACEBOOK_VERIFIED_REQUIRED")
                    .defaultValue(false)
                    .build();

    public static final AttributeGetter<String> TWITTER_URL =
            ConfigurationAttributes.newStringAttribute("TWITTER_URL")
                    .defaultValue("")
                    .build();

    public static final AttributeGetter<String> YOUTUBE_URL =
            ConfigurationAttributes.newStringAttribute("YOUTUBE_URL")
                    .defaultValue("")
                    .build();

    public static final AttributeGetter<String> LINKEDIN_URL =
            ConfigurationAttributes.newStringAttribute("LINKEDIN_URL")
                    .defaultValue("")
                    .build();

    public static final AttributeGetter<String> STORIFY_URL =
            ConfigurationAttributes.newStringAttribute("STORIFY_URL")
                    .defaultValue("")
                    .build();


    //MyEmma configuration
    public static final AttributeGetter<String> MY_EMMA_ACCOUNT_ID =
            ConfigurationAttributes.newStringAttribute("MY_EMMA_ACCOUNT_ID")
                    .defaultValue("")
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
    public static final AttributeGetter<Boolean> IS_MY_EMMA_ACTIVE =
            ConfigurationAttributes.newBooleanAttribute("IS_MY_EMMA_ACTIVE")
                    .defaultValue(TransformedAttribute.of(MY_EMMA_ACCOUNT_ID, StringUtils::isNotEmpty))
                    .build();

    //Messaging settings
    public static final AttributeGetter<Boolean> MESSAGING_EMAIL_ON_RECEIPT_DEFAULT =
            ConfigurationAttributes.newBooleanAttribute("MESSAGING_EMAIL_ON_RECEIPT_DEFAULT")
                    .defaultValue(true)
                    .build();
    public static final AttributeGetter<Boolean> MESSAGING_EMAIL_ON_ACTIVITY_DEFAULT =
            ConfigurationAttributes.newBooleanAttribute("MESSAGING_EMAIL_ON_ACTIVITY_DEFAULT")
                    .defaultValue(true)
                    .build();
    public static final AttributeGetter<Boolean> MESSAGING_DAILY_DIGEST_DEFAULT =
            ConfigurationAttributes.newBooleanAttribute("MESSAGING_DAILY_DIGEST_DEFAULT")
                    .defaultValue(true)
                    .build();
    public static final AttributeGetter<List<String>> MESSAGING_SPAM_ALERT_EMAILS =
            ConfigurationAttributes.newListAttribute("MESSAGING_SPAM_ALERT_EMAILS", s -> s)
            .defaultValue(Collections.emptyList())
            .build();
    public static final AttributeGetter<Boolean> MESSAGING_SEND_TRANSACTION_EMAILS =
            ConfigurationAttributes.newBooleanAttribute("MESSAGING_SEND_TRANSACTION_EMAILS")
            .defaultValue(true)
            .build();

    public static final AttributeGetter<Long> DAILY_DIGEST_TRIGGER_HOUR =
            ConfigurationAttributes.newLongAttribute("DAILY_DIGEST_TRIGGER_HOUR")
                    .defaultValue(1L)
                    .build();

    //lastEmailNotification
    public static final AttributeGetter<String> DAILY_DIGEST_LAST_EMAIL_NOTIFICATION =
            ConfigurationAttributes.newStringAttribute("DAILY_DIGEST_LAST_EMAIL_NOTIFICATION")
                    .defaultValue("")
                    .build();
    //Voting configuration

    /**
     * Comma-separated list of regex patterns for emails that don't require manual verification.
     *
     * Example: @example\.com$,@[.a-zA-Z0-9-]+\.example.com$
     */
    public static final AttributeGetter<List<Pattern>> VOTING_EMAIL_VERIFICATION_WHITELIST =
            ConfigurationAttributes.newListAttribute("VOTING_EMAIL_VERIFICATION_WHITELIST",
                    Pattern::compile)
                    .defaultValue(Collections.emptyList())
                    .build();
    /**
     * Comma-separated list of regex patterns for emails that are immediately invalidated.
     *
     * Example: @example\.com$,@[.a-zA-Z0-9-]+\.example.com$
     */
    public static final AttributeGetter<List<Pattern>> VOTING_EMAIL_VERIFICATION_BLACKLIST =
            ConfigurationAttributes.newListAttribute("VOTING_EMAIL_VERIFICATION_BLACKLIST",
                    Pattern::compile)
                    .defaultValue(Collections.emptyList())
                    .build();

    //Image upload help messages
    public static final AttributeGetter<String> IMAGE_UPLOAD_EXTERNAL_SERVICE_URL =
            ConfigurationAttributes.newStringAttribute("IMAGE_UPLOAD_EXTERNAL_SERVICE_URL")
                    .build();
    public static final LocalizableAttributeGetter<String> IMAGE_UPLOAD_HELP_TEXT =
            ConfigurationAttributes.newLocalizedStringAttribute("IMAGE_UPLOAD_HELP_TEXT")
                    .buildLocalizable();

    //Shared CoLab configuration
    public static final AttributeGetter<Boolean> IS_SHARED_COLAB =
            ConfigurationAttributes.newBooleanAttribute("IS_SHARED_COLAB")
                    .defaultValue(false)
                    .build();
    public static final AttributeGetter<String> SHARED_COLAB_NAMESPACE =
            ConfigurationAttributes.newStringAttribute("SHARED_COLAB_NAMESPACE")
                    .build();
    public static final AttributeGetter<String> PARTNER_COLAB_NAME =
            ConfigurationAttributes.newStringAttribute("PARTNER_COLAB_NAME")
                    .build();
    public static final AttributeGetter<String> PARTNER_COLAB_NAMESPACE =
            ConfigurationAttributes.newStringAttribute("PARTNER_COLAB_NAMESPACE")
                    .build();
    public static final AttributeGetter<String> PARTNER_COLAB_ADDRESS =
            ConfigurationAttributes.newStringAttribute("PARTNER_COLAB_ADDRESS")
                    .map(AttributeTransformers.addDefaultScheme())
                    .build();


    //Social media share text
    public static final LocalizableAttributeGetter<String> OPEN_GRAPH_SHARE_TITLE =
            ConfigurationAttributes.newLocalizedStringAttribute("OPEN_GRAPH_SHARE_TITLE")
                    .defaultValue(COLAB_NAME)
                    .buildLocalizable();
    public static final LocalizableAttributeGetter<String> OPEN_GRAPH_SHARE_DESCRIPTION =
            ConfigurationAttributes.newLocalizedStringAttribute("OPEN_GRAPH_SHARE_DESCRIPTION")
                    .defaultValue("")
                    .buildLocalizable();
    public static final LocalizableAttributeGetter<String> META_PAGE_DESCRIPTION =
            ConfigurationAttributes.newLocalizedStringAttribute("META_PAGE_DESCRIPTION")
                    .defaultValue("")
                    .buildLocalizable();
    public static final LocalizableAttributeGetter<String> META_PAGE_DESCRIPTION_CONTESTS =
            ConfigurationAttributes.newLocalizedStringAttribute("META_PAGE_DESCRIPTION_CONTESTS")
                    .defaultValue(META_PAGE_DESCRIPTION)
                    .buildLocalizable();
    public static final LocalizableAttributeGetter<String> META_PAGE_KEYWORDS =
            ConfigurationAttributes.newLocalizedStringAttribute("META_PAGE_KEYWORDS")
                    .defaultValue("")
                    .buildLocalizable();


    //Impact tab configuration
    public static final AttributeGetter<Boolean> IMPACT_TAB_IS_ACTIVE =
            ConfigurationAttributes.newBooleanAttribute("IMPACT_TAB_IS_ACTIVE")
                    .defaultValue(false)
                    .build();
    public static final AttributeGetter<List<Long>> IMPACT_TAB_EXCLUDED_ONTOLOGY_TERM_IDS =
            ConfigurationAttributes.newIdListAttribute("IMPACT_TAB_EXCLUDED_ONTOLOGY_TERM_IDS")
                    .defaultValue(Collections.emptyList())
                    .build();


    //Misc features
    public static final LocalizableAttributeGetter<String> LOGIN_INFO_MESSAGE =
            ConfigurationAttributes.newLocalizedStringAttribute("LOGIN_INFO_MESSAGE")
                    .defaultValue("")
                    .buildLocalizable();
    public static final AttributeGetter<String> MEMBERS_DEFAULT_SORT_COLUMN =
            ConfigurationAttributes.newStringAttribute("MEMBERS_DEFAULT_SORT_COLUMN")
                    .defaultValue("")
                    .build();

    // Proposal feature flags
    public static final LocalizableAttributeGetter<String> PROPOSALS_SAVE_BUTTON_TEXT =
            ConfigurationAttributes.newLocalizedStringAttribute("PROPOSALS_SAVE_BUTTON_TEXT")
                    .defaultValue("SAVE and PUBLISH changes")
                    .buildLocalizable();
    public static final LocalizableAttributeGetter<String> PROPOSALS_SAVE_HELP_TEXT =
            ConfigurationAttributes.newLocalizedStringAttribute("PROPOSALS_SAVE_HELP_TEXT")
                    .defaultValue((String) null)
                    .buildLocalizable();
    public static final AttributeGetter<Boolean> PROPOSALS_SHOW_IMAGE_UPLOAD =
            ConfigurationAttributes.newBooleanAttribute("PROPOSALS_SHOW_IMAGE_UPLOAD")
                    .defaultValue(true)
                    .build();
    public static final AttributeGetter<Boolean> PROPOSALS_PICKER_DEFAULT_TAB_CONTESTS =
            ConfigurationAttributes.newBooleanAttribute("PROPOSALS_PICKER_DEFAULT_TAB_CONTESTS")
                    .defaultValue(true)
                    .build();
    public static final AttributeGetter<Boolean> PROPOSALS_USER_CAN_PROMOTE_TO_OWNER =
            ConfigurationAttributes.newBooleanAttribute("PROPOSALS_USER_CAN_PROMOTE_TO_OWNER")
                    .defaultValue(true)
                    .build();

    // Points
    public static final AttributeGetter<Boolean> POINTS_IS_ACTIVE =
            ConfigurationAttributes.newBooleanAttribute("POINTS_IS_ACTIVE")
                    .defaultValue(false)
                    .build();

    public static final LocalizableAttributeGetter<String> POINTS_HELP_TEXT =
            ConfigurationAttributes.newLocalizedStringAttribute("POINTS_HELP_TEXT")
                    .defaultValue("")
                    .buildLocalizable();

    //Misc feature flags
    public static final AttributeGetter<Boolean> BETA_RIBBON_SHOW =
            ConfigurationAttributes.newBooleanAttribute("BETA_RIBBON_SHOW")
                    .defaultValue(false)
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
                    .defaultValue(true)
                    .build();

    public static final AttributeGetter<Boolean> CONTESTS_ALLOW_OPEN_PROPOSALS =
            ConfigurationAttributes.newBooleanAttribute("CONTESTS_ALLOW_OPEN_PROPOSALS")
                    .defaultValue(true)
                    .build();
    public static final AttributeGetter<Boolean> CONTESTS_SHOW_PROPOSAL_EDIT_HELP_TEXT =
            ConfigurationAttributes.newBooleanAttribute("CONTESTS_SHOW_PROPOSAL_EDIT_HELP_TEXT")
                    .defaultValue(true)
                    .build();
    public static final AttributeGetter<Boolean> CONTESTS_SHOW_YEAR_WHEN_COMPLETED =
            ConfigurationAttributes.newBooleanAttribute("CONTESTS_SHOW_YEAR_WHEN_COMPLETED")
                    .defaultValue(true)
                    .build();
    public static final AttributeGetter<Boolean> FLAGGING_ALLOW_MEMBERS =
            ConfigurationAttributes.newBooleanAttribute("FLAGGING_ALLOW_MEMBERS")
                    .defaultValue(false)
                    .build();
    public static final AttributeGetter<Boolean> SHOW_CONTEST_COUNTDOWN =
            ConfigurationAttributes.newBooleanAttribute("SHOW_CONTEST_COUNTDOWN")
                    .defaultValue(true)
                    .build();
    public static final AttributeGetter<Boolean> FILTER_PROFANITY =
            ConfigurationAttributes.newBooleanAttribute("FILTER_PROFANITY")
                    .defaultValue(false)
                    .build();
    public static final AttributeGetter<Boolean> SHOW_CONTESTS_DISPLAY_OPTIONS =
            ConfigurationAttributes.newBooleanAttribute("SHOW_CONTESTS_DISPLAY_OPTIONS")
                    .defaultValue(true)
                    .build();
    public static final AttributeGetter<Boolean> GENERATE_SCREEN_NAME =
            ConfigurationAttributes.newBooleanAttribute("GENERATE_SCREEN_NAME")
                    .defaultValue(false)
                    .build();

    public static final AttributeGetter<Boolean> DISPLAY_FIRST_NAME_LAST_NAME =
            ConfigurationAttributes.newBooleanAttribute("DISPLAY_FIRST_NAME_LAST_NAME")
                    .defaultValue(false)
                    .build();


    //Configuration of Solve's header bar
    public static final AttributeGetter<Boolean> MIT_HEADER_BAR_SHOW =
            ConfigurationAttributes.newBooleanAttribute("MIT_HEADER_BAR_SHOW")
                    .defaultValue(false)
                    .build();
    public static final LocalizableAttributeGetter<String> MIT_HEADER_BAR_LINK_TEXT =
            ConfigurationAttributes.newLocalizedStringAttribute("MIT_HEADER_BAR_LINK_TEXT")
                    .defaultValue("")
                    .buildLocalizable();
    public static final AttributeGetter<String> MIT_HEADER_BAR_LINK_URL =
            ConfigurationAttributes.newStringAttribute("MIT_HEADER_BAR_LINK_URL")
                    .defaultValue("")
                    .build();

    //Configuration of Collection Cards
    public static final AttributeGetter<Boolean> COLAB_USES_CARDS =
            ConfigurationAttributes.newBooleanAttribute("COLAB_USES_CARDS")
                    .defaultValue(false)
                    .build();

    //Portlet preferences config
    public static final AttributeGetter<String> PORTLET_CONTACT_FORM_PREFERENCES =
            ConfigurationAttributes.newStringAttribute("PORTLET_CONTACT_FORM_PREFERENCES")
                    .build();

    public static final AttributeGetter<String> PORTLET_CONTESTS_PREFERENCES =
            ConfigurationAttributes.newStringAttribute("PORTLET_CONTESTS_PREFERENCES")
                    .build();

    public static final AttributeGetter<String> PORTLET_CAROUSEL_PREFERENCES =
            ConfigurationAttributes.newStringAttribute("PORTLET_CAROUSEL_PREFERENCES")
                    .defaultValue(EMPTY_JAVASCRIPT_OBJECT).build();

    public static final AttributeGetter<String> PORTLET_FEED_PREFERENCES =
            ConfigurationAttributes.newStringAttribute("PORTLET_FEED_PREFERENCES")
                    .build();

    public static final AttributeGetter<String> PORTLET_RANDOM_PROPOSALS_PREFERENCES =
            ConfigurationAttributes.newStringAttribute("PORTLET_RANDOM_PROPOSALS_PREFERENCES")
                    .build();

    public static final AttributeGetter<String> PORTLET_STAFF_MEMBERS_PREFERENCES =
            ConfigurationAttributes.newStringAttribute("PORTLET_STAFF_MEMBERS_PREFERENCES")
                    .build();

    public static final AttributeGetter<String> PORTLET_PROPOSALS_PREFERENCES =
            ConfigurationAttributes.newStringAttribute("PORTLET_PROPOSALS_PREFERENCES")
                    .build();

    public static final AttributeGetter<String> PORTLET_DISCUSSION_PREFERENCES =
            ConfigurationAttributes.newStringAttribute("PORTLET_DISCUSSION_PREFERENCES")
                    .build();

    public static final AttributeGetter<Long> LANDING_PAGE_BANNER_CONTENT_ARTICLE_ID =
            ConfigurationAttributes.newLongAttribute("LANDING_PAGE_BANNER_CONTENT_ARTICLE_ID")
                    .build();

    public static final AttributeGetter<Long> LANDING_PAGE_BOTTOM_CONTENT_ARTICLE_ID =
            ConfigurationAttributes.newLongAttribute("LANDING_PAGE_BOTTOM_CONTENT_ARTICLE_ID")
                    .build();

    public static final AttributeGetter<Long> FOOTER_CONTENT_ARTICLE_ID =
            ConfigurationAttributes.newLongAttribute("FOOTER_CONTENT_ARTICLE_ID")
                    .build();

    public static final AttributeGetter<Long> MEMBERS_CONTENT_ARTICLE_ID =
            ConfigurationAttributes.newLongAttribute("MEMBERS_CONTENT_ARTICLE_ID")
                    .build();

    public static final AttributeGetter<Long> DISCUSSION_CONTENT_ARTICLE_ID =
            ConfigurationAttributes.newLongAttribute("DISCUSSION_CONTENT_ARTICLE_ID")
                    .build();

    public static final AttributeGetter<Boolean> IS_I18N_ACTIVE =
            ConfigurationAttributes.newBooleanAttribute("IS_I18N_ACTIVE")
                    .defaultValue(false)
                    .build();

    public static final AttributeGetter<Boolean> SNP_IS_ACTIVE =
            ConfigurationAttributes.newBooleanAttribute("SNP_IS_ACTIVE")
                    .defaultValue(false)
                    .build();

    public static final LocalizableAttributeGetter<String> SNP_CONSENT_FORM_TEXT =
            ConfigurationAttributes.newLocalizedStringAttribute("SNP_CONSENT_FORM_TEXT")
                    .buildLocalizable();

    public static final LocalizableAttributeGetter<String> SNP_EXAMPLE_TEXT =
            ConfigurationAttributes.newLocalizedStringAttribute("SNP_EXAMPLE_TEXT")
                    .buildLocalizable();

    public static final AttributeGetter<Boolean> NAVBAR_SHOW_ICONS =
            ConfigurationAttributes.newBooleanAttribute("NAVBAR_SHOW_ICONS")
                    .defaultValue(true)
                    .build();
}
