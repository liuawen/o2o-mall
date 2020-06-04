package cn.liuawen.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import cn.liuawen.dto.ShopCategoryExecution;
import cn.liuawen.entity.ShopCategory;
/**
 * @author : Liu Awen Email:willowawen@gmail.com
 * @create : 2020-02-02
 */
public interface ShopCategoryService {
	/**
	 * 查询指定某个店铺下的所有商品类别信息
	 * 
	 * @param long shopId
	 * @return List<ProductCategory>
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	List<ShopCategory> getFirstLevelShopCategoryList() throws IOException;

	/**
	 * 
	 * @param parentId
	 * @return
	 * @throws IOException
	 */
	List<ShopCategory> getShopCategoryList(Long parentId) throws IOException;

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	List<ShopCategory> getAllSecondLevelShopCategory() throws IOException;

	/**
	 * 
	 * @param shopCategory
	 * @return
	 */
	ShopCategory getShopCategoryById(Long shopCategoryId);

	/**
	 * 
	 * @param shopCategory
	 * @param thumbnail
	 * @return
	 */
	ShopCategoryExecution addShopCategory(ShopCategory shopCategory,
                                          CommonsMultipartFile thumbnail);

	/**
	 * 
	 * @param shopCategory
	 * @param thumbnail
	 * @param thumbnailChange
	 * @return
	 */
	ShopCategoryExecution modifyShopCategory(ShopCategory shopCategory,
                                             CommonsMultipartFile thumbnail, boolean thumbnailChange);

	/**
	 * 
	 * @param shopCategoryId
	 * @return
	 */
	ShopCategoryExecution removeShopCategory(long shopCategoryId);

	/**
	 * 
	 * @param shopCategoryIdList
	 * @return
	 */
	ShopCategoryExecution removeShopCategoryList(List<Long> shopCategoryIdList);

}
