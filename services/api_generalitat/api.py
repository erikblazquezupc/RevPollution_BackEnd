#!/usr/bin/python3

import sys
import json
import MySQLdb

db = 'RevPollution_Dev'
conn = MySQLdb.connect( host='localhost', user= 'dev', passwd='aRqffCdBd9t!', db=db)

date = sys.argv[1]

with open('home/erik/api/'+date+'.json', 'r') as f:
    data = json.load(f)


for concentracion in data:
    nom_estacio = concentracion["nom_estacio"].replace("'", "\\'")
    lat = concentracion["latitud"]
    lon = concentracion["longitud"]
    try:
        print(concentracion)
        cur2 = conn.cursor()
        sql2 = "INSERT INTO Station(name, address, lat, lon, activated) VALUES ('"+ nom_estacio + "', 'address', " + lat + ", " + lon + ", 1)"
        print(sql2)
        cur2.execute(sql2)
        conn.commit()
    except MySQLdb._exceptions.IntegrityError as e:
        print(e)

    for i in range(0,24):
        if i < 10:
            hora = "h0"+str(i)
        else:
            hora = "h"+str(i)
        if hora in concentracion:
            try:
                particula = concentracion["contaminant"]
                cur = conn.cursor()
                sql = "INSERT INTO Concentration(idStation, nameParticle, instant, value) VALUES ((SELECT idStation FROM Station WHERE name = '" + nom_estacio + "'), '" + particula + "', '" + date+" "+str(i)+":00:00" + "', " + str(concentracion[hora]) + ")"
                print(sql)
                cur.execute(sql)
                conn.commit()
            except MySQLdb._exceptions.IntegrityError as e:
                print(e)
print("END")
