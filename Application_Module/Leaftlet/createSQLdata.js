/*45.79536387253486, 4.7619714791069185
45.68898110603114, 4.967278493232123

INSERT INTO public.sensors (sensor_column, sensor_line, intensity, alive) VALUES (0,0,0,true);

sensor_column integer NOT NULL,
sensor_line integer NOT NULL,
longitude integer,
latitude integer,
intensity integer NOT NULL,
alive boolean NOT NULL,*/

var maxlatitude= 45.79536387253486;
var minlatitude=45.68898110603114;
var minlongitude=4.7619714791069185;
var maxlongitude=4.967278493232123;

var difflatitude=maxlatitude-minlatitude;
var difflongitude= maxlatitude-minlatitude;

var nbcolonne=6;
var nbligne=10;

var paslatitude= difflatitude/nbcolonne;
var paslongitude= difflongitude/nbligne;

for (var c = 0; c < nbcolonne; c++) {
  for (var l = 0; l < nbligne; l++) {
    console.log("INSERT INTO public.sensors (sensor_column, sensor_line, longitude, latitude,intensity, alive) VALUES ("+c+","+l+","+paslongitude*l+","+paslatitude*c+",0,true);");
  }
}
