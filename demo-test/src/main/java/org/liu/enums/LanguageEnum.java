package org.liu.enums;

import cn.hutool.core.util.StrUtil;

public enum LanguageEnum {

    CHINESE,

    ENGLISH,

    FRENCH,

    TURKISH,

    SPANISH;

    public static LanguageEnum resolve(String language) {
        if (StrUtil.isEmpty(language)) {
            return CHINESE;
        }
        return valueOf(language);
    }

}
