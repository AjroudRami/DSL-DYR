simulation "simulation" timerange "14-02-2018 03:50:00", "14-02-2018 03:59:00" frequency 1,"SECOND"

addSensor "replicated" withLaw laws.replayCSV("./testScripts/replay.csv")

generateSet "replicated", 3

outputTo out.CSV("out")

runSimulation()