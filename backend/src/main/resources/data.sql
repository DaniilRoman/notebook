-- first admin user
insert into account values (uuid_generate_v4(), 'admin',
                            '$2a$10$snggq7luRl70AT/SLQxgkegH6tfiTv9JqPrnuUYYCzV8FcXm5CZEa',
                            CURRENT_DATE, CURRENT_DATE);
insert into account_role values ((select id from account where username = 'admin'),
                                 (select id from role where name = 'ROLE_ADMIN'));
