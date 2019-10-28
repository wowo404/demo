package org.demo.json;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import org.demo.json.enums.ServiceName;
import org.demo.json.enums.TransType;

import java.time.LocalDate;

@Getter
@Setter
public class BaseReq {
	
	@SerializedName("test-id")
	private String id;
	private String name;
	private ServiceName serviceName;
	private TransType transType;
	private LocalDate birthday;
}
