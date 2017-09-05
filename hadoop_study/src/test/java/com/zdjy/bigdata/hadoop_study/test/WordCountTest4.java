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
 * 取消空字符串的打印
 * 
 * @author zdjy
 *
 */
public class WordCountTest4 {
	@Test
	public void test001() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\zdjy\\Desktop\\Spring.txt"));
		String tmp = null;
		Map<String, Integer> map = new HashMap<>();

		while ((tmp = reader.readLine()) != null) {
			String[] words = tmp.split("[^a-zA-Z0-9_]+");
			for (String word : words) {
				//如果为空字符串跳过
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
		writer.close();
	}
}
