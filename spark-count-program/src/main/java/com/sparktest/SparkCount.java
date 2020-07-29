package com.sparktest;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class SparkCount {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SparkConf sparkConf = new SparkConf().setAppName("Read Text to RDD").setMaster("local[2]")
				.set("spark.executor.memory", "2g");
// start a spark context
		JavaSparkContext sc = new JavaSparkContext(sparkConf);
		sc.setLogLevel("ERROR");
// initialize an integer RDD
		JavaRDD<Integer> numbers = sc.parallelize(Arrays.asList(14, 21, 88, 99, 455));

// map each line to number of words in the line
		JavaRDD<Double> log_values = numbers.map(x -> Math.log(x));

// collect RDD for printing
		for (double value : log_values.collect()) {
			System.out.println(value);
		} 
	}

}
