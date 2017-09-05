package com.zdjy.bigdata.hadoop_study.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
/**
 * 单词统计程序
 * 
 * 将每一行的数据分割出单词，对单词进行计数
 * 
 * @author zdjy
 *
 */
public class WordCountTest {
	@Test
	public void test001() throws Exception{
		//使用BufferedReader的原因是：它提供了按行读取的方法
		BufferedReader reader=new BufferedReader(new FileReader("C:\\Users\\zdjy\\Desktop\\Spring.txt"));
		String tmp=null;
		//key代表单词，value代表单词出现的次数
		Map<String,Integer> map=new HashMap<>();
		
		while((tmp=reader.readLine())!=null){
			//tmp代表每一行的数据，按照空格切分字符串为字符串数组，每一个元素代表一个单词
			String[] words = tmp.split(" ");
			//循环字符串数组，取出每一个单词
			for(String word:words){
				//先匹配单词是否在map里面
				if(map.get(word)!=null){
					//单词已经出现过，取出值+1，重新赋值
					Integer a=map.get(word);
					map.put(word, a+1);
				}else{
					//单词没有出现过，是第一次出现
					map.put(word, 1);
				}
			}
		}
		
		//打印结果，遍历map集合
		//第一种方式借助于set集合
//		Set<String> keySet = map.keySet();
//		for(String key:keySet){
//			Integer value = map.get(key);
//		}
		for(Map.Entry<String,Integer> entry:map.entrySet()){
			String key=entry.getKey();
			Integer value=entry.getValue();
			System.out.println(key+":"+value);
		}
		reader.close();
	}
}
