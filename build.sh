#!/usr/bin/env bash

# Code
echo "building project"
cd Kernel
./build.sh
cd ..

cd GroovySim
./build.sh
cd ..

docker-compose up -d