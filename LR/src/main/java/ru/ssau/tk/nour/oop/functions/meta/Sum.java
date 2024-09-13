package ru.ssau.tk.nour.oop.functions.meta;

import ru.ssau.tk.nour.oop.functions.Function;

public class Sum implements Function {
    private Function fun1;
    private Function fun2;
    private double left_border;
    private double right_border;


    public Sum(Function fun1, Function fun2) {
        this.fun1 = fun1;
        this.fun2 = fun2;
        left_border = Math.max(fun1.getLeftDomainBorder(),fun2.getLeftDomainBorder());
        right_border = Math.min(fun1.getRightDomainBorder(),fun2.getRightDomainBorder());
    }

    public double getLeftDomainBorder() {
        return left_border;
    }

    public double getRightDomainBorder() {
        return right_border;
    }

    public double getFunctionValue(double x) {
        return fun1.getFunctionValue(x)+fun2.getFunctionValue(x);
    }
}
