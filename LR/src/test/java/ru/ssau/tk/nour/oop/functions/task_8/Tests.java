package ru.ssau.tk.nour.oop.functions.task_8;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.nour.oop.functions.*;
import ru.ssau.tk.nour.oop.functions.basic.Cos;
import ru.ssau.tk.nour.oop.functions.basic.Exp;
import ru.ssau.tk.nour.oop.functions.basic.Log;
import ru.ssau.tk.nour.oop.functions.basic.Sin;

import java.io.*;

public class Tests {
    private final Cos cos = new Cos();
    private final Sin sin = new Sin();
    private final Exp exp = new Exp();
    double leftx = 0;
    double rightx = 2*Math.PI;

    @Test
    void testCos(){
        for(double step = leftx; step<=rightx; step+=0.1)
            System.out.println(cos.getFunctionValue(step));
    }
    @Test
    void testSin(){
        for(double step = leftx; step<=rightx; step+=0.1)
            System.out.println(sin.getFunctionValue(step));
    }

    @Test
    void tabulateSin(){
        TabulatedFunction tabulatedFunction = TabulatedFunctions.tabulate(sin, leftx, rightx,10);

        for(double step = leftx; step<=rightx; step+=0.1) {
            System.out.print(tabulatedFunction.getFunctionValue(step));
            System.out.println(" " + sin.getFunctionValue(step));
        }
    }

    @Test
    void tabulateCos(){
        TabulatedFunction tabulatedFunction = TabulatedFunctions.tabulate(cos, leftx, rightx,10);

        for(double step = leftx; step<=rightx; step+=0.1) {
            System.out.print(tabulatedFunction.getFunctionValue(step));
            System.out.println(" " + cos.getFunctionValue(step));
        }
    }

    @Test
    void tabulateSquareCos(){
        Function function = Functions.power(cos,2);
        TabulatedFunction tabulatedFunction = TabulatedFunctions.tabulate(function, leftx, rightx, 10);

        for(double step = leftx; step<=rightx; step+=0.1) {
            System.out.print(tabulatedFunction.getFunctionValue(step));
            System.out.println(" " + cos.getFunctionValue(step));
        }
    }

    @Test
    void tabulateExp(){
        TabulatedFunction tabulatedFunction = TabulatedFunctions.tabulate(exp, 0, 10, 11);
        File file = new File("./src/test/java/ru/ssau/tk/nour/oop/functions/task_8/Exp.txt");

        if(!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (Exception e) {}
        }

        try {
            FileWriter writer = new FileWriter(file);
            TabulatedFunctions.writeTabulatedFunction(tabulatedFunction,writer);
        } catch (IOException e) {
            System.out.println("Error output.");
        }

        TabulatedFunction tabulatedFunction1 = new ArrayTabulatedFunction(0,5,10);
        try {
            FileReader fileReader = new FileReader(file);
            tabulatedFunction1 = TabulatedFunctions.readTabulatedFunction(fileReader);
        }
        catch(IOException e){
            System.out.println("Error input.");
        }

        for(double step = 0; step<=10; step+=1) {
            System.out.print(tabulatedFunction.getFunctionValue(step));
            System.out.println(" " + tabulatedFunction1.getFunctionValue(step));
        }
    }

    @Test
    void tabulateLog(){
        TabulatedFunction tabulatedFunction = TabulatedFunctions.tabulate(new Log(Math.E), 0, 10, 11);
        File file = new File("./src/test/java/ru/ssau/tk/nour/oop/functions/task_8/Log.txt");

        if(!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (Exception e) {}
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            TabulatedFunctions.outputTabulatedFunction(tabulatedFunction, fileOutputStream);
        } catch (IOException e) {
            System.out.println("Error output.");
        }

        TabulatedFunction tabulatedFunction1 = new ArrayTabulatedFunction(0,5,10);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            tabulatedFunction1 = TabulatedFunctions.inputTabulatedFunction(fileInputStream);
        }
        catch(IOException e){
            System.out.println("Error input.");
        }

        for(double step = 0; step<=10; step+=1) {
            System.out.print(tabulatedFunction.getFunctionValue(step));
            System.out.println(" " + tabulatedFunction1.getFunctionValue(step));
        }
    }
}
