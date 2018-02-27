simulation "simulation" timerange "now", "14-02-2018 23:59:00" frequency 1, "SECOND"

sensor "temp" randomlaw 10, 40
sensor "light" randomlaw 20, 30

generateSet "temp", 100
generateSet "light", 500

outputDB "SSL"

run "Sensor Simulation Lab (SSL)"