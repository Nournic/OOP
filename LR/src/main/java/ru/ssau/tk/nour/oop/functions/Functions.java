package ru.ssau.tk.nour.oop.functions;

import ru.ssau.tk.nour.oop.functions.meta.*;

public abstract class Functions {
    public static Function shift(Function f, double shiftX, double shiftY){
        return new Shift(f,shiftX,shiftY);
    }
	public static Function scale(Function f, double scaleX, double scaleY) {
        return new Scale(f,scaleX,scaleY);
    }
	public static Function power(Function f, double power) {
        return new Power(f,power);
    }
	public static Function sum(Function f1, Function f2){
        return new Sum(f1,f2);
    }
	public static Function mult(Function f1, Function f2){
        return new Mult(f1,f2);
    }
	public static Function composition(Function f1, Function f2){
        return new Composition(f1,f2);
    }

    public static double integrate(Function function, double leftBorder, double rightBorder, double step) throws IllegalArgumentException{
        if(function.getLeftDomainBorder() > leftBorder || function.getRightDomainBorder() < rightBorder)
            throw new IllegalArgumentException();

        double result=0;
        int n = (int)((rightBorder-leftBorder)/step);
        result += (function.getFunctionValue(leftBorder)+function.getFunctionValue(rightBorder))/2;
        for(int i = 1; i < n; i++)
            result += function.getFunctionValue(leftBorder + step * i);
        return step*result;
    }
}
