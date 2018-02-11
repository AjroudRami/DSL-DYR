simulation "simulation" timerange 0, 2344 frequency 10

sensor "temp" law random1D(10, 40)
sensor "light" law random1D(20, 30)

sensor "replayed" fromcsv "filename"

generateSet "temp", 6
generateSet "light", 3

outputcsv "Télé/data.csv"

run "GROOVY !!"