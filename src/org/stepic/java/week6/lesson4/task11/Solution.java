package org.stepic.java.week6.lesson4.task11;

import java.util.stream.IntStream;

/*
Напишите метод, возвращающий стрим псевдослучайных целых чисел. Алгоритм генерации чисел следующий:

    Берется какое-то начальное неотрицательное число (оно будет передаваться в ваш метод проверяющей системой).
    Первый элемент последовательности устанавливается равным этому числу.
    Следующие элементы вычисляются по рекуррентной формуле Rn+1=mid(R2n)

, где mid — это функция, выделяющая второй, третий и четвертый разряд переданного числа. Например, mid(123456)=345.
 */
public class Solution {
    public static void main(String[] args) {
        pseudoRandomStream(13).peek(System.out::println).limit(10).toArray();
    }
    public static IntStream pseudoRandomStream(int seed) {
        //return IntStream.iterate(seed, R -> mid(R * R));
        return IntStream.iterate(seed, R -> (R*R %10000/10));
    }
    public static int mid(int i){
        return (i % 10000)/10;
    }
}
//Stream<Integer> streamFromIterate = Stream.iterate(1, n -> n + 1)