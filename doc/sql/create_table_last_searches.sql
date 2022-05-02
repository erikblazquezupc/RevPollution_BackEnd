CREATE TABLE LastSearches (
	idUser int,
	name varchar(50),
	instant DATETIME,
	PRIMARY KEY (idUser, name),
	FOREIGN KEY (idUser) REFERENCES User(idUser)
);

REPLACE INTO LastSearches(idUser, idStation, instant) VALUES (10, 1, '2022-03-25 12:00:00');
REPLACE INTO LastSearches(idUser, idStation, instant) VALUES (10, 2, '2022-03-26 12:00:00');
REPLACE INTO LastSearches(idUser, idStation, instant) VALUES (10, 3, '2022-03-27 12:00:00');
REPLACE INTO LastSearches(idUser, idStation, instant) VALUES (10, 6, '2022-03-28 12:00:00');


SELECT * FROM LastSearches WHERE idUser = 10 ORDER BY instant DESC LIMIT 3;
	