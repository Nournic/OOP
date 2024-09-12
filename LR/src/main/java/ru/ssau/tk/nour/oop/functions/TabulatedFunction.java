package ru.ssau.tk.nour.oop.functions;

public class TabulatedFunction {
    private FunctionPoint functionPoints[];
    private final int capacity = 10;
    private int pointsCount;

    private void resizeArrayIncrease() {
        FunctionPoint functionPointNew[] = new FunctionPoint[functionPoints.length + capacity];
        System.arraycopy(functionPoints, 0, functionPointNew, 0, functionPoints.length);

        functionPoints = functionPointNew;
    }

    private void resizeArrayDecrease() {
        FunctionPoint functionPointNew[] = new FunctionPoint[functionPoints.length - capacity];
        System.arraycopy(functionPoints, 0, functionPointNew, 0, functionPoints.length);

        functionPoints = functionPointNew;
    }

    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        functionPoints = new FunctionPoint[pointsCount + capacity];
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
        functionPoints = new FunctionPoint[values.length + capacity];
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

        if (x >= getLeftDomainBorder() && x <= getRightDomainBorder()) {
            FunctionPoint next_point = functionPoints[1];
            FunctionPoint prev_point = functionPoints[0];
            for (int i = 0; i < pointsCount; i++) {
                if (functionPoints[i].getX() >= x && x != getLeftDomainBorder()) {
                    next_point = functionPoints[i];
                    prev_point = functionPoints[i - 1];
                    break;
                }
            }
            answer = ((x - prev_point.getX()) / (next_point.getX() - prev_point.getX())
                    * (next_point.getY() - prev_point.getY()) + prev_point.getY());
        }
        return answer;
    }

    public int getPointsCount() {
        return pointsCount;
    }

    public FunctionPoint getPoint(int index) {
        return functionPoints[index];
    }

    public void setPoint(int index, FunctionPoint point) {
        // Точка за левой границей
        if (point.getX() < getLeftDomainBorder())
            return;

        // Точка за правой границей
        if (point.getX() > getRightDomainBorder())
            return;

        functionPoints[index] = point;
    }

    public double getPointX(int index) {
        return functionPoints[index].getX();
    }

    public void setPointX(int index, double x) {
        // Точка за левой границей
        if (x < getLeftDomainBorder())
            return;

        // Точка за правой границей
        if (x > getRightDomainBorder())
            return;

        functionPoints[index].setX(x);
    }

    public double getPointY(int index) {
        return functionPoints[index].getY();
    }

    public void setPointY(int index, double y) {
        functionPoints[index].setY(y);
    }

    public void deletePoint(int index) {
        if (pointsCount < 3)
            return;

        if (pointsCount < functionPoints.length - capacity)
            resizeArrayDecrease();

        System.arraycopy(functionPoints, index + 1, functionPoints, index, pointsCount - index);
        pointsCount--;
    }

    public void addPoint(FunctionPoint point) {
        if (pointsCount + 1 > functionPoints.length)
            resizeArrayIncrease();

        int index_insert = getPointsCount();

        for (int i = 0; i < pointsCount; i++) {
            if (functionPoints[i].getX() >= point.getX()) {
                if (functionPoints[i].getX() == point.getX())
                    return;

                index_insert = i;
                break;
            }
        }


        System.arraycopy(functionPoints, index_insert, functionPoints, index_insert + 1, pointsCount - index_insert);
        functionPoints[index_insert] = point;
        pointsCount++;
    }
}
