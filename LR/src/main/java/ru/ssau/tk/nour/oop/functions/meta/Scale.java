package ru.ssau.tk.nour.oop.functions.meta;

import ru.ssau.tk.nour.oop.functions.Function;

public class Scale implements Function {
    private Function function;
    private double scale_Y;
    private double left_border;
    private double right_border;

    public Scale(Function function, double scale_X, double scale_Y) {
        this.function = function;
        this.scale_Y = scale_Y;
        left_border = Math.min(function.getLeftDomainBorder()*scale_X,function.getRightDomainBorder()*scale_X);
        right_border = Math.max(function.getLeftDomainBorder()*scale_X,function.getRightDomainBorder()*scale_X);
    }

    public double getLeftDomainBorder() {
        return left_border;
    }

    public double getRightDomainBorder() {
        return right_border;
    }

    public double getFunctionValue(double x) {
        return function.getFunctionValue(x)*scale_Y;
    }
}
