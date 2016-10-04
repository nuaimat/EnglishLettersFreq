package edu.mum.bigdata.mo;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class EnglishLettersReducer extends Reducer<Text, IntWritable, Text, GenericPair<IntWritable, FloatWritable>> {
	private static int allletters = 0;
	@Override
	protected void setup(
			Reducer.Context context)
			throws IOException, InterruptedException {
		super.setup(context);
		
		System.out.println("Reducer Output:");
	}
	private HashMap<Text, Integer> termsFreqTotal = new HashMap<>();
	@Override
	protected void reduce(
			Text key,
			Iterable<IntWritable> values,
			Context context)
			throws IOException, InterruptedException {
		
		int sum = 0;
        for (IntWritable val : values) {
            sum += val.get();
        }
        
        float ratio = 0.f;
        
        if(key.toString().equalsIgnoreCase("*")){
        	allletters = sum;
        	ratio = 1.f;
        } else {
        	ratio = (sum*1.f)/(allletters*1.f);
        }
        
		System.out.println("Key: " + key + " sum: " + sum);
		context.write(key, new GenericPair<IntWritable, FloatWritable>(new IntWritable(sum), new FloatWritable(ratio)));
		
        
	}
	
 }