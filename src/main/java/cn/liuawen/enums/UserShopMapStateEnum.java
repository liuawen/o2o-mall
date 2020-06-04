package cn.liuawen.enums;
/**
 * @author : Liu Awen Email:willowawen@gmail.com
 * @create : 2020-02-02
 */
public enum UserShopMapStateEnum {
	SUCCESS(1, "操作成功"), INNER_ERROR(-1001, "操作失败"), NULL_USERSHOP_ID(-1002,
			"UserShopId为空"), NULL_USERSHOP_INFO(-1003, "传入了空的信息");

	private int state;

	private String stateInfo;

	private UserShopMapStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static UserShopMapStateEnum stateOf(int index) {
		for (UserShopMapStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
