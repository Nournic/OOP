package ru.ssau.tk.nour.oop;

import ru.ssau.tk.nour.oop.functions.*;

public class Main {
    public static void main(String[] args) {
        double[] values = {1, 2, 4, 8, 16, 32, 64};
        TabulatedFunction tabulatedFunction = new LinkedListTabulatedFunction(-10, 10, values);

        try {
            System.out.println("Вывод значений функции:");
            double step = 20.0 / (values.length - 1);
            System.out.println("Шаг: " + step);
            double current_step = -10.0 - step;
            for (; current_step <= 10 + 2 * step; current_step += step) {
                System.out.println(Math.round(current_step) + ": " + tabulatedFunction.getFunctionValue(current_step));
            }
            System.out.println();

            System.out.println("Проверка методов:");
            System.out.println("-9: " + tabulatedFunction.getFunctionValue(-9));
            System.out.println("-8.7: " + tabulatedFunction.getFunctionValue(-8.7));
            System.out.println(String.format("%d ",Math.round(-10.0+step)) + tabulatedFunction.getFunctionValue(-10 + step));

            FunctionPoint functionPoint = new FunctionPoint(-7, 0);
            tabulatedFunction.addPoint(functionPoint);
            System.out.println("-7: " + tabulatedFunction.getFunctionValue(-7));

            tabulatedFunction.setPointX(0, -12.5);
            System.out.println("-9: " + tabulatedFunction.getFunctionValue(-9));

            tabulatedFunction.deletePoint(0);
            System.out.println(String.format("%d ",Math.round(-10.0+step)) + tabulatedFunction.getFunctionValue(-10 + step));
            System.out.println("-10: " + tabulatedFunction.getFunctionValue(-10));

        } catch (InappropriateFunctionPointException e) {
            System.out.println("Точка не лежит в области определения");
        }
    }
}
