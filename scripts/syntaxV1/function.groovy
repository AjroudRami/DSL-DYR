simulation "simulation" timerange "now", "28-02-2018 01:59:00" frequency 1, "MINUTE"

sensor "temp" functionlaw "x -> 3 * x * x - 2 * x + 1"
sensor "light" functionlaw "x -> 3 * x * x + 2 * x - 6"

generateSet "temp", 5
generateSet "light", 5

outputDB "SSL"

run "Sensor Simulation Lab (SSL)"