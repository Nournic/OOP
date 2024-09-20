package ru.ssau.tk.nour.oop.functions;

public class ArrayTabulatedFunction implements TabulatedFunction {
    private FunctionPoint[] functionPoints;
    private final int capacity = 10;
    private int pointsCount;

    private void resizeArrayIncrease() {
        FunctionPoint[] functionPointNew = new FunctionPoint[functionPoints.length + capacity];
        System.arraycopy(functionPoints, 0, functionPointNew, 0, functionPoints.length);

        functionPoints = functionPointNew;
    }

    private void resizeArrayDecrease() {
        FunctionPoint[] functionPointNew = new FunctionPoint[functionPoints.length - capacity];
        System.arraycopy(functionPoints, 0, functionPointNew, 0, functionPoints.length);

        functionPoints = functionPointNew;
    }

    private void checkIndex(int index) throws FunctionPointIndexOutOfBoundsException {
        if (index > pointsCount - 1 || index < 0)
            throw new FunctionPointIndexOutOfBoundsException();
    }

    public ArrayTabulatedFunction(double leftX, double rightX, int pointsCount) throws IllegalArgumentException{
        if (leftX >= rightX || pointsCount < 2)
            throw new IllegalArgumentException();

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

    public ArrayTabulatedFunction(double leftX, double rightX, double[] values) throws IllegalArgumentException{
        if (leftX >= rightX || values.length < 2)
            throw new IllegalArgumentException();

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

    public ArrayTabulatedFunction(FunctionPoint[] points) throws IllegalArgumentException {
        if(points.length<2)
            throw new IllegalArgumentException();

        this.pointsCount = points.length;
        FunctionPoint prev = points[0];
        for(int i = 1; i < points.length; i++){
            if(prev.getX() > points[i].getX())
                throw new IllegalArgumentException();
            prev = points[i];
        }

        functionPoints = new FunctionPoint[pointsCount+capacity];
        System.arraycopy(points, 0, functionPoints,0, points.length);
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

    public FunctionPoint getPoint(int index) throws FunctionPointIndexOutOfBoundsException {
        checkIndex(index);
        return functionPoints[index];
    }

    public void setPoint(int index, FunctionPoint point) throws FunctionPointIndexOutOfBoundsException, InappropriateFunctionPointException {
        checkIndex(index);
        // Точка за левой границей
        if (point.getX() < getLeftDomainBorder())
            throw new InappropriateFunctionPointException();

        // Точка за правой границей
        if (point.getX() > getRightDomainBorder())
            throw new InappropriateFunctionPointException();

        functionPoints[index] = point;
    }

    public double getPointX(int index) throws FunctionPointIndexOutOfBoundsException {
        checkIndex(index);
        return functionPoints[index].getX();
    }

    public void setPointX(int index, double x) throws FunctionPointIndexOutOfBoundsException, InappropriateFunctionPointException {
        checkIndex(index);
        // Точка за левой границей
        if (x < getLeftDomainBorder())
            throw new InappropriateFunctionPointException();

        // Точка за правой границей
        if (x > getRightDomainBorder())
            throw new InappropriateFunctionPointException();

        functionPoints[index].setX(x);
    }

    public double getPointY(int index) throws FunctionPointIndexOutOfBoundsException {
        checkIndex(index);
        return functionPoints[index].getY();
    }

    public void setPointY(int index, double y) throws FunctionPointIndexOutOfBoundsException {
        checkIndex(index);
        functionPoints[index].setY(y);
    }

    public void deletePoint(int index) throws FunctionPointIndexOutOfBoundsException, IllegalStateException {
        checkIndex(index);

        if (pointsCount < 3)
            throw new IllegalStateException();

        if (pointsCount < functionPoints.length - capacity)
            resizeArrayDecrease();

        System.arraycopy(functionPoints, index + 1, functionPoints, index, pointsCount - index);
        pointsCount--;
    }

    public void addPoint(FunctionPoint point) throws InappropriateFunctionPointException {
        if (pointsCount + 1 > functionPoints.length)
            resizeArrayIncrease();

        int index_insert = getPointsCount();

        for (int i = 0; i < pointsCount; i++) {
            if (functionPoints[i].getX() >= point.getX()) {
                if (functionPoints[i].getX() == point.getX())
                    throw new InappropriateFunctionPointException();

                index_insert = i;
                break;
            }
        }


        System.arraycopy(functionPoints, index_insert, functionPoints, index_insert + 1, pointsCount - index_insert);
        functionPoints[index_insert] = point;
        pointsCount++;
    }

    @Override
    public int hashCode() {
        int hash=0;
        for (int i = 0; i < pointsCount; i++)
            hash+=Double.hashCode(functionPoints[i].getX()) + Double.hashCode(functionPoints[i].getY());

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TabulatedFunction)) return false;

        TabulatedFunction function = (TabulatedFunction) obj;
        if(this.pointsCount != function.getPointsCount())
            return false;

        if(function instanceof ArrayTabulatedFunction){
            for (int i = 0; i < function.getPointsCount(); i++) {
                FunctionPoint functionPoint = ((ArrayTabulatedFunction) function).functionPoints[i];
                if(functionPoint.getX() != this.functionPoints[i].getX()
                        || functionPoint.getY() != this.functionPoints[i].getY())
                    return false;
            }
        }
        else {
            for (int i = 0; i < function.getPointsCount(); i++) {
                FunctionPoint functionPoint = function.getPoint(i);
                if(functionPoint.getX() != this.functionPoints[i].getX()
                        || functionPoint.getY() != this.functionPoints[i].getY())
                    return false;
            }
        }

        return true;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(functionPoints.clone());
        return arrayTabulatedFunction;
    }

    @Override
    public String toString() {
        String format = "{";

        for (int i = 0; i < pointsCount; i++) {
            format += String.format("(%f; %f)",functionPoints[i].getX(), functionPoints[i].getY());
            if(i < pointsCount - 1)
                format += ", ";
        }
        format += "}";

        return format;
    }
}
