package org.stepic.java.week5.lesson2.task8;
import java.io.*;
/*
Напишите метод, читающий входной поток и вычисляющий контрольную сумму прочитанных данных.

Контрольная сумма данных вычисляется по следующему алгоритму:

    Контрольная сумма представляет собой число типа int. Контрольная сумма пустого набора данных равна нулю.
    Контрольная сумма непустого набора данных вычисляется по следующей рекуррентной формуле:
     $C_{n+1}=\mathrm{rotateLeft}(C_n)\ \mathrm{xor}\ b_{n+1}$ ,
     где $C_n$ — контрольная сумма первых n байт данных, rotateLeft — циклический сдвиг бит числа на один
     бит влево (чтобы не изобретать велосипед, используйте Integer.rotateLeft), $b_n$ — n-ный байт данных.

Поскольку метод не открывал данный InputStream, то и закрывать его он не должен. Выброшенное из методов
InputStream исключение должно выбрасываться из метода.
 */
public class Solution {
    public static void main(String[] args) {

        int res;
        byte[] data = new byte[] {0x33, 0x45, 0x01};
        InputStream stream = new ByteArrayInputStream(data);
        try {
            System.out.println(checkSumOfStream(stream));
        } catch (IOException e) {

        }
    }
    public static int checkSumOfStream(InputStream inputStream) throws IOException {
        // your implementation here
        int sum = 0;
        int ch;
        while ( (ch = inputStream.read()) != -1 ) {

            sum = (Integer.rotateLeft(sum ,1)) ^ ch;

        }
        return sum;

    }
}
