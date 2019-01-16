package org.liu.json;

import lombok.Getter;
import lombok.Setter;

/**
 * 个人开户request
 *
 * @author liuzhangsheng
 * @create 2018/8/28
 */
@Setter
@Getter
public class AccountRegisterRequest extends CommonRequestData {

    /**
     * 参数名	类型	必填	长度	参数说明
     realName	string	Y	50	用户真实姓名
     idCardType	enum	Y		见【证件类型】
     idCardNo	string	Y	32	身份证号
     bankcardNo	string	Y	32	银行卡号
     mobile	string	Y	50	银行预留手机号
     */
    private String realName;
    private Integer idCardType;
    private String idCardNo;
    private String bankcardNo;
    private String mobile;

}
