#!/bin/sh
#Authors: Vasileios Vlachos, Liatsis Fotis
#Date: 24/10/2013

echo "\nRemoving Simulation Data Files..."
rm -rf ../../results/*

cd ../../other/
rm -rf ./com_virology/* ./plots/* ./splots/* ./statistics/* ./tempfiles/* ./verification/*

sleep 1 ; echo "\033[0;31mDone!\033[0m"
