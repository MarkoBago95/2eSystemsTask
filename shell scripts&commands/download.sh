#!bin/bash
#download current data 
curl https://www.aviationweather.gov/adds/dataserver_current/current/metars.cache.xml?accesType=DOWNLOAD > metar.xml
cp metar.xml /var/lib/mysql-files
