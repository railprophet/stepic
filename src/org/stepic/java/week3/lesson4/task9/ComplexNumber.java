package org.stepic.java.week3.lesson4.task9;

/**
 * Created by Dmitry on 18.09.2016.
 */
public final class ComplexNumber {
    private final double re;
    private final double im;

    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComplexNumber that = (ComplexNumber) o;

        if (Double.compare(that.getRe(), getRe()) != 0) return false;
        return Double.compare(that.getIm(), getIm()) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(getRe());
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getIm());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
