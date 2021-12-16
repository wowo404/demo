package algorithm.des;

import org.apache.commons.lang3.StringUtils;

public enum LanguageEnum {

    CHINESE,

    ENGLISH,

    FRENCH,

    TURKISH,

    SPANISH;

    public static LanguageEnum resolve(String language) {
        if (StringUtils.isEmpty(language)) {
            return CHINESE;
        }
        LanguageEnum lan = valueOf(language);
        return lan == null ? CHINESE : lan;
    }

}
