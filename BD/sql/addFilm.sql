DROP TABLE IF EXISTS Film;

CREATE TABLE IF NOT EXISTS Film (
	idFilm SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
	titre VARCHAR(100) NOT NULL,
	anneeSortie YEAR,
	notePublic TINYINT UNSIGNED CHECK (note <=100 AND note >=0),
	dureeFilm TIME,
	PRIMARY KEY (idFilm),
	UNIQUE(titre,anneeSortie)
)
ENGINE=INNODB;

LOAD DATA LOCAL INFILE '/home/coralie/luna/Film/Film.csv'
INTO TABLE Film
FIELDS TERMINATED BY ';' ENCLOSED BY '"'
LINES TERMINATED BY '\n'
(idFilm,titre,anneeSortie,notePublic,dureeFilm);

ALTER TABLE Film
ADD FULLTEXT indexTitre (titre);


