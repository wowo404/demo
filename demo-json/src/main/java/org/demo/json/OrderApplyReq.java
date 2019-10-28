package org.demo.json;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 申请订单req
 *
 * @author liuzhangsheng
 * @create 2019/5/21
 */
@Data
public class OrderApplyReq {

    private String borrowerNo;
    private String mobile;
    private LoanDetail loanDetail;
    private PersonDetail personDetail;
    private SpouseDetail spouseDetail;
    private ContactsDetail contactsDetail;
    private LiveDetail liveDetail;
    private WorkDetail workDetail;

    @Data
    public static class LoanDetail {
        private String productNo;
        private String loanUsed;
        private BigDecimal loanAmt;
        private Integer term;
        private Integer termType;
        private Integer repayMethod;
        private Integer repayTerm;
        private BigDecimal loanRate;
        private String repayAccount;
        private String bankNo;
        private String openingBank;
    }

    @Data
    public static class PersonDetail {
        private String realName;
        private String identityNo;
        private String phone;
        private Integer sex;
        private String famousRace;
        private String birthDate;
        private Integer personalHealth;
        private Integer educationLevel;
        private Integer highestDegree;
        private Integer isHousehold;
        private String province;
        private String permanentAddress;
        private Integer isMarriage;
        private String identityCard1;
        private String identityCard2;
        private String householdBook1;
        private String householdBook2;
    }

    @Data
    public static class SpouseDetail {
        private String name;
        private String identity;
        private Integer sex;
        private String famousRace;
        private String birthDate;
        private Integer personalHealth;
        private Integer isHousehold;
        private String identityCard1;
        private String identityCard2;
        private String weddingPhoto;
    }

    @Data
    public static class ContactsDetail {
        private String name;
        private String mobile;
    }

    @Data
    public static class LiveDetail {
        private String liveArea;
        private String address;
        private Integer liveStatus;
        private Integer livingCondition;
    }

    @Data
    public static class WorkDetail {
        private String workType;
        private Integer incomeSource;
        private String afterTaxAmt;
        private String licenseNumber;
        private String shopName;
        private String taxNo;
    }
}
