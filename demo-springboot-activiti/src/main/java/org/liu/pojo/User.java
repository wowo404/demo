package org.liu.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.liu.json.serializer.CustomDateTimeJsonSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("用户表")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER")
public class User {

	@ApiModelProperty(value = "用户ID", required = true)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	@ApiModelProperty(value = "用户名", required = true)
	@NotBlank
	private String userName;
	@ApiModelProperty(value = "邮箱", required = true)
	@NotBlank
	private String email;
	@ApiModelProperty(value = "年龄", required = true)
	@NotNull
	private Integer age;
	@ApiModelProperty(value = "创建时间")
	@JsonSerialize(using = CustomDateTimeJsonSerializer.class)
	@Column(insertable = false)//使用系统默认时间
	private Date createTime;
	
}
