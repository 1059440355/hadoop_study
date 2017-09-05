package com.zdjy.bigdata.hadoop_study.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
/**
 * 单词统计程序
 * 
 * 结果输出到文件中
 * 
 * @author zdjy
 *
 */
public class WordCountTest3 {
	@Test
	public void test001() throws Exception{
		BufferedReader reader=new BufferedReader(new FileReader("C:\\Users\\zdjy\\Desktop\\Spring.txt"));
		String tmp=null;
		Map<String,Integer> map=new HashMap<>();
		
		while((tmp=reader.readLine())!=null){
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
		reader.close();
		//将结果输出到文件
		BufferedWriter writer=new BufferedWriter(new FileWriter("C:\\Users\\zdjy\\Desktop\\Spring_out.txt"));
		for(Map.Entry<String,Integer> entry:map.entrySet()){
			String key=entry.getKey();
			Integer value=entry.getValue();
			//将输入写入输出流中
			writer.write(key+":"+value+"\n");
			//将输出流数据写入文件中（write方法并不是直接写入到文件中，而是先存储到内存中，调用close的时候才去强制刷新到文件中）
			writer.flush();
//			writer.newLine();
		}
		//关闭流
		writer.close();
	}
}
