-- сперва создается бд
CREATE DATABASE notebook;
CREATE USER admin WITH PASSWORD 'admin';
GRANT ALL PRIVILEGES ON DATABASE "notebook" to admin;
-- \c notebook
-- чтобы создавать все под конкретной бд нужно подконектится под нее


CREATE TABLE account(
	id uuid PRIMARY KEY,
	username VARCHAR (50) UNIQUE NOT NULL,
	password VARCHAR (50) NOT NULL
--	email VARCHAR (355) UNIQUE NOT NULL,
--	created_on TIMESTAMP NOT NULL,
--	last_login TIMESTAMP
);

CREATE TABLE notebook(
    id uuid PRIMARY KEY,
    title VARCHAR (50),
    text VARCHAR (280)
)
