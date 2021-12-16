package algorithm.des;

public enum DeviceEnum {
	PC("PC",  "PC后台端"),
	SEA("SEA",  "移动溯源端"),
	SALES("SALES",  "移动台账端");

	private String code;

	private String message;

	private DeviceEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
