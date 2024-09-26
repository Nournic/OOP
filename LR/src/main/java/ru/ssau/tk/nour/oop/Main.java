package ru.ssau.tk.nour.oop;

import ru.ssau.tk.nour.oop.functions.Function;
import ru.ssau.tk.nour.oop.functions.basic.Log;
import ru.ssau.tk.nour.oop.functions.threads.Task;


public class Main {
    public static void main(String[] args) {

    }

    void nonThread(){
        Task task = new Task();
        task.setCountTasks(100);
        Function function = new Log((int)(Math.random()*10)%10+1);
        double leftBorder = Math.random()*100;
        double rightBorder = Math.random()*100 + 100;
        double step = Math.random();

        task.setFunction(function).setLeftBorder(leftBorder)
                .setRightBorder(rightBorder).setStep(step);

        System.out.printf("Source <%f> <%f> <%f>\n", leftBorder, rightBorder, step);

    }
}
