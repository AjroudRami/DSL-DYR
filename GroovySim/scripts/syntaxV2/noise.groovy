simulation "simulation" timerange "now", "14-02-2018 23:59:00" frequency 1,"SECOND"

addSensor "randomisedWithNoise" withLaw law.random1D(10, 40) applyNoise transform.random(10.0)

generateSet "randomisedWithNoise", 3

outputTo out.CSV("randomisedWithNoise.csv")

run()