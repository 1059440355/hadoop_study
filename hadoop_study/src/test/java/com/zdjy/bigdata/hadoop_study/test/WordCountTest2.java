package com.zdjy.bigdata.hadoop_study.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
/**
 * 单词统计程序
 * 
 * 切分分隔符使用正则表达式
 * 
 * @author zdjy
 *
 */
public class WordCountTest2 {
	@Test
	public void test001() throws Exception{
		BufferedReader reader=new BufferedReader(new FileReader("C:\\Users\\zdjy\\Desktop\\Spring.txt"));
		String tmp=null;
		Map<String,Integer> map=new HashMap<>();
		
		while((tmp=reader.readLine())!=null){
			//tmp代表每一行的数据，按照非单词字符切分字符串为字符串数组，每一个元素代表一个单词
			String[] words = tmp.split("[^a-zA-Z0-9_]+");
			for(String word:words){
				if(map.get(word)!=null){
					Integer a=map.get(word);
					map.put(word, a+1);
				}else{
					map.put(word, 1);
				}
			}
		}
		for(Map.Entry<String,Integer> entry:map.entrySet()){
			String key=entry.getKey();
			Integer value=entry.getValue();
			System.out.println(key+":"+value);
		}
		reader.close();
	}
}
