package org.liu.database;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

//监控点 DO
public class EqpVariableRelationDO implements Serializable {
   
	private static final long serialVersionUID = 1L;

	private Long id;

    private String name;

    private String code;

    private Integer status;

    private Integer warnLevel;

    private Integer controlType;

    private Long eqpId;//监控目标id

    private String eqpName;//监控目标名称

    private Integer eqpType;//监控级别 1=工厂 2=生产线 3=工艺系统 4=主机 5=子机 6=设备

    private Integer dataSource;

    private Long categoryId;

    private String categoryName;

    private Long variableId;

    private String variableName;

    private Long gatewayId;

    private String gatewaySerialNum;

    private Long dataAlleywayId;

    private Integer dataAlleywayNum;

    private String remark;

    private String ext1;

    private String ext2;

    private String ext3;

    private Integer upInterval;

    private Integer upIntervalUnit;

    private Integer historySave;

    private Integer historySaveUnit;

    private Integer trendSave;

    private Integer trendSaveUnit;

    private Long deptId;

    private String deptName;

    private Integer prodLineId;

    private String productLine;

    private Long systemId;

    private String systemName;

    private Long mainId;

    private String mainName;

    private Long sonEqpId;//废弃

    private String sonEqpName;//废弃

    private Long createUid;

    private String createUname;

    private Date createTime;

    private Long modifyUid;

    private String modifyUname;

    private Date modifyTime;

    private String sysFlag;

    private String gsDesc;

    private Integer gsType;
    
    private BigDecimal rangeBegin;
    
    private BigDecimal rangeEnd;
    
    private BigDecimal exportBegin;
    
    private BigDecimal exportEnd;
    
    private BigDecimal precision;
    
    private BigDecimal compensation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getControlType() {
        return controlType;
    }

    public void setControlType(Integer controlType) {
        this.controlType = controlType;
    }

    public Long getEqpId() {
        return eqpId;
    }

    public void setEqpId(Long eqpId) {
        this.eqpId = eqpId;
    }

    public String getEqpName() {
        return eqpName;
    }

    public void setEqpName(String eqpName) {
        this.eqpName = eqpName;
    }

    public Integer getDataSource() {
        return dataSource;
    }

    public void setDataSource(Integer dataSource) {
        this.dataSource = dataSource;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getVariableId() {
        return variableId;
    }

    public void setVariableId(Long variableId) {
        this.variableId = variableId;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public Long getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(Long gatewayId) {
        this.gatewayId = gatewayId;
    }

    public String getGatewaySerialNum() {
        return gatewaySerialNum;
    }

    public void setGatewaySerialNum(String gatewaySerialNum) {
        this.gatewaySerialNum = gatewaySerialNum;
    }

    public Long getDataAlleywayId() {
        return dataAlleywayId;
    }

    public void setDataAlleywayId(Long dataAlleywayId) {
        this.dataAlleywayId = dataAlleywayId;
    }

    public Integer getDataAlleywayNum() {
        return dataAlleywayNum;
    }

    public void setDataAlleywayNum(Integer dataAlleywayNum) {
        this.dataAlleywayNum = dataAlleywayNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

    public Integer getUpInterval() {
        return upInterval;
    }

    public void setUpInterval(Integer upInterval) {
        this.upInterval = upInterval;
    }

    public Integer getUpIntervalUnit() {
        return upIntervalUnit;
    }

    public void setUpIntervalUnit(Integer upIntervalUnit) {
        this.upIntervalUnit = upIntervalUnit;
    }

    public Integer getHistorySave() {
        return historySave;
    }

    public void setHistorySave(Integer historySave) {
        this.historySave = historySave;
    }

    public Integer getHistorySaveUnit() {
        return historySaveUnit;
    }

    public void setHistorySaveUnit(Integer historySaveUnit) {
        this.historySaveUnit = historySaveUnit;
    }

    public Integer getTrendSave() {
        return trendSave;
    }

    public void setTrendSave(Integer trendSave) {
        this.trendSave = trendSave;
    }

    public Integer getTrendSaveUnit() {
        return trendSaveUnit;
    }

    public void setTrendSaveUnit(Integer trendSaveUnit) {
        this.trendSaveUnit = trendSaveUnit;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getProdLineId() {
        return prodLineId;
    }

    public void setProdLineId(Integer prodLineId) {
        this.prodLineId = prodLineId;
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public Long getSystemId() {
        return systemId;
    }

    public void setSystemId(Long systemId) {
        this.systemId = systemId;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public Long getMainId() {
        return mainId;
    }

    public void setMainId(Long mainId) {
        this.mainId = mainId;
    }

    public String getMainName() {
        return mainName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    public Long getSonEqpId() {
        return sonEqpId;
    }

    public void setSonEqpId(Long sonEqpId) {
        this.sonEqpId = sonEqpId;
    }

    public String getSonEqpName() {
        return sonEqpName;
    }

    public void setSonEqpName(String sonEqpName) {
        this.sonEqpName = sonEqpName;
    }

    public Long getCreateUid() {
        return createUid;
    }

    public void setCreateUid(Long createUid) {
        this.createUid = createUid;
    }

    public String getCreateUname() {
        return createUname;
    }

    public void setCreateUname(String createUname) {
        this.createUname = createUname;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getModifyUid() {
        return modifyUid;
    }

    public void setModifyUid(Long modifyUid) {
        this.modifyUid = modifyUid;
    }

    public String getModifyUname() {
        return modifyUname;
    }

    public void setModifyUname(String modifyUname) {
        this.modifyUname = modifyUname;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getSysFlag() {
        return sysFlag;
    }

    public void setSysFlag(String sysFlag) {
        this.sysFlag = sysFlag;
    }

    public Integer getEqpType() {
        return eqpType;
    }

    public void setEqpType(Integer eqpType) {
        this.eqpType = eqpType;
    }

    public Integer getWarnLevel() {
        return warnLevel;
    }

    public void setWarnLevel(Integer warnLevel) {
        this.warnLevel = warnLevel;
    }

    public String getGsDesc() {
        return gsDesc;
    }

    public void setGsDesc(String gsDesc) {
        this.gsDesc = gsDesc;
    }

    public Integer getGsType() {
        return gsType;
    }

    public void setGsType(Integer gsType) {
        this.gsType = gsType;
    }

	public BigDecimal getRangeBegin() {
		return rangeBegin;
	}

	public void setRangeBegin(BigDecimal rangeBegin) {
		this.rangeBegin = rangeBegin;
	}

	public BigDecimal getRangeEnd() {
		return rangeEnd;
	}

	public void setRangeEnd(BigDecimal rangeEnd) {
		this.rangeEnd = rangeEnd;
	}

	public BigDecimal getExportBegin() {
		return exportBegin;
	}

	public void setExportBegin(BigDecimal exportBegin) {
		this.exportBegin = exportBegin;
	}

	public BigDecimal getExportEnd() {
		return exportEnd;
	}

	public void setExportEnd(BigDecimal exportEnd) {
		this.exportEnd = exportEnd;
	}

	public BigDecimal getPrecision() {
		return precision;
	}

	public void setPrecision(BigDecimal precision) {
		this.precision = precision;
	}

	public BigDecimal getCompensation() {
		return compensation;
	}

	public void setCompensation(BigDecimal compensation) {
		this.compensation = compensation;
	}
}