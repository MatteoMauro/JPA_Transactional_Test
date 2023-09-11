DROP TABLE IF EXISTS tabella CASCADE;

CREATE TABLE IF NOT EXISTS tabella
(
    id uuid NOT NULL,
	name character varying(100) NOT NULL,
	description character varying(100) NOT NULL,
	PRIMARY KEY (id)
);