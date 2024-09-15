package ru.ssau.tk.nour.oop.functions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

    public static void outputTabulatedFunction(TabulatedFunction function, OutputStream out) throws IOException {
        int length = function.getPointsCount();

        for(int i = 0; i < length; i++){
            out.write(((Double)function.getPoint(i).getX()).byteValue());
        }
    }

    public static TabulatedFunction inputTabulatedFunction(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(in);
        List<FunctionPoint> list = new ArrayList<>();
        while(objectInputStream.available() != 0){
            list.add((FunctionPoint) objectInputStream.readObject());
        }
        FunctionPoint[] functionPoints = new FunctionPoint[list.size()];
        for (int i = 0; i < functionPoints.length; i++) {
            functionPoints[i] = list.get(i);
        }

        return new ArrayTabulatedFunction(functionPoints);
    }
}
