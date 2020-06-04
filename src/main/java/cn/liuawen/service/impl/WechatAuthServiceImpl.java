package cn.liuawen.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.liuawen.dao.PersonInfoDao;
import cn.liuawen.dao.WechatAuthDao;
import cn.liuawen.dto.WechatAuthExecution;
import cn.liuawen.entity.PersonInfo;
import cn.liuawen.entity.WechatAuth;
import cn.liuawen.enums.WechatAuthStateEnum;
import cn.liuawen.service.WechatAuthService;
import cn.liuawen.util.FileUtil;
import cn.liuawen.util.ImageUtil;
/**
 * @author : Liu Awen Email:willowawen@gmail.com
 * @create : 2020-02-02
 */
@Service
public class WechatAuthServiceImpl implements WechatAuthService {
	private static Logger log = LoggerFactory
			.getLogger(WechatAuthServiceImpl.class);
	@Autowired
	private WechatAuthDao wechatAuthDao;
	@Autowired
	private PersonInfoDao personInfoDao;

	@Override
	public WechatAuth getWechatAuthByOpenId(String openId) {
		return wechatAuthDao.queryWechatInfoByOpenId(openId);
	}

	@Override
	@Transactional
	public WechatAuthExecution register(WechatAuth wechatAuth,
			CommonsMultipartFile profileImg) throws RuntimeException {
		if (wechatAuth == null || wechatAuth.getOpenId() == null) {
			return new WechatAuthExecution(WechatAuthStateEnum.NULL_AUTH_INFO);
		}
		try {
			wechatAuth.setCreateTime(new Date());
			if (wechatAuth.getPersonInfo() != null
					&& wechatAuth.getPersonInfo().getUserId() == null) {
				if (profileImg != null) {
					try {
						addProfileImg(wechatAuth, profileImg);
					} catch (Exception e) {
						log.debug("addUserProfileImg error:" + e.toString());
						throw new RuntimeException("addUserProfileImg error: "
								+ e.getMessage());
					}
				}
				try {
					wechatAuth.getPersonInfo().setCreateTime(new Date());
					wechatAuth.getPersonInfo().setLastEditTime(new Date());
					wechatAuth.getPersonInfo().setCustomerFlag(1);
					wechatAuth.getPersonInfo().setShopOwnerFlag(1);
					wechatAuth.getPersonInfo().setAdminFlag(0);
					wechatAuth.getPersonInfo().setEnableStatus(1);
					PersonInfo personInfo = wechatAuth.getPersonInfo();
					int effectedNum = personInfoDao
							.insertPersonInfo(personInfo);
					wechatAuth.setUserId(personInfo.getUserId());
					if (effectedNum <= 0) {
						throw new RuntimeException("添加用户信息失败");
					}
				} catch (Exception e) {
					log.debug("insertPersonInfo error:" + e.toString());
					throw new RuntimeException("insertPersonInfo error: "
							+ e.getMessage());
				}
			}
			int effectedNum = wechatAuthDao.insertWechatAuth(wechatAuth);
			if (effectedNum <= 0) {
				throw new RuntimeException("帐号创建失败");
			} else {
				return new WechatAuthExecution(WechatAuthStateEnum.SUCCESS,
						wechatAuth);
			}
		} catch (Exception e) {
			log.debug("insertWechatAuth error:" + e.toString());
			throw new RuntimeException("insertWechatAuth error: "
					+ e.getMessage());
		}
	}

	private void addProfileImg(WechatAuth wechatAuth,
			CommonsMultipartFile profileImg) {
		String dest = FileUtil.getPersonInfoImagePath();
		String profileImgAddr = ImageUtil.generateThumbnail(profileImg, dest);
		wechatAuth.getPersonInfo().setProfileImg(profileImgAddr);
	}

}
