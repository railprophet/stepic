package org.stepic.java.week6.lesson4.task12;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
Напишите метод, находящий в стриме минимальный и максимальный элементы в соответствии порядком,
заданным Comparator'ом.

Найденные минимальный и максимальный элементы передайте в minMaxConsumer следующим образом:

minMaxConsumer.accept(min, max);

Если стрим не содержит элементов, то вызовите

minMaxConsumer.accept(null, null);
 */
public class Solution {
    public static void main(String[] args) {
        //findMinMax(new ArrayList<Integer>().stream(), (o1, o2) -> o1 - o2, (o1, o2) -> {
        //    assert o1 == null && o2 == null : "Null expected";
        //});


        Stream<Integer> intStream = Stream.iterate(1, n->n+1).limit(50);

        Comparator<Integer> comparator = Integer::compare;

        BiConsumer<Integer,Integer> consumer= (n1,n2) -> {
            System.out.println("min: " + n1);
            System.out.println("max: " + n2);
        };

        findMinMax(intStream, comparator, consumer);
    }

    public static <T> void findMinMax(
            Stream<? extends T> stream,
            Comparator<? super T> order,
            BiConsumer<? super T, ? super T> minMaxConsumer) {


            List<T> list = stream.collect(Collectors.toList());

            if (list.size() == 0){
                minMaxConsumer.accept(null, null);
            } else {
                T min = list.stream().min(order).get();
                T max = list.stream().max(order).get();
                minMaxConsumer.accept(min, max);
            }



    }
}
