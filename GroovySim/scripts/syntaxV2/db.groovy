simulation "simulation" timerange "14-02-2018 23:58:00", "14-02-2018 23:59:00" frequency 1, "SECOND"

sensor "temp" withLaw law.random1D(10, 40)

outputTo out.DBOutput("SSL")

run()