package org.stepic.java.week5.lesson3.task13;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double sum=0;
        String str;
        while (scan.hasNextDouble()) {
            sum += scan.nextDouble();
        }
    }
}
