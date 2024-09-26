package ru.ssau.tk.nour.oop.functions;

import java.io.Serializable;

public class FunctionPoint implements Serializable {
    private double x,y;

    public FunctionPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public FunctionPoint(FunctionPoint point) {
        this.x = point.getX();
        this.y = point.getY();
    }

    public FunctionPoint() {
        this.x = 0;
        this.y = 0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public int hashCode() {
        long bitsX = Double.doubleToLongBits(x);
        long bitsY = Double.doubleToLongBits(y);

        int xLow = (int) bitsX;
        int xHigh = (int) (bitsX >>> 32);

        int yLow = (int) bitsY;
        int yHigh = (int) (bitsY >>> 32);

        return xLow ^ xHigh ^ yLow ^ yHigh;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FunctionPoint)) return false;

        FunctionPoint point = (FunctionPoint) obj;
        if(point.getX() != this.x || point.getY() != this.y)
            return false;

        return true;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new FunctionPoint(this);
    }

    @Override
    public String toString() {
        return String.format("(%f; %f)", x, y);
    }
}
