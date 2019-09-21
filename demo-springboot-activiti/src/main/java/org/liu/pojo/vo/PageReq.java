package org.liu.pojo.vo;

import org.springframework.data.domain.Sort.Direction;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 基础分页req对象
 * @author liuzhsh
 */
@ApiModel("基础分页req对象")
public class PageReq {
	
	private static final int DEFAULT_PAGE_SIZE = 10;
	//注意：spring data jpa默认起始页码是从0开始
	private static final int DEFAULT_PAGE_NUMBER = 0;

	@ApiModelProperty("页号")
	private int pageNumber = DEFAULT_PAGE_NUMBER;
	@ApiModelProperty("每页数量")
	private int pageSize = DEFAULT_PAGE_SIZE;
	//asc/desc,不区分大小
	@ApiModelProperty("排序方式，ASC/DESC,默认按创建时间倒序排列")
	private String direction = "DESC";
	@ApiModelProperty("排序字段数组")
	private String[] properties;
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Direction getDirection() {
		return Direction.fromString(direction);
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String[] getProperties() {
		return properties;
	}
	public void setProperties(String[] properties) {
		this.properties = properties;
	}
	
}
