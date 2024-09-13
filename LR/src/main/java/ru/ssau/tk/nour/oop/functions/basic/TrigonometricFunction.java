package ru.ssau.tk.nour.oop.functions.basic;

import ru.ssau.tk.nour.oop.functions.Function;

public abstract class TrigonometricFunction implements Function {
    public double getLeftDomainBorder() {
        return Double.NEGATIVE_INFINITY;
    }

    public double getRightDomainBorder(){
        return Double.POSITIVE_INFINITY;
    }

    public abstract double getFunctionValue(double x);
}
