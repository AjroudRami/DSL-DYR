simulation "simulation" timerange "now", "14-02-2018 23:59:00" frequency 1,"SECOND"

addSensor "randomised" withLaw laws.random1D(10, 40)

generateSet "randomised", 3

outputTo out.CSV("randomised.csv")

run()