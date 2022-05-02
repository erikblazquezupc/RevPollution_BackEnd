CREATE TABLE Location (
	idUser INTEGER, 
	instant DATETIME,
	value DOUBLE,
	PRIMARY KEY (idUser, instant),
	FOREIGN KEY (idUser) REFERENCES User(idUser)
);

CALL InsertLocation (10, '2022-04-20 12:00:00', 50.0);
CALL InsertLocation (10, '2022-04-20 13:00:00', 100.0);

CREATE PROCEDURE InsertLocation(IN idUserO INTEGER, IN instantO DATETIME, IN valueO DOUBLE)
BEGIN
	DECLARE prevDate DATE;
	DECLARE media DOUBLE;
	IF (NOT EXISTS (
		SELECT * FROM Location 
		WHERE DATE(instant) = DATE(instantO))
	) THEN
		IF EXISTS (
			SELECT * FROM Location
			WHERE idUser = idUserO AND DATE(instant) < DATE(instantO)
		) THEN
			SELECT avg(value) FROM Location
			WHERE idUser = idUserO AND DATE(instant) < DATE(instantO)
			GROUP BY idUser INTO media;
			
			SELECT DISTINCT DATE(instant) FROM Location
			WHERE idUser = idUserO AND DATE(instant) < DATE(instantO) INTO prevDate;
		
			INSERT INTO DailyExposition VALUES (idUserO, prevDate, media);
		
			DELETE FROM Location WHERE idUser = idUserO and DATE(instant) < DATE(instantO);
		
		END IF;
	END IF;
	INSERT INTO Location VALUES (idUserO, instantO, valueO);
END;
