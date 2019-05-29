package com.huangguizhao.v12centerweb;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V12CenterWebApplicationTests {

	@Autowired
	private FastFileStorageClient client;

	@Test
	public void uploadTest() throws FileNotFoundException {
		File file = new File(
				"D:\\dev\\v12\\v12-center-web\\src\\main\\resources\\templates\\index.html");
		StorePath storePath = client.uploadFile(
				new FileInputStream(file), file.length(), "html", null);

		System.out.println(storePath.getFullPath());
	}

	@Test
	public void delTest(){
		client.deleteFile("group1/M00/00/1B/CiQICFzs_W2Ad_FMAAAPXEhDTLk88.html");
		System.out.println("删除成功！");
	}

}
