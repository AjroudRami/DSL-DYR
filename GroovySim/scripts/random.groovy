simulation "simulation" timerange "now", "13-02-2018 23:59:00" offset "SECOND", 1

sensor "randomised" randomlaw 10, 40

generateSet "randomised", 3

outputcsv "randomised.csv"

run "Sensor Simulation Lab (SSL) - Randomised mode"