package com.zdjy.bigdata.hadoop_study.test.hdfsTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

public class HdfsTest {
	@Test
	public void test001() throws Exception {
		// 创建连接
		// FileSystem.get()获取文件系统的连接对象，需要3个参数
		// 1、URI：core-sire.xml
		// 2、配置对象:new Configuration()
		// 3、用户名：hdfs的用户名---linux用户名
		FileSystem fileSystem = FileSystem.get(new URI("hdfs://hadoop211:9000"), new Configuration(), "hadoop");
		// 查看目录文件的方法FileSystem.listStatus，参数是一个Path对象
		// 返回值是一个FileStatus对象的数组
		FileStatus[] listStatus = fileSystem.listStatus(new Path("/"));
		// for(FileStatus fileStatus:listStatus){
		// System.out.println("===============");
		// System.out.println("块大小："+fileStatus.getBlockSize());
		// System.out.println("访问时间："+new Date(fileStatus.getAccessTime()));
		// System.out.println("修改时间："+new
		// Date(fileStatus.getModificationTime()));
		// System.out.println("文件大小："+fileStatus.getLen());
		// System.out.println("所属用户："+fileStatus.getOwner());
		// System.out.println("所属用户组："+fileStatus.getGroup());
		// System.out.println("路劲："+fileStatus.getPath());
		// System.out.println("权限："+fileStatus.getPermission());
		// System.out.println("副本数："+fileStatus.getReplication());
		// }
		for (FileStatus fileStatus : listStatus) {
			System.out.println(fileStatus.getPath());
			if (fileStatus.isDirectory()) {

			}
		}
	}

	public void printPath(FileSystem fileSystem, Path path) throws Exception {
		FileStatus[] listStatus = fileSystem.listStatus(path);
		for (FileStatus fileStatus : listStatus) {
			System.out.println(fileStatus.getPath());
			if (fileStatus.isDirectory()) {
				printPath(fileSystem, fileStatus.getPath());
			}
		}
	}

	/**
	 * 递归查询 hdfs dfs -ls -R /
	 * 
	 * @throws Exception
	 */
	@Test
	public void test002() throws Exception {
		FileSystem fileSystem = FileSystem.get(new URI("hdfs://hadoop211:9000"), new Configuration(), "hadoop");
		printPath(fileSystem, new Path("/"));
	}

	/**
	 * 上传 hdfs dfs -put
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	public void test003() throws Exception {
		FileSystem fileSystem = FileSystem.get(new URI("hdfs://hadoop211:9000"), new Configuration(), "hadoop");
		// 上传，从本地文件系统传到hdfs系统
		fileSystem.copyFromLocalFile(new Path("e:\\test\\hdfsTest\\apache-maven-3.3.9-bin.zip"), new Path("/"));
		// 查看是够上传
		printPath(fileSystem, new Path("/"));
	}

	@Test
	public void test004() throws Exception {
		FileSystem fileSystem = FileSystem.get(new URI("hdfs://hadoop211:9000"), new Configuration(), "hadoop");
		fileSystem.copyToLocalFile(new Path("hdfs://hadoop211:9000/apache-maven-3.3.9-bin.zip"),
				new Path("e:\\test\\hdfsTest\\apache-maven-3.3.9-bin2.zip"));
	}

	@Test
	public void test005() throws Exception {
		FileSystem fileSystem = FileSystem.get(new URI("hdfs://hadoop211:9000"), new Configuration(), "hadoop");
		// 第二个参数代表递归删除，主要用在删除目录上
		fileSystem.delete(new Path("hdfs://hadoop211:9000/apache-maven-3.3.9-bin.zip"), true);
		// 查看是够删除
		printPath(fileSystem, new Path("/"));
	}

	/**
	 * 流式数据写入，上传
	 * 
	 * @throws Exception
	 */
	@Test
	public void test006() throws Exception {
		FileSystem fileSystem = FileSystem.get(new URI("hdfs://hadoop211:9000"), new Configuration(), "hadoop");
		// 创建一个记录，然后打开输出流，往数据输出流中写入数据
		FSDataOutputStream fsDataOutputStream = fileSystem
				.create(new Path("hdfs://hadoop211:9000/apache-maven-3.3.9-bin.zip"));
		FileInputStream fileInputStream = new FileInputStream("e:\\test\\hdfsTest\\apache-maven-3.3.9-bin2.zip");

		// 流式传输的标准代码
		int len = 0;
		byte[] bytes = new byte[4 * 1024];// 4KB
		while ((len = fileInputStream.read(bytes)) >= 0) {
			fsDataOutputStream.write(bytes, 0, len);
		}

		fileInputStream.close();
		fsDataOutputStream.close();

	}

	/**
	 * 流式数据写出，下载
	 * 
	 * @throws Exception
	 */
	@Test
	public void test007() throws Exception {
		FileSystem fileSystem = FileSystem.get(new URI("hdfs://hadoop211:9000"), new Configuration(), "hadoop");
		FSDataInputStream inputStream = fileSystem.open(new Path("hdfs://hadoop211:9000/apache-maven-3.3.9-bin.zip"));

		FileOutputStream fileOutputStream = new FileOutputStream("e:\\test\\hdfsTest\\apache-maven-3.3.9-bin2.zip");

		// 流式传输的标准代码
		int len = 0;
		byte[] bytes = new byte[4 * 1024];// 4KB
		while ((len = inputStream.read(bytes)) >= 0) {
			fileOutputStream.write(bytes, 0, len);
		}

		fileOutputStream.close();
		inputStream.close();
	}

}
