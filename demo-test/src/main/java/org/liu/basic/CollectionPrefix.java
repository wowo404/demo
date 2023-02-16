package org.liu.basic;

/**
 * mongo中集合的前缀
 * 枚举名称是报表名，prefix是集合名前缀
 */
public enum CollectionPrefix {
    //0-101名录库法人单位，1-101名录库产业活动单位，2-农业单位数据导入，3-规模农户数据导入，4-201-1表数据导入，
    //基础数据
    baseDataCompany1011(0, "baseDataCompany1011", 0),
    baseDataCompany1012(0, "baseDataCompany1012", 1),
    baseDataFarmer1013(0, "baseDataFarmer1013_", 2),
    baseDataFarmer1014(0, "baseDataFarmer1014_", 3),
    baseDataCompany2011(0, "baseDataCompany2011_", 4),
    //统计数据
    // 5-统计数据之工业数据，6-统计数据之服务业数据，7-统计数据之批发零售业，8-统计数据之住宿餐饮业，9-统计数据之投资，10-统计数据之房地产，11-统计数据之建筑业，
    countDataIndustry(1, "countDataIndustry_", 5),
    countDataService(1, "countDataService_", 6),
    countDataWholesaleRetail(1, "countDataWholesaleRetail_", 7),
    countDataAccommodationFood(1, "countDataAccommodationFood_", 8),
    countDataInvestment(1, "countDataInvestment_", 9),
    countDataRealEstate(1, "countDataRealEstate_", 10),
    countDataConstruction(1, "countDataConstruction_", 11),
    //部门数据
    // 12-部门数据之税务数据，13-部门数据之供电数据，
    departmentDataTax(2, "departmentDataTax_", 12),
    departmentDataElectricity(2, "departmentDataElectricity_", 13),
    //普查底册数据-企业
    //26-censusData
    censusDataCompanyLegalEntity(3, "censusDataCompanyLegalEntity_", 26),
    censusDataCompanyArchitectureTarget(3, "censusDataCompanyArchitectureTarget_", 27),
    censusDataCompanyIndustryTarget(3, "censusDataCompanyIndustryTarget_", 28),
    censusDataCompanyRestaurantLodgingTarget(3, "censusDataCompanyRestaurantLodgingTarget_", 29),
    censusDataCompanyRealEstateTarget(3, "censusDataCompanyRealEstateTarget_", 30),
    censusDataCompanyRetailTarget(3, "censusDataCompanyRetailTarget_", 32),
    censusDataCompanyServiceTarget(3, "censusDataCompanyServiceTarget_", 33),
    //censusDataCompanyWholesaleTarget(3, "censusDataCompanyWholesaleTarget_", 34),
    censusDataCompanyAgriculturalBaseInfo(3, "censusDataCompanyAgriculturalBaseInfo_", 35),
    censusDataCompanyAgriculturalBaseTarget(3, "censusDataCompanyAgriculturalBaseTarget_", 36),
    censusDataCompanyIndividualBusiness(3, "censusDataCompanyIndividualBusiness_", 37),
    censusDataCompanyIndividualBusinessesTarget(3, "censusDataCompanyIndividualBusinessesTarget_", 38),
    //年度统计数据
    // 14-年度统计之国民经济核算，15-年度统计之工业增加值，16-年度统计之用电量，17-年度统计之投资领域，18-年度统计之社会消费品零售额，
    // 19-年度统计之财政收支（一般公共预算收入），20-年度统计之财政收支（一般公共预算支出），21-年度统计之外资，22-年度统计之存贷款余额，
    // 23-年度统计之居民可支配收入（农村居民可支配收入），24-年度统计之居民可支配收入（城镇居民可支配收入），25-年度统计之居民消费价格指数
    yearCountDataGdpSummary(4, "yearCountDataGdpSummary", 14),
    yearCountDataIndustrialAddedValue(4, "yearCountDataIndustrialAddedValue", 15),
    yearCountDataElectricityConsumption(4, "yearCountDataElectricityConsumption", 16),
    yearCountDataInvestmentField(4, "yearCountDataInvestmentField", 17),
    yearCountDataSocialConsumableTotalRetailSales(4, "yearCountDataSocialConsumableTotalRetailSales", 18),
    yearCountDataGeneralPublicBudgetRevenueExpenditure(4, "yearCountDataGeneralPublicBudgetRevenueExpenditure", 19),
    yearCountDataGeneralPublicBudgetExpenditure(4, "yearCountDataGeneralPublicBudgetExpenditure", 20),
    yearCountDataForeignCapital(4, "yearCountDataForeignCapital", 21),
    yearCountDataDepositLoanBalance(4, "yearCountDataDepositLoanBalance", 22),
    yearCountDataDisposableIncome(4, "yearCountDataDisposableIncome", 23),
    yearCountDataUrbanDisposableIncome(4, "yearCountDataUrbanDisposableIncome_", 24),
    yearCountDataConsumerPriceIndex(4, "yearCountDataConsumerPriceIndex", 25),
    ;

    private Integer classify;
    private String prefix;
    private Integer type;

    CollectionPrefix(Integer classify, String prefix, Integer type) {
        this.classify = classify;
        this.prefix = prefix;
        this.type = type;
    }

    public Integer getClassify() {
        return classify;
    }

    public String getPrefix() {
        return prefix;
    }

    public Integer getType() {
        return type;
    }

    public String getCollectionName(String reportAt) {
        if (type == 0 || type == 1 || null == reportAt) {
            return prefix;
        }
        return prefix + reportAt;
    }

}
