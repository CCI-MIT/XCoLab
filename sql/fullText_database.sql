ALTER TABLE user__user
    ADD FULLTEXT(`first_name`,`last_name`,`short_bio`,`screen_name`);

ALTER TABLE contest__proposal_attribute
    ADD FULLTEXT(`string_value`);


ALTER TABLE contest__contest
    ADD FULLTEXT(`description`);


ALTER TABLE comment__comment
    ADD FULLTEXT(`content`);
