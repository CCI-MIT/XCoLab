UPDATE `User_` SET `passwordModifiedDate` = NULL , `emailAddress` = 'test@climatecolab.org', `lastLoginDate` = NULL , `lastFailedLoginDate` = NULL , `lockoutDate` = NULL WHERE `User_`.`userId` =10115 LIMIT 1;

INSERT INTO `Contact_` (`contactId`, `companyId`, `userId`, `userName`, `createDate`, `modifiedDate`, `accountId`, `parentContactId`, `firstName`, `middleName`, `lastName`, `prefixId`, `suffixId`, `male`, `birthday`, `smsSn`, `aimSn`, `facebookSn`, `icqSn`, `jabberSn`, `msnSn`, `mySpaceSn`, `skypeSn`, `twitterSn`, `ymSn`, `employeeStatusId`, `employeeNumber`, `jobTitle`, `jobClass`, `hoursOfOperation`) VALUES ('185586', '10112', '10144', '', '2009-08-19 00:46:18', '2009-08-19 00:46:18', '10114', '0', 'Test', '', 'Test', '0', '0', '1', '1970-01-01 00:00:00', '', '', '', '', '', '', '', '', '', '', '', '', 'Test', '', '');

UPDATE `User_` SET `passwordModifiedDate` = NULL , `screenName` = '185585', `lastFailedLoginDate` = NULL , `lockoutDate` = NULL WHERE `User_`.`userId` =185585 LIMIT 1;


TRUNCATE TABLE MBBan;
TRUNCATE TABLE MBCategory;
TRUNCATE TABLE MBDiscussion;
TRUNCATE TABLE MBMailingList;
TRUNCATE TABLE MBMessage;
TRUNCATE TABLE MBMessageFlag;
TRUNCATE TABLE MBStatsUser;
TRUNCATE TABLE MBThread;


RENAME TABLE ActivitySubscription TO xcolab_ActivitySubscription;
RENAME TABLE Contest TO xcolab_Contest;
RENAME TABLE ContestDebate TO xcolab_ContestDebate;
RENAME TABLE ContestPhase TO xcolab_ContestPhase;
RENAME TABLE ContestPhaseColumn TO xcolab_ContestPhaseColumn;
RENAME TABLE ContestPhaseType TO xcolab_ContestPhaseType;
RENAME TABLE ContestTeamMember TO xcolab_ContestTeamMember;
RENAME TABLE DiscussionCategoryGroup TO xcolab_DiscussionCategoryGroup;
RENAME TABLE DiscussionCategory TO xcolab_DiscussionCategory;
RENAME TABLE DiscussionMessage TO xcolab_DiscussionMessage;
RENAME TABLE DiscussionMessageFlag TO xcolab_DiscussionMessageFlag;
RENAME TABLE Message TO xcolab_Message;
RENAME TABLE MessageRecipientStatus TO xcolab_MessageRecipientStatus;
RENAME TABLE MessagingUserPreferences TO xcolab_MessagingUserPreferences;
RENAME TABLE ModelDiscussion TO xcolab_ModelDiscussion;
RENAME TABLE ModelPosition TO xcolab_ModelPosition;
RENAME TABLE ModelGlobalPreference TO xcolab_ModelGlobalPreference;
RENAME TABLE ModelCategory TO xcolab_ModelCategory;
RENAME TABLE ModelInputGroup TO xcolab_ModelInputGroup;
RENAME TABLE ModelInputItem TO xcolab_ModelInputItem;
RENAME TABLE ModelOutputChartOrder TO xcolab_ModelOutputChartOrder;
RENAME TABLE ModelOutputItem TO xcolab_ModelOutputItem;
RENAME TABLE OntologySpace TO xcolab_OntologySpace;
RENAME TABLE OntologyTerm TO xcolab_OntologyTerm;
RENAME TABLE OntologyTermEntity TO xcolab_OntologyTermEntity;
RENAME TABLE FocusArea TO xcolab_FocusArea;
RENAME TABLE FocusAreaOntologyTerm TO xcolab_FocusAreaOntologyTerm;
RENAME TABLE PlanAttribute TO xcolab_PlanAttribute;
RENAME TABLE PlanPosition TO xcolab_PlanPosition;
RENAME TABLE PlansUserSettings TO xcolab_PlansUserSettings;
RENAME TABLE PlanVote TO xcolab_PlanVote;
RENAME TABLE PlanAttributeFilter TO xcolab_PlanAttributeFilter;
RENAME TABLE PlanPropertyFilter TO xcolab_PlanPropertyFilter;
RENAME TABLE PlanColumnSettings TO xcolab_PlanColumnSettings;
RENAME TABLE PlansFilterPosition TO xcolab_PlansFilterPosition;
RENAME TABLE PlanType TO xcolab_PlanType;
RENAME TABLE PlanTypeAttribute TO xcolab_PlanTypeAttribute;
RENAME TABLE PlanTypeColumn TO xcolab_PlanTypeColumn;
RENAME TABLE PlanItem TO xcolab_PlanItem;
RENAME TABLE PlanDescription TO xcolab_PlanDescription;
RENAME TABLE PlanMeta TO xcolab_PlanMeta;
RENAME TABLE PlanModelRun TO xcolab_PlanModelRun;
RENAME TABLE PlanPositions TO xcolab_PlanPositions;
RENAME TABLE PlanPositionItem TO xcolab_PlanPositionItem;
RENAME TABLE PlanFan TO xcolab_PlanFan;
RENAME TABLE PlanTeamHistory TO xcolab_PlanTeamHistory;
RENAME TABLE PlanSectionDefinition TO xcolab_PlanSectionDefinition;
RENAME TABLE PlanSection TO xcolab_PlanSection;
RENAME TABLE PlanSectionPlanMap TO xcolab_PlanSectionPlanMap;
RENAME TABLE PlanTemplate TO xcolab_PlanTemplate;
RENAME TABLE PlanTemplateSection TO xcolab_PlanTemplateSection;



UPDATE `xcolab_OntologyTerm` SET parentId = 0 WHERE parentId is null;
UPDATE `xcolab_DiscussionMessage` SET threadId = 0 WHERE threadId is null;

delete from ClassName_ where value LIKE '%com.ext.portlet.model%';
update ClassName_ set value = REPLACE(value, 'com.ext.portlet.plans', 'com.ext.portlet') WHERE value LIKE '%com.ext.portlet.plans%';
update ClassName_ set value = REPLACE(value, 'com.ext.portlet.discussions', 'com.ext.portlet') WHERE value LIKE '%com.ext.portlet.discussions%';
update ClassName_ set value = REPLACE(value, 'com.ext.portlet.Activity', 'com.ext.portlet') WHERE value LIKE '%com.ext.portlet.Activity%';
update ClassName_ set value = REPLACE(value, 'com.ext.portlet.contests', 'com.ext.portlet') WHERE value LIKE '%com.ext.portlet.contests%';
update ClassName_ set value = REPLACE(value, 'com.ext.portlet.messaging', 'com.ext.portlet') WHERE value LIKE '%com.ext.portlet.messaging%';
update ClassName_ set value = REPLACE(value, 'com.ext.portlet.models', 'com.ext.portlet') WHERE value LIKE '%com.ext.portlet.models%';
update ClassName_ set value = REPLACE(value, 'com.ext.portlet.ontology', 'com.ext.portlet') WHERE value LIKE '%com.ext.portlet.ontology%';
update ClassName_ set value = REPLACE(value, 'com.ext.portlet.Activity', 'com.ext.portlet') WHERE value LIKE '%com.ext.portlet.Activity%';
update ClassName_ set value = REPLACE(value, 'com.ext.portlet.Activity', 'com.ext.portlet') WHERE value LIKE '%com.ext.portlet.Activity%';

insert into Roles_Permissions SELECT 10119 as roleId, p.permissionId FROM Permission_ p left join Roles_Permissions rp on rp.roleId = 10119 and rp.permissionId = p.permissionId where rp.roleId is Null and p.actionId = 'VIEW';


UPDATE `xcolab_lportal`.`WikiPage` SET `content` = '==Contents of this page==
* Introduction
* What are the goals of the contest?
* Who can participate?
* What does an entry in the contest contain?
* How will the contest be structured?
* How do teams work?
* How can people show their support for a proposal before the final round?
* How can users contribute new simulation models?
* How does the CoLab community deal with different points of view?
* How will proposals be judged?
* What are the prizes?
* How will the results of the contest influence policy?

==Introduction==

These are the rules for the 2010 Climate CoLab Contest. Please read these Rules in full. You will be required to accept them when you enter. 

The rules may be supplemented or revised by posting  supplements or revisions to this page and by email notification to members of teams that are entered in the contest.

This short contest is a trial run (a "beta test") for what is expected to be a series of annual Climate CoLab Contests in future years. 

In these contests, people from all over the world will work together to develop proposals for what humanity should do about global climate change.

==What are the goals of the contest?==
The primary goals of the Climate CoLab Contests are to: 
* Harness the collective intelligence of thousands of people around the world to create proposals for what humanity should do about global climate change. Rather than focusing on specific technologies or geographical regions, these contests are focused on the macro-engineering challenge of designing global political, economic, social, and technological systems to deal with the possibilities of global climate change. By helping scientists, policy makers, business people, and concerned citizens think constructively together about this problem, we believe it will be possible to create better solutions than would ever have been developed otherwise.
* Help to educate the general public about the real issues involved in global climate change.  By trying to solve the problem themselves, based on the best current scientific knowledge, we believe many people will understand the issues better and be better able to participate in political decision-making in their countries and communities.
* Provide a large-scale test of new collective intelligence approaches. Examples like Wikipedia and Linux show that it''s now possible for very large groups of people to work together on very complex problems in ways that would have been impossible a few years ago.  We believe the new model-based approach to collaborative problem solving being used here may be useful for other large-scale problems in the future.

The goals of this contest are not to advocate any particular position or point of view about global climate change.  Instead, we hope to provide a neutral forum where the best ideas and information can be shared.

==Who can participate?==

The contest is open to anyone in the world, regardless of age, nationality, or political viewpoint. 

However, this Contest is void where prohibited by law.  It is your responsibility to check with your local laws to make sure that this Contest does not violate any applicable law or ordinance, and to make sure that you are eligible to participate.  

If you are under 18 years old, you will need your parent or legal guardian to register on the Climate CoLab website and submit your entry for you.

You may submit projects that you have previously published or exhibited so long as they conform to the entry guidelines and the Rules of this Contest. 

==What does an entry in the contest contain?==
Contest entries consist of proposals that are created and submitted on-line in the Climate CoLab [[www.climatecolab.org]].

For the 2010 contest, the proposals will focus on the question:
What international climate agreements should the world community make?

Each proposal must contain the following items (see details on-line):
* Elements of proposed international climate agreements:
** Emission reduction commitments in three different regions of the world
** Global commitments for deforestation reductions and increases in forest cover
* Predictions of the physical and economic impacts of these commitments, including temperature change, sea level rise, and costs of adapting to climate change.  These predictions are computed automatically by computer simulation models included in the site.
* Descriptions (see [[Sample template for Proposal Descriptions]]) of the proposals that include:
** Justification of why these commitments are feasible.  For instance, this might include:
*** Commitments for financial transfers between countries to help defray the costs of climate change 
*** Other political actions that would enable climate agreements to be made
*** Technical analyses of how emission reduction commitments could be reached
** Justification of why these actions and outcomes are desirable 


In addition, contestants are encouraged but not required to include the following kinds of information in proposals:
* More detailed models of how the commitments could be achieved, such as how they could be divided among countries, or how different technologies could help achieve them (see section on Models below).
* Artistic or other representations of the world that would be created by this proposal (images, short stories, videos, etc.)
* Identification of the specific positions this proposal represents on key issues about climate change that are included in the site''s on-line debates (see details on-line). 
* Any other relevant information about the proposal, including its goals, the history of its development, etc.

==Instructions for Entering== 
To enter, start by going to the [[http://www.climatecolab.org/web/guest/plans#plans=contests:active|Active Contests]] page and select "Begin a new proposal." To submit a proposal to the contest, you must select the button in the Admin tab of the proposal that says "The proposal is: An entry in the contest."
* By submitting a Proposal, you are agreeing to these Rules and the [[Terms of Use]].
* If you created your Proposal with other individuals, all of the co-authors must sign up with the Climate CoLab website. All co-authors must be listed as members of your Proposal team, and all co-authors must understand and agree to the [[Terms of Use]] and these Rules. 
* The individual who initiates a proposal will be listed as the Owner of that proposal in the Team tab and will receive all official communications about the Climate CoLab 2010 Contest. Team members can decide among themselves who will receive any travel funds which may be designated for their team should they win this Contest. In cases where team members cannot agree among themselves, any travel funds will be allocated to the Owner of the proposal.
* As set forth in the [[Terms of Use]], all Proposals will be made available to third parties under the Creative Commons Attribution-Share Alike 3.0 United States License [[http://creativecommons.org/licenses/by-nc-sa/3.0]].
* If you do not list any Co-Authors of the work, you are representing that you are the sole author. If you do list Co-Authors of the work, you represent that you are not violating any Co-Author’s rights by entering the work, and that any Co-Authors have given you permission to submit the work. 

==How will the contest be structured?==
The contest will have two rounds. 

* //Preliminary round// (October 1 - October 31).  Teams will create proposals.  At the end of this round, expert judges will select proposals for the finals. Judging will be based on the feasibility of the actions proposed in the proposal, the novelty of the proposal''s ideas, and the quality of the proposal presentation. In addition, the judges will seek to select a group of finalists that incorporate a broad and diverse range of approaches.  The finalists will thus be proposals the judges believe are feasible and well-presented and that represent a diversity of possible approaches for dealing with climate change.  Judges will be specifically asked not to select proposals on the basis of what they personally think is desirable.  (See details about judging below.)  
* //Final round// (November 8-26).  All registered users of the CoLab will be able to vote on the proposals they find most desirable.  Two kinds of prizes will be given:  (a) "Popular Vote" prizes for the proposals that receive the most votes, and (b) "Judges'' Choice" prizes for the proposals the judges feel are most desirable.  All these results of the contest will be available for use by negotiators at the UN climate talks in Cancun November 29-December 10. 

==How do teams work?==

Individuals may create proposals by themselves, but participants are encouraged to form teams.  For instance, a team might include different people with expertise in quantitative modeling, political analysis, writing, and artistic creation. 

Anyone who wants to join the team that is creating a specific proposal can request to join that team.  Then the current team members decide whether they want that person to join. 

If they wish, teams can restrict the rights to edit their proposals to team members only.  Alternatively, teams may also let anyone who is interested edit their proposals.  In this way, teams get input from lots of people without the overhead of requiring everyone to join the team.  Team members can easily undo any changes they don''t like.

==How can people show their support for a proposal before the final round?==
At any time during the preliminary round, a member of the CoLab can become a "supporter" of as many proposals as they want.  This makes it easy for judges and other viewers to see which proposals are most popular at each stage of the contest.  

==How can users contribute new simulation models?==
All proposals automatically use the models currently included in the CoLab; however, users are also encouraged to submit extensions to or alternatives for these models.  

Models submitted by users will be reviewed by the CoLab staff and expert advisors and, if appropriate, added to the site where all users can use them.

While it is technically possible to add many different kinds of models, the CoLab is designed to make it especially easy to add new models that are represented in spreadsheets.

In the 2010 contest, users are especially encouraged to submit new spreadsheet models to calculate the inputs to the current model using more basic inputs such as emission reductions by country and/or emission reductions resulting from changes in technology mix (such as coal, solar, wind, and nuclear). For more on this, see [[Models help]].   

==How does the CoLab community deal with different points of view?==
The Climate CoLab is an open forum where all points of view on the climate change issue are welcome. At the same time, the community expects members to respect facts, evidence, and rational argument.

The community also expects its members to engage with each other respectfully and courteously.  Failure to do this may, in the discretion of the contest organizers, result in individuals or teams being disqualified from the contest.

==How will proposals be judged?== 

Judges will be asked to evaluate proposals on the following criteria: 
* //Feasibility// of the actions proposed in the proposal. For instance, judges with different kinds of expertise will evaluate the technical, economic, social, and political feasibility of the proposals. 
* //Novelty// of the proposal''s ideas. Rather than selecting a number of feasible proposals that are very similar, judges will try to select proposals that represent a variety of different approaches to global climate change. 
* //Presentation quality//. Even though presentation quality is the least important of the three criteria, proposals that are well-presented will be favored over those that aren''t. Presentation quality includes how well-written a proposal is, how well it uses graphics or other visual elements, and how compelling are its artistic representations of possible future worlds (if any). 

There are no explicit weightings for these three criteria, but judges will be asked to use all three criteria to select proposals that are most likely to lead to useful outcomes of the contest overall. As a "tie-breaker", judges may also use the popularity of a proposal as indicated by the number of people who support it.  For example, in cases where a number of proposals are similar, judges will try to pick one or two proposals to represent the whole group. In selecting these representative proposals, judges will take into account the quality of the proposal presentations and the number of people who support the proposals. And in order to increase the diversity of ideas considered in the next round, judges may accept slightly lower levels of feasibility for proposals that include highly novel and interesting ideas. 

In the preliminary round, judges will also be explicitly asked not to select proposals based on their own personal preferences about which proposals are most desirable. In other words, judges are asked to use their expertise to judge the feasibility of proposals but not their desirability. Judgments of desirability are primarily made in the final stage of the contest by popular vote. For example, a judge should not reject a proposal that is technically, economically, and politically feasible, just because the judge feels that the proposal would lead to socially undesirable consequences.  

In the final round, the judges will be asked to select the proposals they think are most desirable.

==What are the prizes?==
At the conclusion of the contest, the winning teams and their plans will be featured on the home page of the Climate CoLab and in a press release from MIT. 

The Climate CoLab team will support travel by at least one representative from each winning team to one or more of the briefings planned with policy makers (more details below).

==How will the results of the contest influence policy?==
The Climate CoLab team is arranging briefings for relevant policy-makers about the results of the contest, including the winning teams and plans. Events confirmed so far include: 
* A briefing on Capitol Hill sponsored by the U.S. House of Representatives Select Committee on Energy Independence and Global Warming (Washington, DC)
* A briefing to the United Nations Secretary General''s Climate Change Support Team (New York, NY)
' WHERE `WikiPage`.`pageId` = 146624;
