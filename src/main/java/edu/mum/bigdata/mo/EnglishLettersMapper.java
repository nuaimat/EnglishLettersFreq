package edu.mum.bigdata.mo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class EnglishLettersMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private final static IntWritable one = new IntWritable(1);
    private static Pattern alpha = Pattern.compile("[a-zA-Z]+");
        
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        
        char[] carr = line.toUpperCase().toCharArray();
        for(int i=0;i<carr.length;i++){
        	char c = carr[i];
        	
        	if(isAlpha(c)){
        		context.write(new Text("*"), one);
        		context.write(new Text(""+c), one);
        	}
        }
    }
    
    
    private boolean isAlpha(char c) {
    	Matcher inputMatch = alpha.matcher(""+c);
		return inputMatch.matches();
	}

 } 