package cn.liuawen.entity;

import java.util.Date;

/**
 * @author : Liu Awen Email:willowawen@gmail.com
 * @create : 2020-01-04
 * 用户信息  PersonInfo
 * ID 姓名
 * 为什么字段设置引用类型呢
 * 基本类型 会赋值 默认值的 0 0.0 null等x
 * 我希望不是默认的
 */
public class PersonInfo {

	private Long userId;
	private String name;
	private Date birthday;
	private String gender;
	private String phone;
	//邮箱
	private String email;
	private String profileImg;
	private Integer customerFlag;
	private Integer shopOwnerFlag;
	private Integer adminFlag;
	private Date createTime;
	private Date lastEditTime;
	//0:禁止使用本商城，1:允许使用本商城
	private Integer enableStatus;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public Integer getCustomerFlag() {
		return customerFlag;
	}

	public void setCustomerFlag(Integer customerFlag) {
		this.customerFlag = customerFlag;
	}

	public Integer getShopOwnerFlag() {
		return shopOwnerFlag;
	}

	public void setShopOwnerFlag(Integer shopOwnerFlag) {
		this.shopOwnerFlag = shopOwnerFlag;
	}

	public Integer getAdminFlag() {
		return adminFlag;
	}

	public void setAdminFlag(Integer adminFlag) {
		this.adminFlag = adminFlag;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	public Integer getEnableStatus() {
		return enableStatus;
	}

	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}

}
