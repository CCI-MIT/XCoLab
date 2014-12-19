/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */
package com.ext.portlet.models;




/**
 * Class holding all constants that are used across Debates portlet.
 *
 * @author janusz.p
 * @version 1.0
 *
 */
public class DebatesConstants {

    /**
     * It's a utility class, it shouldn't be instantiated.
     */
    private DebatesConstants() {
    }

    /**
     * Represents base message type (it should be set to value that won't
     * conflict with message flag types in Message Boards core).
     */
    private static final int MSG_TYPE_BASE = 10000;

    /**
     * Normal message type. Used for to flag a debate message.
     */
    public static final int NORMAL_MSG_TYPE = MSG_TYPE_BASE;

    /**
     * Issue message type. Used for to flag a debate message.
     */
    public static final int ISSUE_MSG_TYPE = MSG_TYPE_BASE + 1;

    /**
     * Position message type. Used for to flag a debate message.
     */
    public static final int POSITION_MSG_TYPE = MSG_TYPE_BASE + 2;

    /**
     * Argument Pro message type. Used for to flag a debate message.
     */
    public static final int ARGUMENT_PRO_MSG_TYPE = MSG_TYPE_BASE + 3;

    /**
     * Argument Con message type. Used for to flag a debate message.
     */
    public static final int ARGUMENT_CON_MSG_TYPE = MSG_TYPE_BASE + 4;

    /**
     * Normal message type name.
     */
    public static final String NORMAL_MSG_TYPE_NAME = "message";

    /**
     * Argument message type name.
     */
    public static final String ARGUMENT_MSG_TYPE_NAME = "argument";

    /**
     * Issue message type name.
     */
    public static final String ISSUE_MSG_TYPE_NAME = "issue";

    /**
     * Position message type name.
     */
    public static final String POSITION_MSG_TYPE_NAME = "position";

    /**
     * Argument pro message type name.
     */
    public static final String ARGUMENT_PRO_MSG_TYPE_NAME = "argument-pro";

    /**
     * Argument con message type name.
     */
    public static final String ARGUMENT_CON_MSG_TYPE_NAME = "argument-con";

    /**
     * Name of request attribute used to pass message type.
     */
    public static final String MESSAGE_TYPE = "messageType";

    /**
     * Name of request attribute used to pass message type name.
     */
    public static final String MESSAGE_TYPE_NAME = "messageTypeName";

    /**
     * Name of request attribute used to pass message id.
     */
    public static final String MESSAGE_ID = "messageId";

    /**
     * Name of request attribute used to pass parent message id.
     */
    public static final String PARENT_MESSAGE_ID = "parentMessageId";

    /**
     * Name of request attribute used to pass edited entity type.
     */
    public static final String EDITED_ENTITY_TYPE = "editedEntityType";

    /**
     * Question entity type name.
     */
    public static final String QUESTION_ENTITY_TYPE = "issue";

    /**
     * Topic entity type name.
     */
    public static final String TOPIC_ENTITY_TYPE = "topic";

    /**
     * Name of request attribute used to pass entity id.
     */
    public static final String ENTITY_ID = "categoryId";

    /**
     * Name of request attribute used to pass topic id.
     */
    public static final String TOPIC_ID = "topicId";

    /**
     * Name of request attribute used to pass topic.
     */
    public static final String TOPIC = "topic";

    /**
     * Name of request attribute used to pass issue id.
     */
    public static final String ISSUE_ID = "categoryId";

    /**
     * Name of request attribute used to pass category id.
     */
    public static final String CATEGORY_ID = "categoryId";

    /**
     * Name of request attribute used to pass url for redirect.
     */
    public static final String REDIRECT = "redirect";

    /**
     * Name of request attribute used to pass question.
     */
    public static final String QUESTION = "name";

    /**
     * Name of request attribute used to pass question.
     */
    public static final String REQUIRES_TYPE = "requiresType";

    /**
     * Name of request attribute used to pass debate index category id.
     */
    public static final String DEBATE_INDEX_CATEGORY_ID = "debateIndexCategoryId";

    /**
     * Name of request attribute used to pass debate discussion category id.
     */
    public static final String DEBATE_DISCUSSION_CATEGORY_ID = "debateDiscussionCategoryId";

    /**
     * Name of request attribute used to pass topics category.
     */
    public static final String TOPICS_CATEGORY = "topicsCategory";

    /**
     * Name of request attribute used to pass parent category id.
     */
    public static final String PARENT_CATEGORY_ID = "parentCategoryId";

    /**
     * Name of request attribute used to pass discussion category id.
     */
    public static final String DISCUSSION_CATEGORY = "discussionCategory";

    /**
     * Name of request attribute used to pass debate category.
     */
    public static final String DEBATE_CATEGORY = "debateCategory";

    /**
     * Name of request attribute used to pass main category.
     */
    public static final String MAIN_CATEGORY = "mainCategory";

    /**
     * Name of request attribute used to pass main category candidates.
     */
    public static final String MAIN_CATEGORY_CANDIDATES = "mainCategoryCandidates";

    /**
     * Name of request attribute used to pass positions.
     */
    public static final String POSITIONS = "positions";

    /**
     * Name of request attribute used to pass parent messages.
     */
    public static final String PARENT_MESSAGES = "parentMessages";

    /**
     * Name of request attribute used to pass message.
     */
    public static final String MESSAGE = "message";

    /**
     * Name of request attribute used to pass breadcrumb.
     */
    public static final String BREADCRUMB = "breadcrumb";

    /**
     * Name of request attribute used to pass parent message.
     */
    public static final String PARENT_MESSAGE = "parentMessage";

    /**
     * Name of request attribute used to pass debate message.
     */
    public static final String DEBATE_MESSAGE = "debateMessage";

    /**
     * Name of request attribute used to pass base category id.
     */
    public static final String BASE_CATEGORY_ID = "baseCategoryId";

    /**
     * Name of request attribute used to pass viewed tab name.
     */
    public static final String VIEW_DEBATES_INDEX_TABS = "ModelDebates";

    /**
     * Name of request attribute used to pass viewed tab name.
     */
    public static final String MODEL_OUTER_TABS = "ModelOuterTab";

    /**
     * Name of request attribute used to pass viewed tab name.
     */
    public static final String MODEL_OUTER_TABS_DEBATE = "ModelOuterTabDebate";
    /**
     * Name of request attribute used to pass viewed tab name.
     */
    public static final String MODEL_OUTER_TABS_MODEL = "ModelOuterTabModel";


    /**
     * Name of debate index tab.
     */
    public static final String DEBATE_INDEX_TAB = "debate-index";

    /**
     * Name of debate summary tab.
     */
    public static final String DEBATE_SUMMARY_TAB = "debate-summary";

    /**
     * Name of discussion tab.
     */
    public static final String DISCUSSION_TAB = "discussion";

    /**
     * Forward name for viewing discussion.
     */
    public static final String VIEW_DISCUSSION_FORWARD = "portlet.ext.models.view_discussion";

    /**
     * Forward name for main view.
     */
    public static final String VIEW_ACTION_FORWARD = "portlet.ext.models.view_debates";


    /**
     * Forward name for editing issue.
     */
    public static final String EDIT_ISSUE_FORWARD = "portlet.ext.models.edit_issue";

    /**
     * Forward name for moving a message.
     */
    public static final String MOVE_MESSAGE_FORWARD = "portlet.ext.models.move_message";

    /**
     * Forward name for viewing a debate.
     */
    public static final String VIEW_DEBATE_FORWARD = "portlet.ext.models.view_debate";

    /**
     * Name of view that displays debate index.
     */
    public static final String DEBATE_INDEX_VIEW = "debateIndexView";

    /**
     * Name of view that displays debate summary.
     */
    public static final String DEBATE_SUMMARY_VIEW = "debateSummaryView";

    /**
     * Name of request attribute used to pass .
     */
    public static final String ISSUE_DEBATE_CATEGORY_NAME = "Debate";

    /**
     * Name of request attribute used to pass .
     */
    public static final String ISSUE_DISCUSSION_CATEGORY_NAME = "Discussion";

    /**
     * Path to jsp file containing portlet configuration.
     */
    public static final String CONFIGURATION_JSP = "/html/portlet/ext/models/configuration.jsp";

    /**
     * Name of discussion posts parameter.
     */
    public static final String DISCUSSION_POSTS = "discussionPosts";

    /**
     * Name of debate posts parameter.
     */
    public static final String DEBATE_POSTS = "debatePosts";

    /**
     * Name of debate last post parameter.
     */
    public static final String DEBATE_LAST_POST_DATE = "debateLastPostDate";

    /**
     * Name of portlet resource parameter.
     */
    public static final String PORTLET_RESOURCE = "portletResource";

    /**
     * Name of default category for Debates portlet.
     */
    public static final String DEFAULT_DEBATES_CATEGORY_NAME = "ModelDebates";

    /**
     * Name of attribute indicating that user wants to see message preview.
     */
    public static final String PREVIEW = "preview";

    /**
     * Array of supported message types.
     */
    public static final int[] MESSAGE_TYPES = { DebatesConstants.ISSUE_MSG_TYPE, DebatesConstants.POSITION_MSG_TYPE,
    	DebatesConstants.ARGUMENT_PRO_MSG_TYPE, DebatesConstants.ARGUMENT_CON_MSG_TYPE };

    /**
     * Name of request parameter representing list of topics.
     */
    public static final String TOPICS = "debates";
}
