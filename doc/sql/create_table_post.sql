CREATE TABLE Post (
	text TEXT NOT NULL,
	idCreator INT,
	postedOn BIGINT DEFAULT (UNIX_TIMESTAMP()*1000), -- The default value is the current timestamp in miliseconds (as the frontend code expects it). If ECOConnect's API gives the timestamp in another unit (p.ex seconds) then it should be changed (here and in the frontend).
	PRIMARY KEY (idCreator, postedOn),
	FOREIGN KEY (idCreator) REFERENCES User(idUser)
);
