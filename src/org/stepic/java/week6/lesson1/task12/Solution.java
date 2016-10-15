package org.stepic.java.week6.lesson1.task12;

import java.util.NoSuchElementException;
import java.util.Objects;

/*

Реализуйте generic-класс Pair, похожий на Optional, но содержащий пару элементов разных типов
и не запрещающий элементам принимать значение null.

Реализуйте методы getFirst(), getSecond(), equals() и hashCode(), а также статический фабричный
метод of(). Конструктор должен быть закрытым (private)

 */
class Pair<T,K> {
    private Pair(T t, K k){
        this.t = t;
        this.k = k;
    }

    public static <T,K> Pair<T,K> of(T t, K k){
        return new Pair<T,K>(t,k);
    }

    public T getFirst(){
        return this.t;
    }
    public K getSecond(){
        return this.k;
    }
    private T t;
    private K k;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair<?, ?> pair = (Pair<?, ?>) o;

        if (t != null ? !t.equals(pair.t) : pair.t != null) return false;
        return k != null ? k.equals(pair.k) : pair.k == null;

    }

    @Override
    public int hashCode() {
        int result = t != null ? t.hashCode() : 0;
        result = 31 * result + (k != null ? k.hashCode() : 0);
        return result;
    }
}



public class Solution {
    public static void main(String[] args) {
        Pair<Integer, String> pair = Pair.of(1, "hello");
        Integer i = pair.getFirst(); // 1
        System.out.println(i);
        String s = pair.getSecond(); // "hello"
        System.out.println(s);

        Pair<Integer, String> pair2 = Pair.of(1, "hello");
        boolean mustBeTrue = pair.equals(pair2); // true!
        System.out.println(mustBeTrue);
        boolean mustAlsoBeTrue = pair.hashCode() == pair2.hashCode(); // true!
        System.out.println(mustAlsoBeTrue);

        Pair<Integer, String> n = Pair.of(null, "hello");
        s.equals(null);

    }
}
