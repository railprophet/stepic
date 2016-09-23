package org.stepic.java.week3.lesson5.task8;

import java.lang.reflect.Array;
import java.util.Arrays;

/*
Напишите класс AsciiCharSequence, реализующий компактное хранение последовательности ASCII-символов
(их коды влезают в один байт) в массиве байт. По сравнению с классом String, хранящим каждый символ
как char, AsciiCharSequence будет занимать в два раза меньше памяти.

Класс AsciiCharSequence должен:

- реализовывать интерфейс java.lang.CharSequence;
- иметь конструктор, принимающий массив байт;
- определять методы length(), charAt(), subSequence() и toString()
- Сигнатуры методов и ожидания по их поведению смотрите в описании интерфейса
  java.lang.CharSequence (JavaDoc или исходники).

В данном задании методам charAt() и subSequence() всегда будут подаваться корректные входные параметры,
 поэтому их проверкой и обработкой ошибок заниматься не нужно. Тем более мы еще не проходили исключения.
 */
public class AsciiCharSequence implements java.lang.CharSequence {
    public static void main(String[] args) {
        //for tests
        AsciiCharSequence seq = new AsciiCharSequence();
        seq = new AsciiCharSequence(new byte[]{'H','e','l','l','o',',',' ','S','t','e','p','i','c','!'});
        System.out.println("test toString():\t"   +seq);
        System.out.println("test charAt(2):\t\t"  +seq.charAt(2));
        System.out.println("test length()\t\t"    +seq.length());
        System.out.println("subSequence(0,14)\t"+seq.subSequence(0,14));

    }

    private byte[] bytes;

    AsciiCharSequence(byte[] bytes) {
        this.bytes = new byte[bytes.length];
        for (int i=0;i<bytes.length;i++){
           this.bytes[i] = bytes[i];
        }
    }
    AsciiCharSequence(){
        this(new byte[0]);
    }

    @Override
    public int length(){
        return this.bytes.length;
    }

    @Override
    public char charAt(int iter) throws IndexOutOfBoundsException{
        if (iter < this.bytes.length)
            return (char)(this.bytes[iter]);
        else
            throw (new IndexOutOfBoundsException());
    }

    @Override
    public CharSequence subSequence(int start, int end) throws IndexOutOfBoundsException {
        if(start < 0)
            throw (new IndexOutOfBoundsException());
        if(end > this.length())
            throw (new IndexOutOfBoundsException());

        return new AsciiCharSequence(Arrays.copyOfRange(this.bytes , start, end));
    }

    @Override
    public String toString(){
        return new String(bytes);
    }
}
