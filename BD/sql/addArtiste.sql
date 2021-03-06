DROP TABLE IF EXISTS aPrActeur;
DROP TABLE IF EXISTS aPrReal;
DROP TABLE IF EXISTS Artiste;

CREATE TABLE IF NOT EXISTS Artiste(
	idArtiste INT UNSIGNED NOT NULL AUTO_INCREMENT,
	prenom VARCHAR(40) NOT NULL,
	nom VARCHAR(40) NOT NULL,
	nationalite VARCHAR(20),
	dateNaissance DATE,
	PRIMARY KEY (idArtiste)
)
ENGINE=INNODB;

LOAD DATA LOCAL INFILE '/home/coralie/luna/Film/Reference.csv'
INTO TABLE Artiste
FIELDS TERMINATED BY ';' ENCLOSED BY '"'
LINES TERMINATED BY '\n'
(idArtiste,prenom,nom,nationalite,dateNaissance);



CREATE TABLE IF NOT EXISTS aPrActeur(
	idFilm SMALLINT UNSIGNED NOT NULL ,
	idActeur INT UNSIGNED NOT NULL,
	PRIMARY KEY (idFilm,idActeur),
	CONSTRAINT fk_idFilmActeur
        FOREIGN KEY (idFilm)
        REFERENCES Film(idFilm) ON DELETE CASCADE ON UPDATE CASCADE 
)
ENGINE=INNODB;

ALTER TABLE aPrActeur
ADD CONSTRAINT fk_idActeur FOREIGN KEY (idActeur) REFERENCES Artiste(idArtiste) 
ON DELETE CASCADE
ON UPDATE CASCADE;

LOAD DATA LOCAL INFILE '/home/coralie/luna/Film/aPrActeur.csv'
INTO TABLE aPrActeur
FIELDS TERMINATED BY ';' ENCLOSED BY '"'
LINES TERMINATED BY '\n'
(idFilm,idActeur);


CREATE TABLE IF NOT EXISTS aPrReal(
	idFilm SMALLINT UNSIGNED NOT NULL ,
	idReal INT UNSIGNED NOT NULL,
	PRIMARY KEY (idFilm,idReal),
	CONSTRAINT fk_idFilmReal
        FOREIGN KEY (idFilm)
        REFERENCES Film(idFilm) ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE=INNODB;

ALTER TABLE aPrReal
ADD CONSTRAINT fk_idReal FOREIGN KEY (idReal) REFERENCES Artiste(idArtiste) 
ON DELETE CASCADE
ON UPDATE CASCADE;

LOAD DATA LOCAL INFILE '/home/coralie/luna/Film/aPrReal.csv'
INTO TABLE aPrReal
FIELDS TERMINATED BY ';' ENCLOSED BY '"'
LINES TERMINATED BY '\n'
(idFilm,idReal);
