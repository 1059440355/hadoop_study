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
 * 统计结果进行排序
 * 思路：
 * 可以将结果放到集合中，然后通过集合来进行排序
 * 
 * @author zdjy
 *
 */
public class WordCountTest5 {
	@Test
	public void test001() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\zdjy\\Desktop\\Spring.txt"));
		String tmp = null;
		Map<String, Integer> map = new HashMap<>();

		while ((tmp = reader.readLine()) != null) {
			String[] words = tmp.split("[^a-zA-Z0-9_]+");
			for (String word : words) {
				if (!"".equals(word)) {
					if (map.get(word) != null) {
						Integer a = map.get(word);
						map.put(word, a + 1);
					} else {
						map.put(word, 1);
					}
				}
			}
		}
		reader.close();
		BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\zdjy\\Desktop\\Spring_out.txt"));
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			String key = entry.getKey();
			Integer value = entry.getValue();
			writer.write(key + ":" + value + "\n");
			writer.flush();
		}
		// TODO 排序实现
		
		
		writer.close();
	}
}
