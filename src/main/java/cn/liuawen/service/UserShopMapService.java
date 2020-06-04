package cn.liuawen.service;

import cn.liuawen.dto.UserShopMapExecution;
import cn.liuawen.entity.UserShopMap;
/**
 * @author : Liu Awen Email:willowawen@gmail.com
 * @create : 2020-02-02
 */
public interface UserShopMapService {

	UserShopMapExecution listUserShopMap(UserShopMap userShopMapCondition,
                                         int pageIndex, int pageSize);

}
