package org.liu.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询用户req
 * @author liuzhsh
 */
@ApiModel("查询用户req")
@Data
@EqualsAndHashCode(callSuper=false)
public class ListUserReq extends PageReq {

	@ApiModelProperty("用户名")
	private String userName;
	@ApiModelProperty("邮箱")
	private String email;
	@ApiModelProperty("起始时间")
	private String startDate;
	@ApiModelProperty("结束时间")
	private String endDate;
	
}
