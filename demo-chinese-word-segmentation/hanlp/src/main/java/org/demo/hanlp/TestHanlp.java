package org.demo.hanlp;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.seg.Dijkstra.DijkstraSegment;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author lzs
 * @Date 2023/2/21 10:35
 **/
public class TestHanlp {

    public static void main(String[] args) {
        String[] arr = {"2021年HanLPv2.1为生产环境带来次世代最先进的多语种NLP技术。",
                "阿婆主来到北京立方庭参观自然语义科技公司。",
                "其他公司生产农具和服务。",
                "社会经济发展形势一片良好。",
                "自然数的范围是1到N",
                "3日晚Brace在总统府发表声明，尊重现执政当局的权威",
                "我听到叮当叮当的声音，老张在打铁",
                "家具销售",
                "道路运输","家"};
        for (String text : arr) {
            List<Term> terms = HanLP.newSegment().enableIndexMode(1).seg(text);
            System.out.println(terms);
        }
    }

    private static final Segment N_SHORT_SEGMENT = new NShortSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
    private static final Segment DIJKSTRA_SEGMENT = new DijkstraSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);

    public Map<String, String> segMore(String text) {
        Map<String, String> map = new HashMap<>();
        map.put("标准分词", standard(text));
//        map.put("NLP分词", nlp(text));
        map.put("索引分词", index(text));
        map.put("N-最短路径分词", nShort(text));
        map.put("最短路径分词", shortest(text));
        map.put("极速词典分词", speed(text));
        return map;
    }

    private static String standard(String text) {
        StringBuilder result = new StringBuilder();
        StandardTokenizer.segment(text).forEach(term -> result.append(term.word).append(" "));
        return result.toString();
    }

    private static String nlp(String text) {
        StringBuilder result = new StringBuilder();
        NLPTokenizer.segment(text).forEach(term -> result.append(term.word).append(" "));
        return result.toString();
    }

    private static String index(String text) {
        StringBuilder result = new StringBuilder();
        IndexTokenizer.segment(text).forEach(term -> result.append(term.word).append(" "));
        return result.toString();
    }

    private static String speed(String text) {
        StringBuilder result = new StringBuilder();
        SpeedTokenizer.segment(text).forEach(term -> result.append(term.word).append(" "));
        return result.toString();
    }

    private static String nShort(String text) {
        StringBuilder result = new StringBuilder();
        N_SHORT_SEGMENT.seg(text).forEach(term -> result.append(term.word).append(" "));
        return result.toString();
    }

    private static String shortest(String text) {
        StringBuilder result = new StringBuilder();
        DIJKSTRA_SEGMENT.seg(text).forEach(term -> result.append(term.word).append(" "));
        return result.toString();
    }

}
