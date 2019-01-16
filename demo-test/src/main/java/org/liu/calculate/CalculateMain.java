package org.liu.calculate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.liu.model.ChannelProductReq;

public class CalculateMain {

	public static void main(String[] args) {
		BigDecimal a = new BigDecimal("30");
        Integer b = 10;
        change(a);
        changeInt(b);
        System.out.println("change BigDecimal:" + a);
        System.out.println("change Integer:" + b);

        //值传递，引用传递
        ChannelProductReq req = null;
        setValue(req);//这里无法改变req
        System.out.println(req.getPhone());//报NPE，

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, a);
        map.put(2, b);
        Set<Entry<Integer, Object>> set = map.entrySet();
		for (Entry<Integer, Object> entry : set) {
			System.out.println(entry.getValue());
		}
	}
	
	private static void setValue(ChannelProductReq req){
		req = new ChannelProductReq();
		req.setPhone("111111");
	}
	
	private static void changeInt(Integer a){
		a = a - 4;
	}
	
	private static void change(BigDecimal amt){
		amt = amt.subtract(new BigDecimal("10"));
	}
	
	public static void testOrder(){
		List<Rule> ruleList = new ArrayList<Rule>();
		ruleList.add(new Rule(6));
		ruleList.add(new Rule(3));
		ruleList.add(new Rule(4));
		ruleList.add(new Rule(2));
		ruleList.sort((rule1, rule2) -> {
			if(rule1.getOrder() < rule2.getOrder()){
				return -1;
			} else if(rule1.getOrder() == rule2.getOrder()){
				return 0;
			} else {
				return 1;
			}
		});
		for (Rule rule : ruleList) {
			System.out.println(rule.getOrder());
		}
	}

	public static class Rule{
		private Integer order;

		public Rule(Integer order){
			this.order = order;
		}
		
		public Integer getOrder() {
			return order;
		}

		public void setOrder(Integer order) {
			this.order = order;
		}
		
	}
	
}
