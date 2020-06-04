package cn.liuawen.util.weixin.message.pojo;


/**
 * @author : Liu Awen Email:willowawen@gmail.com
 * @create : 2020-02-02
 *
 * 微信通用接口凭证
 */
public class AccessToken {  
    // 获取到的凭证  
    private String token;  
    // 凭证有效时间，单位：秒  
    private int expiresIn;  
  
    public String getToken() {  
        return token;  
    }  
  
    public void setToken(String token) {  
        this.token = token;  
    }  
  
    public int getExpiresIn() {  
        return expiresIn;  
    }  
  
    public void setExpiresIn(int expiresIn) {  
        this.expiresIn = expiresIn;  
    }  
}  
