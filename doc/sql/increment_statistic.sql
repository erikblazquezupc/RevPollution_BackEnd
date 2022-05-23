UPDATE UserLogros ul
SET ul.points = ul.points+1
WHERE EXISTS (  SELECT * 
                FROM User u, Logro l
                WHERE l.activated = 1 
                    AND l.statistic = algo 
                    AND u.token = token 
                    AND ul.idUser = u.idUser
                    AND ul.nameLogro = l.name);