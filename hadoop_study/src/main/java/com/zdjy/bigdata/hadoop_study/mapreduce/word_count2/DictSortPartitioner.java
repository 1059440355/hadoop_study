package com.zdjy.bigdata.hadoop_study.mapreduce.word_count2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 自定义分区函数
 * 1集成抽象类Partitioner，泛型是map输出的泛型
 * 2重写getPartition方法
 * 3按照单词的首字母进行分区
 * @author zdjy
 *
 */
public class DictSortPartitioner extends Partitioner<Text,IntWritable>{

	@Override
	public int getPartition(Text key, IntWritable value, int numPartitions) {
		String word = key.toString().toLowerCase();
		//获取单词首字母
		char charAt = word.charAt(0);
		//比较判断
		if(charAt>='a'&& charAt<='g')
			return 0;
		else if(charAt>='h' && charAt<='n')
			return 1;
		else if(charAt>='o' && charAt<='t')
			return 2;
		else if(charAt>='u'&& charAt<='z')
			return 3;
		return 4;//以数字开头的或者_开头的
	}

}
