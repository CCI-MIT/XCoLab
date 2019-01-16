INSERT INTO user__user (id, screen_name, email_address, is_email_confirmed, first_name, last_name, hashed_password, created_at, updated_at, password_updated_at, country, short_bio, facebook_id, google_id, open_id, login_ip, login_date, status, forgot_password_token, forgot_password_token_expire_time, portrait_file_entry_id, report_karma, auto_registered_member_status, uuid, login_token_id, login_token_key, login_token_expiration_date)
    VALUES (10144, 'admin', 'admin+u10144@example.com', 0, 'Admin', 'CoLab', 'PBKDF2_160_128000_g81/ioSNrXo=_pRj14MUWBMdoAVhbI7pqUlGdrII=', '2009-08-19 01:00:00', '2009-08-19 01:00:00', '2009-08-19 01:00:00', 'United States', '', 0, null, '', '127.0.0.1', '2009-08-19 01:00:00', 0, null, null, 0, 100, 0, null, null, null, null);
INSERT INTO user__user (id, screen_name, email_address, is_email_confirmed, first_name, last_name, hashed_password, created_at, updated_at, password_updated_at, country, short_bio, facebook_id, google_id, open_id, login_ip, login_date, status, forgot_password_token, forgot_password_token_expire_time, portrait_file_entry_id, report_karma, auto_registered_member_status, uuid, login_token_id, login_token_key, login_token_expiration_date)
    VALUES (10145, 'member', 'member+u10145@example.com', 0, 'Member', 'CoLab', 'PBKDF2_160_128000_g81/ioSNrXo=_pRj14MUWBMdoAVhbI7pqUlGdrII=', '2009-08-19 01:00:00', '2009-08-19 01:00:00', '2009-08-19 01:00:00', 'United States', '', 0, null, '', '127.0.0.1', '2009-08-19 01:00:00', 0, null, null, 0, 100, 0, null, null, null, null);
INSERT INTO user__user (id, screen_name, email_address, is_email_confirmed, first_name, last_name, hashed_password, created_at, updated_at, password_updated_at, country, short_bio, facebook_id, google_id, open_id, login_ip, login_date, status, forgot_password_token, forgot_password_token_expire_time, portrait_file_entry_id, report_karma, auto_registered_member_status, uuid, login_token_id, login_token_key, login_token_expiration_date)
    VALUES (10146, 'deletedMember', 'member+u10146@example.com', 0, 'Member', 'CoLab', 'PBKDF2_160_128000_g81/ioSNrXo=_pRj14MUWBMdoAVhbI7pqUlGdrII=', '2009-08-19 01:00:00', '2009-08-19 01:00:00', '2009-08-19 01:00:00', 'United States', '', 0, null, '', '127.0.0.1', '2009-08-19 01:00:00', 5, null, null, 0, 100, 0, null, null, null, null);

INSERT INTO user__user_role (user_id, role_id) VALUES (10144, 10118);
INSERT INTO user__user_role (user_id, role_id) VALUES (10144, 10122);
INSERT INTO user__user_role (user_id, role_id) VALUES (10145, 10122);
INSERT INTO user__user_role (user_id, role_id) VALUES (10146, 10122);

INSERT INTO user__member_category (role_id, display_name, category_name, sort_order, show_in_list, image_name, description)
  VALUES (10118, 'Admin', 'Admin', 1, 0, 'icon_mem-staff', '');
INSERT INTO user__member_category (role_id, display_name, category_name, sort_order, show_in_list, image_name, description)
  VALUES (10122, 'Member', 'Members', 2, 1, 'icon_mem-member', '');
