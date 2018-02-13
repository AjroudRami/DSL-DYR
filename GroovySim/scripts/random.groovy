simulation "RandomSimulation" timerange 0, 2344 frequency 10

sensor "randomised" randomlaw 10, 40

generateSet "randomised", 3

outputcsv "randomised.csv"

run "Sensor Simulation Lab (SSL) - Randomised mode"