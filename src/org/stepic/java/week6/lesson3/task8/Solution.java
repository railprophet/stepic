package org.stepic.java.week6.lesson3.task8;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;


/**
 *
 * Дан предикат condition и две функции ifTrue и ifFalse.
 * Напишите метод ternaryOperator, который из них построит новую функцию,
 * возвращающую значение функции ifTrue, если предикат выполнен,
 * и значение ifFalse иначе.
 */
public class Solution {

    /**
     * Проверочный метод, не редактируйте его, если точно не знаете для чего это делаете
     *
     * @param args
     */
    public static void main(String[] args) {

        Predicate<Object> condition = Objects::isNull; // condition(..) { if ==null ... return null;}

        Function<Object, Integer> ifTrue = obj -> 0; // int ifTrue(obj) { return 0}

        Function<CharSequence, Integer> ifFalse = CharSequence::length; // int ifFalse (chSeq) { return length }

        Function<String, Integer> safeStringLength = ternaryOperator(condition, ifTrue, ifFalse);



    }

    public static <T, U> Function<T, U> ternaryOperator(
            Predicate<? super T> condition,
            Function<? super T, ? extends U> ifTrue,
            Function<? super T, ? extends U> ifFalse) {

        // Измените код в этом методе так, чтобы он работал правильно

        return t -> condition.test(t) ? ifTrue.apply(t) : ifFalse.apply(t);
        //return t -> ifTrue.apply(t);

    }
}
