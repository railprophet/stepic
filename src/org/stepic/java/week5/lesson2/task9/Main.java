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

\r == 13
\n == 10

 */
public class Main {
    public static void main(String[] args) {
        int ch, buffer;
        try (
                //InputStream inStream = new ByteArrayInputStream(new byte[]{13,10,99,13,10});
                InputStream inStream = System.in;
                //ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                OutputStream outStream = System.out;

            ){

            buffer = inStream.read();

            if (buffer == -1) {
                System.exit(0); //пустой поток
            }
            while ( (ch = inStream.read()) != -1 ){
                if ( (buffer == 13) && (ch == 10)) {// \r \n
                    buffer = ch;
                    continue;
                }
                outStream.write(buffer);
                buffer = ch;
            }

            outStream.write(buffer);

            outStream.flush();
            //byte[] res = outStream.toByteArray();
            //System.out.println(Arrays.toString(res));
        } catch (IOException ioe){ /* на самом деле не ловим */ }
    }
}
