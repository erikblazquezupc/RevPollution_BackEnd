#!/bin/bash

ENDPOINT=https://analisi.transparenciacatalunya.cat/resource/tasf-thgu.json?data=
DATE=$(date +"%Y-%m-%d")
echo "${ENDPOINT}${DATE}T00:00:00.00"

#Obtenemos los datos de la api
wget "${ENDPOINT}${DATE}T00:00:00.00" -O $HOME/api/$DATE.json

echo $HOME
#Ejecutamos script de python para insertar datos en la BD
python3 $HOME/api.py $DATE

#Borramos fichero json descargado de la api
rm -rf $HOME/api/$DATE.json


