DELIMITER $$
CREATE PROCEDURE IncrementStatistic(IN st TEXT, IN token TEXT)
BEGIN
	DECLARE user_id INT;
	DECLARE last_id int;
	DECLARE last_name TEXT;
	DECLARE last_tier TEXT;

	SET last_id = 0;

    SELECT idUser FROM User u WHERE u.token = token INTO user_id;
   
	  label: LOOP
		    IF NOT EXISTS(SELECT * FROM Logro l WHERE l.statistic = st AND l.activated = 1 AND l.id > last_id)
		    THEN
		  		LEAVE label;
		    END IF; 
		 
			SELECT min(l.id) FROM Logro l WHERE l.statistic = st AND l.activated = 1 AND l.id > last_id INTO last_id;
		  	SELECT l.name FROM Logro l WHERE l.id = last_id INTO last_name;
		  	SELECT l.tier FROM Logro l WHERE l.id = last_id INTO last_tier;
		 	IF (NOT EXISTS (SELECT * 
	                        FROM UserLogros ul
	                        WHERE ul.idUser = user_id AND ul.nameLogro = last_name AND ul.tier = last_tier))
	        THEN
	            INSERT INTO UserLogros (idUser, nameLogro, tier, points)
	            VALUES (user_id, last_name, last_tier, 1);
	        ELSE
	            UPDATE UserLogros ul
	            SET ul.points = ul.points+1
	            WHERE ul.idUser = user_id AND ul.nameLogro = last_name AND ul.tier = last_tier;
	        END IF;
	    ITERATE label;
	  END LOOP;
    
END
$$ 
DELIMITER ;