DROP TABLE IF EXISTS aPrSaga;
DROP TABLE IF EXISTS Saga;

CREATE TABLE IF NOT EXISTS Saga(
	idSaga SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
	nomSaga VARCHAR(40) NOT NULL,
	PRIMARY KEY (idSaga)
)
ENGINE=INNODB;

LOAD DATA LOCAL INFILE '/home/coralie/luna/Film/Saga.csv'
INTO TABLE Saga
FIELDS TERMINATED BY ';' ENCLOSED BY '"'
LINES TERMINATED BY '\n'
(idSaga,nomSaga);


CREATE TABLE IF NOT EXISTS aPrSaga(
	idFilm SMALLINT UNSIGNED NOT NULL ,
	idSaga SMALLINT UNSIGNED NOT NULL,
	PRIMARY KEY (idFilm,idSaga),
	CONSTRAINT fk_idFilmSaga
        FOREIGN KEY (idFilm)
        REFERENCES Film(idFilm) ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE=INNODB;

ALTER TABLE aPrSaga
ADD CONSTRAINT fk_idSaga FOREIGN KEY (idSaga) REFERENCES Saga(idSaga) 
ON DELETE CASCADE
ON UPDATE CASCADE;

LOAD DATA LOCAL INFILE '/home/coralie/luna/Film/aPrSaga.csv'
INTO TABLE aPrSaga
FIELDS TERMINATED BY ';' ENCLOSED BY '"'
LINES TERMINATED BY '\n'
(idFilm,idSaga);
