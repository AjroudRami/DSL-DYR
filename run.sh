#!/usr/bin/env bash

echo "Executing SSL"
FILE1=$1
if [ -z "$FILE1" ]
then
    echo "usage: sh run.sh [input file]"
else
    java -jar GroovySim/target/GroovySim-1.0-SNAPSHOT-jar-with-dependencies.jar $FILE1
fi