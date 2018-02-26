simulation "simulation" timerange "now", "14-02-2018 23:59:00" frequency 1,"SECOND"

addSensor "polynomialised" withLaw laws.polynomial1D([4.0, -3.0, 2.0])

generateSet "polynomialised", 3

outputTo out.CSV("polynomialised.csv")

run()