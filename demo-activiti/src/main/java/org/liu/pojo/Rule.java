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

@ApiModel("规则表")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RULE")
public class Rule {

	@ApiModelProperty(value = "规则ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ruleId;
	@ApiModelProperty(value = "规则名", required = true)
	@NotBlank
	private String ruleName;
	@ApiModelProperty(value = "规则包", required = true)
	@NotBlank
	private String rulePackage;
	@ApiModelProperty(value = "规则内容", required = true)
	@NotBlank
	private String content;
	@ApiModelProperty(value = "规则分值", required = true)
	@NotNull
	private Integer score;
	@ApiModelProperty(value = "创建时间")
	@JsonSerialize(using = CustomDateTimeJsonSerializer.class)
	@Column(insertable = false)//使用系统默认时间
	private Date createTime;
	
}
