simulation "simulation" timerange "now", "14-02-2018 23:59:00" offset 1,"SECOND"

sensor "polynomialised" polynomiallaw "[4, -3, 2]"

generateSet "polynomialised", 3

outputcsv "polynomialised.csv"

run "Sensor Simulation Lab (SSL) - Polynomialised mode"