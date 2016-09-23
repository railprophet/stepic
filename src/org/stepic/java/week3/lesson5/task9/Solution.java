package org.stepic.java.week3.lesson5.task9;
import java.util.regex.*;
public class Solution {
    public static Label checkLabels(TextAnalyzer[] analyzers, String text) {

        for (TextAnalyzer analyzer: analyzers){
            Label label = analyzer.processText(text);
            if (label != Label.OK)
                return label;
        }
        return Label.OK;
    }
    public static void main(String[] args) {
        String text = "Допустим, мы будем фильтровать спам, комментарии с негативным содержанием и слишком длинные комментарии.\n" +
                "Спам будем фильтровать по наличию указанных ключевых слов в тексте. \n" +
                "Негативное содержание будем определять по наличию одного из трех смайликов – :( =( :|\n" +
                "Слишком длинные комментарии будем определять исходя из данного числа – максимальной длины комментария";

        TextAnalyzer ta = new TooLongTextAnalyzer(12000);

        System.out.println("label: "+ta.processText(text));

        ta = new SpamAnalyzer(new String[]{"Спам1","б1удем"});
        System.out.println("label: "+ta.processText(text));

        ta = new NegativeTextAnalyzer();
        System.out.println("label: "+ta.processText(text));

        System.out.println(
                checkLabels(new TextAnalyzer[]{
                        new TooLongTextAnalyzer(12),
                        new SpamAnalyzer(new String[]{"Спам","будем"}),
                        new NegativeTextAnalyzer()},
                        text)
        );
    }



}


interface TextAnalyzer {
    Label processText(String text);
}

enum Label {
    SPAM, NEGATIVE_TEXT, TOO_LONG, OK
}

class TooLongTextAnalyzer implements TextAnalyzer {
    private int maxSize;
    TooLongTextAnalyzer(int maxSize){
        this.maxSize = maxSize;
    }

    public Label processText(String text) {
        if (text.length() > maxSize)
            return Label.TOO_LONG;
        else
            return Label.OK;
    }
}

abstract class KeywordAnalyzer implements TextAnalyzer {
    protected Label label;
    protected String[] keyWords;
    protected String[] getKeywords(){
        return this.keyWords;
    }
    protected Label getLabel() {return this.label;}
    public Label processText(String text){
        for (String keyWord : getKeywords()){
            if (text.contains(keyWord))
                return getLabel();
        }
        return Label.OK;
    }
}

class SpamAnalyzer extends KeywordAnalyzer {

    SpamAnalyzer(String[] keywords) {
        this.keyWords = keywords;
        this.label = Label.SPAM;
    }
}

class NegativeTextAnalyzer extends KeywordAnalyzer {

    NegativeTextAnalyzer(){
        this.keyWords = new String[] { ":|",":(","=("};
        this.label = Label.NEGATIVE_TEXT;
    }
}