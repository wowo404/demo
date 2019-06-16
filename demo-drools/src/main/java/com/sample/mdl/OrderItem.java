package com.sample.mdl;

import lombok.Data;

@Data
public class OrderItem {
	
	private Order order;
	private String itemName;
	private double value;

}
