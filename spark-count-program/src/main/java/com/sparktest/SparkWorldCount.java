package com.sparktest;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class SparkWorldCount {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SparkConf sparkConf = new SparkConf().setAppName("Read Text to RDD").setMaster("local[2]")
				.set("spark.executor.memory", "2g");
		// start a spark context
		JavaSparkContext sc = new JavaSparkContext(sparkConf);
		sc.setLogLevel("ERROR");
		JavaRDD<String> textFile = sc.textFile("TextFile.txt");
		JavaPairRDD<String, Integer> counts = textFile.flatMap(s -> Arrays.asList(s.split(" ")).iterator())
				.mapToPair(word -> new Tuple2<>(word, 1)).reduceByKey((a, b) -> a + b);
		for (Tuple2<String, Integer> value : counts.collect()) {
			System.out.println(value);
		}
		sc.close();
	}

}
