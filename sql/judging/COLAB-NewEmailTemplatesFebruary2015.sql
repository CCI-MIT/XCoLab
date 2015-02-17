delete from xcolab_ContestEmailTemplate where type_="SCREENING_DO_NOT_ADVANCE_OTHER";
insert into xcolab_ContestEmailTemplate (type_,subject, header, footer) values("SCREENING_DO_NOT_ADVANCE_OTHER","Your proposal in the Climate CoLab","TBD","Note: The decisions of the Judges are final. If you have questions, please contact the Climate CoLab staff admin@climatecolab.org.");
UPDATE xcolab_ContestEmailTemplate SET header ='
Proposal: <proposal-title/>
Contest:  <contest-title/>

Thank you for your contest entry.  We appreciate your willingness to share your ideas and also the time and effort you put into developing a proposal and submitting it to the contest.

We, the Judges, have strongly considered your proposal and found that it contained intriguing elements; however, we have chosen to not advance it to the next round of competition.

We encourage you to keep developing your idea.  Transfer your proposal to the Proposal Workspace to re-open it, make edits, add collaborators, and even submit it into a future contest.  You can do so by logging into your account, opening your proposal, selecting the Admin tab, and clicking \"Move proposal\".

We welcome you to stay involved in the Climate CoLab community: support and comment on proposals that have been named Semi-Finalists and finalists, and even volunteer to join one those teams if you have relevant expertise.  During the voting period, you can help select the contest''s Popular Choice Winner.  The Climate CoLab will be opening more contests in the coming months, and you are welcome to submit your proposals to those contests as well.

Keep up the great work. We hope that by working together, we all can create solutions that wouldn''t otherwise be possible.

Contest Judges

If there are additional comments from the Judges, they will be included below.


COMMENTS FROM JUDGES:' WHERE type_ = 'SCREENING_DO_NOT_ADVANCE_OTHER';

delete from xcolab_ContestEmailTemplate where type_="SCREENING_DO_NOT_ADVANCE_OFF_TOPIC";
insert into xcolab_ContestEmailTemplate (type_,subject, header, footer) values("SCREENING_DO_NOT_ADVANCE_OFF_TOPIC","Your proposal in the Climate CoLab","TBD","Note: The decisions of the Judges are final. If you have questions, please contact the Climate CoLab staff admin@climatecolab.org.");
UPDATE xcolab_ContestEmailTemplate SET header ='
Proposal: <proposal-title/>
Contest:  <contest-title/>

Thank you for your contest entry.  We appreciate your willingness to share your ideas and also the time and effort you put into developing a proposal and submitting it to the contest.

Your entry did not address key aspects of the contest prompt, so we were not able to advance it to the next round of competition.

We encourage you to keep developing your idea.  Transfer your proposal to the Proposal Workspace to re-open it, make edits, add collaborators, and even submit it into a future contest.  You can do so by logging into your account, opening your proposal, selecting the Admin tab, and clicking \"Move proposal\".

We welcome you to stay involved in the Climate CoLab community: support and comment on proposals that have been named Semi-Finalists and finalists, and even volunteer to join one those teams if you have relevant expertise.  During the voting period, you can help select the contest''s Popular Choice Winner.  The Climate CoLab will be opening more contests in the coming months, and you are welcome to submit your proposals to those contests as well.

Keep up the great work. We hope that by working together, we all can create solutions that wouldn''t otherwise be possible.

Contest Judges

If there are additional comments from the Judges, they will be included below.

COMMENTS FROM JUDGES:' WHERE type_ = 'SCREENING_DO_NOT_ADVANCE_OFF_TOPIC';


delete from xcolab_ContestEmailTemplate where type_="SCREENING_DO_NOT_ADVANCE_INCOMPLETE";
insert into xcolab_ContestEmailTemplate (type_,subject, header, footer) values("SCREENING_DO_NOT_ADVANCE_INCOMPLETE","Your proposal in the Climate CoLab","TBD","Note: The decisions of the Judges are final. If you have questions, please contact the Climate CoLab staff admin@climatecolab.org.");
UPDATE xcolab_ContestEmailTemplate SET header ='
Proposal: <proposal-title/>
Contest:  <contest-title/>

Thank you for your contest entry.  We appreciate your willingness to share your ideas and also the time and effort you put into developing a proposal and submitting it to the contest.

As your entry was missing content in some of the key fields of the proposal template, we were not able to advance it to the next round of competition.

We encourage you to keep developing your idea.  Transfer your proposal to the Proposal Workspace to re-open it, make edits, add collaborators, and even submit it into a future contest.  You can do so by logging into your account, opening your proposal, selecting the Admin tab, and clicking \"Move proposal\".

We welcome you to stay involved in the Climate CoLab community: support and comment on proposals that have been named Semi-Finalists and finalists, and even volunteer to join one those teams if you have relevant expertise.  During the voting period, you can help select the contest''s Popular Choice Winner.  The Climate CoLab will be opening more contests in the coming months, and you are welcome to submit your proposals to those contests as well.

Keep up the great work. We hope that by working together, we all can create solutions that wouldn''t otherwise be possible.

Contest Judges

If there are additional comments from the Judges, they will be included below.


COMMENTS FROM JUDGES:' WHERE type_ = 'SCREENING_DO_NOT_ADVANCE_INCOMPLETE';


delete from xcolab_ContestEmailTemplate where type_="ADVANCING_ADVANCE_TO_SEMIFINALIST";
insert into xcolab_ContestEmailTemplate (type_,subject, header, footer) values("ADVANCING_ADVANCE_TO_SEMIFINALIST","Congratulations!  Your proposal has been selected as a Semi-Finalist!","TBD","Note: The decisions of the Judges are final. If you have questions, please contact the Climate CoLab staff admin@climatecolab.org.");
UPDATE xcolab_ContestEmailTemplate SET header ='

Congratulations!  Your proposal, <proposal-title/>  in the <contest-title/> contest, has been selected to advance to the Semi-Finalists round.

You will be able to revise your proposal and add new collaborators if you wish, from now until March 31, 2015, midnight Eastern Time.

We''ve also included feedback that will be posted to the comment section of your proposal.  Please incorporate this feedback in your revisions, or your proposal may not be advanced to the Finalists round.  As you make revisions, we recommend you save an offline copy as a backup.

At the revision deadline listed below, your proposal will be locked and considered in final form.  The Judges will undergo another round of evaluation to ensure that Semi-Finalist proposals have addressed the feedback given, and select which proposals will continue to the Finalists round.  Finalists are eligible for the contest''s Judges Choice award, as well as for public voting to select the contest''s Popular Choice award.

Thank you for your great work and again, congratulations!

Contest Judges

If there are additional comments from the Judges, they will be included below.

COMMENTS FROM JUDGES:' WHERE type_ = 'ADVANCING_ADVANCE_TO_SEMIFINALIST';