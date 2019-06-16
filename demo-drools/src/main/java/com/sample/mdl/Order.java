package com.sample.mdl;

import java.util.Date;

import lombok.Data;

@Data
public class Order {

	private String orderId;
	private String userId;
	private Date createTime;
	
}
