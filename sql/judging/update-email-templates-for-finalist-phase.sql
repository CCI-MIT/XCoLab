UPDATE `xcolab_ContestEmailTemplate` SET `type_`='semifinalistphase_ADVANCING_ADVANCE_TO_SEMIFINALIST' WHERE `type_`='ADVANCING_ADVANCE_TO_SEMIFINALIST';
UPDATE `xcolab_ContestEmailTemplate` SET `type_`='semifinalistphase_ADVANCING_DO_NOT_ADVANCE' WHERE `type_`='ADVANCING_DO_NOT_ADVANCE';


INSERT INTO xcolab_ContestEmailTemplate (type_, subject, header, footer) VALUES (
'ADVANCING_DO_NOT_ADVANCE', 'Climate CoLab Judging Results', 'Proposal: <proposal-title/>
Contest:  <contest-title/>

Thank you for participating in the 2014 Climate CoLab <contest-title/> contest, and for the time you spent in creating and revising your entry.

The Judges have strongly considered your proposal and your revisions, and have chosen to not advance it as a Finalist for this contest.  However, we, the Judges and contest Fellows, are truly grateful for your contribution to the Climate CoLab and for your commitment to address climate change.

We encourage you to keep developing your work. Transfer it to the Proposal Workspace to re-open it, make edits, add collaborators, and even submit it into a future contest. You can do so by logging into your account, opening your proposal, selecting the Admin tab, and clicking “Move proposal”.

We hope you will stay involved in the Climate CoLab community. Please support and comment on proposals that have been named Finalists and vote for which proposal you would like to be nominated as the contest’s Popular Choice Winner. We also hope you’ll attend our Crowds & Climate Conference this fall, either in person or virtually.

If you have questions, please contact the Climate CoLab staff at <a href="mailto:admin@climatecolab.org">admin@climatecolab.org</a>. 

Keep up the great work.  And thank you again for being a part of this mission to harness the world’s collective efforts to develop and share innovative climate change solutions.

2014 Climate CoLab Judges 

<i>If there are specific comments from the Judges, they will be included below and on your proposal’s comments page.</i>

', ''),
('ADVANCING_ADVANCE_TO_SEMIFINALIST', 'Climate CoLab Judging Results', 'Congratulations! Your proposal, “<proposal-title/>” in the <contest-title/> contest, has been selected to advance to the Finalists round.

Be proud of your accomplishment – over 500 proposals were submitted this year and only a very small number have been advanced through these two rounds of judging.

As a Finalist, your proposal is eligible for the contest’s Judges Choice award, as well as the contest’s Popular Choice award, which is determined by public voting.  

If you haven’t already, you will soon receive an email from the Climate CoLab staff with details about the voting period.  If you don’t receive that email within the next day, or have other questions, please contact Laur Fisher and the Climate CoLab staff at admin@climatecolab.org.

All winners will be announced the week after the voting period ends, on September 20, 2014 at midnight Eastern Time.

Both Judges Choice and Popular Choice winners will be connected with people who can support the implementation of their proposal, which may include policy makers, business executives, NGO and foundation officials, scientists, and others. They will be recognized and publicized by the MIT Climate CoLab and invited to showcase their proposals at a conference held at MIT November 5-7, 2014, where a $10,000 Grand Prize will be awarded (see 2013 conference: <a href="http://climatecolab.org/conference2013">http://climatecolab.org/conference2013</a>). Some contests have additional prizes given by the contest sponsor.

Thank you for your work on this very important issue.  We’re proud of your proposal, and we hope that you are too. Again, congratulations! 

2014 Climate CoLab Judges 

<i>If there are specific comments from the Judges, they will be included below and on your proposal’s comments page.</i>

', '');