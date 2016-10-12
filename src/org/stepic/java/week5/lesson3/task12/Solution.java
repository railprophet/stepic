package org.stepic.java.week5.lesson3.task12;
import java.io.*;
import java.nio.charset.Charset;

public class Solution {
    public static void main(String[] args) throws IOException{
        InputStreamReader reader = new InputStreamReader(System.in, Charset.forName("UTF-8"));
    }
    public static String readAsString(InputStream inputStream, Charset charset) throws IOException {
        // your implementation here
        ByteArrayOutputStream outArrayStream = new ByteArrayOutputStream();
        int ch;
        while ((ch = inputStream.read()) != -1 ){
            outArrayStream.write(ch);
        }
        return new String(outArrayStream.toByteArray(), charset);
    }
}
