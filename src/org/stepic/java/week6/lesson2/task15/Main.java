package org.stepic.java.week6.lesson2.task15;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;
import java.util.stream.*;

/*
Напишите программу, которая прочитает из System.in последовательность целых чисел, разделенных пробелами,
затем удалит из них все числа, стоящие на четных позициях, и затем выведет получившуюся последовательность
в обратном порядке в System.out.

Все числа влезают в int. Позиции чисел в последовательности нумеруются с нуля.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        LinkedList<Integer> seq = new LinkedList<>();


        while (scan.hasNextInt()){
            seq.add(scan.nextInt());
        }

        Iterator<Integer> iter = seq.descendingIterator();

        int i = seq.size();

        while (i>0){
            if (i % 2 == 0){
                System.out.print(iter.next()+ " ");
            } else {
                iter.next();
            }
            i--;
        }

        System.out.println();
    }
}
