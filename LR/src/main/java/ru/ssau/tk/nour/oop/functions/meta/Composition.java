package ru.ssau.tk.nour.oop.functions.meta;

import ru.ssau.tk.nour.oop.functions.Function;

public class Composition implements Function {
    private Function function1;
    private Function function2;

    public Composition(Function function1, Function function2) {
        this.function1 = function1;
        this.function2 = function2;
    }

    public double getLeftDomainBorder() {
        return function1.getLeftDomainBorder();
    }

    public double getRightDomainBorder() {
        return function1.getRightDomainBorder();
    }

    public double getFunctionValue(double x) {
        return function1.getFunctionValue(function2.getFunctionValue(x));
    }
}
