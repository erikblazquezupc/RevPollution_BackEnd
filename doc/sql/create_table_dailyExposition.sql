CREATE TABLE DailyExposition (
	idUser INTEGER, 
	dat DATE,
	value DOUBLE,
	PRIMARY KEY (idUser, dat),
	FOREIGN KEY (idUser) REFERENCES User(idUser)
);