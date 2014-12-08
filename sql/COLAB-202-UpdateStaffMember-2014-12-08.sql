update xcolab_staffmember set role="CCI Associate Director" where id_=149;
update xcolab_staffmember set role="Project Manager" where id_=150;
update xcolab_staffmember set categoryId=8, id_=155, role="" where id_=317;
update xcolab_staffmember set userId=1003305, photoUrl="" where id_=159;
update xcolab_staffmember set photoUrl="https://fbcdn-sphotos-e-a.akamaihd.net/hphotos-ak-frc3/v/t1.0-9/559760_529631463785266_1319441340_n.jpg?oh=888f25bc1e9033c472058ca30bee0534&oe=550F9D89&__gda__=1426450045_30abccd54194dc9bd928c74a12dca7fe" where id_=161;
insert into xcolab_staffmember (id_,userID, categoryId, firstNames, lastName, url, photoUrl, role, organization, sort) values(317, 1947856, 5,"","","","", "Software Engineer","", 9);