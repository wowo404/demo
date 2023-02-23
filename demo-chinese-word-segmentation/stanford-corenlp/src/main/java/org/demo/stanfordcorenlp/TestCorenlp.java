package org.demo.stanfordcorenlp;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.List;
import java.util.Properties;

/**
 * @Author lzs
 * @Date 2023/2/22 18:24
 **/
public class TestCorenlp {

    public static void main(String[] args) {

    }

    private static void runPipeline() {
        String text = "Marie was born in Paris.";
        // set up pipeline properties
        Properties props = new Properties();
        // set the list of annotators to run
        props.setProperty("annotators", "tokenize,pos,lemma,ner,depparse");
        // build pipeline
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        // create a document object
        CoreDocument document = pipeline.processToCoreDocument(text);
        System.out.println(document);
    }

    private static void test() {
        String text = "要是有这样的姐姐操心就不会这么烦了";

        System.out.println(seg(text));
    }

    private static String seg(String text) {
        StanfordCoreNLP stanfordCoreNLP = new StanfordCoreNLP();
        Annotation document = new Annotation(text);
        stanfordCoreNLP.annotate(document);
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        StringBuilder result = new StringBuilder();
        for (CoreMap sentence : sentences) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                result.append(word).append(" ");
            }
        }
        return result.toString();
    }
}
