package ru.ssau.tk.nour.oop.functions.threads;

import ru.ssau.tk.nour.oop.functions.basic.Log;

public class SimpleGenerator implements Runnable{
    private Task task;

    public SimpleGenerator(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        synchronized(task){
            for (int i = 0; i < task.getCountTasks(); i++) {
                double base = (Math.random() * 10) % 10 + 1;
                task.setFunction(new Log(base));
                task.setLeftBorder(Math.random() * 100);
                task.setRightBorder(Math.random() * 100 + 100);
                task.setStep(Math.random());

                System.out.printf("%d. Source <%f> <%f> <%f>\n",
                        i + 1, task.getLeftBorder(), task.getRightBorder(), task.getStep());
                try {
                    task.wait();
                    task.notify();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println(Thread.currentThread().getName() + " is done.");
    }
}
