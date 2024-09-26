package ru.ssau.tk.nour.oop.functions.threads;

import ru.ssau.tk.nour.oop.functions.Function;

public class Task {
    private Function function;
    private double leftBorder;
    private double rightBorder;
    private double step;

    private static int countTasks = 0;

    public int getCountTasks() {
        return countTasks;
    }

    public Task setCountTasks(int countTasks) {
        Task.countTasks = countTasks;
        return this;
    }

    public Function getFunction() {
        return function;
    }

    public Task setFunction(Function function) {
        this.function = function;
        return this;
    }

    public double getLeftBorder() {
        return leftBorder;
    }

    public Task setLeftBorder(double leftBorder) {
        this.leftBorder = leftBorder;
        return this;
    }

    public double getRightBorder() {
        return rightBorder;
    }

    public Task setRightBorder(double rightBorder) {
        this.rightBorder = rightBorder;
        return this;
    }

    public double getStep() {
        return step;
    }

    public Task setStep(double step) {
        this.step = step;
        return this;
    }
}
