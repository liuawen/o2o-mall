package cn.liuawen.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.liuawen.BaseTest;
import cn.liuawen.dto.ShopAuthMapExecution;
import cn.liuawen.entity.ShopAuthMap;
import cn.liuawen.enums.ShopAuthMapStateEnum;

public class ShopAuthServiceTest extends BaseTest {
	@Autowired
	private ShopAuthMapService shopAuthService;

	@Test
	public void testUpdateShopAuthMap() {
		Long shopAuthId = 1L;
		String title = "CEO";
		Integer titleFlag = 2;
		Integer enableStatus = null;
		ShopAuthMap shopAuthMap = new ShopAuthMap();
		shopAuthMap.setShopAuthId(shopAuthId);
		shopAuthMap.setTitle(title);
		shopAuthMap.setTitleFlag(titleFlag);
		shopAuthMap.setEnableStatus(enableStatus);
		ShopAuthMapExecution same = shopAuthService
				.modifyShopAuthMap(shopAuthMap);
		assertEquals(ShopAuthMapStateEnum.SUCCESS.getState(), same.getState());
	}

	@Test
	public void testListShopAuthMapByShopId() {
		ShopAuthMapExecution shopAuthMapExecution = shopAuthService
				.listShopAuthMapByShopId(1L, 2, 1);
		List<ShopAuthMap> shopAuthMapList = shopAuthMapExecution
				.getShopAuthMapList();
		assertEquals(1, shopAuthMapList.size());
		shopAuthMapExecution = shopAuthService
				.listShopAuthMapByShopId(1L, 1, 2);
		shopAuthMapList = shopAuthMapExecution.getShopAuthMapList();
		assertEquals(2, shopAuthMapList.size());
	}
}
