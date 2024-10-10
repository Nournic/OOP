package ru.ssau.tk.nour.oop.functions.basic;

import ru.ssau.tk.nour.oop.functions.Function;

public class Log implements Function {
    private double base;

    public Log(double base) {
        this.base = base;
    }

    public double getLeftDomainBorder() {
        return 0;
    }

    public double getRightDomainBorder() {
        return Double.POSITIVE_INFINITY;
    }

    public double getFunctionValue(double x) {
        if(x==1) return 0;
        return Math.log(x) / Math.log(base);
    }
}
