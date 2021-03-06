package com.zdjy.bigdata.hadoop_study.mapreduce.word_count;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

/**
 * application
 * 入口类，只有main方法
 * 
 * 在集群上运行任务的流程
 * 1、上传jar，输入文件
 * 2、在hdfs上新建目录(in,out)
 * 3、hadoop jar ....jar com.zdjy.bigdata.hadoop_study.mapreduce.word_count.WordCountApp .../in   ../out
 * 
 * 
 * 
 * @author zdjy
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
