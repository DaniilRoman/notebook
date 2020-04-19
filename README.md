-Dspring.profiles.active=dev

-- сперва создается бд
CREATE DATABASE notebook;
CREATE USER admin WITH PASSWORD 'admin';
GRANT ALL PRIVILEGES ON DATABASE "notebook" to admin;
-- \c notebook
-- чтобы создавать все под конкретной бд нужно подконектится под нее