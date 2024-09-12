package ru.ssau.tk.nour.oop.functions;

public class TabulatedFunction {
    private FunctionPoint functionPoints[];
    private int pointsCount;

    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        functionPoints = new FunctionPoint[pointsCount];

        this.pointsCount = pointsCount;
        double step = (rightX - leftX) / (pointsCount - 1);

        int i;
        double current_step;
        for(i = 0, current_step=leftX; i < pointsCount; i++) {
            functionPoints[i].setX(current_step);
            current_step += step;
        }
    }

    public TabulatedFunction(double leftX, double rightX, double[] values) {
        functionPoints = new FunctionPoint[values.length];
        double step = (rightX - leftX) / values.length;
        pointsCount = values.length;

        int i;
        double current_step;
        for(i = 0, current_step=leftX; i < values.length; i++) {
            functionPoints[i].setX(current_step);
            functionPoints[i].setY(values[i]);
            current_step+=step;
        }
    }
}
