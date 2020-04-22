ALTER TABLE notebook
  ADD COLUMN created DATE NOT NULL default CURRENT_DATE,
  ADD COLUMN updated DATE NOT NULL default CURRENT_DATE;

create extension "uuid-ossp";

insert into role values (uuid_generate_v4(), 'ROLE_USER', CURRENT_DATE, CURRENT_DATE),
                        (uuid_generate_v4(), 'ROLE_ADMIN', CURRENT_DATE, CURRENT_DATE);
