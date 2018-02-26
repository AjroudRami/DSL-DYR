#!/usr/bin/env bash

echo "Executing SSL"
FILE1=$1
if [ -z "$FILE1" ]
then
    echo "usage: sh run.sh [input file]"
else
    java -jar GroovySim/target/*.jar $FILE1
fi