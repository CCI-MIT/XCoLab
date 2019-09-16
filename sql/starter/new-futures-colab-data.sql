-- Starter SQL statements for the New Futures CoLab

REPLACE INTO admin__configuration_attribute (name, additional_id, numeric_value, string_value, real_value) VALUES ('COLAB_NAME', 0, 1, 'Futures CoLab', 0);
REPLACE INTO admin__configuration_attribute (name, additional_id, numeric_value, string_value, real_value) VALUES ('ACTIVE_THEME', 0, 1, 'FUTURES_COLAB', 0);
-- REPLACE INTO admin__configuration_attribute (name, additional_id, numeric_value, string_value, real_value) VALUES ('PROPOSALS_COMMENTS_IN_SEPARATE_TAB', 0, 0, '', 0);
-- REPLACE INTO admin__configuration_attribute (name, additional_id, numeric_value, string_value, real_value) VALUES ('IS_I18N_ACTIVE', 0, 1, '', 0);
-- REPLACE INTO admin__configuration_attribute (name, additional_id, numeric_value, string_value, real_value) VALUES ('PROPOSALS_PHASE_CLOSED_SORT_ORDER', 0, 1, 'PROPOSAL_ID', 0);
-- REPLACE INTO admin__configuration_attribute (name, additional_id, numeric_value, string_value, real_value) VALUES ('PROPOSALS_PHASE_VOTING_SORT_ORDER', 0, 1, 'PROPOSAL_ID', 0);
REPLACE INTO admin__configuration_attribute (name, additional_id, numeric_value, string_value, real_value) VALUES ('FOOTER_CONTENT_ARTICLE_ID', 0, 8, '', 8);

REPLACE INTO content__content_article (id, author_user_id, created_at, max_version_id, folder_id, edit_role_group_id, view_role_group_id, visible) VALUES (8, 10144, '2016-05-11 09:21:08', 7, 2, null, null, 1);
REPLACE INTO content__content_article_version (id, article_id, folder_id, author_user_id, created_at, title, content) VALUES (8, 8, 2, 10144, '2016-07-19 12:38:47', 'Footer', '<div class="row py-3">
<div class="col-md-6 my-2"><a href="https://cci.mit.edu/" target="_blank"><img alt="MIT CCI" height="54" src="/images/logos/mit-cci.png" /> </a></div>

<div class="col-md-6 my-2">
<div class="media">
<div class="media-body c-Footer__text">Your use of the Ley del Cancer CoLab is subject to our <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">Creative Commons License</a>, <a href="/wiki/Privacy+policy">Privacy Statement</a>, and other <a href="/wiki/Terms+of+use">Terms of Use</a>.</div>
<img alt="CC BY NC SA" class="align-self-center ml-2" height="31" src="/images/logos/cc-by-nc-sa.svg" width="88" /></div>
</div>
</div>
<script> $(function() {
// link navbar button to contest directly
$($(".nav-item a")[1]).attr("href","/contests/2019/inteligencia-colectiva-para-la-ley-nacional-del-cancer");

// remove the slash from the proposals table
$(".c-TableGrid__header .col-12").contents().filter(function() {return this.nodeType == 3;}).remove();
$(".propname-authors").contents().filter(function() {return this.nodeType == 3;}).remove();

// increase the assigned area for the proposal name in the proposal table
$(".c-TableGrid__header .col-xl-6").addClass("col-xl-8").removeClass("col-xl-6");
$(".c-TableGrid__row--continued .col-xl-6").addClass("col-xl-8").removeClass("col-xl-6");

console.log("done");
}) </script>');
