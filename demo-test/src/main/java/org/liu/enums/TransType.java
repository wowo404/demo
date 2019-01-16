package org.liu.enums;

import org.liu.gsonextension.GsonEnum;

/**
 * 交易类型 具体字段注释见文档：账户管理平台接口说明文档_V3.0.0.3.pdf
 * 
 * @author liuzhangsheng
 * @create 2018/3/20
 */
public enum TransType implements GsonEnum<TransType> {
	flag_017("017"), flag_010("010"), flag_018("018"), flag_019("019");
	public String type;

	TransType(String type) {
		this.type = type;
	}
	
	public static TransType parse(String type) {
		switch (type) {
		case "017":
			return TransType.flag_017;
		case "010":
			return TransType.flag_010;
		case "018":
			return TransType.flag_018;
		case "019":
			return TransType.flag_019;
		default:
			throw new IllegalArgumentException("There is no enum names with [" + type + "] exists! ");
		}
	}

	@Override
	public String serialize() {
		return type;
	}

	@Override
	public TransType deserialize(String jsonEnum) {
		return parse(jsonEnum);
	}
}
