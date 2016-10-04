#!/bin/bash

JAR_FILE="target/EnglishMostFreqLetters-1.0-SNAPSHOT.jar"
CLASS_NAME="edu.mum.bigdata.mo.EnglishLettersDriver"
HADOOP_OUTPUT_FOLDER="/user/hive/warehouse/englishwords_output_`date +'%Y%m%d%H%M%S'`"


HADOOP_INPUT_DEST="/user/hive/warehouse/englishwords"


mvn clean package
time sudo hadoop jar $JAR_FILE $CLASS_NAME $HADOOP_INPUT_DEST $HADOOP_OUTPUT_FOLDER 

echo "Output written to $HADOOP_OUTPUT_FOLDER"
echo "Output was: "
hadoop fs -cat $HADOOP_OUTPUT_FOLDER/part-r-00000
