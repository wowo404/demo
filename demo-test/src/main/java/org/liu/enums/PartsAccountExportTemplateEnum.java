package org.liu.enums;

import java.util.ArrayList;
import java.util.List;

public enum PartsAccountExportTemplateEnum {
    code {
        @Override
        public String getMsg() {
            switch (language) {
                case CHINESE:
                    return "编号";
                case ENGLISH:
                    return "";
                case FRENCH:
                    return "";
                case TURKISH:
                    return "";
                case SPANISH:
                    return "";
                default:
                    break;
            }
            return "";
        }
    }, drawing_number {
        @Override
        public String getMsg() {
            switch (language) {
                case CHINESE:
                    return "图号";
                case ENGLISH:
                    return "";
                case FRENCH:
                    return "";
                case TURKISH:
                    return "";
                case SPANISH:
                    return "";
                default:
                    break;
            }
            return "";
        }
    }, name {
        @Override
        public String getMsg() {
            switch (language) {
                case CHINESE:
                    return "零部件名称";
                case ENGLISH:
                    return "";
                case FRENCH:
                    return "";
                case TURKISH:
                    return "";
                case SPANISH:
                    return "";
                default:
                    break;
            }
            return "";
        }
    }, status {
        @Override
        public String getMsg() {
            switch (language) {
                case CHINESE:
                    return "状态";
                case ENGLISH:
                    return "";
                case FRENCH:
                    return "";
                case TURKISH:
                    return "";
                case SPANISH:
                    return "";
                default:
                    break;
            }
            return "";
        }
    }, vehicle_vin_code {
        @Override
        public String getMsg() {
            switch (language) {
                case CHINESE:
                    return "已使用车辆VIN号";
                case ENGLISH:
                    return "";
                case FRENCH:
                    return "";
                case TURKISH:
                    return "";
                case SPANISH:
                    return "";
                default:
                    break;
            }
            return "";
        }
    }, vehicle_name {
        @Override
        public String getMsg() {
            switch (language) {
                case CHINESE:
                    return "已使用车辆名称";
                case ENGLISH:
                    return "";
                case FRENCH:
                    return "";
                case TURKISH:
                    return "";
                case SPANISH:
                    return "";
                default:
                    break;
            }
            return "";
        }
    }, create_time {
        @Override
        public String getMsg() {
            switch (language) {
                case CHINESE:
                    return "登记时间";
                case ENGLISH:
                    return "";
                case FRENCH:
                    return "";
                case TURKISH:
                    return "";
                case SPANISH:
                    return "";
                default:
                    break;
            }
            return "";
        }
    }, creator_name {
        @Override
        public String getMsg() {
            switch (language) {
                case CHINESE:
                    return "登记人";
                case ENGLISH:
                    return "";
                case FRENCH:
                    return "";
                case TURKISH:
                    return "";
                case SPANISH:
                    return "";
                default:
                    break;
            }
            return "";
        }
    }, last_update_time {
        @Override
        public String getMsg() {
            switch (language) {
                case CHINESE:
                    return "最后更新时间";
                case ENGLISH:
                    return "";
                case FRENCH:
                    return "";
                case TURKISH:
                    return "";
                case SPANISH:
                    return "";
                default:
                    break;
            }
            return "";
        }
    }, last_updater_name {
        @Override
        public String getMsg() {
            switch (language) {
                case CHINESE:
                    return "最后更新人";
                case ENGLISH:
                    return "";
                case FRENCH:
                    return "";
                case TURKISH:
                    return "";
                case SPANISH:
                    return "";
                default:
                    break;
            }
            return "";
        }
    };

    private static LanguageEnum language;

    public static void setLanguage(LanguageEnum language) {
        PartsAccountExportTemplateEnum.language = language;
    }

    public abstract String getMsg();

    public static List<String> getMsgList(LanguageEnum language) {
        PartsAccountExportTemplateEnum.language = language;
        List<String> msgList = new ArrayList<>();
        for (PartsAccountExportTemplateEnum value : PartsAccountExportTemplateEnum.values()) {
            msgList.add(value.getMsg());
        }
        return msgList;
    }
}
