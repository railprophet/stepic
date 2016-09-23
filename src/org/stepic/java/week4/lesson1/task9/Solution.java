package org.stepic.java.week4.lesson1.task9;
import java.lang.Math;
/*
Реализуйте метод sqrt(), вычисляющий квадратный корень числа. В отличие от Math.sqrt(),
это метод при передаче отрицательного параметра должен бросать исключение
java.lang.IllegalArgumentException с сообщением "Expected non-negative number, got ?",
 где вместо вопросика будет подставлено фактически переданное в метод число.
 */
public class Solution {
    public static void main(String[] args) {
        try{
            System.out.println("Sqrt(15): "+sqrt(15));
            System.out.println("Sqrt(-1): "+sqrt(-1));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    public static double sqrt(double x) throws java.lang.IllegalArgumentException {

        if (x <0) throw new IllegalArgumentException("Expected non-negative number, got "+x);
        //System.out.println("in sqrt()");
        return Math.sqrt(x);
    }
}
