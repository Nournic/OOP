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
        DataOutputStream dataOutputStream = new DataOutputStream(out);

        for(int i = 0; i < length; i++){
            dataOutputStream.writeDouble(function.getPointX(i));
            dataOutputStream.writeDouble(function.getPointY(i));
        }
    }

    public static TabulatedFunction inputTabulatedFunction(InputStream in) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(in);
        List<FunctionPoint> list = new ArrayList<>();

        //Чтение данных в формате X Y и запись в список
        while(dataInputStream.available() != 0){
            double x = dataInputStream.readDouble();
            double y = dataInputStream.readDouble();

            list.add(new FunctionPoint(x,y));
        }

        //Перевод списка в массив
        FunctionPoint[] functionPoints = new FunctionPoint[list.size()];
        for (int i = 0; i < list.size(); i++)
            functionPoints[i] = list.get(i);

        return new ArrayTabulatedFunction(functionPoints);
    }

    public static void writeTabulatedFunction(TabulatedFunction function, Writer out) throws IOException{
        int length = function.getPointsCount();

        for(int i = 0; i < length; i++){
            out.write(((Double)function.getPointX(i)).toString());
            out.write(((Double)function.getPointY(i)).toString());
        }
    }

    public static TabulatedFunction readTabulatedFunction(Reader in) throws IOException{
        List<Double> num_list = new ArrayList<>();
        StreamTokenizer streamTokenizer = new StreamTokenizer(in);

        int token = streamTokenizer.nextToken();
        while (token != StreamTokenizer.TT_EOF) {
            token = streamTokenizer.nextToken();
            switch (token) {
                case StreamTokenizer.TT_NUMBER:
                    double num = streamTokenizer.nval;
                    num_list.add(num);
                    break;
                case StreamTokenizer.TT_EOF:
                    break;
            }
        }

        //Перевод списка в массив
        FunctionPoint[] functionPoints = new FunctionPoint[num_list.size()];
        for (int i = 0; i < functionPoints.length; i+=2)
            functionPoints[i] = new FunctionPoint(num_list.get(i),num_list.get(i+1));

        return new ArrayTabulatedFunction(functionPoints);
    }
}
