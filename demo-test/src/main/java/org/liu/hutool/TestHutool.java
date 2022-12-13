package org.liu.hutool;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Date;

public class TestHutool {

    public static void main(String[] args) {
        String s = "ab\r"+ "cd\n" + "e   f\r\n g";
        System.out.println(s);
        System.out.println(StrUtil.removeAll(s, '\r', '\n', ' '));
        System.out.println(s.replace("\r", "").replace("\n", "").replace(" ", ""));

        String text = "绝对值($d)*(绝对值($e)+$f)-绝对值({$a+绝对值($b+($c*$g))})";
        System.out.println(ordinalIndexOf(text, "", 3, 19));
    }

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
