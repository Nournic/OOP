package ru.ssau.tk.nour.oop.functions.task_9;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.nour.oop.functions.*;
import ru.ssau.tk.nour.oop.functions.basic.Exp;
import ru.ssau.tk.nour.oop.functions.basic.Log;

import java.io.*;

public class SerializableTest {
    @Test
    void serializeTabulateLog() throws IOException, ClassNotFoundException {
        //Создание функции
        Function exp = new Exp();
        Function function = Functions.composition(new Log(Math.E), exp);
        TabulatedFunction tabulatedFunction = TabulatedFunctions.tabulate(function, 0, 10, 11);

        //Сериализация функции в файл
        FileOutputStream fileOutputStream = new FileOutputStream("./function.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(tabulatedFunction);
        objectOutputStream.close();

        //Десериализация функции из файла
        FileInputStream fileInputStream = new FileInputStream("./function.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        TabulatedFunction tabulatedFunction1 = (TabulatedFunction) objectInputStream.readObject();
        objectInputStream.close();

        //Вывод значений входной и выходной функции
        for(double step = 0; step<=10; step+=1) {
            System.out.print(tabulatedFunction.getFunctionValue(step));
            System.out.println(" " + tabulatedFunction1.getFunctionValue(step));
        }
    }
}
