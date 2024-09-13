package ru.ssau.tk.nour.oop.functions;

import java.io.*;

public abstract class TabulatedFunctions {
    public static TabulatedFunction tabulate(Function function, double leftX, double rightX, int pointsCount) throws IllegalArgumentException{
        if(leftX<function.getLeftDomainBorder() || rightX>function.getRightDomainBorder())
            throw new IllegalArgumentException();

        TabulatedFunction tabulatedFunction;
        FunctionPoint[] functionPoints = new FunctionPoint[pointsCount];
        double step = (rightX - leftX)/(pointsCount-1);

        int i; double current_step;
        for(i = 0, current_step=leftX; i < pointsCount; i++) {
            functionPoints[i].setX(current_step);
            functionPoints[i].setY(function.getFunctionValue(current_step));
            current_step+=step;
        }

        if(pointsCount<100)
            tabulatedFunction = new LinkedListTabulatedFunction(functionPoints);
        else
            tabulatedFunction = new ArrayTabulatedFunction(functionPoints);

        return tabulatedFunction;
    }
}
