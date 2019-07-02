<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>基本信息</title>
</head>
<style>
.item {
    border-bottom: 1px solid rgba(32, 32, 32, 0.10);
    min-width: 1200px;
    width: 1300px;
    margin: 0 auto;
}

.item .title {
    height: 54px;
    line-height: 54px;
    margin-bottom: 0;
    margin-top: 16px;
}

.item .itemDetail {
    padding-top: 6px;
}

.item .itemDetail span {
    display: inline-block;
    width: 33%;
    line-height: 22px;
    margin-bottom: 16px;
    vertical-align: top;
}
</style>
<#assign repayMethodArray=['等额本息','到期还本息','按月付息到期还本','等额本金','按季付息到期还本']>
<#function recursion riskTypeName secondRiskTypeName thirdRiskTypeName>
    <#list riskItemList as item>
        <#if item.riskType == riskTypeName>
            <#if item.details??>
            <#--如果第二个参数是空字符串，则直接返回不再迭代第二个子list-->
                <#if secondRiskTypeName??>
                    <#return item.riskCount/>
                </#if>
                <#list item.details as subItem>
                    <#if subItem.riskType == secondRiskTypeName>
                        <#if subItem.details??>
                        <#--如果第三个参数是空字符串，则直接返回不再迭代第三个子list-->
                            <#if thirdRiskTypeName??>
                                <#return subItem.riskCount/>
                            </#if>
                            <#list subItem.details as thirdItem>
                                <#if thirdItem.riskType == thirdRiskTypeName>
                                    <#return thirdItem.riskCount/>
                                </#if>
                            </#list>
                        <#else>
                            <#return subItem.riskCount/>
                        </#if>
                    </#if>
                </#list>
            <#else>
                <#return item.riskCount/>
            </#if>
        </#if>
    </#list>
</#function>
<body>
    <div class="item">
        <div class="title">借款信息</div>
        <div class="itemDetail">
            <span>${queryDate}</span>
            <span>${recursion('司法执行信息','','')}</span>
            <span>${recursion('信贷逾期信息','近一个月','')}</span>
            <span>${recursion('机构查询次数','近一个月','商业银行')}</span>
            <span>${recursion('多设备','','')}</span>
            <span>${mobile[0..2] + "****" + mobile[7..10]}</span>
            <span>
                <#if idNo??>
                        <#assign idNoLen = idNo?length/>
                    ${idNo[0..6]+"****"+idNo[idNoLen-2..idNoLen-1]}
                </#if>
            </span>
            <span>${(idNo?length gt 0) ? string(idNo,"无")}</span>
            <span>借款用途：${ loanDetail.loanUsed }</span>
            <span>还款方式：<#if loanDetail.repayMethod??>${ repayMethodArray[loanDetail.repayMethod] }</#if></span>
            <span>还款户名：${ loanDetail.repayAccount!"" }</span>
            <span>借款金额：${ loanDetail.loanAmt }</span>
            <span>还款期数：${ loanDetail.repayTerm }</span>
            <span>银行卡号：${ loanDetail.bankNo }</span>
            <span>借款期限：${ loanDetail.term }个月</span>
            <span>借款利率：${ loanDetail.loanRate }%</span>
            <span>开户银行：${ loanDetail.openingBank }</span>
        </div>
    </div>
    <div class="item">
        <div class="title">借款人身份信息</div>
        <div class="itemDetail">
            <span>姓名：${ personDetail.realName }</span>
            <span>身份证号：${ personDetail.identityNo }</span>
            <span>性别：${ ['男', '女'][personDetail.sex-1] }</span>
            <span>手机号码：${ personDetail.phone }</span>
            <span>民族：${ personDetail.famousRace }</span>
            <span>出生日期：${ personDetail.birthDate }</span>
            <span>个人健康：${ ['良好','一般','较差'][personDetail.personalHealth] }</span>
            <span>最高学历：${ ['研究生','大学本科','大学专科','技术学校','中等专科学校','高中','初中','小学','文盲'][personDetail.educationLevel] }</span>
            <span>最高学位：${ ['博士','硕士','学士','其它'][personDetail.highestDegree] }</span>
            <span>是否户主：${ ['是 ', '否'][personDetail.isHousehold-1] }</span>
            <span>户口省份：${ personDetail.provinceDetail }</span>
            <span>户籍地址：${ personDetail.permanentAddressDetail }</span>
            <span>婚姻状态：${ ['未婚 ', '已婚', '丧偶', '离异'][personDetail.isMarriage] }</span>
        </div>
    </div>
    <div class="item">
        <div class="title">居住信息</div>
        <div class="itemDetail">
            <span>居住区域：${ liveDetail.liveAreaDetail }</span>
            <span>详细地址：${ liveDetail.address }</span>
            <span>居住状态：${ ['常住','临租'][liveDetail.liveStatus] }</span>
            <span>居住状况：${ ['自置','按揭','亲属房','集体宿舍','租房','共有住宅','其它'][liveDetail.livingCondition] }</span>
        </div>
    </div>
    <div class="item">
        <div class="title">工作收入信息</div>
        <div class="itemDetail">
            <span>工作类型：${ ['公务员','国企职工','普通职工','个体工商户等'][workDetail.workType] }</span>
            <span>收入来源：${ ['工资收入','经营收入','其它'][workDetail.incomeSource] }</span>
            <span>税后月收入：${ workDetail.afterTaxAmt }</span>
            <span>营业执照号：${ workDetail.licenseNumber }</span>
            <span>门店字号：${ workDetail.shopName }</span>
            <span>税务登记号：${ workDetail.taxNo }</span>
        </div>
    </div>
    <div class="item">
        <div class="title">配偶身份信息</div>
        <div class="itemDetail">
            <span>姓名：${ spouseDetail.name }</span>
            <span>身份证号：${ spouseDetail.identity }</span>
            <span>性别：${ ['男', '女'][spouseDetail.sex-1] }</span>
            <span>民族：${ spouseDetail.famousRace }</span>
            <span>出生日期：${ spouseDetail.birthDate }</span>
            <span>个人健康：${ ['良好','一般','较差'][spouseDetail.personalHealth] }</span>
            <span>是否户主：${ ['是 ', '否'][spouseDetail.isHousehold-1] }</span>
        </div>
    </div>
    <div class="item">
        <div class="title">紧急联系人信息</div>
        <div class="itemDetail">
            <span>姓名：${ contactsDetail.name }</span>
            <span>手机号码：${ contactsDetail.mobile }</span>
        </div>
    </div>
</body>

</html>