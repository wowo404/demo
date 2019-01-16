package org.liu.json;

import java.time.LocalDate;

import org.liu.enums.ServiceName;
import org.liu.enums.TransType;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

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
