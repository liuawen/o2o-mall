package cn.liuawen.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.liuawen.dao.PersonInfoDao;
import cn.liuawen.dao.ShopDao;
import cn.liuawen.dao.UserProductMapDao;
import cn.liuawen.dao.UserShopMapDao;
import cn.liuawen.dto.UserProductMapExecution;
import cn.liuawen.entity.PersonInfo;
import cn.liuawen.entity.Shop;
import cn.liuawen.entity.UserProductMap;
import cn.liuawen.entity.UserShopMap;
import cn.liuawen.enums.UserProductMapStateEnum;
import cn.liuawen.service.UserProductMapService;
import cn.liuawen.util.PageCalculator;
/**
 * @author : Liu Awen Email:willowawen@gmail.com
 * @create : 2020-02-02
 */
@Service
public class UserProductMapServiceImpl implements UserProductMapService {
	@Autowired
	private UserProductMapDao userProductMapDao;
	@Autowired
	private UserShopMapDao userShopMapDao;
	@Autowired
	private PersonInfoDao personInfoDao;
	@Autowired
	private ShopDao shopDao;

	@Override
	public UserProductMapExecution listUserProductMap(
			UserProductMap userProductCondition, Integer pageIndex,
			Integer pageSize) {
		if (userProductCondition != null && pageIndex != null
				&& pageSize != null) {
			int beginIndex = PageCalculator.calculateRowIndex(pageIndex,
					pageSize);
			List<UserProductMap> userProductMapList = userProductMapDao
					.queryUserProductMapList(userProductCondition, beginIndex,
							pageSize);
			int count = userProductMapDao
					.queryUserProductMapCount(userProductCondition);
			UserProductMapExecution se = new UserProductMapExecution();
			se.setUserProductMapList(userProductMapList);
			se.setCount(count);
			return se;
		} else {
			return null;
		}

	}

	@Override
	@Transactional
	public UserProductMapExecution addUserProductMap(
			UserProductMap userProductMap) throws RuntimeException {
		if (userProductMap != null && userProductMap.getUserId() != null
				&& userProductMap.getShopId() != null) {
			userProductMap.setCreateTime(new Date());
			try {
				int effectedNum = userProductMapDao
						.insertUserProductMap(userProductMap);
				if (effectedNum <= 0) {
					throw new RuntimeException("添加消费记录失败");
				}
				if (userProductMap.getPoint() != null
						&& userProductMap.getPoint() > 0) {
					UserShopMap userShopMap = userShopMapDao.queryUserShopMap(
							userProductMap.getUserId(),
							userProductMap.getShopId());
					if (userShopMap != null) {
						if (userShopMap.getPoint() >= userProductMap.getPoint()) {
							userShopMap.setPoint(userShopMap.getPoint()
									+ userProductMap.getPoint());
							effectedNum = userShopMapDao
									.updateUserShopMapPoint(userShopMap);
							if (effectedNum <= 0) {
								throw new RuntimeException("更新积分信息失败");
							}
						}

					} else {
						// 在店铺没有过消费记录，添加一条积分信息
						userShopMap = compactUserShopMap4Add(
								userProductMap.getUserId(),
								userProductMap.getShopId(),
								userProductMap.getPoint());
						effectedNum = userShopMapDao
								.insertUserShopMap(userShopMap);
						if (effectedNum <= 0) {
							throw new RuntimeException("积分信息创建失败");
						}
					}
				}
				return new UserProductMapExecution(
						UserProductMapStateEnum.SUCCESS, userProductMap);
			} catch (Exception e) {
				throw new RuntimeException("添加授权失败:" + e.toString());
			}
		} else {
			return new UserProductMapExecution(
					UserProductMapStateEnum.NULL_USERPRODUCT_INFO);
		}
	}

	private UserShopMap compactUserShopMap4Add(Long userId, Long shopId,
			Integer point) {
		UserShopMap userShopMap = null;
		if (userId != null && shopId != null) {
			userShopMap = new UserShopMap();
			PersonInfo personInfo = personInfoDao.queryPersonInfoById(userId);
			Shop shop = shopDao.queryByShopId(shopId);
			userShopMap.setUserId(userId);
			userShopMap.setShopId(shopId);
			userShopMap.setUserName(personInfo.getName());
			userShopMap.setShopName(shop.getShopName());
			userShopMap.setCreateTime(new Date());
			userShopMap.setPoint(point);
		}
		return userShopMap;
	}

}
