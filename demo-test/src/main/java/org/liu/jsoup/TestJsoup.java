package org.liu.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestJsoup {

    public static void main(String[] args) {
        String html = "<p style=\"font-size: 16px; -webkit-tap-highlight-color: rgba(26, 26, 26, 0.3); -webkit-text-size-adjust: " +
                "auto; margin-bottom: 20px; padding: 0px; text-indent: 28px; line-height: 32px; text-align: justify; " +
                "word-break: break-all; caret-color: rgb(43, 43, 43); color: rgb(43, 43, 43); font-family: PingFangSC-Regular, " +
                "\"Pingfang SC\", \"Hiragino Sans GB\", \"Noto Sans\", \"Microsoft YaHei\", simsun, arial, helvetica, clean, " +
                "sans-serif;\">海外网1月9日电 据NBC报道，推特公司在一份声明中宣布，由于存在进一步煽动暴力的风险，" +
                "已“永久封禁”特朗普总统的账号。</p><p style=\"font-size: 16px; -webkit-tap-highlight-color: rgba(26, 26, 26, 0.3); " +
                "-webkit-text-size-adjust: auto; margin-bottom: 20px; padding: 0px; text-indent: 28px; line-height: 32px; " +
                "text-align: justify; word-break: break-all; caret-color: rgb(43, 43, 43); color: rgb(43, 43, 43); " +
                "font-family: PingFangSC-Regular, \"Pingfang SC\", \"Hiragino Sans GB\", \"Noto Sans\", \"Microsoft YaHei\", " +
                "simsun, arial, helvetica, clean, sans-serif;\">据此前报道，美国社交媒体平台推特和脸书6日宣布暂时封禁特朗普的账户，" +
                "理由是他的帖文缺乏事实根据并可能产生“暴力风险”。视频共享网站优兔6日删除一条特朗普“再次声称选举涉嫌舞弊，" +
                "但同时让示威者回家”的类似视频，暂时没有对特朗普账户采取其他限制举措。推特称，如果未来有进一步违反平台规则的情况发生，" +
                "包括暴力威胁政策等，将会导致账号被永久移除。（海外网 张敏）" +
                "<img src=\"http://manman-note.oss-cn-beijing.aliyuncs.com/note-api/20210112/jpg/9095299750797709.jpg?" +
                "Expires=1925742913&OSSAccessKeyId=LTAI4GF9egcA7vfQSU7iFEB2&Signature=H4QmpMbUXgGEflmD3lo31kuuDb8%3D\" " +
                "id=\"uploadImg0\" class=\"real-img\" style=\"font-size: 14px; font-family: UICTFontTextStyleBody; " +
                "-webkit-text-size-adjust: 100%;\"></p>";
        String html2 = "【防疫严防“掺假”，对违法者严惩不贷】篡改核酸检测报告、制作假核酸检测报告、开发假冒健康码APP……防疫不是儿戏，决不允许类似“掺假”行为。一些人以造假破坏防疫，是在给防疫挖坑，置我们通过艰苦努力换来的防疫重大战略成果而不顾。这本质上是做病毒的帮凶，是与人民为敌，必须对这种行为果断亮剑。#见识律镜##洞见计划##微博公开课# ￼央视网评：核酸报告都敢篡改，对给防疫挖坑者当果断亮剑！<br><br><img src=\"http://manman-note.oss-cn-beijing.aliyuncs.com/note-api/20210128/jpg/10521294988154261.jpg?Expires=1927168914&amp;OSSAccessKeyId=LTAI4GF9egcA7vfQSU7iFEB2&amp;Signature=z4xJRYe40iBggvxbHhNbihtn%2FYo%3D\" alt=\"\"><br>";
        System.out.println(calculateLineNumber(html2, 3));
    }

    public static void test() {
        String html = "<p style='white-space: normal;'>wo shi dashi</p>" +
                "<p style='white-space: normal;'>nishi shui</p>" +
                "<p style='white-space: normal;'>现在是北京时间xxx</p>" +
                "<p style='white-space: normal;'>美国懂王</p>" +
                "<p style='white-space: normal;'><span style='border: 1px solid rgb(0, 0, 0);'><strong><em>" +
                "<span style='border: 1px solid rgb(0, 0, 0); text-decoration-line: underline;'>" +
                "当你老了，头发白了，睡衣昏沉，火炉旁打盹，回忆青春，多少人曾爱慕你青春欢畅的时辰，爱慕你的美丽、假意或真心，" +
                "只有一个人还爱你虔诚的灵魂，爱你苍老的脸上的皱纹，当你老了，眼眉低垂，灯火昏黄不定，风吹过来，你的消息，" +
                "这就是我心里的歌，当你老了，头发白了，睡意昏沉，当你老了，走不动了，炉火旁打盹，回忆青春，多少人曾爱你青春欢畅的时辰，" +
                "爱慕你的美丽、假意或真心，只有一个人还爱你虔诚的灵魂，爱你苍老的脸上的皱纹，当你老了，眼眉低垂，灯火昏黄不定，" +
                "风吹过来，你的消息，这就是我心里的歌，当你老了，我真希望，这首歌是唱给你的</span></em></strong></span></p>" +
                "<p style='white-space: normal;'><span style='border: 1px solid rgb(0, 0, 0);'><strong><em>" +
                "<span style='border: 1px solid rgb(0, 0, 0); text-decoration-line: underline;'></span></em>" +
                "</strong></span></p><p style='white-space: normal;'>" +
                "<img src='http://dev-insurance.oss-cn-hangzhou.aliyuncs.com/27280564790268397.png?Expires=1915804800&OSSAccessKeyId=LTAI00MMtUnEjnPV&Signature=kUiDXQVxUP19mOjuYtFL%2B05CCO0%3D'/>" +
                "<br/></p>" +
                "<p style='white-space: normal;'><br/></p><p><br/></p>";
        Document document = Jsoup.parse(html);
        System.out.println(document.html());
    }

    /**
     * 获取笔记的前几行内容
     *
     * @param htmlStr 笔记的html字符串
     * @param line    需要的行数
     * @return
     */
    public static String calculateLineNumber(String htmlStr, int line) {
        StringBuilder stringBuilder = new StringBuilder();
        // 获取笔记内容的doc对象
        Document parse = Jsoup.parse(htmlStr, "UTF-8");
        // 获取body标签下的对象列表
        Elements children = parse.body().children();
        // 记录已存储的行数
        int len = 0;
        for (Element child : children) {
            // 获取对象的文本值，如果为图片会是空字符串
            String text = child.text();
            if (text != null && text.trim().length() > 0) {
                child.getElementsByTag("img").remove();
                // 该对象就是这一行需要的内容
                stringBuilder.append(child);
                len++;
                if (len >= line) {
                    break;
                }
            }
        }
        return String.valueOf(stringBuilder);
    }

}
