package org.liu.freemarker;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuzhangsheng
 * @create 2019/6/14
 */
public class TestFreemarker {

    public static void main(String[] args) throws IOException, TemplateException {
        createHtmlFromTpl();
    }

    public static void createHtmlFromTpl() throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setClassForTemplateLoading(TestFreemarker.class, "/freemarker");
        Template template = cfg.getTemplate("base_info_tpl.ftl");

        LoanDetail detail = new LoanDetail();
        detail.setLoanUsed("ok");
        detail.setRepayMethod(2);
        detail.setRepayAccount("aaaa");
        Map<String, Object> map = new HashMap<>();
        map.put("loanDetail", detail);
        JSONObject jsonObject = JSON.parseObject("{\"msg\":\"成功\",\"code\":0,\"data\":{\"queryDate\":\"2019-06-1721:26:56\",\"bornPlace\":\"暂无\",\"riskNo\":\"151114574945325276\",\"riskItemList\":[{\"riskType\":\"司法执行信息\",\"details\":null,\"riskCount\":1},{\"riskType\":\"失信信息\",\"details\":null,\"riskCount\":0},{\"riskType\":\"税务执法信息\",\"details\":null,\"riskCount\":0},{\"riskType\":\"催欠公告信息\",\"details\":null,\"riskCount\":0},{\"riskType\":\"机构查询次数\",\"details\":[{\"riskType\":\"近一个月\",\"details\":[{\"riskType\":\"商业银行\",\"riskCount\":2},{\"riskType\":\"小额贷款公司\",\"riskCount\":0},{\"riskType\":\"互联网金融\",\"riskCount\":0},{\"riskType\":\"汽车金融\",\"riskCount\":0},{\"riskType\":\"消费金融公司\",\"riskCount\":0},{\"riskType\":\"支付机构\",\"riskCount\":0}],\"riskCount\":0},{\"riskType\":\"近三个月\",\"details\":[{\"riskType\":\"商业银行\",\"riskCount\":0},{\"riskType\":\"小额贷款公司\",\"riskCount\":0},{\"riskType\":\"互联网金融\",\"riskCount\":0},{\"riskType\":\"汽车金融\",\"riskCount\":0},{\"riskType\":\"消费金融公司\",\"riskCount\":0},{\"riskType\":\"支付机构\",\"riskCount\":0}],\"riskCount\":0},{\"riskType\":\"近六个月\",\"details\":[{\"riskType\":\"商业银行\",\"riskCount\":0},{\"riskType\":\"小额贷款公司\",\"riskCount\":0},{\"riskType\":\"互联网金融\",\"riskCount\":0},{\"riskType\":\"汽车金融\",\"riskCount\":0},{\"riskType\":\"消费金融公司\",\"riskCount\":0},{\"riskType\":\"支付机构\",\"riskCount\":0}],\"riskCount\":0}],\"riskCount\":0},{\"riskType\":\"信贷逾期信息\",\"details\":[{\"riskType\":\"近一个月\",\"details\":null,\"riskCount\":3},{\"riskType\":\"近三个月\",\"details\":null,\"riskCount\":0},{\"riskType\":\"近六个月\",\"details\":null,\"riskCount\":0}],\"riskCount\":0},{\"riskType\":\"多头借贷\",\"details\":[{\"riskType\":\"近7天\",\"details\":null,\"riskCount\":0},{\"riskType\":\"近一个月\",\"details\":null,\"riskCount\":0},{\"riskType\":\"近三个月\",\"details\":null,\"riskCount\":0}],\"riskCount\":0},{\"riskType\":\"多设备\",\"details\":[{\"riskType\":\"近7天\",\"details\":null,\"riskCount\":0},{\"riskType\":\"近一个月\",\"details\":null,\"riskCount\":0},{\"riskType\":\"近三个月\",\"details\":null,\"riskCount\":0}],\"riskCount\":0},{\"riskType\":\"多个关联手机号\",\"details\":[{\"riskType\":\"近3个月\",\"details\":null,\"riskCount\":0}],\"riskCount\":0}],\"currentPlace\":\"暂无\",\"blackList\":[{\"name\":\"新颜黑名单\",\"desc\":\"未命中\"},{\"name\":\"大圣黑名单\",\"desc\":\"未命中\"}],\"userName\":\"粟广源\",\"idNo\":\"430105199005122516\",\"age\":29},\"success\":true}");
        FileWriter fw = new FileWriter(new File("/Users/liuzhangsheng/Documents/new.html"));
        JSONObject data = jsonObject.getJSONObject("data");
        data.put("mobile", "15058124996");
        JSONArray riskItemList = data.getJSONArray("riskItemList");
        for(int i = 0; i < riskItemList.size(); i++){
            JSONObject item = riskItemList.getJSONObject(i);
            Object details = item.get("details");
            System.out.println();
        }
        template.process(data, fw);
    }

    //--__--内部类一定要public，不然freemarker中获取不到内部类的属性，自己坑自己
    public static class LoanDetail {
        private String loanUsed;
        private Integer repayMethod;
        private String repayAccount;

        public String getRepayAccount() {
            return repayAccount;
        }

        public void setRepayAccount(String repayAccount) {
            this.repayAccount = repayAccount;
        }

        public String getLoanUsed() {
            return loanUsed;
        }

        public void setLoanUsed(String loanUsed) {
            this.loanUsed = loanUsed;
        }

        public Integer getRepayMethod() {
            return repayMethod;
        }

        public void setRepayMethod(Integer repayMethod) {
            this.repayMethod = repayMethod;
        }
    }

}
