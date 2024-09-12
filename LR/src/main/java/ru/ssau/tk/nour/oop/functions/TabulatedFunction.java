package ru.ssau.tk.nour.oop.functions;

public class TabulatedFunction {
    private FunctionPoint functionPoints[];
    private int pointsCount;

    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        functionPoints = new FunctionPoint[pointsCount];
        for (int i = 0; i < functionPoints.length; i++)
            functionPoints[i] = new FunctionPoint();

        this.pointsCount = pointsCount;
        double step = (rightX - leftX) / (pointsCount - 1);

        int i;
        double current_step;
        for (i = 0, current_step = leftX; i < pointsCount; i++) {
            functionPoints[i].setX(current_step);
            current_step += step;
        }
    }

    public TabulatedFunction(double leftX, double rightX, double[] values) {
        functionPoints = new FunctionPoint[values.length];
        for (int i = 0; i < functionPoints.length; i++)
            functionPoints[i] = new FunctionPoint();

        double step = (rightX - leftX) / (values.length - 1);
        pointsCount = values.length;

        int i;
        double current_step;
        for (i = 0, current_step = leftX; i < values.length; i++) {
            functionPoints[i].setX(current_step);
            functionPoints[i].setY(values[i]);
            current_step += step;
        }
    }

    public double getLeftDomainBorder() {
        int left_index = 0;
        return functionPoints[left_index].getX();
    }

    public double getRightDomainBorder() {
        int right_index = pointsCount - 1;
        return functionPoints[right_index].getX();
    }

    public double getFunctionValue(double x) {
        double answer = Double.NaN;

        if(x>=getLeftDomainBorder() && x<=getRightDomainBorder()) {
            FunctionPoint next_point = functionPoints[1];
            FunctionPoint prev_point = functionPoints[0];
            for (int i = 0; i < pointsCount; i++){
                if (functionPoints[i].getX() >= x && x != getLeftDomainBorder()) {
                    next_point = functionPoints[i];
                    prev_point = functionPoints[i-1];
                    break;
                }
            }
            answer = ((x - prev_point.getX())/(next_point.getX() - prev_point.getX())
                    * (next_point.getY() - prev_point.getY()) + prev_point.getY());
        }
        return answer;
    }
}
