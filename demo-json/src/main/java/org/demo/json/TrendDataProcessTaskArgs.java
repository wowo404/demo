package org.demo.json;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrendDataProcessTaskArgs {
    private Integer executeInterval;//执行间隔
    private Integer executeIntervalUnit;//执行间隔单位，0-秒，1-分钟，2-小时，3-天，4-周，5-月
    private String collectionName;//mongo的集合名
    private Boolean isNeedSplitCollectionById;//是否按监测点ID创建集合，格式为：collectionName_id
}
