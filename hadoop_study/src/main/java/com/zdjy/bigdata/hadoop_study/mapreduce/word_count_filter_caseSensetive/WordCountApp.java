package com.zdjy.bigdata.hadoop_study.mapreduce.word_count_filter_caseSensetive;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

/**
 *过滤掉无用的单词数据
 *缓存文件存储无用的单词---在map初始化的时候读取到一个集合中，map循环的时候去判断过滤集合中是否存在
 *
 *
 *可以动态的表示是否大小写敏感
 *-Dword.case.sensitive=true/false
 *true：word.toLowerCase()
 *
 */
public class WordCountApp {
	public static void main(String[] args) throws Exception {
		//main方法的参数是一个字符串数组，他的第一个值就是第一个参数，第二个值就是第二个参数
		//args[0]--第一个参数
		//args[1]--第二个参数
		
		
		//构造Job对象
		Job job = Job.getInstance();
		
		//设置任务名，任务类
		job.setJobName("word_count");
		job.setJarByClass(WordCountApp.class);
		
		//设置输入
		TextInputFormat.setInputPaths(job, new Path(args[0]));//路径
		job.setInputFormatClass(TextInputFormat.class);//读取格式
		
		
		//设置map
		job.setMapperClass(WordCountMapper.class);//设置map类
		job.setMapOutputKeyClass(Text.class);//设置map输出key
		job.setMapOutputValueClass(IntWritable.class);//设置map输出value
		
		
		//设置reduce
		job.setReducerClass(WordCountReduer.class);//设置reduce
		job.setOutputKeyClass(Text.class);//设置reduce输出key
		job.setOutputValueClass(IntWritable.class);//设置reduce输出value
		job.setNumReduceTasks(3);
		
		
		//设置输出
		TextOutputFormat.setOutputPath(job, new Path(args[1]+System.currentTimeMillis()));//设置输出路径
		job.setOutputFormatClass(TextOutputFormat.class);//写出格式
		
		job.setPartitionerClass(HashPartitioner.class);
		
		
		
		//提交任务
//		job.submit();
		//提交任务，等待任务结束，打印统计信息
		//正常结束返回true，非正常结束返回false
		System.exit(job.waitForCompletion(true)?0:1);
		
		
		
		
		
	}
}
