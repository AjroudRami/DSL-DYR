simulation "simulation" timerange "now", "13-02-2018 23:59:00" offset "SECOND", 1

noise 10

sensor "randomisedWithNoise" randomlaw 10, 40

generateSet "randomisedWithNoise", 3

outputcsv "randomisedWithNoise.csv"

run "Sensor Simulation Lab (SSL) - Randomised with noise mode"