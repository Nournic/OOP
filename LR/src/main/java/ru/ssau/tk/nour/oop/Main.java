package ru.ssau.tk.nour.oop;

import ru.ssau.tk.nour.oop.functions.Functions;
import ru.ssau.tk.nour.oop.functions.basic.Log;
import ru.ssau.tk.nour.oop.functions.threads.*;

import java.util.concurrent.Semaphore;


public class Main {
    private static void nonThread(){
        Task task = new Task();
        task.setCountTasks(100);
        for (int i = 0; i < task.getCountTasks(); i++) {
            double base = (Math.random()*10)%10+1;
            task.setFunction(new Log(base));
            task.setLeftBorder(Math.random()*100);
            task.setRightBorder(Math.random()*100 + 100);
            task.setStep(Math.random());

            System.out.printf("%d. Source <%f> <%f> <%f>\n",
                    i+1, task.getLeftBorder(), task.getRightBorder(), task.getStep());

            double result_integrate = Functions.integrate(
                    task.getFunction(),task.getLeftBorder(),task.getRightBorder(),task.getStep());

            System.out.printf("%d. Result <%f> <%f> <%f> <%f>\n",
                    i+1, task.getLeftBorder(), task.getRightBorder(), task.getStep(), result_integrate);
        }

    }

    private static void simpleThreads(){
        Task task = new Task();
        task.setCountTasks(100);

        Thread simpleGenerator = new Thread(new SimpleGenerator(task), "SimpleGenerator");
        Thread simpleIntegrator = new Thread(new SimpleIntegrator(task), "SimpleIntegrator");

        simpleGenerator.start();
        simpleIntegrator.start();
    }

    private static void complicatedThreads() throws InterruptedException {
        Task task = new Task();
        task.setCountTasks(100);
        Semaphore semaphore = new Semaphore(1, true);

        Generator generator = new Generator(task, semaphore);
        Integrator integrator = new Integrator(task, semaphore);

        generator.start();
        integrator.start();

        Thread.sleep(50);

        generator.interrupt();
        integrator.interrupt();
    }

    public static void main(String[] args) {
        try {
            complicatedThreads();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
