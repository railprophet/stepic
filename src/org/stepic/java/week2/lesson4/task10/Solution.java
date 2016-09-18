package org.stepic.java.week2.lesson4.task10;
import java.util.regex.*;

/**
 * Created by Dmitry on 17.09.2016.
 */
public class Solution {


    public static void main(String[] args) {

        String[] roles = {"Городничий",
                        "Аммос Федорович",
                        "Артемий Филиппович",
                        "Лука Лукич", "sda"};
        String[] textLines =  {"Городничий: Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор.",
                            "Аммос Федорович: Как ревизор?",
                            "Артемий Филиппович: Как ревизор?",
                            "Городничий: Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем.",
                            "Аммос Федорович: Вот те на!",
                            "Артемий Филиппович: Вот не было заботы, так подай!",
                            "Лука Лукич: Господи боже! еще и с секретным предписаньем!"};

        System.out.println(printTextPerRole2(roles, textLines));
    }

    public static final String printTextPerRole(String[] roles, String[] textLines){
        StringBuilder result = new StringBuilder("");
        for (String role : roles){
            result.append(role).append(":\n");
            Pattern pattern = Pattern.compile("^(" + role + ": )");

            for (int i =0;i < textLines.length; i++){

                Matcher matcher = pattern.matcher(textLines[i]);

                if (matcher.find()) {
                    result.append(i).append(") ").append(textLines[i].substring(matcher.end())).append("\n");

                }
            }
            result.append("\n");
        }
        return result.toString();
    }

    public static final String printTextPerRole2(String[] roles, String[] textLines){
        StringBuilder result = new StringBuilder("");
        for (String role : roles){
            result.append(role).append(":\n");
            for (int i =0; i < textLines.length; i++){


                if (role.length()+1<=textLines[i].length() && (role+":").equals(textLines[i].substring(0,role.length()+1))){
                    result.append(i+1).append(")").append(textLines[i].substring(role.length()+1)).append("\n");
                }
            }
            result.append("\n");
        }
        return result.toString();
    }
}
