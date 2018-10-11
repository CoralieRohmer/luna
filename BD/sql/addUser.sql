DROP TABLE IF EXISTS aNote;
DROP TABLE IF EXISTS aVu;
DROP TABLE IF EXISTS estCompose;
DROP TABLE IF EXISTS Marathon;
DROP TABLE IF EXISTS Usager;

CREATE TABLE IF NOT EXISTS Usager (
	idUsager SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
	alias VARCHAR(20) NOT NULL,
	nomUsager VARCHAR(20),
	prenomUsager VARCHAR(20),
	status VARCHAR(20) DEFAULT 'client' CHECK (status='root' OR status='admin' OR status='client') ,
	PRIMARY KEY (idUsager)
)
ENGINE=INNODB;
ALTER TABLE Usager
ADD UNIQUE indexAlias (alias(20));

LOAD DATA LOCAL INFILE '/home/coralie/luna/Film/User.csv'
INTO TABLE Usager
FIELDS TERMINATED BY ';' ENCLOSED BY '"'
LINES TERMINATED BY '\n'
(idUsager,alias,nomUsager,prenomUsager);

CREATE TABLE IF NOT EXISTS aVu(
	idUsager SMALLINT UNSIGNED NOT NULL,
	idFilm SMALLINT UNSIGNED NOT NULL,
	PRIMARY KEY (idUsager,idFilm),
	CONSTRAINT fk_idFilmVu
        FOREIGN KEY (idFilm)
        REFERENCES Film(idFilm) ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE=INNODB;

ALTER TABLE aVu
ADD CONSTRAINT fk_idUsagerVu FOREIGN KEY (idUsager) REFERENCES Usager(idUsager) 
ON DELETE CASCADE
ON UPDATE CASCADE;

LOAD DATA LOCAL INFILE '/home/coralie/luna/Film/aVu.csv'
INTO TABLE aVu
FIELDS TERMINATED BY ';' ENCLOSED BY '"'
LINES TERMINATED BY '\n'
(idUsager,idFilm);

CREATE TABLE IF NOT EXISTS aNote(
	idUsager SMALLINT UNSIGNED NOT NULL,
	idFilm SMALLINT UNSIGNED NOT NULL,
	note TINYINT UNSIGNED CHECK (note <=100 AND note >=0),
	PRIMARY KEY (idUsager,idFilm),
	CONSTRAINT fk_idFilmNote
        FOREIGN KEY (idUsager,idFilm)
        REFERENCES aVu(idUsager,idFilm) ON UPDATE CASCADE
)
ENGINE=INNODB;

LOAD DATA LOCAL INFILE '/home/coralie/luna/Film/aNote.csv'
INTO TABLE aNote
FIELDS TERMINATED BY ';' ENCLOSED BY '"'
LINES TERMINATED BY '\n'
(idUsager,idFilm,note);

CREATE TABLE IF NOT EXISTS Marathon(
	idMarathon SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
	idUsager SMALLINT UNSIGNED NOT NULL,
	nomMarathon VARCHAR(20),
	dureeMarathon TIME,
	PRIMARY KEY (idMarathon),
	CONSTRAINT fk_idMarathonUser
        FOREIGN KEY (idUsager)
        REFERENCES Usager(idUsager) ON UPDATE CASCADE
)
ENGINE=INNODB;

LOAD DATA LOCAL INFILE '/home/coralie/luna/Film/Marathon.csv'
INTO TABLE Marathon
FIELDS TERMINATED BY ';' ENCLOSED BY '"'
LINES TERMINATED BY '\n'
(idMarathon,idUsager,nomMarathon,dureeMarathon);

CREATE TABLE IF NOT EXISTS estCompose (
	idMarathon SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
	idFilm SMALLINT UNSIGNED NOT NULL,
	PRIMARY KEY (idMarathon,idFilm),
	CONSTRAINT fk_idFilmMarathon
        FOREIGN KEY (idFilm)
        REFERENCES Film(idFilm) ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE=INNODB;

LOAD DATA LOCAL INFILE '/home/coralie/luna/Film/estCompose.csv'
INTO TABLE estCompose
FIELDS TERMINATED BY ';' ENCLOSED BY '"'
LINES TERMINATED BY '\n'
(idMarathon,idFilm);