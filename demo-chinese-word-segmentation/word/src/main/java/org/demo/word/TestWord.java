package org.demo.word;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;

import java.util.List;

/**
 * @Author lzs
 * @Date 2023/2/23 8:31
 **/
public class TestWord {

    public static void main(String[] args) {
        //移除停用词：
        List<Word> words = WordSegmenter.seg("杨尚川是APDPlat应用级产品开发平台的作者");
        System.out.println(words);
        //保留停用词：
        List<Word> words2 = WordSegmenter.segWithStopWords("杨尚川是APDPlat应用级产品开发平台的作者");
        System.out.println(words2);
    }

}
