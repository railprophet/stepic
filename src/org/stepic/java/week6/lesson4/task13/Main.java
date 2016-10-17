package org.stepic.java.week6.lesson4.task13;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Напишите программу, читающую из System.in текст в кодировке UTF-8, подсчитывающую в нем частоту появления слов,
и в конце выводящую 10 наиболее часто встречающихся слов.

Словом будем считать любую непрерывную последовательность символов, состоящую только из букв и цифр. Например,
в строке "Мама мыла раму 33 раза!" ровно пять слов: "Мама", "мыла", "раму", "33" и "раза".

Подсчет слов должен выполняться без учета регистра, т.е. "МАМА", "мама" и "Мама" — это одно и то же слово.
Выводите слова в нижнем регистре.

Если в тексте меньше 10 уникальных слов, то выводите сколько есть.

Если в тексте некоторые слова имеют одинаковую частоту, т.е. их нельзя однозначно упорядочить только по частоте,
то дополнительно упорядочите слова с одинаковой частотой в лексикографическом порядке.

Задача имеет красивое решение через стримы без циклов и условных операторов. Попробуйте придумать его.
 */
public class Main {
    public static void main(String[] args) {

        //debug
        String inst = "ПLorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales consectetur purus at " +
                "faucibus. Donec mi quam, tempor vel ipsum non, faucibus suscipit massa. Morbi lacinia velit blandit " +
                "tincidunt efficitur. Vestibulum eget metus imperdiet sapien laoreet faucibus. Nunc eget vehicula " +
                "mauris, ac auctor lorem. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer vel odio " +
                "nec mi tempor dignissim.";
        InputStream instream = new ByteArrayInputStream(inst.getBytes(StandardCharsets.UTF_8));

        // \debud

        //читаем ввод в стрим
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = new BufferedReader(new InputStreamReader(instream));
        Stream<String> stream = in.lines();

        //freq сопоставляет слово с его частотой
        TreeMap<String, Integer> freq = new TreeMap<String, Integer>(String::compareToIgnoreCase);

        stream.forEach( line -> {
            String[] words = line.split(" ");
            for (String word : words) {
                int count = freq.containsKey(word) ? freq.get(word) : 0;
                freq.put(word, count+1);
            }

        });

        //TreeMap<String, Integer> filtered = freq.
        stream.collect(Collectors.toList())
//---------
        freq.forEach((k,v) -> {
            System.out.println(k+" : "+v);
        });




    }

}
