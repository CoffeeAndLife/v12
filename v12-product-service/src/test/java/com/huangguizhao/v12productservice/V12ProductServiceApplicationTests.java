package com.huangguizhao.v12productservice;

import com.github.pagehelper.PageInfo;
import com.huangguizhao.v12.api.IProductApi;
import com.huangguizhao.v12.entity.TProduct;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V12ProductServiceApplicationTests {

	@Autowired
	private IProductApi productApi;

	@Test
	public void listTest() {
		List<TProduct> list = productApi.list();
		Assert.assertNotNull(list);
	}

	@Test
	public void pageTest(){
		PageInfo<TProduct> page = productApi.page(1, 1);
		Assert.assertEquals(page.getList().size(),1);
	}

}
