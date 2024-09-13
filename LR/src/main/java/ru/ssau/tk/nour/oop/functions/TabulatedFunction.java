package ru.ssau.tk.nour.oop.functions;

public interface TabulatedFunction {
    double getLeftDomainBorder();

    double getRightDomainBorder() throws FunctionPointIndexOutOfBoundsException;

    double getFunctionValue(double x) throws InappropriateFunctionPointException;

    int getPointsCount();

    FunctionPoint getPoint(int index) throws FunctionPointIndexOutOfBoundsException;

    void setPoint(int index, FunctionPoint point) throws FunctionPointIndexOutOfBoundsException, InappropriateFunctionPointException;

    double getPointX(int index) throws FunctionPointIndexOutOfBoundsException;

    void setPointX(int index, double x) throws FunctionPointIndexOutOfBoundsException, InappropriateFunctionPointException;

    double getPointY(int index) throws FunctionPointIndexOutOfBoundsException;

    void setPointY(int index, double y) throws FunctionPointIndexOutOfBoundsException;

    void deletePoint(int index) throws FunctionPointIndexOutOfBoundsException, IllegalStateException;

    void addPoint(FunctionPoint point) throws InappropriateFunctionPointException;

}
