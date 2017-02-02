-- -------------------------
-- Crowdsensor SPECIFIC
--
-- -------------------------

UPDATE xcolab_ConfigurationAttribute SET numericValue = 0, stringValue = '{
 "CALL_TO_ACTION": "",
 "CONTEST_TYPE_ID": "2"}', realValue = 0 WHERE name = 'PORTLET_PROPOSALS_PREFERENCES' AND additionalId = 0;

INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('ACTIVE_THEME', '0', '0', 'CROWDSENSOR', '0.0');

-- content article ids TODO
INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('LANDING_PAGE_BANNER_CONTENT_ARTICLE_ID', '0', '490', ' ', '0');
INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('LANDING_PAGE_BOTTOM_CONTENT_ARTICLE_ID', '0', '492', ' ', '0');
INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('FOOTER_CONTENT_ARTICLE_ID', '0', '494', ' ', '0');
INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('MEMBERS_CONTENT_ARTICLE_ID', '0', '505', ' ', '0');
INSERT INTO xcolab_ConfigurationAttribute (`name`, `additionalId`, `numericValue`, `stringValue`, `realValue`) VALUES ('DISCUSSION_CONTENT_ARTICLE_ID', '0', '506', ' ', '0');

-- Content
-- Folders
INSERT INTO xcolab_ContentFolder (contentFolderId, contentFolderName, contentFolderDescription, parentFolderId) VALUES (6, 'About', null, 2);
INSERT INTO xcolab_ContentFolder (contentFolderId, contentFolderName, contentFolderDescription, parentFolderId) VALUES (7, 'News', null, 2);
INSERT INTO xcolab_ContentFolder (contentFolderId, contentFolderName, contentFolderDescription, parentFolderId) VALUES (8, 'Community', null, 2);
INSERT INTO xcolab_ContentFolder (contentFolderId, contentFolderName, contentFolderDescription, parentFolderId) VALUES (9, '2016', null, 7);
INSERT INTO xcolab_ContentFolder (contentFolderId, contentFolderName, contentFolderDescription, parentFolderId) VALUES (10, 'September', null, 9);
INSERT INTO xcolab_ContentFolder (contentFolderId, contentFolderName, contentFolderDescription, parentFolderId) VALUES (11, 'August', null, 9);
INSERT INTO xcolab_ContentFolder (contentFolderId, contentFolderName, contentFolderDescription, parentFolderId) VALUES (12, 'October', null, 9);

-- Articles
INSERT INTO xcolab_ContentArticle (contentArticleId, authorId, createDate, maxVersionId, folderId, editRoleGroupId, viewRoleGroupId, visible) VALUES (493, 2348404, '2017-01-26 10:25:39', 560, 6, null, null, 1);
INSERT INTO xcolab_ContentArticle (contentArticleId, authorId, createDate, maxVersionId, folderId, editRoleGroupId, viewRoleGroupId, visible) VALUES (494, 2348404, '2017-01-26 10:38:08', 564, 2, null, null, 1);
INSERT INTO xcolab_ContentArticle (contentArticleId, authorId, createDate, maxVersionId, folderId, editRoleGroupId, viewRoleGroupId, visible) VALUES (498, 2348404, '2017-01-26 14:26:01', 572, 6, null, null, 1);
INSERT INTO xcolab_ContentArticle (contentArticleId, authorId, createDate, maxVersionId, folderId, editRoleGroupId, viewRoleGroupId, visible) VALUES (499, 2348404, '2017-01-26 14:30:41', 573, 6, null, null, 1);
INSERT INTO xcolab_ContentArticle (contentArticleId, authorId, createDate, maxVersionId, folderId, editRoleGroupId, viewRoleGroupId, visible) VALUES (500, 2348404, '2017-01-26 14:31:11', 574, 6, null, null, 1);
INSERT INTO xcolab_ContentArticle (contentArticleId, authorId, createDate, maxVersionId, folderId, editRoleGroupId, viewRoleGroupId, visible) VALUES (501, 2348404, '2017-01-26 14:32:13', 575, 6, null, null, 1);
INSERT INTO xcolab_ContentArticle (contentArticleId, authorId, createDate, maxVersionId, folderId, editRoleGroupId, viewRoleGroupId, visible) VALUES (502, 2348404, '2017-01-26 14:32:36', 576, 6, null, null, 1);
INSERT INTO xcolab_ContentArticle (contentArticleId, authorId, createDate, maxVersionId, folderId, editRoleGroupId, viewRoleGroupId, visible) VALUES (503, 2348404, '2017-01-26 14:33:04', 577, 6, null, null, 1);
INSERT INTO xcolab_ContentArticle (contentArticleId, authorId, createDate, maxVersionId, folderId, editRoleGroupId, viewRoleGroupId, visible) VALUES (504, 2348404, '2017-01-26 14:33:43', 578, 6, null, null, 1);
INSERT INTO xcolab_ContentArticle (contentArticleId, authorId, createDate, maxVersionId, folderId, editRoleGroupId, viewRoleGroupId, visible) VALUES (505, 2348404, '2017-01-26 14:38:21', 579, 8, null, null, 1);
INSERT INTO xcolab_ContentArticle (contentArticleId, authorId, createDate, maxVersionId, folderId, editRoleGroupId, viewRoleGroupId, visible) VALUES (506, 2348404, '2017-01-26 14:39:32', 580, 8, null, null, 1);
INSERT INTO xcolab_ContentArticle (contentArticleId, authorId, createDate, maxVersionId, folderId, editRoleGroupId, viewRoleGroupId, visible) VALUES (507, 2348404, '2017-01-26 14:52:51', 581, 7, null, null, 1);
INSERT INTO xcolab_ContentArticle (contentArticleId, authorId, createDate, maxVersionId, folderId, editRoleGroupId, viewRoleGroupId, visible) VALUES (508, 2348404, '2017-01-26 15:00:22', 585, 7, null, null, 1);
INSERT INTO xcolab_ContentArticle (contentArticleId, authorId, createDate, maxVersionId, folderId, editRoleGroupId, viewRoleGroupId, visible) VALUES (510, 2348404, '2017-01-26 15:10:28', 595, 10, null, null, 1);
INSERT INTO xcolab_ContentArticle (contentArticleId, authorId, createDate, maxVersionId, folderId, editRoleGroupId, viewRoleGroupId, visible) VALUES (511, 2348404, '2017-01-26 15:26:17', 594, 10, null, null, 1);
INSERT INTO xcolab_ContentArticle (contentArticleId, authorId, createDate, maxVersionId, folderId, editRoleGroupId, viewRoleGroupId, visible) VALUES (512, 2348404, '2017-01-26 15:40:32', 596, 11, null, null, 1);
INSERT INTO xcolab_ContentArticle (contentArticleId, authorId, createDate, maxVersionId, folderId, editRoleGroupId, viewRoleGroupId, visible) VALUES (513, 2348404, '2017-02-01 00:00:00', 597, 11, null, null, 1);
INSERT INTO xcolab_ContentArticle (contentArticleId, authorId, createDate, maxVersionId, folderId, editRoleGroupId, viewRoleGroupId, visible) VALUES (514, 2348404, '2017-02-01 00:00:00', 598, 11, null, null, 1);

-- Pages
INSERT INTO xcolab_ContentPage (pageId, title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES (1, 'about', 498, 493, '2017-01-26 16:43:08', '2017-01-26 16:43:17');
INSERT INTO xcolab_ContentPage (pageId, title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES (2, 'smart-nation', 498, 499, '2017-01-26 16:43:10', '2017-01-26 16:43:17');
INSERT INTO xcolab_ContentPage (pageId, title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES (3, 'crowdsourcing', 498, 500, '2017-01-26 16:43:10', '2017-01-26 16:43:18');
INSERT INTO xcolab_ContentPage (pageId, title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES (4, 'get-involved', 498, 501, '2017-01-26 16:43:11', '2017-01-26 16:43:18');
INSERT INTO xcolab_ContentPage (pageId, title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES (5, 'people', 498, 502, '2017-01-26 16:43:11', '2017-01-26 16:43:18');
INSERT INTO xcolab_ContentPage (pageId, title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES (6, 'faqs', 498, 503, '2017-01-26 16:43:11', '2017-01-26 16:43:19');
INSERT INTO xcolab_ContentPage (pageId, title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES (7, 'contact', 498, 504, '2017-01-26 16:43:12', '2017-01-26 16:43:19');
INSERT INTO xcolab_ContentPage (pageId, title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES (8, 'news', null, 508, '2017-01-26 16:43:12', '2017-01-26 16:43:19');
INSERT INTO xcolab_ContentPage (pageId, title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES (9, 'news-deadline-for-entries-extended-to-october-10', null, 510, '2017-01-26 16:43:12', '2017-01-26 16:43:20');
INSERT INTO xcolab_ContentPage (pageId, title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES (10, 'news-get-feedback-from-crowdsensor-s-judges', null, 511, '2017-01-26 16:43:13', '2017-01-26 16:43:20');
INSERT INTO xcolab_ContentPage (pageId, title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES (11, 'news-welcome-to-the-crowdsensor-', null, 512, '2017-01-26 16:43:13', '2017-01-26 16:43:21');
INSERT INTO xcolab_ContentPage (pageId, title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES (12, 'news-improve-your-chance-to-win-a-prize', null, 513, '2017-01-26 16:43:13', '2017-01-26 16:43:21');
INSERT INTO xcolab_ContentPage (pageId, title, menuArticleId, contentArticleId, createdDate, modifiedDate) VALUES (13, 'news-winners-announced', null, 514, '2017-01-26 16:43:14', '2017-01-26 16:43:21');

-- Article Versions
INSERT INTO xcolab_ContentArticleVersion (contentArticleVersionId, contentArticleId, folderId, authorId, createDate, title, content) VALUES (560, 493, 6, 2348404, '2017-01-26 10:30:17', 'About', '<h1 class="p1">About the Project</h1>

<h2 class="p9">Objective</h2>

<p class="p9">The aim of the Crowdsensor: Smart Nation is to elicit a broad range of ideas about how worldwide trends could impact Singapore&rsquo;s plans to become a <a href="/web/guest/smart-nation">Smart Nation</a>. What changes in the economic, technological, environmental or other realms might occur&nbsp;<strong>by 2030</strong> that could affect Singapore?&nbsp;Thinking expansively about what lies ahead can help Singapore to make the most out of the best circumstances, and guard against the worst.</p>

<p class="p10">Singapore&#39;s <a href="http://www.rahs.gov.sg/">Risk Assessment and Horizon Scanning (RAHS)</a>&nbsp;Programme Office and the <a href="http://cci.mit.edu">Center for Collective Intelligence&nbsp;</a><a a="" center="" collective="" for="" href="http://cci.mit.edu/">(CCI)</a> at the Massachusetts Institute of Technology (MIT) have developed this platform to elicit the best ideas from individuals around the world.</p>

<div class="p-About__approachIllustration"><img alt="" src="/images/about/about_diagram.png" />
<div class="p-About__approachIllustration__col">
<h3>World community</h3>

<p>proposes trends</p>
</div>

<div class="p-About__approachIllustration__col">
<h3>Experts</h3>

<p>provide guidance</p>
</div>

<div class="p-About__approachIllustration__col">
<h3>Collective intelligence</h3>

<p>to plan for the future</p>
</div>
</div>

<h2 class="p10" style="margin-top:40px;">Approach</h2>

<p class="p10">Anyone in the world can join the community&nbsp;to propose trends and evaluate their impact. Members propose trends&nbsp;in 6 different categories (Economy, Technology, Security, Environment, Society and Politics), which are then evaluated by expert judges to select a set of finalists. Members can then vote for the finalists, and judges will&nbsp;select&nbsp;5 winners&nbsp;each of whom will receive $500 SGD.&nbsp;</p>

<p class="p10">The 5 prizes will be awarded based on these guidelines:</p>

<ul>
	<li class="p10">2 prizes of $500 SGD for trends that represent opportunities for Singapore</li>
	<li class="p10">2 prizes of $500 SGD for trends that represent&nbsp;challenges for Singapore</li>
	<li class="p10">1 prize of $500 SGD for the trend judged to be the most novel&nbsp;</li>
</ul>

<p>&nbsp;</p>

<p>The timeline for the competition will be:&nbsp;</p>

<ul>
	<li>Sept 1-Oct 10:&nbsp;Members of the community submit trends</li>
	<li>Oct 11-27: Judges select winners</li>
	<li>Oct 28: Winners announced</li>
</ul>

<h2 class="p10" style="margin-top: 40px;">Rationale for using crowdsourcing</h2>

<p>As the internet has grown, so has the power to harness&nbsp;the great diversity of individuals around the world. Crowdsourcing platforms such as <a href="https://fold.it/portal/">Foldit</a> have enabled the discovery of new proteins to target diseases, while the&nbsp;<a href="http://climatecolab.org">Climate CoLab</a> has yielded strategies to tackle climate change. Here, Crowdsensor takes&nbsp;the same approach to elicit distinctive viewpoints from around the world,&nbsp;that will&nbsp;develop a wide range of possibilities about what growing&nbsp;trends will change the way we live in 2030.</p>
');
INSERT INTO xcolab_ContentArticleVersion (contentArticleVersionId, contentArticleId, folderId, authorId, createDate, title, content) VALUES (572, 498, 6, 2348404, '2017-01-26 14:29:57', 'Navigation', '<ul>
	<li id="about"><a href="/page/about">About The Project</a></li>
	<li id="smart-nation"><a href="/page/smart-nation">Singapore Smart Nation</a></li>
	<li id="crowdsourcing"><a href="/page/crowdsourcing">How Does It Work?</a>
	<ul>
		<li><a href="/page/crowdsourcing" id="crowdsourcing">Contest&nbsp;phases</a></li>
		<li id="get-involved"><a href="/page/get-involved" id="get-involved">How you can participate</a></li>
	</ul>
	</li>
	<li id="people"><a href="/page/people">People</a></li>
	<li id="faqs"><a href="/page/faqs">FAQs</a></li>
	<li id="contact"><a href="/page/contact">Contact</a></li>
</ul>
<script>
var siteForNav=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
$("#"+siteForNav).addClass("c");
</script>');
INSERT INTO xcolab_ContentArticleVersion (contentArticleVersionId, contentArticleId, folderId, authorId, createDate, title, content) VALUES (573, 499, 6, 2348404, '2017-01-26 14:30:41', 'Smart Nation', '<p>&nbsp;</p>

<h1>Singapore Smart Nation</h1>

<h2>What is a Smart City?</h2>

<p>Electric grids that redistribute electricity according to where it is needed; levies that rise as water levels rise; driverless tow trucks that respond immediately to accidents and traffic lights that divert cars around it.&nbsp;These are examples of cities that are &quot;smart&quot; <span style="font-family: ''Fira Sans'', Arial, sans-serif; font-size: 16px; background-color: rgb(255, 255, 255);">&mdash;</span>&nbsp;cities that&nbsp;have embedded information and communications technology (ICT) into their&nbsp;infrastructure, allowing them to&nbsp;function adaptively in response to different needs.</p>

<p>By having such technology in place, a smart city is able to operate much more efficiently. Data can be collected about how different infrastructure is being used, so that it can be employed only as and when it is needed. For example, data&nbsp;about the soil moisture in parks&nbsp;can determine when and where sprinklers need to be turned on, helping to save the amount of water used; or data informing drivers where to find empty parking spots can optimize the amount of parking a city needs to build.&nbsp;At the same time, such technology can help citizens become more efficient in the way they&nbsp;use&nbsp;the city <span style="font-family: ''Fira Sans'', Arial, sans-serif; font-size: 16px; background-color: rgb(255, 255, 255);">&mdash;</span> radio frequency identification (RFID) in transport passes can make it easy to both pay for a bus ride and&nbsp;pay for meals.</p>

<p>Altogether, these technologies&nbsp;can improve the services that a city provides to its citizens, lower costs of maintaining the city, ensure it is safer and more reliable, and even reduce the impact it has on the environment.&nbsp;Overall, smart cities can improve our quality of life.</p>

<p>&nbsp;</p>

<h2>What is Singapore Smart Nation?</h2>

<p>With the Smart Nation project, Singapore aims to make the entire country smart. Everything from housing to transportation to emergency services across the nation will be ICT-enabled so that they can better meet citizens&#39; needs. Many of these efforts are already underway <span style="font-family: ''Fira Sans'', Arial, sans-serif; font-size: 16px; background-color: rgb(255, 255, 255);">&mdash;</span>&nbsp;<a href="http://smart.mit.edu/news-a-events/press-room/article/51-first-driverless-vehicles-for-public-launched.html">driverless vehicles</a> are already being tested in the campuses of the&nbsp;National University of Singapore or in the Gardens by the Bay. Housing estates under the new&nbsp;<a href="http://www20.hdb.gov.sg/fi10/fi10296p.nsf/PressReleases/F93B15F80588397748257D500009CE6C?OpenDocument">Smart HDB Town Framework</a> are embedding technologies to monitor the wellbeing of elderly citizens within apartments to provide them with timely healthcare and embedding sensors in fans that are triggered only at certain temperature thresholds to reduce energy consumption.</p>

<p>&nbsp;</p>

<h2>How Will the Future Affect Smart Nation?</h2>

<p>Because the development of Smart Nation will affect so many aspects of life, future changes in the world can have a large impact on the degree to which the Smart Nation effort can benefit Singapore. As economic or political conditions change, for example, they could affect which policies and supporting infrastructure need to be put in place to build a Smart Nation. If smart sensors become widespread and cybersecurity becomes a major concern for the public, new policies about wifi coverage or data privacy may need to be implemented. Likewise, how the climate might change or how terrorist threats might grow could also greatly impact key steps that must be taken to make Singapore a Smart Nation.</p>

<p>By taking advantage of the Crowdsensor community to envision the future as expansively as possible, we can explore all of the potential opportunities or challenges that Singapore&rsquo;s Smart Nation activities should take into account between now and 2030.</p>

<p>&nbsp;</p>

<h2>Additional Resources</h2>

<ul>
	<li><a href="http://www.smartnation.sg">Smart Nation Singapore</a></li>
	<li>Wall Street Journal, <a href="http://www.wsj.com/articles/singapore-is-taking-the-smart-city-to-a-whole-new-level-1461550026">Singapore is Taking the &#39;Smart City&#39; to a Whole New Level</a></li>
	<li>Forbes, <a href="http://www.smartnation-forbes.com">Smart Nation Singapore</a></li>
</ul>

<p>&nbsp;</p>
');
INSERT INTO xcolab_ContentArticleVersion (contentArticleVersionId, contentArticleId, folderId, authorId, createDate, title, content) VALUES (574, 500, 6, 2348404, '2017-01-26 14:31:11', 'Crowdsourcing', '<h1 class="p1">Contest Phases</h1>

<h2 class="p2">September-October 2016: Crowdsourcing Trends</h2>

<p><img alt="" src="http://i.imgur.com/fm2zzc2.png" style="width: 660px; height: 510px;" /></p>

<p>From&nbsp;<strong>September 1-October 10,&nbsp;2016</strong>, members of the community are invited to contribute their ideas about what trends might arise by 2030, and the opportunities or challenges those trends may pose for Singapore&rsquo;s Smart Nation effort.&nbsp;</p>

<p>Trends can be in any of the 6 categories <span style="font-family: ''Fira Sans'', Arial, sans-serif; font-size: 16px; background-color: rgb(255, 255, 255);">&mdash;</span> Security,&nbsp;Economy, Society, Politics, Environment and Technology. Trends are issues that may seem small today, but may grow into significant opportunities or challenges by 2030 that can greatly affect the way we live, and thus how our future smart cities need to be designed.&nbsp;</p>

<p class="p2">Take electricity, for example <span style="font-family: ''Fira Sans'', Arial, sans-serif; font-size: 16px; background-color: rgb(255, 255, 255);">&mdash;</span> improving technology in smart meters could allow our energy supply to be managed more efficiently and cheaply; yet at the same time, growing cybersecurity threats could place a web-connected electrical grid at risk. Further resources&nbsp;on&nbsp;the types of possible trends are given in <a href="/trends">each of the trends category pages</a>, and&nbsp;an example of a completed trend submimssion is given in this <a href="/web/guest/trend-example-1">technology trend example.</a></p>

<p class="p2">From<strong>&nbsp;October 11-27, 2016</strong>, expert judges will select a set of finalists from the pool of contributions, based on what they deem to be the most:</p>

<ul>
	<li class="p2">unique,</li>
	<li class="p2">likely, and</li>
	<li class="p2">impactful</li>
</ul>

<p class="p2">&nbsp;</p>

<p class="p2">The judges will select the ultimate 5 winners, each of whom will receive $500&nbsp;SGD.&nbsp;</p>

<p class="p2">On <strong>October 28, t</strong>he winners will be announced.&nbsp;</p>

<h2 class="p2">Prizes</h2>

<p class="p10"><img alt="" src="http://i.imgur.com/RghFyUf.png" style="width: 660px; height: 510px;" /></p>

<p class="p10">The 5 prizes will be awarded as follows:</p>

<ul>
	<li class="p10">2 prizes of $500 SGD for trends that represent opportunities for Singapore</li>
	<li class="p10">2 prizes of $500 SGD for trends that represent&nbsp;challenges for Singapore</li>
	<li class="p10">1 prize of $500 SGD for the trend judged to be the most novel</li>
</ul>

<p class="p2">&nbsp;&nbsp;</p>

<p class="p2">Selected winners may also have an opportunity to present their ideas to the public at the coming International Risk Assessment and Horizon Scanning Symposium (IRAHSS) 2017.</p>

<div class="popup-wrap p2" id="previousProposalsPopup" style="display: none;">
<div class="popup">
<div class="close">&nbsp;</div>

<h4>Proposals from past contests</h4>

<p><a href="http://climatecolab.org/web/guest/plans/-/plans/contestId/1300203/planId/1309346">Improve Building Energy Performance: Green Job Skills Training</a><br />
2014 Grand Prize winner, 2014 Buildings contest Judges&#39; Choice winner<br />
<span>by Danielle Dahan</span></p>

<p><a href="http://climatecolab.org/web/guest/plans/-/plans/contestId/25/planId/1304134">Whose Home is wasting more energy, yours or your neighbours?</a><br />
2013 Grand Prize winner, 2013 Reducing consumption Judges&#39; Choice winner<br />
<span>by Dr Geoffrey J. Hay</span></p>

<p><a href="http://climatecolab.org/plans/-/plans/contestId/1300404/planId/1307606">A Carbon Tax in Pro-Growth Fiscal Reform</a><br />
2014 Judges&#39; Choice winner US Carbon Price contest, Honorable Mention<br />
<span>by Adele Morris</span></p>

<p><a href="http://climatecolab.org/plans/-/plans/contestId/1300401/planId/1307119">Democratic Finance: Energy Of the People, By the People, For the People</a><br />
2014 Judges&#39; and Popular Choice winner U.S. Government contest, Honorable Mention<br />
<span>by Job Taminiau, Gordon Schweitzer, Kathleen Saul &amp; Sardar Mohazzam</span></p>

<p><a href="http://climatecolab.org/web/guest/plans/-/plans/contestId/1300801/planId/1309001">A collaborative solutions communication platform</a><br />
2014 Judges&#39; Choice winner, Honorable Mention<br />
<span>by Anne-Marie Soulsby</span></p>
</div>
</div>

<div class="popup-wrap p2" id="modelRunPopup" style="display: none;">
<div class="popup">
<div class="close">&nbsp;</div>

<h4>Sample model run</h4>
<img alt="" src="/climatecolab-theme/images/model_run_sample.jpg" /></div>
</div>
');
INSERT INTO xcolab_ContentArticleVersion (contentArticleVersionId, contentArticleId, folderId, authorId, createDate, title, content) VALUES (575, 501, 6, 2348404, '2017-01-26 14:32:13', 'Get Involved', '<h1>How you can participate</h1>

<h2>Who can participate?</h2>

<div class="contribute" style="margin-top: 5px; margin-bottom: 10px">
<p>Anyone in the world can participate on the Crowdsensor platform &mdash;&nbsp;<a href="/register">all you have to do is register</a></p>
</div>

<h2>How can I participate?</h2>

<div class="getinvolved-img" style="margin-top: 5px;">
<div class="ge1">&nbsp;</div>

<div class="ge2">&nbsp;</div>

<div class="ge3">&nbsp;</div>
</div>

<div class="getinvolved">
<div class="ge1b">
<h4>Contribute</h4>

<p>Submit your ideas about trends that could have an impact on Singapore by&nbsp;2030 in the areas of&nbsp;<a href="/trends/2016/economy">Economy</a>, <a href="http://crowdsensor.org/trends/2016/society">Society</a>, <a href="/trends/2016/technology">Technology</a>,&nbsp;<a href="/trends/2016/environment">Environment</a>, <a href="/trends/2016/politics">Politics</a>, and <a href="/trends/2016/security">Security</a>.&nbsp;</p>
</div>

<div class="ge2b">
<h4>Support</h4>

<p>If you see a trend you like, you can support it. This will guide other community members to the most interesting and plausible trends.&nbsp;</p>
</div>

<div class="ge3b">
<h4>Comment</h4>

<p>You can comment on any trend. If you&#39;d like to help the author who submitted the trend, you can ask to become a member of the team working on the trend.</p>
</div>
</div>
');
INSERT INTO xcolab_ContentArticleVersion (contentArticleVersionId, contentArticleId, folderId, authorId, createDate, title, content) VALUES (576, 502, 6, 2348404, '2017-01-26 14:32:36', 'People', '<h1 class="spn">People</h1>

<p>Crowdsensor is made up of a diverse community of members, judges and staff <span style="font-family: ''Fira Sans'', Arial, sans-serif; font-size: 16px; background-color: rgb(255, 255, 255);">&mdash;</span> all of whom contribute to exploring what the future might look like and what strategies can be adopted that will be robust in light of those trends.&nbsp;</p>

<h2 style="margin-top:40px">Members</h2>

<p>Members are the core users of the website, who contribute, comment on, support, and vote on trends. They can be from all around the world, from any background. Every contribution, whether a comment, support, or a vote, adds to the collective work of identifying the trends that Singapore Smart Nation will be likely to face.</p>

<h2 style="margin-top:40px">Fellows</h2>

<p>Fellows are subject matter experts who have a deep knowledge of the contest theme and strong connections in the field. Their expertise is critical for providing feedback to contest participants throughout the duration of the competition.</p>

<h2 style="margin-top: 40px;"><span class="s1">Judges</span></h2>

<p>Judges are subject matter experts who have a deep knowledge of the contest theme and strong connections in the field. <span class="s1">Judges may provide feedback during the competition&nbsp;and will select the top entries of this competition.</span></p>

<p class="p1"><span class="s1">The Judging panel includes representatives from&nbsp;</span><span class="s2">RAHS </span><span class="s1">and MIT, along with experts on smart cities and future scanning, including several members of the&nbsp;<a href="http://www.millennium-project.org/">Millenium Project</a>&#39;s network:</span></p>

<p class="p2" style="margin-left: 40px;"><span class="s3"><a href="https://sg.linkedin.com/in/lock-pin-chew-6274667">Lock Pin Chew</a></span><br />
Director,&nbsp;<a href="http://www.rahs.gov.sg/public/www/home.aspx"><span class="s4">RAHS Programme Office</span></a>&nbsp;at National Security Coordinating Secretariat, Singapore<br />
<br />
<a href="http://cci.mit.edu/laubacher/laubacher2%20copy.html">Robert Laubacher</a><br />
Executive Director,&nbsp;<span class="s6"><a href="http://cci.mit.edu/">MIT Center for Collective Intelligence</a></span></p>

<p class="p2" style="margin-left: 40px;"><a href="http://www.worldcitiessummit.com.sg/wcsyoungleaders/mr-aaron-maniam">Aaron Maniam</a><br />
Director, Industry&nbsp;Division,&nbsp;<a href="https://www.mti.gov.sg/Pages/home.aspx"><span class="s4">Ministry of Trade&nbsp;and Industry</span></a>, Singapore</p>

<p class="p2" style="margin-left: 40px;"><a href="https://si.linkedin.com/in/blaz-golob-74282715">Blaž Golob</a><br />
CEO &amp; Partner,&nbsp;<span class="s4"><a href="http://en.smartiscity.eu/">SmartIScity Ltd</a></span></p>

<p class="p2" style="margin-left: 40px;"><a href="https://www.linkedin.com/in/ted-gordon-8b439457">Ted Gordon</a><br />
Co-Founder,&nbsp;<span class="s6"><a href="http://www.millennium-project.org/">Millennium Project</a></span></p>

<p class="p2" style="margin-left: 40px;"><a href="https://es.linkedin.com/in/ibon-zugasti-27543213">Ibon Zugasti</a><br />
Managing Director,&nbsp;<a href="http://www.prospektiker.es/"><span class="s4">PROSPEKTIKER</span></a>&nbsp;<br />
&nbsp;</p>

<h2 style="margin-top:40px">Staff</h2>

<p>Members of Singapore&#39;s&nbsp;<a href="http://www.rahs.gov.sg/">Risk Assessment and Horizon Scanning</a>&nbsp;(RAHS)&nbsp;Programme Office and MIT&#39;s&nbsp;<a href="http://cci.mit.edu/">Center for Collective Intelligence</a>&nbsp;(CCI) guide the development of the Crowdsensor platform.</p>
');
INSERT INTO xcolab_ContentArticleVersion (contentArticleVersionId, contentArticleId, folderId, authorId, createDate, title, content) VALUES (577, 503, 6, 2348404, '2017-01-26 14:33:04', 'FAQs', '<h1 class="spn">Frequently Asked Questions</h1>

<div id="webContent">
<div id="main">
<h3><span class="s1">Table of Contents</span></h3>

<p class="p1"><span class="s1"><a href="#General User Questions">General user questions</a></span><br />
<span class="s1"><a href="#Proposals &amp; Contests">Submitting trends</a></span><br />
<a href="#Integrated Proposals">Prizes and Judging</a></p>

<p class="p1">&nbsp;</p>

<h3 class="p1"><a id="General User Questions" name="General User Questions"><span class="s1"><b>General user questions</b></span></a></h3>

<p class="p1"><strong><span class="s1">How can I create a profile?</span></strong><br />
Register on the site (or login if you have already registered). Click on your user name in the upper right corner of the screen and select &quot;My profile.&quot;&nbsp;Once on your profile page, select &quot;Manage profile and settings.&quot;&nbsp;Fill out and save your profile--and&nbsp;don&#39;t forget to upload a photo!</p>

<p class="p1"><strong><span class="s1">How can I retrieve my password if I&#39;ve forgotten it?</span></strong><br />
Go to Login on the top right, and click &quot;Forgot your password?&quot;&nbsp;Enter your username or email, whichever you remember . Click &quot;Request new password.&quot;&nbsp;A&nbsp;message will be sent to the email address you used to register. In it will be a link.&nbsp;Just click on the link to set a new password.</p>

<p class="p1"><strong>Why do I keep getting error messages on the site?</strong><br />
If you&#39;re receiving error messages, it may be because you&#39;re using Internet Explorer. The Crowdsensor site functions best on Safari, Chrome, and Firefox. If you encounter difficulties while using those browsers, please describe your problem in the discussion forum called, <a href="http://crowdsensor.org/web/guest/discussion/-/discussion/thread/1355303">Report bugs and request features</a>.&nbsp;</p>

<p class="p1"><strong><span class="s1">How can I get fewer emails from Crowdsensor or unsubscribe?</span></strong><br />
Crowdsensor sends members emails about site activity to which&nbsp;they have subscribed and&nbsp;occasional newsletters.&nbsp;To change how often you receive messages, follow these steps:</p>

<ol>
	<li class="p1"><span class="s1">Log in to your&nbsp;account and select &quot;My profile&quot;</span></li>
	<li class="p1"><span class="s1">Click &quot;Manage profile and settings&quot;</span></li>
	<li class="p1"><span class="s1">On the right column under &quot;Message settings,&quot; select&nbsp;<span class="s1">&quot;Only send me a daily summary of all activities I&#39;m subscribed to&quot; (you will receive a once-a-day digest of notifications) or u<span class="s1">ncheck all the checkboxes to&nbsp;stop all notifications.&nbsp;</span></span></span></li>
	<li class="p1"><span class="s1">Click Save</span></li>
</ol>

<p class="p1">To unsubscribe from the email newsletters sent to the Crowdsensor community, you can simply click on the &quot;Opt out&quot;&nbsp;link at the bottom of the message.&nbsp;</p>

<p class="p2"><strong><span class="s1">Can I message people on the Crowdsensor?</span></strong></p>

<p class="p2">Yes, you can send messages to other members via the Crowdsensor messaging system.&nbsp; Open the profile of the person you&#39;d like to contact&nbsp;and select &quot;Send message.&quot;&nbsp;They will receive notification on the Crowdsensor site and, if they have selected the option to be notified, an email message as well.&nbsp;Members can send a maximum of 15 messages per 24 hour period. &nbsp;</p>

<p class="p1"><strong><span class="s1">Do you have a policy or guidelines on community etiquette on the Crowdsensor?</span></strong></p>

<p class="p1"><span class="s1">Yes, please see our <a href="/web/guest/community-philosophy">Community Philosophy and Policy</a>.</span></p>

<p class="p2">&nbsp;</p>

<h3 class="p1"><a id="Proposals &amp; Contests" name="Proposals &amp; Contests"><span class="s1"><b>Trends</b></span></a></h3>

<p class="p1"><strong><span class="s1">How can I submit a trend?</span></strong></p>

<p class="p1"><span class="s1">Register on the site or log in if you have already registered, then&nbsp;</span>go to the <a href="/trends/">Entries&nbsp;page</a>. &nbsp;Select&nbsp;whichever category for which you would like to create a trend. A new page will open. Simply&nbsp;click&nbsp;&quot;Create trend&quot; and fill out the fields in the template.</p>

<p class="p1"><strong><span class="s1">What if my trend falls under more than one category?</span></strong></p>

<p class="p1">Select the category&nbsp;that you think is most applicable.&nbsp;Trends can sometimes touch on more than one category<span style="font-family: ''Fira Sans'', Arial, sans-serif; font-size: 16px; background-color: rgb(255, 255, 255);">&mdash;</span>a technological advance&nbsp;can shape&nbsp;both how the economy works and&nbsp;what security threats&nbsp;we may face. The most interesting contributions to this competition&nbsp;may&nbsp;cut across several categories, by reflection on&nbsp;the broad repercussions a trend in one category may have in others. It is best to&nbsp;think about the root cause&nbsp;of the trend you are considering&nbsp;and to choose&nbsp;the most relevant category based on that.</p>

<p class="p1"><strong>Do you have an example of what a good trend might look like?&nbsp;</strong></p>

<p class="p1">Refer to this <a href="/web/guest/trend-example-1">Sample Technology Trend</a> to get an idea of how a complete and comprehensive submission might&nbsp;look.</p>

<p class="p1"><strong><span class="s1">How do I delete a trend?</span></strong></p>

<p class="p1"><span class="s1">Go to the Admin tab on your trend&nbsp;page and&nbsp;click the delete button.</span>&nbsp;</p>

<p class="p1"><strong><span class="s1">How can I save a draft of a trend?</span></strong></p>

<p class="p1"><span class="s1">Unfortunately, it is only possible to save and publish&nbsp;trends&nbsp;at the same time, but you can always go back and edit after your contribution is initially&nbsp;published. Don&#39;t worry about posting&nbsp;work-in-progress on the site--other members may see your draft and make useful comments. Give the online community a chance to help!&nbsp;</span></p>

<p class="p1"><strong><span class="s1">Can I edit my trend after submission?</span></strong></p>

<p class="p1"><span class="s1">You can always edit your submissions by logging into your account and clicking Edit.</span></p>

<p class="p2"><strong><span class="s1">How can I join a team? How can I add people to my team?</span></strong></p>

<p class="p1"><span class="s1">To join a team, you should first log in.&nbsp;Then go to the &quot;Contributors&quot; tab of the trend you would like to join and click the &quot;Request membership&quot; button in the upper right.&nbsp;The system&nbsp;will send your membership request to the trend&#39;s owner.&nbsp;If the owner approves your request, your username will show up as a team member in the Contributors tab and you will have the privileges of a team member, that is,&nbsp;the ability to contribute by making edits.&nbsp;</span></p>

<p class="p1"><strong><span class="s1">Can I participate in more than one category?</span></strong></p>

<p class="p1"><span class="s1">Yes, you can submit as many times as you want in any&nbsp;trend category.</span></p>

<p class="p1"><strong><span class="s1">Who can submit a trend?</span></strong></p>

<p class="p1"><span class="s1">The Crowdsensor is open to anyone in the world, regardless of age, nationality, or political viewpoint, though participation is void where prohibited by law. It is your responsibility to check to make sure that participation in the Crowdsensor does not violate any applicable law or ordinance&nbsp;and to make sure that you are eligible. If you are under 18 years old, you will need your parent or legal guardian to register on the Crowdsensor website and submit your ideas for you.&nbsp;You may submit ideas that you have previously published or exhibited so long as they conform to the entry guidelines and the rules.</span></p>

<p class="p2"><strong><span class="s1">What is the format for submissions?</span></strong></p>

<p class="p1"><span class="s1">When you start to create a trend, a template will appear, showing the sections that need to be completed.&nbsp;</span></p>

<p class="p1"><strong>How can I include images in my proposal?</strong></p>

<p class="p1"><span class="s1">If you would like to include images in your proposal, you will first need to upload them on a public image hosting site (such as ImageShack, Flickr, or Imgur). Once the image is on the hosting site, you can select the &quot;Insert Image&quot; icon (a small square that looks like a landscape painting). A pop up menu will appear. Copy your image&#39;s URL from the image hosing site and paste it&nbsp;into the&nbsp;&nbsp;&quot;URL&quot; field in the pop up. You may also want to adjust the image&#39;s dimensions by selecting its height and width (which are measured in pixels).&nbsp;&nbsp;</span></p>

<p class="p1"><span class="s1">Here is an example of how you could follow these steps, using the image hosting site Imgur as an example.&nbsp;Go to imgur.com, click on &quot;Computer&quot;&nbsp;just under &quot;Upload Images&quot;&nbsp;on the right hand side. Browse you folders to&nbsp;select the image, click on &quot;Start upload,&quot;&nbsp;and your image should show up on the site.&nbsp;On the right hand side of the screen displaying your image, you can see a little table. Go to &quot;Direct Link:&nbsp;and copy the link you find in there to your clipboard. The URL will start with&nbsp;http://i.imgur.com/ and then include some other characters. Go to your proposal on Crowdsensor and select&nbsp;the place in the text field where you would like to insert the image. Select the image icon (the small square that looks like a landscapre portrait) or click on the text field as though you want to write something. If you do the latter, several buttons will show up just below the upper border of the text field. Select &quot;Image&quot;&nbsp;which is located to the very right.&nbsp;Paste the link to your Imgur image into the URL field and click &quot;OK.&quot;&nbsp;To adjust size of the image, you cabn&nbsp;use Width and Height fields in the image edit dialog box.</span></p>

<p class="p1"><strong><span class="s1">Do we translate submissions?</span></strong></p>

<p class="p1"><span class="s1">Currently we do not have the capacity to translate submissions. We recommend using a trusted translation website to assist you.</span></p>

<p class="p1"><strong><span class="s1">What are the copyrights on this proposal?</span></strong></p>

<p class="p1"><span class="s1">Everything posted is subject to our Creative Commons License, which is at the bottom of each website page and linked here: <a href="http://creativecommons.org/licenses/by-nc-sa/3.0/us/">http://creativecommons.org/licenses/by-nc-sa/3.0/us/</a></span></p>

<p class="p2"><strong><span class="s1">How can I make sure my submission has been entered?</span></strong></p>

<p class="p1"><span class="s1">Once you click &quot;Publish,&quot;&nbsp;it will automatically appear on the page and be considered entered.</span></p>

<p class="p2">&nbsp;</p>

<h3 class="p1"><a id="Integrated Proposals" name="Integrated Proposals">Prizes&nbsp;and Judging</a></h3>

<p class="p1"><strong><span class="s1">How are prizes awarded?&nbsp;</span></strong></p>

<p class="p1"><span class="s1">Prizes will be awarded for 5 trends, based on these criteria:&nbsp;</span></p>

<ul>
	<li class="p10">2 prizes of $500 SGD for trends that represent opportunities for Singapore</li>
	<li class="p10">2 prizes of $500 SGD for trends that represent&nbsp;challenges for Singapore</li>
	<li class="p10">1 prize of $500 SGD for the trend judged to be the most novel&nbsp;<br />
	&nbsp;</li>
</ul>

<p class="p1">Selected winners may also have an opportunity to present their ideas to the public at the coming International Risk Assessment and Horizon Scanning Symposium (IRAHSS) 2017.</p>

<p class="p1"><strong>How do judges decide on finalists?</strong></p>

<p class="p1">Finalists evaluate trends based on their uniqueness, likelihood, and potential impact.</p>

<p class="p1"><strong><span class="s1">I saw the number of supports for my trend go down. What&#39;s going on?&nbsp;</span></strong></p>

<p class="p1"><span class="s1">To prevent abuse, we require a valid email address in order to count support received by any trend.&nbsp;Any supports&nbsp;made by accounts with invalid email addresses are removed on a regular basis.</span></p>
</div>
</div>
');
INSERT INTO xcolab_ContentArticleVersion (contentArticleVersionId, contentArticleId, folderId, authorId, createDate, title, content) VALUES (578, 504, 6, 2348404, '2017-01-26 14:33:43', 'Contact', '<div class="clearfix">&nbsp;</div>

<h1>Contact</h1>

<table>
	<tbody>
		<tr>
			<td style="padding-right:18px"><!-- <h3>Did you find a bug or do you want to request a feature?</h3>
            <p>Tell us about it&nbsp;<a href="http://:8080/jira/">in our bugtracker</a>.</p>-->
			<p class="noclr"><strong>Interested in helping us to&nbsp;improving&nbsp;the design or content of&nbsp;the Crowdsensor?</strong></p>

			<p>Visit our&nbsp;<a href="/web/guest/discussion">discussion pages</a>&nbsp;to&nbsp;contribute your idea.</p>

			<p class="noclr"><strong>Want to send some feedback to the site administrators?</strong></p>

			<p>Send&nbsp;us a&nbsp;<a href="javascript:;" onclick="jQuery(''#contactForm'').toggle(); initRecaptcha(); return false;">feedback message</a>.&nbsp;</p>
			</td>
			<td>
			<div class="contact-addr">Center for Collective Intelligence<br />
			MIT Building E62-424<br />
			50 Memorial Drive Cambridge, MA 02142<br />
			<br />
			Risk and Horizon Scanning Programme Office<br />
			Revenue House #15-01<br />
			55 Newton Road<br />
			Singapore 307987</div>
			</td>
		</tr>
	</tbody>
</table>

<h3>&nbsp;</h3>

<h3>&nbsp;</h3>

<p>&nbsp;</p>
');
INSERT INTO xcolab_ContentArticleVersion (contentArticleVersionId, contentArticleId, folderId, authorId, createDate, title, content) VALUES (581, 507, 7, 2348404, '2017-01-26 14:52:51', 'Top News', '<h2>Top News</h2>

<div class="newsbox" style="margin-top: 0px;">
<p>Oct 2016&nbsp;<a href="http://crowdsensor.org/community/-/blogs/winners-announced">Winners announced</a><br />
Oct 2016&nbsp;<a href="http://crowdsensor.org/community/-/blogs/improve-your-chance-to-win-a-prize?_33_redirect=http%3A%2F%2Fcrowdsensor.org%2Fcommunity%3Fp_p_id%3D33%26p_p_lifecycle%3D0%26p_p_state%3Dnormal%26p_p_mode%3Dview%26p_p_col_id%3Dcolumn-3%26p_p_col_pos%3D1%26p_p_col_count%3D2">Improve your chance to win a prize</a><br />
Sept 2016&nbsp;<a href="http://crowdsensor.org/web/guest/community/-/blogs/deadline-for-entries-extended-to-october-10?_33_redirect=http%3A%2F%2Fcrowdsensor.org%2Fweb%2Fguest%2Fcommunity%3Fp_p_id%3D33%26p_p_lifecycle%3D0%26p_p_state%3Dnormal%26p_p_mode%3Dview%26p_p_col_id%3Dcolumn-3%26p_p_col_pos%3D1%26p_p_col_count%3D2">Deadline for entries extended to Oct 10&nbsp;</a><br />
Sept&nbsp;2016&nbsp;<a href="http://crowdsensor.org/web/guest/community/-/blogs/get-feedback-from-crowdsensor-s-judges?_33_redirect=http%3A%2F%2Fcrowdsensor.org%2Fweb%2Fguest%2Fcommunity%3Fp_p_id%3D33%26p_p_lifecycle%3D0%26p_p_state%3Dnormal%26p_p_mode%3Dview%26p_p_col_id%3Dcolumn-3%26p_p_col_pos%3D1%26p_p_col_count%3D2">Get feedback from Crowdsensor&#39;s&nbsp;Judges</a><br />
Aug&nbsp;2016&nbsp;<a href="http://crowdsensor.org/web/guest/community/-/blogs/welcome-to-the-crowdsensor-?_33_redirect=http%3A%2F%2Fcolab2.mit.edu%3A18081%2Fweb%2Fguest%2Fcommunity%3Fp_p_id%3D33%26p_p_lifecycle%3D0%26p_p_state%3Dnormal%26p_p_mode%3Dview%26p_p_col_id%3Dcolumn-3%26p_p_col_pos%3D1%26p_p_col_count%3D2">Welcome to Crowdsensor</a></p>
</div>
');
INSERT INTO xcolab_ContentArticleVersion (contentArticleVersionId, contentArticleId, folderId, authorId, createDate, title, content) VALUES (585, 508, 7, 2348404, '2017-01-26 15:03:37', 'News Overview', '<div class="proposal-head">
<div class="inner">
<div class="headline">
<h1>Community News</h1>
</div>
<!-- /headline -->

<ul class="c-TabBar clearfix">
	<li class="c-TabBar__tab--first"><a href="/members">Members</a></li>
	<li class="c-TabBar__tab"><a href="/discussion">Discussion</a></li>
	<li class="c-TabBar__tab--last active"><a href="/page/news">News</a></li>
</ul>
</div>
<!-- /inner --><!-- /proposal-head --></div>

<p>&nbsp;</p>

<div class="comm_news">
<div class="entry approved" id="_33_2365001">
<div class="entry-content">
<div class="entry-title">
<h2><a href="/community/-/blogs/winners-announced?_33_redirect=http%3A%2F%2Fcrowdsensor.org%2Fcommunity%3Fp_p_id%3D33%26p_p_lifecycle%3D0%26p_p_state%3Dnormal%26p_p_mode%3Dview%26p_p_col_id%3Dcolumn-3%26p_p_col_pos%3D1%26p_p_col_count%3D2">Winners announced</a></h2>
</div>

<div class="comm_meta">
<div class="comm_date">October 28, 2016</div>
</div>
</div>

<div class="comm_div">&nbsp;</div>

<div class="entry-body">We are pleased to announce the winners of the 2016 Crowdsensor Smart Nation competition. 70 entries were submitted and 5 selected as winners by the panel of&nbsp; judges .&nbsp; 2 were for trends expected to make life easier for Singapore by 2030:&nbsp; Simulated Government - Not sure? Run a sim first! &nbsp; &nbsp;&nbsp; Advances in virtual intelligence and simulation...<br />
<a href="/community/-/blogs/winners-announced?_33_redirect=http%3A%2F%2Fcrowdsensor.org%2Fcommunity%3Fp_p_id%3D33%26p_p_lifecycle%3D0%26p_p_state%3Dnormal%26p_p_mode%3Dview%26p_p_col_id%3Dcolumn-3%26p_p_col_pos%3D1%26p_p_col_count%3D2">Read More <span class="hide-accessible">About Winners announced</span> &raquo;</a></div>

<div class="entry-footer">
<div class="entry-author">By <a href="/web/guest/member/-/member/userId/2352812">Robert Laubacher</a></div>

<div class="stats">&nbsp;</div>

<div class="clearfix">&nbsp;</div>
</div>
</div>

<div class="separator"><!-- --></div>

<div class="comm_news">
<div class="entry approved" id="_33_2364463">
<div class="entry-content">
<div class="entry-title">
<h2><a href="/community/-/blogs/improve-your-chance-to-win-a-prize?_33_redirect=http%3A%2F%2Fcrowdsensor.org%2Fcommunity%3Fp_p_id%3D33%26p_p_lifecycle%3D0%26p_p_state%3Dnormal%26p_p_mode%3Dview%26p_p_col_id%3Dcolumn-3%26p_p_col_pos%3D1%26p_p_col_count%3D2">Improve your chance to win a prize</a></h2>
</div>

<div class="comm_meta">
<div class="comm_date">October 4, 2016</div>
</div>
</div>

<div class="comm_div">&nbsp;</div>

<div class="entry-body">To members of the Crowdsensor community,&nbsp; As of October 4, there are twice as many entries in Crowdsensor about changes that will make life easier in the future vs. changes that will make life more difficult.&nbsp; The 5 prizes of $500 SGD will be awarded in this way:&nbsp; 2 for changes that will make life easier, 2 for changes that will make life more...<br />
<a href="/community/-/blogs/improve-your-chance-to-win-a-prize?_33_redirect=http%3A%2F%2Fcrowdsensor.org%2Fcommunity%3Fp_p_id%3D33%26p_p_lifecycle%3D0%26p_p_state%3Dnormal%26p_p_mode%3Dview%26p_p_col_id%3Dcolumn-3%26p_p_col_pos%3D1%26p_p_col_count%3D2">Read More <span class="hide-accessible">About Improve your chance to win a prize</span> &raquo;</a></div>

<div class="entry-footer">
<div class="entry-author">By <a href="/web/guest/member/-/member/userId/2352812">Robert Laubacher</a></div>

<div class="stats">&nbsp;</div>

<div class="clearfix">&nbsp;</div>
</div>
</div>

<div class="separator"><!-- --></div>

<div class="comm_news">
<div class="entry approved" id="_33_2364131">
<div class="entry-content">
<div class="entry-title">
<h2><a href="/community/-/blogs/deadline-for-entries-extended-to-october-10?_33_redirect=http%3A%2F%2Fcrowdsensor.org%2Fcommunity%3Fp_p_id%3D33%26p_p_lifecycle%3D0%26p_p_state%3Dnormal%26p_p_mode%3Dview%26p_p_col_id%3Dcolumn-3%26p_p_col_pos%3D1%26p_p_col_count%3D2">Deadline for entries extended to October 10</a></h2>
</div>

<div class="comm_meta">
<div class="comm_date">September 27, 2016</div>
</div>
</div>

<div class="comm_div">&nbsp;</div>

<div class="entry-body">To the Crowdsensor community, The Crowdsensor team has decided to extend the contest deadline.&nbsp; Entries are now due at 11:59:59 PM Singapore time on Monday, October 10 .&nbsp; To accommodate the longer submission period, we have&nbsp;eliminated the intermediate stage of selecting&nbsp;finalists.&nbsp; We encourage you to submit your ideas even before the final deadine to...<br />
<a href="/community/-/blogs/deadline-for-entries-extended-to-october-10?_33_redirect=http%3A%2F%2Fcrowdsensor.org%2Fcommunity%3Fp_p_id%3D33%26p_p_lifecycle%3D0%26p_p_state%3Dnormal%26p_p_mode%3Dview%26p_p_col_id%3Dcolumn-3%26p_p_col_pos%3D1%26p_p_col_count%3D2">Read More <span class="hide-accessible">About Deadline for entries extended to October 10</span> &raquo;</a></div>

<div class="entry-footer">
<div class="entry-author">By <a href="/web/guest/member/-/member/userId/2352812">Robert Laubacher</a></div>

<div class="stats">&nbsp;</div>

<div class="clearfix">&nbsp;</div>
</div>
</div>

<div class="separator"><!-- --></div>

<div class="comm_news">
<div class="entry approved" id="_33_2363432">
<div class="entry-content">
<div class="entry-title">
<h2><a href="/community/-/blogs/get-feedback-from-crowdsensor-s-judges?_33_redirect=http%3A%2F%2Fcrowdsensor.org%2Fcommunity%3Fp_p_id%3D33%26p_p_lifecycle%3D0%26p_p_state%3Dnormal%26p_p_mode%3Dview%26p_p_col_id%3Dcolumn-3%26p_p_col_pos%3D1%26p_p_col_count%3D2">Get feedback from Crowdsensor&#39;s judges</a></h2>
</div>

<div class="comm_meta">
<div class="comm_date">September 18, 2016</div>
</div>
</div>

<div class="comm_div">&nbsp;</div>

<div class="entry-body">To members of the Crowdsensor community,&nbsp; If you submit your entry by midnight this Friday, Sept 23, Crowdsensor&#39;s judges will provide feedback on your ideas before Wednesday, September 28.&nbsp; That means you&#39;ll can incorporate the judges&#39; input before the September 30 contest deadline--which means you&#39;ll&nbsp;have a&nbsp;better chance to win one of five&nbsp;$500 SGD...<br />
<a href="/community/-/blogs/get-feedback-from-crowdsensor-s-judges?_33_redirect=http%3A%2F%2Fcrowdsensor.org%2Fcommunity%3Fp_p_id%3D33%26p_p_lifecycle%3D0%26p_p_state%3Dnormal%26p_p_mode%3Dview%26p_p_col_id%3Dcolumn-3%26p_p_col_pos%3D1%26p_p_col_count%3D2">Read More <span class="hide-accessible">About Get feedback from Crowdsensor&#39;s judges</span> &raquo;</a></div>

<div class="entry-footer">
<div class="entry-author">By <a href="/web/guest/member/-/member/userId/2352812">Robert Laubacher</a></div>

<div class="stats">&nbsp;</div>

<div class="clearfix">&nbsp;</div>
</div>
</div>

<div class="separator"><!-- --></div>

<div class="comm_news">
<div class="entry approved" id="_33_2359778">
<div class="entry-content">
<div class="entry-title">
<h2><a href="/community/-/blogs/welcome-to-the-crowdsensor-?_33_redirect=http%3A%2F%2Fcrowdsensor.org%2Fcommunity%3Fp_p_id%3D33%26p_p_lifecycle%3D0%26p_p_state%3Dnormal%26p_p_mode%3Dview%26p_p_col_id%3Dcolumn-3%26p_p_col_pos%3D1%26p_p_col_count%3D2">Welcome to Crowdsensor!</a></h2>
</div>

<div class="comm_meta">
<div class="comm_date">August 1, 2016</div>
</div>
</div>

<div class="comm_div">&nbsp;</div>

<div class="entry-body">The team at the Risk Assessment and Horizon Scanning (RAHS) Programme Office of Singapore and the Center for Collective Intelligence&nbsp; at the Massachusetts Institute of Technology (MIT) are very excited to launch Crowdsensor, an online collective intelligence platform that invites the world community to contribute ideas about what trends might drive the future&nbsp;and what that might...<br />
<a href="/community/-/blogs/welcome-to-the-crowdsensor-?_33_redirect=http%3A%2F%2Fcrowdsensor.org%2Fcommunity%3Fp_p_id%3D33%26p_p_lifecycle%3D0%26p_p_state%3Dnormal%26p_p_mode%3Dview%26p_p_col_id%3Dcolumn-3%26p_p_col_pos%3D1%26p_p_col_count%3D2">Read More <span class="hide-accessible">About Welcome to Crowdsensor!</span> &raquo;</a></div>

<div class="entry-footer">
<div class="entry-author">By <a href="/web/guest/member/-/member/userId/2355501">Carolyn Fu</a></div>
</div>
</div>

<div class="separator"><!-- --></div>

<div class="taglib-page-iterator" id="_33_ocerSearchContainerPageIterator">&nbsp;</div>
</div>
</div>
</div>
</div>
</div>
');
INSERT INTO xcolab_ContentArticleVersion (contentArticleVersionId, contentArticleId, folderId, authorId, createDate, title, content) VALUES (594, 511, 10, 2348404, '2017-01-26 15:35:51', '18 - Get feedback', '<h1>Get feedback from Crowdsensor&#39;s judges</h1>

<div class="comm_news">
<div class="entry approved" id="_33_2363432">
<div class="entry-content">
<div class="comm_meta">
<div class="comm_date">September 18, 2016</div>
</div>
</div>

<div class="comm_div">&nbsp;</div>

<div class="entry-body">
<div>
<p>To members of the Crowdsensor community,&nbsp;</p>

<p>If you submit your entry by midnight this Friday, Sept 23, Crowdsensor&#39;s judges will provide feedback on your ideas before Wednesday, September 28.&nbsp;</p>

<p>That means you&#39;ll can incorporate the judges&#39; input before the September 30 contest deadline--which means you&#39;ll&nbsp;have a&nbsp;better chance to win one of five&nbsp;$500 SGD prizes.&nbsp;</p>

<p>Also, we&#39;ve made it easier to participate by simplifying the entry form--you now need to complete only two fields:</p>

<ul>
	<li>Describe the change</li>
	<li>How will this change affect Smart Nation Singapore in 2030?<br />
	&nbsp;</li>
</ul>

<p>We encourage you to input your ideas&nbsp;by the end of this week--even if they&#39;re still a work in progress--just go to the <a href="http://crowdsensor.org/trends">Trends tab</a> and select a category!&nbsp;</p>

<p>Looking forward to hearing what you&#39;re thinking,&nbsp;</p>

<p>The Crowdsensor Team</p>
</div>
</div>

<div class="entry-footer">
<div class="entry-author">By <a href="/web/guest/member/-/member/userId/2352812">Robert Laubacher</a></div>

<div class="clearfix">&nbsp;</div>
</div>
</div>

<div class="separator"><!-- --></div>
</div>
');
INSERT INTO xcolab_ContentArticleVersion (contentArticleVersionId, contentArticleId, folderId, authorId, createDate, title, content) VALUES (595, 510, 10, 2348404, '2017-01-26 15:36:35', '27 - Deadline for entries extended', '<div class="comm_news">
<div class="entry approved" id="_33_2364131">
<div class="entry-content">
<h1>Deadline for entries extended to October 10</h1>

<div class="comm_meta">
<div class="comm_date">September 27, 2016</div>
</div>
</div>

<div class="comm_div">&nbsp;</div>

<div class="entry-body">
<div>
<p>To the Crowdsensor community,</p>

<p>The Crowdsensor team has decided to extend the contest deadline.&nbsp;</p>

<p>Entries are now due at <strong>11:59:59 PM Singapore time on Monday, October 10</strong>.&nbsp;</p>

<p>To accommodate the longer submission period, we have&nbsp;eliminated the intermediate stage of selecting&nbsp;finalists.&nbsp;</p>

<p>We encourage you to submit your ideas even before the final deadine to receive input from other community members via their comments.&nbsp;</p>

<p>Thank you for your interest in Crowdsensor, we look forward to seeing your ideas on the platform!</p>

<p>Best regards,</p>

<p>The Crowdsensor Team</p>
</div>
</div>

<div class="entry-footer">
<div class="entry-author">By <a href="/members/profile/2352812">Robert Laubacher</a></div>

<div class="clearfix">&nbsp;</div>
</div>
</div>

<div class="separator"><!-- --></div>
</div>
');
INSERT INTO xcolab_ContentArticleVersion (contentArticleVersionId, contentArticleId, folderId, authorId, createDate, title, content) VALUES (596, 512, 11, 2348404, '2017-01-26 15:40:32', '01 - Welcome to crowdsensor', '<h1>Welcome to Crowdsensor!</h1>

<div class="comm_news">
<div class="entry approved">
<div class="entry-content">
<div class="comm_meta">
<div class="comm_date">August 1, 2016</div>
</div>
</div>

<div class="comm_div">&nbsp;</div>

<div class="entry-body">
<div>
<p>The team at the <a href="http://www.rahs.gov.sg/">Risk Assessment and Horizon Scanning (RAHS)</a> Programme Office of Singapore and the <a href="http://cci.mit.edu/">Center for Collective Intelligence&nbsp;</a>at the Massachusetts Institute of Technology (MIT) are very excited to launch Crowdsensor, an online collective intelligence platform that invites the world community to contribute ideas about what trends might drive the future&nbsp;and what that might mean for Singapore&#39;s Smart Nation effort.</p>

<p>Starting September 1, 2016, we will be opening the first round of competitions, which invite contributors to submit their ideas of about what trends might have a big impact in the future across 6 different categories: Security, Environment, Technology, Politics, Economy and Society.</p>

<p>This phase of the contest will end September 30. Between October 1-14, a set of judges will select a group of finalists. Between Oct 15-23, members of the Crowdsensor online community will then vote on what trends they think are best. The winners will be announced on October 28, and the authors of the 5 winning trends will each receive a prize of $500 SGD. Find out more about the competition <a href="/page/crowdsourcing">here</a>.</p>

<p>We hope you will join our community and help to brainstorm&nbsp;about the future by&nbsp;submitting your ideas about trends that might shape the smart cities of the future. By using&nbsp;this online&nbsp;platform, we envision that we&#39;ll be able to get a broader range of more diverse ideas about what could happen in coming years, and thus help to make Singapore and other cities future-resilient.</p>
</div>
</div>

<div class="entry-footer">
<div class="entry-author">By <a href="/members/profile/2355501">Carolyn Fu</a></div>

<div class="clearfix">&nbsp;</div>
</div>
</div>

<div class="separator"><!-- --></div>
</div>
');
INSERT INTO xcolab_ContentArticleVersion (contentArticleVersionId, contentArticleId, folderId, authorId, createDate, title, content) VALUES (597, 513, 12, 2348404, '2017-01-26 15:43:26', '04 - Improve your chance', '<h1>Improve your chance to win a prize</h1>

<div class="comm_news">
<div class="entry approved">
<div class="entry-content">
<div class="comm_meta">
<div class="comm_date">October 4, 2016</div>
</div>
</div>

<div class="comm_div">&nbsp;</div>

<div class="entry-body">
<div>
<p class="p1"><span class="s1">To members of the Crowdsensor community,&nbsp;</span></p>

<p class="p1"><span class="s1">As of October 4, there are twice as many entries in Crowdsensor about changes that will make life easier in the future vs. changes that will make life more difficult.&nbsp;</span></p>

<p class="p1"><span class="s1">The 5 prizes of $500 SGD will be awarded in this way:&nbsp;</span></p>

<ul class="ul1">
	<li class="li1"><span class="s1">2 for changes that will make life easier,</span></li>
	<li class="li1"><span class="s1">2 for changes that will make life more difficult,&nbsp;</span></li>
	<li class="li1"><span class="s1">1 for the most novel idea.&nbsp;</span><br />
	&nbsp;</li>
</ul>

<p class="p1"><span class="s1">Given that breakout, if you were to submit an entry now about a change that will make life more difficult, you might have a&nbsp;better chance to win.&nbsp;</span></p>

<p class="p1"><span class="s1">Here&#39;s a list of current entries by category:</span></p>

<ul class="ul1">
	<li class="li1"><a href="https://t.e2ma.net/click/phupj/t4iv3d/553l7d"><span class="s2">Economy</span></a><span class="s1">: 7 entries, 5 Easier, 2 More difficult</span></li>
	<li class="li1"><a href="https://t.e2ma.net/click/phupj/t4iv3d/ly4l7d"><span class="s2">Society</span></a><span class="s1">: 9 entries, 6 Easier, 3 More difficult</span></li>
	<li class="li1"><a href="https://t.e2ma.net/click/phupj/t4iv3d/1q5l7d"><span class="s2">Technology</span></a><span class="s1">: 11 entries, 8 Easier, 3 More difficult</span></li>
	<li class="li1"><a href="https://t.e2ma.net/click/phupj/t4iv3d/hj6l7d"><span class="s2">Environment</span></a><span class="s1">: 5 entries, 4 Easier, 1 More difficult</span></li>
	<li class="li1"><a href="https://t.e2ma.net/click/phupj/t4iv3d/xb7l7d"><span class="s2">Politics</span></a><span class="s1">: 5 entries, 3 Easier, 2 More difficult</span></li>
	<li class="li1"><a href="https://t.e2ma.net/click/phupj/t4iv3d/d47l7d"><span class="s2">Security</span></a><span class="s1">: 5 entries, 2 Easier, 3 More difficult</span></li>
	<li class="li1"><span class="s1"><a href="http://crowdsensor.org/trends">Overall</a>: 42 entries, 28 Easier, 14 More difficult</span><br />
	&nbsp;</li>
</ul>

<p class="p1"><span class="s1">A reminder that the&nbsp;<b>contest deadline is</b>&nbsp;<b>11:59:59 PM Singapore time on Monday, October 10</b>.&nbsp;</span></p>

<p class="p1">Thank you for your interest in <a href="https://t.e2ma.net/click/phupj/t4iv3d/tw8l7d"><span class="s2">Crowdsensor</span></a>, we look forward to seeing your ideas.</p>

<p class="p2">The Crowdsensor Team</p>
</div>
</div>

<div class="entry-footer">
<div class="entry-author">By <a href="/members/profile/2352812">Robert Laubacher</a></div>

<div class="clearfix">&nbsp;</div>
</div>
</div>

<div class="separator"><!-- --></div>
</div>
');
INSERT INTO xcolab_ContentArticleVersion (contentArticleVersionId, contentArticleId, folderId, authorId, createDate, title, content) VALUES (598, 514, 12, 2348404, '2017-01-26 15:46:35', '28 - Winners announced', '<h1>Winners announced</h1>

<div class="entry approved">
<div class="entry-content">
<div class="comm_meta">
<div class="comm_date">October 28, 2016</div>
</div>
</div>

<div class="comm_div">&nbsp;</div>

<div class="entry-body">
<div>
<p class="p1"><span class="e2ma-style">We are pleased to announce the winners of the 2016 Crowdsensor Smart Nation competition.</span></p>

<p class="p1"><span>70 entries were submitted and 5 selected as winners by the panel of&nbsp;</span><a href="http://crowdsensor.org/web/guest/people">judges</a><span>.&nbsp;</span></p>

<p class="p1"><span class="e2ma-style">2 were for trends expected to make life easier for Singapore by 2030:&nbsp;</span></p>

<p class="p1"><a href="http://crowdsensor.org/trends/2016/politics/c/trend/1327711"><span><b>Simulated Government - Not sure? Run a sim first!</b></span></a><span><a href="http://crowdsensor.org/trends/2016/politics/c/trend/1327711">&nbsp;</a>&nbsp;&nbsp;<br />
Advances in virtual intelligence and simulation technology will enable policy makers and the public to experiment before implementation. &nbsp;&nbsp;<br />
<i>by&nbsp;</i></span><span><i>Sulaiman Daud</i></span></p>

<p class="p2"><a href="http://crowdsensor.org/trends/2016/society/c/trend/1327714"><span class="s7"><span class="e2ma-style"><b>Managing our emotional wellness</b></span></span></a><span class="s4"><span class="e2ma-style"><b>&nbsp;</b><br />
I believe most of us are concerned with our physical wellness, but how about our emotional wellness? Do we take care of it?<br />
<i>by Vinleon Koh</i></span></span></p>

<p class="p2"><span class="e2ma-style">2 winners were for trends expected to make life more difficult for Singapore by 2030:</span></p>

<p class="p1"><a href="http://crowdsensor.org/trends/2016/politics/c/trend/1327716"><span class="s1"><b><span class="e2ma-style">Techolitics - When technology and international politics collide</span></b></span></a><br />
<span class="s3">State backed fragmentation of standards, operating systems, platforms for selected emerging technologies. &nbsp;</span><span class="s3">b<i>y W.M. Hum</i></span></p>

<p class="p2"><a href="http://crowdsensor.org/trends/2016/politics/c/trend/1327716"><span class="s1"><b>AI Replacing People, Taking Away Jobs</b></span></a><br />
<span class="s3">Artificial intelligence and automation may soon replace people in existing jobs. &nbsp;&nbsp;</span><br />
<span class="s3"><span class="e2ma-style">b</span><i><span class="e2ma-style">y Ronald Wong</span></i></span></p>

<p class="p1"><span class="e2ma-style">1 winner was selected as the trend judged to be most novel/unique:&nbsp;</span></p>

<p class="p1"><span class="e2ma-style"><a href="http://crowdsensor.org/trends/2016/economy/c/trend/1327701"><strong>Good deeds sensed and rewarded</strong></a>&nbsp;&nbsp;&nbsp;<br />
Positive civic actions are sensed with smart city technology and monetarily rewarded. &nbsp;&nbsp;</span><br />
<span class="e2ma-style"><i>by Mike Matessa</i>&nbsp;</span></p>

<p class="p1"><span class="e2ma-style">In addition, 7 entries received Honourable Mention:</span></p>

<p class="p1"><a href="http://crowdsensor.org/trends/2016/politics/c/trend/1327611"><span class="s1"><span class="e2ma-style">Anticipatory Personalized Governance&nbsp; &nbsp;</span></span></a><br />
<span class="s2"><span class="e2ma-style">Data Analytics would enable government to anticipate citizens&#39; demand and tailor its response based on individual&#39;s characteristics.&nbsp;<i>by Danliang Ho</i></span></span></p>

<p class="p1"><a href="http://crowdsensor.org/trends/2016/society/c/trend/1327829"><span class="e2ma-style">The Smart Civilization: Wither Identity</span></a><span class="e2ma-style">&nbsp; &nbsp;<br />
The fast-paced evolution into a smart city will come at the expense of cultural and individual identity.&nbsp;<i>by&nbsp;Sidtharthan Manunethi</i></span></p>

<p class="p1"><a href="http://crowdsensor.org/trends/2016/politics/c/trend/1327710"><span class="e2ma-style">50 Shades of Corporate - A State-Led Cyberocracy with Strong Corporate Influence</span></a><span class="e2ma-style">&nbsp;&nbsp;&nbsp;<br />
Decline in leadership qualities leads to stupid nation.&nbsp;<i>by&nbsp;Sidtharthan Manunethi</i></span></p>

<p class="p1"><span class="e2ma-style"><a href="http://crowdsensor.org/trends/2016/economy/c/trend/1327612">Emergent Algorithm-Based Society&nbsp;</a>&nbsp;&nbsp;<br />
Singapore will transit from Knowledge-Based Economy to Algorithm-Based Economy by 2030 in the advent of Algorithmic Age.&nbsp;<i>by Danliang Ho</i>&nbsp;&nbsp;</span></p>

<p class="p1"><a href="http://crowdsensor.org/trends/2016/security/c/trend/1327804"><span class="e2ma-style">UAVs will stand vigilant as Smart Nations&#39; first responders</span></a><span class="e2ma-style">&nbsp;&nbsp;&nbsp;<br />
A fleet of UAVs will be in the air at all times, ready to be activated and render assistance to citizens in peril.&nbsp;<i>by Iqbal Khirudeen</i></span></p>

<p class="p1"><a href="http://crowdsensor.org/trends/2016/technology/c/trend/1327705"><span class="e2ma-style">Floors that provide electricity</span></a><span class="e2ma-style">&nbsp;&nbsp;&nbsp;<br />
What if one day we can harness usable energy by just walking, Will you help to save the polar bears by walking?&nbsp;<i>by Benjamin HM Wang</i></span></p>

<p class="p1"><a href="http://crowdsensor.org/trends/2016/technology/c/trend/1327708"><span class="e2ma-style">Smart Utensils for the Future</span></a><span class="e2ma-style">&nbsp;&nbsp;&nbsp;</span><br />
<span class="e2ma-style">Smart utensils to support the growth of the market for elder consumers and national initiatives in active ageing.&nbsp;</span><em><span class="e2ma-style">by Manjusri ALP</span></em></p>

<p class="p1"><span>Sincere thanks to all Crowdsensor members for their participation and to contest entrants for their intriguing ideas.&nbsp;</span></p>

<p class="p1"><span>Sincerely,&nbsp;<br />
The Crowdsensor Team</span></p>
</div>
</div>

<div class="entry-footer">
<div class="entry-author">By <a href="/members/profile/2352812">Robert Laubacher</a></div>

<div class="clearfix">&nbsp;</div>
</div>
</div>
');
INSERT INTO xcolab_ContentArticleVersion (contentArticleVersionId, contentArticleId, folderId, authorId, createDate, title, content) VALUES (607, 515, 5, 2348404, '2017-01-26 16:34:32', 'Homepage Bottom', '<div class="inner">
<div class="home-left">
<div class="colabwidget" data-url="/randomproposalswidget">&nbsp;</div>
</div>

<div class="home-right">
<div class="colabwidget" data-article-id="507">&nbsp;</div>

<div class="colabwidget" data-url="/feedswidget">&nbsp;</div>
</div>
</div>
');
INSERT INTO xcolab_ContentArticleVersion (contentArticleVersionId, contentArticleId, folderId, authorId, createDate, title, content) VALUES (608, 506, 8, 2348404, '2017-01-26 16:38:56', 'Navigation Discussion', '<div class="proposal-head">
<div class="inner">
<div class="headline">
<h1>Community Discussions</h1>
</div>
<!-- /headline -->

<ul class="c-TabBar clearfix">
	<li class="c-TabBar__tab--first"><a href="/members">Members</a></li>
	<li class="c-TabBar__tab active"><a href="/discussion">Discussion</a></li>
	<li class="c-TabBar__tab--last"><a href="/page/news">News</a></li>
</ul>
</div>
<!-- /inner --><!-- /proposal-head --></div>
');
INSERT INTO xcolab_ContentArticleVersion (contentArticleVersionId, contentArticleId, folderId, authorId, createDate, title, content) VALUES (609, 505, 8, 2348404, '2017-01-26 16:39:17', 'Navigation Members', '<div style="margin-bottom: 10px">
<div>
<div class="headline">
<h1>Community Members</h1>
</div>
<!-- /headline -->

<ul class="c-TabBar clearfix">
	<li class="c-TabBar__tab--first active"><a href="/members">Members</a></li>
	<li class="c-TabBar__tab"><a href="/discussion">Discussion</a></li>
	<li class="c-TabBar__tab--last"><a href="/page/news">News</a></li>
</ul>
</div>
</div>
');
INSERT INTO xcolab_ContentArticleVersion (contentArticleVersionId, contentArticleId, folderId, authorId, createDate, title, content) VALUES (610, 494, 2, 2348404, '2017-01-26 16:44:24', 'Page Footer', '<div id="footleft"><a href="http://cci.mit.edu/" target="_blank"><img alt="MIT" src="/images/footer_logo.png" style="float: left; width: 210px; height: 51px;" /></a><a href="http://www.rahs.gov.sg/" target="_blank"><img alt="" src="/images/rahs-logo-footer.png" style="text-align: right; width: 202px; height: 51px;" /></a></div>

<div id="footright">
<div id="txt" style="text-align: right;">Your use of Crowdsensor is subject to our<br />
<a href="http://creativecommons.org/licenses/by-nc-sa/3.0/us/">Creative Commons License</a> and other <a href="/wiki/Terms%20of%20use">Terms of Use</a>.</div>

<div id="cc"><img alt="CC" height="31" src="/images/cc_logo.gif" style="float: right;" width="88" /></div>
</div>
');
INSERT INTO xcolab_ContentArticleVersion (contentArticleVersionId, contentArticleId, folderId, authorId, createDate, title, content) VALUES (611, 490, 5, 2348404, '2017-01-26 16:44:56', 'Homepage', '<div class="p-Homepage__homespot">
<div class="topshade">&nbsp;</div>

<div class="p-Homepage__homespot__background clearfix">
<div class="inner">
<div class="homespot-left">
<h2 style="text-transform: none; margin-top: 35px; margin-bottom: 35px;"><strong>Imagine it is the year 2030</strong>...<br />
In Crowdsensor, you can&nbsp;brainstorm with the community about what changes could impact Singapore&#39;s plans to become a Smart Nation&nbsp;</h2>
<a class="learn large" href="/web/guest/get-involved" style="text-align: center; padding-top: 5px; padding-bottom: 5px; vertical-align: middle;">How you can<br />
participate</a> <a href="/web/guest/smart-nation" style="text-align: center; vertical-align: middle; background: transparent; text-align: center; display: inline-block; margin-left: 40px; font-size: 16px; font-weight: 400; text-transform: uppercase;">Learn more about<br />
Smart Nation Singapore</a>

<div style="height:90px;width:1px;">&nbsp;</div>

<div class="clearfix">
<div style="width: 530px; font-size: 15px; font-weight: 300;">Crowdsensor is a project of the <a href="http://cci.mit.edu/">Center for Collective Intelligence</a> at the Massachusetts Institute of Technology and the <a href="http://www.rahs.gov.sg/public/www/home.aspx">Risk Assessment and Horizon Scanning (RAHS) Programme Office</a> of the Republic of Singapore</div>

<div style="width: 530px; margin-top: 10px;"><img src="/images/logo_mit_themed.png" style="display: inline; vertical-align: bottom;" /> <img src="/images/rahs-logo-footer.png" style="position: absolute; right: 0; bottom: 0" width="200" /></div>
</div>
<!-- /homespot-left --></div>

<div id="yui_patched_v3_11_0_1_1477660557950_390" style="position: absolute; bottom: 200px; right: 190px; text-align: center; vertical-align: top">
<div style="font-size: 17px;font-weight: 500;">Winners announced</div>
<a href="http://crowdsensor.org/community/-/blogs/winners-announced" style="text-transform: uppercase;">See list of winners</a><br />
5 prizes of $500 SGD to be won</div>

<div style="position: absolute; bottom: 212px; right: 0; text-align: center; vertical-align: top"><a class="get large" href="/web/guest/loginregister">Register now</a></div>
<!-- /inner --></div>
<!-- /p-Homepage__homespot__background --></div>
<!-- /p-Homepage__homespot --></div>
');