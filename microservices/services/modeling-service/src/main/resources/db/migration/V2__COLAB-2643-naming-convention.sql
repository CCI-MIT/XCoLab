rename table xcolab_ModelCategory to modeling__model_category;
rename table xcolab_ModelDiscussion to modeling__model_discussion;
rename table xcolab_ModelGlobalPreference to modeling__model_global_preference;
rename table xcolab_ModelInputGroup to modeling__model_input_group;
rename table xcolab_ModelInputItem to modeling__model_input_item;
rename table xcolab_ModelOutputChartOrder to modeling__model_output_chart_order;
rename table xcolab_ModelOutputItem to modeling__model_output_item;
rename table xcolab_ModelPosition to modeling__model_position;

ALTER TABLE modeling__model_category CHANGE modelCategoryPK id bigint(20) NOT NULL;
ALTER TABLE modeling__model_category CHANGE modelCategoryName name varchar(256);
ALTER TABLE modeling__model_category CHANGE modelCategoryDescription description varchar(2048);
ALTER TABLE modeling__model_category CHANGE modelCategoryDisplayWeight display_weight int(11);

ALTER TABLE modeling__model_discussion CHANGE modelDiscussionId id bigint(20) NOT NULL;
ALTER TABLE modeling__model_discussion CHANGE modelId model_id bigint(20);
ALTER TABLE modeling__model_discussion CHANGE categoryId category_id bigint(20);

ALTER TABLE modeling__model_global_preference CHANGE modelGlobalPreferencePK id bigint(20) NOT NULL;
ALTER TABLE modeling__model_global_preference CHANGE modelId model_id bigint(20);
ALTER TABLE modeling__model_global_preference CHANGE expertEvaluationPageId expert_evaluation_page_id bigint(20);
ALTER TABLE modeling__model_global_preference CHANGE modelCategoryId model_category_id bigint(20);
ALTER TABLE modeling__model_global_preference CHANGE usesCustomInputs uses_custom_inputs tinyint(4);
ALTER TABLE modeling__model_global_preference CHANGE customInputsDefinition custom_inputs_definition longtext;

ALTER TABLE modeling__model_input_group CHANGE modelInputGroupPK id bigint(20) NOT NULL;
ALTER TABLE modeling__model_input_group CHANGE modelId model_id bigint(20);
ALTER TABLE modeling__model_input_group CHANGE nameAndDescriptionMetaDataId name_and_description_meta_data_id bigint(20);
ALTER TABLE modeling__model_input_group CHANGE displayItemOrder display_item_order int(11);
ALTER TABLE modeling__model_input_group CHANGE groupType group_type varchar(256);
ALTER TABLE modeling__model_input_group CHANGE parentGroupPK parent_group_id bigint(20);

ALTER TABLE modeling__model_input_item CHANGE modelInputItemPK id bigint(20) NOT NULL;
ALTER TABLE modeling__model_input_item CHANGE modelId model_id bigint(20);
ALTER TABLE modeling__model_input_item CHANGE modelInputItemID model_input_item_id bigint(20);
ALTER TABLE modeling__model_input_item CHANGE modelGroupId model_group_id bigint(20);
ALTER TABLE modeling__model_input_item CHANGE displayItemOrder display_item_order int(11);
ALTER TABLE modeling__model_input_item CHANGE type_ type varchar(256);

ALTER TABLE modeling__model_output_chart_order CHANGE modelOutputChartOrderPK id bigint(20) NOT NULL;
ALTER TABLE modeling__model_output_chart_order CHANGE modelId model_id bigint(20);
ALTER TABLE modeling__model_output_chart_order CHANGE modelOutputLabel model_output_label varchar(1024);
ALTER TABLE modeling__model_output_chart_order CHANGE modelOutputChartOrder model_output_chart_order int(11);
ALTER TABLE modeling__model_output_chart_order CHANGE modelIndexRangePolicy model_index_range_policy varchar(2048);
ALTER TABLE modeling__model_output_chart_order CHANGE modelIndexRangeMessage model_index_range_message varchar(2048);
ALTER TABLE modeling__model_output_chart_order CHANGE modelIndexErrorPolicy model_index_error_policy varchar(2048);
ALTER TABLE modeling__model_output_chart_order CHANGE modelIndexErrorMessage model_index_error_message varchar(2048);
ALTER TABLE modeling__model_output_chart_order CHANGE modelChartIsVisible model_chart_is_visible tinyint(4);

ALTER TABLE modeling__model_output_item CHANGE modelOutputItemModifierPK model_output_item_modifier_pk bigint(20) NOT NULL;
ALTER TABLE modeling__model_output_item CHANGE modelId model_id bigint(20);
ALTER TABLE modeling__model_output_item CHANGE modelOutputItemId model_output_item_id bigint(20);
ALTER TABLE modeling__model_output_item CHANGE modelOutputItemOrder model_output_item_order int(11);
ALTER TABLE modeling__model_output_item CHANGE modelItemRangePolicy model_item_range_policy varchar(2048);
ALTER TABLE modeling__model_output_item CHANGE modelItemRangeMessage model_item_range_message varchar(2048);
ALTER TABLE modeling__model_output_item CHANGE modelItemErrorPolicy model_item_error_policy varchar(2048);
ALTER TABLE modeling__model_output_item CHANGE modelItemErrorMessage model_item_error_message varchar(2048);
ALTER TABLE modeling__model_output_item CHANGE modelItemLabelFormat model_item_label_format varchar(2048);
ALTER TABLE modeling__model_output_item CHANGE modelItemIsVisible model_item_is_visible tinyint(4);
ALTER TABLE modeling__model_output_item CHANGE itemType item_type varchar(256);
ALTER TABLE modeling__model_output_item CHANGE relatedOutputItem related_output_item bigint(20);

ALTER TABLE modeling__model_position CHANGE id_ id bigint(20) NOT NULL;
ALTER TABLE modeling__model_position CHANGE positionId position_id bigint(20);
ALTER TABLE modeling__model_position CHANGE modelId model_id bigint(20);
