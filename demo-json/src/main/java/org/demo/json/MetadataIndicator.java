package org.demo.json;

import lombok.Data;

import java.io.Serializable;

@Data
public class MetadataIndicator implements Serializable {
    /**
     * 标识符
     */
    private Long id;
    /**
     * 版本
     */
    private String version;
    /**
     * 名称_中
     */
    private String nameChinese;
    /**
     * 别称_中
     */
    private String aliasChinese;
    /**
     * 名称_英
     */
    private String nameEnglish;
    /**
     * 指标解释
     */
    private String explanation;
    /**
     * 计算类型：0-无，1-excel公式，2-其他
     */
    private Integer calculateType;
    /**
     * 计算方法
     */
    private String calculateMethod;
    /**
     * 计量单位
     */
    private String measureUnit;
    /**
     * 指标代码
     */
    private String code;
    /**
     * 种类（一），按指标数据的来源渠道分：01-调查指标，02-综合指标，03-分析指标
     */
    private String typeOne;
    /**
     * 种类（二），按指标数据的生成方式分：01-原生指标，02-派生指标
     */
    private String typeTwo;
    /**
     * 种类（三），按指标数据的计量方式分：01-实物量指标，02-价值量指标
     */
    private String typeThree;
    /**
     * 状态：0-停用，1-启用
     */
    private Integer status;
    /**
     * 数据类型：0-文本，1-整数，2-浮点数，3-日期，4-时间戳，5-布尔
     */
    private Integer dataType;
    /**
     * 数据表示格式，数据类型的具体表现形式，即对应的数据库数据类型
     */
    private String dataRepresentationFormat;
    /**
     * 浮点数小数位数
     */
    private Integer dataPrecision;
    /**
     * 数据长度
     */
    private Integer dataLength;
    /**
     * 是否字典数据：0-否，1-是
     */
    private Integer isDictionaryData;
    /**
     * 字典key
     */
    private String dictionaryKey;
    /**
     * 是否脱敏：0-否，1-是；此字段还需配合用户表desensitization_flag使用
     */
    private Integer isDesensitization;
    /**
     * 脱敏类型：0-自定义，1-姓名，2-手机号码，3-身份证号，4-邮箱
     */
    private Integer desensitizationType;
    /**
     * 转换后的信息，json格式，字段和此表相同
     */
    private String transformationInfo;
}

