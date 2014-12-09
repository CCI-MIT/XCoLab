

UPDATE xcolab_ContestEmailTemplate SET header = '
Proposal: <proposal-title/>
Contest:  <contest-title/>

Thank you for your Climate CoLab contest entry.  We appreciate your willingness to share your ideas and also the time and effort you put into developing a proposal and submitting it to the Climate CoLab site.
 
We, the Judges, have strongly considered your proposal and found that it contained intriguing elements; however, we have chosen to not advance it to the next round of competition.  Below, we’ve included feedback that we hope will be useful.  This feedback has also been posted to the comment section of your proposal.

We encourage you to keep developing your proposal.  Transfer it to the 2014 Proposal Workspace to re-open it, make edits, add collaborators, and even submit it into a future contest.  You can do so by logging into your account, opening your proposal, selecting the Admin tab, and clicking “Move proposal”.

We welcome you to stay involved in the Climate CoLab community: support and comment on proposals that have been named Semi-Finalists and finalists, and even volunteer to join one those teams if you have relevant expertise.  Voting period will open in a few weeks, where you can help select the contest’s Popular Choice Winner. We also hope you’ll attend our Crowds & Climate Conference this fall, either in person or virtually (see 2013 conference: http://climatecolab.org/conference2013 ).
 
If you have questions or concerns, please contact the Climate CoLab staff at admin@climatecolab.org.
 
Keep up the great work. We hope that the Climate CoLab community, working together, can create solutions that wouldn’t be possible otherwise.
 
2014 Climate CoLab Judges

COMMENTS FROM JUDGES:' WHERE type_ = 'ADVANCING_DO_NOT_ADVANCE';

UPDATE xcolab_ContestEmailTemplate SET header = 'Proposal: <proposal-title/>
Contest:  <contest-title/>

Thank you for your Climate CoLab contest entry.  We appreciate your willingness to share your ideas and also the time and effort you put into developing a proposal and submitting it to the Climate CoLab site.
 
As your entry was missing content in some of the key fields of the proposal template, we were not able to advance it to the next round of competition.

We encourage you to keep developing your idea.  Transfer your proposal to the 2014 Proposal Workspace to re-open it, make edits, add collaborators, and even submit it into a future contest.  You can do so by logging into your account, opening your proposal, selecting the Admin tab, and clicking “Move proposal”.

We welcome you to stay involved in the Climate CoLab community: support and comment on proposals that have been named Semi-Finalists and finalists, and even volunteer to join one those teams if you have relevant expertise.  Voting period will open in a few weeks, where you can help select the contest’s Popular Choice Winner. We also hope you’ll attend our Crowds & Climate Conference this fall, either in person or virtually (see 2013 conference: http://climatecolab.org/conference2013 ).
 
If you have questions or concerns, please contact the Climate CoLab staff at admin@climatecolab.org.
 
Keep up the great work. We hope that the Climate CoLab community, working together, can create solutions that wouldn’t otherwise be possible.
 
2014 Climate CoLab Judges

COMMENTS FROM JUDGES:' WHERE type_ = 'SCREENING_DO_NOT_ADVANCE_INCOMPLETE';


UPDATE xcolab_ContestEmailTemplate SET header = 'Proposal: <proposal-title/>
Contest:  <contest-title/>

Thank you for your Climate CoLab contest entry.  We appreciate your willingness to share your ideas and also the time and effort you put into developing a proposal and submitting it to the Climate CoLab site.
 
Your entry did not address key aspects of the contest prompt, so we were not able to advance it to the next round of competition.

We encourage you to keep developing your idea.  Transfer your proposal to the 2014 Proposal Workspace to re-open it, make edits, add collaborators, and even submit it into a future contest.  You can do so by logging into your account, opening your proposal, selecting the Admin tab, and clicking “Move proposal”.

We welcome you to stay involved in the Climate CoLab community: support and comment on proposals that have been named Semi-Finalists and finalists, and even volunteer to join one those teams if you have relevant expertise.  Voting period will open in a few weeks, where you can help select the contest’s Popular Choice Winner. We also hope you’ll attend our Crowds & Climate Conference this fall, either in person or virtually (see 2013 conference: http://climatecolab.org/conference2013 ).

If you have questions or concerns, please contact the Climate CoLab staff at admin@climatecolab.org.
 
Keep up the great work. We hope that the Climate CoLab community, working together, can create solutions that wouldn’t otherwise be possible.
 
2014 Climate CoLab Judges

COMMENTS FROM JUDGES:' WHERE type_ = 'SCREENING_DO_NOT_ADVANCE_OFF_TOPIC';

UPDATE xcolab_ContestEmailTemplate SET header = 'Proposal: <proposal-title/>
Contest:  <contest-title/>

Thank you for your Climate CoLab contest entry.  We appreciate your willingness to share your ideas and also the time and effort you put into developing a proposal and submitting it to the Climate CoLab site.

Your entry did not address key aspects of the contest prompt, so we were not able to advance it to the next round of competition.

We encourage you to keep developing your idea.  Transfer your proposal to the 2014 Proposal Workspace to re-open it, make edits, add collaborators, and even submit it into a future contest.  You can do so by logging into your account, opening your proposal, selecting the Admin tab, and clicking “Move proposal”.

We welcome you to stay involved in the Climate CoLab community: support and comment on proposals that have been named Semi-Finalists and finalists, and even volunteer to join one those teams if you have relevant expertise.  Voting period will open in a few weeks, where you can help select the contest’s Popular Choice Winner. We also hope you’ll attend our Crowds & Climate Conference this fall, either in person or virtually (see 2013 conference: http://climatecolab.org/conference2013 ).

If you have questions or concerns, please contact the Climate CoLab staff at admin@climatecolab.org.

Keep up the great work. We hope that the Climate CoLab community, working together, can create solutions that wouldn’t otherwise be possible.

2014 Climate CoLab Judges

COMMENTS FROM JUDGES:' WHERE type_ = 'SCREENING_DO_NOT_ADVANCE_OTHER';