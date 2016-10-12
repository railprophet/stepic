package org.stepic.java.week5.lesson2.task9;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

/*
Напишите программу, которая будет преобразовывать переводы строк из формата Windows в формат Unix.
Данные в формате Windows подаются программе в System.in, преобразованные данные должны выводиться в
System.out. На этот раз вам надо написать программу полностью, т.е. объявить класс (с именем Main —
таково ограничение проверяющей системы), метод main, прописать все import'ы.
 */
public class Main {
    public static void main(String[] args) {
        int ch, bufferedCh;
        try (
                InputStream inStream = new ByteArrayInputStream(new byte[]{99, 13, 13, 10, 13,99});
                //InputStream inStream = System.in;
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                //OutputStream outStream = System.out;

            ){
            bufferedCh = inStream.read();
            if (bufferedCh == -1) {
                System.exit(0); //пустой поток
            }
            while ( (ch = inStream.read()) != -1 ){
                if ( (bufferedCh == 13) && (ch == 10)) {// \r \n
                    bufferedCh = ch;
                    continue;
                }
                outStream.write(bufferedCh);
                bufferedCh = ch;
            }

            outStream.write(bufferedCh);

            outStream.flush();
            byte[] res = outStream.toByteArray();
            System.out.println(Arrays.toString(res));
        } catch (IOException ioe){ /* на самом деле не ловим */ }
    }
}
