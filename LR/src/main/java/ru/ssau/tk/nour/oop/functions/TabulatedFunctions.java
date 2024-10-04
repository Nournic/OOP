package ru.ssau.tk.nour.oop.functions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class TabulatedFunctions {
    private static TabulatedFunctionFactory factory = new ArrayTabulatedFunction.ArrayTabulatedFunctionFactory();

    public static TabulatedFunction tabulate(Function function, double leftX, double rightX, int pointsCount) throws IllegalArgumentException{
        if(leftX<function.getLeftDomainBorder() || rightX>function.getRightDomainBorder())
            throw new IllegalArgumentException();

        TabulatedFunction tabulatedFunction;
        FunctionPoint[] functionPoints = new FunctionPoint[pointsCount];
        double step = (rightX - leftX)/(pointsCount-1);

        int i; double current_step;
        for(i = 0, current_step=leftX; i < pointsCount; i++) {
            functionPoints[i] = new FunctionPoint(current_step, function.getFunctionValue(current_step));
            current_step+=step;
        }

        return createTabulatedFunction(functionPoints);
    }

    public static void setTabulatedFunctionFactory(TabulatedFunctionFactory factory){
        TabulatedFunctions.factory = factory;
    }

    public static TabulatedFunction createTabulatedFunction(double leftX, double rightX, int pointsCount) throws IllegalArgumentException {
        return factory.createTabulatedFunction(leftX, rightX, pointsCount);
    }

    public static TabulatedFunction createTabulatedFunction(double leftX, double rightX, double[] values) throws IllegalArgumentException {
        return factory.createTabulatedFunction(leftX, rightX, values);
    }

    public static TabulatedFunction createTabulatedFunction(FunctionPoint[] points) throws IllegalArgumentException {
        return factory.createTabulatedFunction(points);
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

        return createTabulatedFunction(functionPoints);
    }

    public static void writeTabulatedFunction(TabulatedFunction function, Writer out) throws IOException{
        int length = function.getPointsCount();

        for(int i = 0; i < length; i++){
            String x = ((Double)function.getPointX(i)).toString();
            String y = ((Double)function.getPointY(i)).toString();
            out.write(x + " ");
            out.write(y + " ");
        }
        out.flush();
    }

    public static TabulatedFunction readTabulatedFunction(Reader in) throws IOException{
        List<Double> num_list = new ArrayList<>();
        StreamTokenizer streamTokenizer = new StreamTokenizer(in);

        streamTokenizer.parseNumbers();
        int token = streamTokenizer.nextToken();
        while (token != StreamTokenizer.TT_EOF) {
            switch (token) {
                case StreamTokenizer.TT_NUMBER:
                    double num = streamTokenizer.nval;
                    num_list.add(num);
                    token = streamTokenizer.nextToken();
                    if(token != streamTokenizer.TT_NUMBER)
                        throw new IOException();
                    num = streamTokenizer.nval;
                    num_list.add(num);
                    break;
                case StreamTokenizer.TT_EOF:
                    break;
            }
            token = streamTokenizer.nextToken();
        }

        //Перевод списка в массив
        FunctionPoint[] functionPoints = new FunctionPoint[num_list.size()/2];
        int j = 0;
        for (int i = 0; i < functionPoints.length; i++)
            functionPoints[i] = new FunctionPoint(num_list.get(j++),num_list.get(j++));

        return createTabulatedFunction(functionPoints);
    }
}
