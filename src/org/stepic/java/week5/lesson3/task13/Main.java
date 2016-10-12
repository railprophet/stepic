package org.stepic.java.week5.lesson3.task13;

/*
Напишите программу, читающую текст из System.in и выводящую в System.out сумму всех встреченных в тексте вещественных
чисел с точностью до шестого знака после запятой. Числом считается последовательность символов, отделенная от
окружающего текста пробелами или переводами строк и успешно разбираемая методом Double.parseDouble.

 */

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        double sum=0;

        while (scan.hasNext()) {
            try {
                sum += Double.parseDouble(scan.next());
            } catch (java.lang.NumberFormatException e){}
            //System.out.println(sum);
        }
        System.out.format("%.6f", sum);

    }
}
