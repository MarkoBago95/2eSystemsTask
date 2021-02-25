#!bin/bash
echo "insert icao to get data for example LDZA"
read icao;
myvariable=$(echo "SELECT * FROM metar WHERE station_id=\"$icao\"" | mysql -u marko -p data);
echo "result set:"$myvariable
