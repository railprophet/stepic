package org.stepic.java.week6.lesson2.task14;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*

Реализуйте метод, вычисляющий симметрическую разность двух множеств.
 */
public class Solution {
    public static void main(String[] args) {

        HashSet<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set1.add(4);
        set1.add(5);
        set1.add(6);

        HashSet<Integer> set2 = new HashSet<>();
        set2.add(4);
        set2.add(5);
        set2.add(6);
        set2.add(7);
        set2.add(8);
        set2.add(9);



        Set<Integer> res = symmetricDifference(set1,set2);

        res.forEach(System.out::println);

    }

    public static <T> Set<T> symmetricDifference(Set<? extends T> set1, Set<? extends T> set2) {

        Set<T> result = new HashSet<>();

        Iterator<? extends T> iter1 = set1.iterator();

        while (iter1.hasNext()){
            T elem = iter1.next();
            if (!set2.contains(elem)) {
                result.add(elem);
            }
        }

        Iterator<? extends T> iter2 = set2.iterator();

        while (iter2.hasNext()){
            T elem = iter2.next();
            if (!set1.contains(elem)) {
                result.add(elem);
            }
        }
        return result;
    }
}
