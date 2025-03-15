package algorithm.des;

import lombok.Data;

/**
 * @author liangxp
 * @createTime 2021年11月16日 13:55
 */

@Data
public class AuthInfo {

    /**
     * 登录用户id
     */
    private Long userId;
	/**
	 * 用户真实姓名
	 */
	private String userRealName;
	/**
	 * 登陆设备
	 */
	private DeviceEnum deviceEnum;
	/**
	 * 语言
	 */
	private LanguageEnum languageEnum;
    /**
     * 登录信息失效时间
     */
    private Long expireTime;

    public AuthInfo() {
		// TODO Auto-generated constructor stub
	}

	public AuthInfo(Long userId, Long expireTime) {
		super();
		this.userId = userId;
		this.expireTime = expireTime;
	}
    
    
}
