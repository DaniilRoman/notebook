CREATE DATABASE notebook;
CREATE USER admin WITH PASSWORD 'admin' SUPERUSER;
GRANT ALL PRIVILEGES ON DATABASE notebook to admin;
