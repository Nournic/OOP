package ru.ssau.tk.nour.oop.functions.threads;

import ru.ssau.tk.nour.oop.functions.Functions;
import ru.ssau.tk.nour.oop.functions.basic.Log;

public class SimpleIntegrator implements Runnable{
    private Task task;

    public SimpleIntegrator(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        synchronized(task){
            for (int i = 0; i < task.getCountTasks(); i++) {
                double result_integrate = Functions.integrate(
                        task.getFunction(),task.getLeftBorder(),task.getRightBorder(),task.getStep());

                System.out.printf("%d. Result <%f> <%f> <%f> <%f>\n",
                        i+1, task.getLeftBorder(), task.getRightBorder(), task.getStep(), result_integrate);

                try {
                    task.notify();
                    task.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        System.out.println(Thread.currentThread().getName() + " is done.");
    }
}
