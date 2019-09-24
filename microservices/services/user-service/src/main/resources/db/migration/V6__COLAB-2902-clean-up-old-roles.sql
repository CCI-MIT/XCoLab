delete from user__role where id in (31213, 10121, 38133, 1013998, 1289509, 1423086, 1427577);
delete from user__user_role where role_id in (31213, 10121, 38133, 1013998, 1289509, 1423086, 1427577);
delete from user__member_category where role_id in (31213);

DROP TABLE IF EXISTS xcolab_RolesCategory;
