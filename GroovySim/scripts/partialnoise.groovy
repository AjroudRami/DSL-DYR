simulation "simulation" timerange "now", "14-02-2018 23:59:00" frequency 1,"SECOND"

partialnoise 30, 10

sensor "randomisedWithNoise" randomlaw 10, 40

generateSet "randomisedWithNoise", 3

outputcsv "randomisedWithNoise.csv"

run "Sensor Simulation Lab (SSL) - Randomised with noise mode"