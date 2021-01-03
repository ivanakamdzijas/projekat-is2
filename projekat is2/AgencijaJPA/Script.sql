--<ScriptOptions statementTerminator=";"/>

ALTER TABLE destinacija DROP PRIMARY KEY;

ALTER TABLE destinacija DROP INDEX nazivD_UNIQUE;

DROP TABLE destinacija;

CREATE TABLE destinacija (
	idDestinacija INT NOT NULL,
	nazivD VARCHAR(45) NOT NULL,
	idDestinacija INT NOT NULL,
	nazivD VARCHAR(45) NOT NULL,
	smestaj_idSmestaj INT NOT NULL,
	PRIMARY KEY (idDestinacija,smestaj_idSmestaj)
);

CREATE UNIQUE INDEX nazivD_UNIQUE ON destinacija (nazivD ASC);

ALTER TABLE destinacija ADD UNIQUE (smestaj_idSmestaj);

