package ru.ssau.tk.nour.oop.functions.threads;

import ru.ssau.tk.nour.oop.functions.Functions;

import java.util.concurrent.Semaphore;

public class Integrator extends  Thread{
    private Task task;
    private Semaphore semaphore;

    public Integrator(Task task, Semaphore semaphore) {
        this.task = task;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < task.getCountTasks(); i++) {
                semaphore.acquire();

                double result_integrate = Functions.integrate(
                        task.getFunction(), task.getLeftBorder(), task.getRightBorder(), task.getStep());

                System.out.printf("%d. Result <%f> <%f> <%f> <%f>\n",
                        i + 1, task.getLeftBorder(), task.getRightBorder(), task.getStep(), result_integrate);

                semaphore.release();
            }
            System.out.println(Thread.currentThread().getName() + " is done.");
        }
        catch(InterruptedException e){
            System.out.println(Thread.currentThread().getName() + " is interrupted");
        }
    }
}
