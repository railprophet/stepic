package org.stepic.java.week4.lesson1.task10;

/*
Реализуйте метод, позволяющий другим методам узнать, откуда их вызвали.

Метод getCallerClassAndMethodName() должен возвращать имя класса и метода,
откуда вызван метод, вызвавший данный утилитный метод. Или null (нулевую ссылку,
а не строку "null"), если метод, вызвавший getCallerClassAndMethodName()
является точкой входа в программу, т.е. его никто не вызывал.

Это актуально, например, в библиотеках логирования, где для каждого сообщения в
логе надо выводить класс и метод, откуда сообщение было залогировано.
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(getCallerClassAndMethodName());

        anotherMethod();

    }

    private static void anotherMethod() {
        System.out.println(getCallerClassAndMethodName());

    }

    public static String getCallerClassAndMethodName() {
        Throwable th = new Throwable();
        StackTraceElement[] stElems = th.getStackTrace();

        if (stElems.length < 3) return null;

        return stElems[2].getClassName() + "#" + stElems[2].getMethodName();
    }
}
