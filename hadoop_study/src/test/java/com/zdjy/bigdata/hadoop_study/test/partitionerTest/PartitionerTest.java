package com.zdjy.bigdata.hadoop_study.test.partitionerTest;

import org.junit.Test;

public class PartitionerTest {
	@Test
	public void test001(){
		int hashCode = new Integer(11).hashCode();
		System.out.println(hashCode);
		int hashCode2 = new String("hjskfdhoqwernsadf").hashCode()&Integer.MAX_VALUE;
		System.out.println(hashCode2);
		
	}
	@Test
	public void test002(){
		String aString="asdfasdf1";
		char[] charArray = aString.toCharArray();
		int sum=0;
		for(int i=0;i<charArray.length;i++){
			char tmp=charArray[i];
			sum=31*sum+tmp;
		}
		System.out.println(sum);
	}
	
	@Test
	public void test003(){
		System.out.println(1<<0);//左移0位   1*2的0次方
		System.out.println(1<<1);//左移1位   1*2的1次方
		System.out.println(1<<2);//左移2位   1*2的2次方
		System.out.println(1<<3);//左移3位   1*2的3次方
		System.out.println(1<<4);//左移4位   1*2的4次方
		
		//int值4个字节，32位
		
		//00000000 00000000 00000000 00000001
		//00000000 00000000 00000000 00000010
		//00000000 00000000 00000000 00000100
		//00000000 00000000 00000000 00001000
		//int型最大值
		
		//01111111 1111111 1111111 1111111
		
		//10000000 00000000 00000000 00000000
		
		//&运算，每一位做&运算
		
		
		//00000000 00000000 00000000 00000000
		
		
		
		//long型值64位
	}
	@Test
	public void test004(){
		System.out.println("".charAt(0));
	}
}
