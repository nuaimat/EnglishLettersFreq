#!/bin/bash

cd input

find . -iname '*.zip' -exec mv {} . \;
for i in `ls -1 *.zip`; do unzip $i; done

cd -
