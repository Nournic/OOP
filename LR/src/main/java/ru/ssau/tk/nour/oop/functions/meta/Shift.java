package ru.ssau.tk.nour.oop.functions.meta;

import ru.ssau.tk.nour.oop.functions.Function;

public class Shift implements Function {
    private Function function;
    private double shift_Y;
    private double shift_X;
    private double left_border;
    private double right_border;

    public Shift(Function function, double shift_X, double shift_Y) {
        this.function = function;
        this.left_border = function.getLeftDomainBorder()+shift_X;
        this.right_border = function.getRightDomainBorder()+shift_X;
        this.shift_Y = shift_Y;
        this.shift_X = shift_X;
    }

    public double getLeftDomainBorder() {
        return left_border;
    }

    public double getRightDomainBorder() {
        return right_border;
    }

    public double getFunctionValue(double x) {
        return function.getFunctionValue(x - shift_X) + shift_Y;
    }
}
