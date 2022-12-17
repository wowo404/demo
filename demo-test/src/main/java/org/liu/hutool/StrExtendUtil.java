package org.liu.hutool;

import cn.hutool.core.util.StrUtil;

/**
 * 扩展hutool的StrUtil
 *
 * @Author lzs
 * @Date 2022/12/13 11:29
 **/
public class StrExtendUtil {

    public static int ordinalIndexOf(CharSequence str, CharSequence searchStr, int ordinal, int startIndex) {
        if (str == null || searchStr == null || ordinal <= 0) {
            return -1;
        }
        if (searchStr.length() == 0) {
            return 0;
        }
        int found = 0;
        int index = startIndex;
        do {
            index = StrUtil.indexOf(str, searchStr, index + 1, false);
            if (index < 0) {
                return index;
            }
            found++;
        } while (found < ordinal);
        return index;
    }

}
