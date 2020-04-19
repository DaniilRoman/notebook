
CREATE TABLE IF NOT EXISTS account(
	id uuid PRIMARY KEY,
	username VARCHAR (50) UNIQUE NOT NULL,
	password VARCHAR (50) NOT NULL
--	email VARCHAR (355) UNIQUE NOT NULL,
--	created_on TIMESTAMP NOT NULL,
--	last_login TIMESTAMP
);

CREATE TABLE IF NOT EXISTS notebook(
    id uuid PRIMARY KEY,
    title VARCHAR (50),
    text VARCHAR (280)
)
