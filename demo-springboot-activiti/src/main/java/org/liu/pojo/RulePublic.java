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

@ApiModel("规则的头部import,declare,global,function,query")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RULE_PUBLIC")
public class RulePublic {

	@ApiModelProperty(value = "ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rulePublicId;
	@ApiModelProperty(value = "类型(import,declare,global,function,query)", required = true)
	@NotBlank
	private String contentType;
	@ApiModelProperty(value = "内容", required = true)
	@NotBlank
	private String content;
	@ApiModelProperty(value = "排序，方便在查询出结果后拼接规则文件", required = true)
	@NotNull
	private Integer orderNum;
	@ApiModelProperty(value = "创建时间")
	@JsonSerialize(using = CustomDateTimeJsonSerializer.class)
	@Column(insertable = false)//使用系统默认时间
	private Date createTime;
	
}
