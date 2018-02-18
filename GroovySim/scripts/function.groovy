simulation "simulation" timerange "now", "14-02-2018 03:59:00" frequency 1, "SECOND"

sensor "temp" functionlaw "function(x) 3 * x * x - 2 * x + 1"
sensor "light" functionlaw "function(x) 3 * x * x + 2 * x -6"

generateSet "temp", 500
generateSet "light", 500

outputDB "SSL"

run "Sensor Simulation Lab (SSL)"