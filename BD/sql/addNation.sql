DROP TABLE IF EXISTS aPrNation;
DROP TABLE IF EXISTS Nation;

CREATE TABLE IF NOT EXISTS Nation (
	idNation TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
	nomNation VARCHAR(20) NOT NULL,
	PRIMARY KEY (idNation)
)
ENGINE=INNODB;

LOAD DATA LOCAL INFILE '/home/coralie/luna/Film/Nationalite.csv'
INTO TABLE Nation
FIELDS TERMINATED BY ';' ENCLOSED BY '"'
LINES TERMINATED BY '\n'
(idNation,nomNation);

CREATE UNIQUE INDEX indexNation
ON Nation (nomNation(10));

CREATE TABLE IF NOT EXISTS aPrNation(
	idFilm SMALLINT UNSIGNED NOT NULL ,
	idNation TINYINT UNSIGNED NOT NULL,
	PRIMARY KEY (idFilm,idNation),
	CONSTRAINT fk_idFilmNationalite
        FOREIGN KEY (idFilm)
        REFERENCES Film(idFilm) ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE=INNODB;

ALTER TABLE aPrNation
ADD CONSTRAINT fk_idNation FOREIGN KEY (idNation) REFERENCES Nation(idNation) 
ON DELETE CASCADE 
ON UPDATE CASCADE;

LOAD DATA LOCAL INFILE '/home/coralie/luna/Film/aPrNation.csv'
INTO TABLE aPrNation
FIELDS TERMINATED BY ';' ENCLOSED BY '"'
LINES TERMINATED BY '\n'
(idFilm,idNation);