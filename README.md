# 2eSystemsTask
Here is my backend assignment

Hi,
here is my backend assignment for job aplication.

I gave myself the freedom to add a frontend part and some features to make it easier to test the app.
Also, as an automatic task (metar) i have implemented one external api call to the controller so we have real time information every time we request data for a specific airport.
To create a database(CREATE DATABASE AIRPORTS) in the repository there are sql files for both tables with some data.

The application runs in a linux terminal as command: $ mvn spring-boot:run   , and is used in one of the web browsers at localhost:8080


list of linux terminal commands to get metar data from external sources 'https://www.aviationweather.gov/dataserver/current'  and store it to mysql database:

curl https://www.aviationweather.gov/adds/dataserver_current/current/metars.cache.xml?accesType=DOWNLOAD > metar.xml

cp metar.xml /var/lib/mysql-files 

mysql> LOAD XML
       INFILE "/var/lib/mysql-files/metar.xml" INTO TABLE data.metar
       ROWS IDENTIFIED BY '<METAR>';
       SELECT * FROM metar WHERE station_id ='LDZA';
       
       

