package ru.ssau.tk.nour.oop.functions.threads;

import ru.ssau.tk.nour.oop.functions.basic.Log;

import java.util.concurrent.Semaphore;

public class Generator extends Thread{
    private Task task;
    private Semaphore semaphore;

    public Generator(Task task, Semaphore semaphore) {
        this.task = task;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < task.getCountTasks(); i++) {
                semaphore.acquire();

                double base = (Math.random() * 10) % 10 + 1;
                task.setFunction(new Log(base));
                task.setLeftBorder(Math.random() * 100);
                task.setRightBorder(Math.random() * 100 + 100);
                task.setStep(Math.random());

                System.out.printf("%d. Source <%f> <%f> <%f>\n",
                        i + 1, task.getLeftBorder(), task.getRightBorder(), task.getStep());

                semaphore.release();
            }

            System.out.println(Thread.currentThread().getName() + " is done.");
        }
        catch (InterruptedException e){
            System.out.println(Thread.currentThread().getName() + " is interrupted");
        }
    }
}
