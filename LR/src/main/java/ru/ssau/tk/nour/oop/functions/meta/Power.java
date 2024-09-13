package ru.ssau.tk.nour.oop.functions.meta;

import ru.ssau.tk.nour.oop.functions.Function;

public class Power implements Function {
    private Function function;
    private double pow;

    public Power(Function function, double pow) {
        this.function = function;
        this.pow = pow;
    }

    public double getLeftDomainBorder() {
        return function.getLeftDomainBorder();
    }

    public double getRightDomainBorder() {
        return function.getRightDomainBorder();
    }

    public double getFunctionValue(double x) {
        return Math.pow(function.getFunctionValue(x), pow);
    }
}
