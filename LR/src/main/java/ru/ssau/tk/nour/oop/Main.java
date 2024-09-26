package ru.ssau.tk.nour.oop;

import ru.ssau.tk.nour.oop.functions.FunctionPoint;
import ru.ssau.tk.nour.oop.functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {
        double[] values = {1, 2, 4, 8, 16, 32, 64};
        TabulatedFunction tabulatedFunction = new TabulatedFunction(-10, 10, values);

        double step = 20.0 / (values.length - 1);
        double current_step = -10.0 - step;
        for (; current_step <= 10 + 2 * step; current_step += step) {
            System.out.println(tabulatedFunction.getFunctionValue(current_step));
        }
        System.out.println(tabulatedFunction.getFunctionValue(-9));
        System.out.println(tabulatedFunction.getFunctionValue(-8.7));
        System.out.println(tabulatedFunction.getFunctionValue(-10 + step));
        FunctionPoint functionPoint = new FunctionPoint(-7, 0);
        tabulatedFunction.addPoint(functionPoint);
        System.out.println(tabulatedFunction.getFunctionValue(-7));
        tabulatedFunction.setPointX(0, -12.5);
        System.out.println(tabulatedFunction.getFunctionValue(-9));
        tabulatedFunction.deletePoint(0);
        System.out.println(tabulatedFunction.getFunctionValue(-10 + step));
        System.out.println(tabulatedFunction.getFunctionValue(-10));
    }
}
