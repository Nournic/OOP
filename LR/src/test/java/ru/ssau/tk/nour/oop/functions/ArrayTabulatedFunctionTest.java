package ru.ssau.tk.nour.oop.functions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ArrayTabulatedFunctionTest {
    ArrayTabulatedFunction arrayTabulatedFunctionArray;
    ArrayTabulatedFunction tabulatedFunctionPointsCount;

    @BeforeEach
    void init() {
        double[] values = {1, 2, 4, 8, 16, 32, 64, 128, 256};
        arrayTabulatedFunctionArray = new ArrayTabulatedFunction(-5.0, 5.0, values);
        tabulatedFunctionPointsCount = new ArrayTabulatedFunction(-5.0, 5.0, 9);
    }

    @Test
    void getLeftDomainBorder() {
        double expected = -5.0;

        Assertions.assertEquals(expected, arrayTabulatedFunctionArray.getLeftDomainBorder());
        Assertions.assertEquals(expected, tabulatedFunctionPointsCount.getLeftDomainBorder());
    }

    @Test
    void getRightDomainBorder() {
        double expected = 5.0;

        Assertions.assertEquals(expected, arrayTabulatedFunctionArray.getRightDomainBorder());
        Assertions.assertEquals(expected, tabulatedFunctionPointsCount.getRightDomainBorder());
    }

    @Test
    void getFunctionValueArray() {
        double[] expected = {1, 2, 4, 8, 16, 32, 64, 128, 256};

        double step = 10.0 / 8.0;
        double current_step = -5.0;
        for (double v : expected) {
            Assertions.assertEquals(v, arrayTabulatedFunctionArray.getFunctionValue(current_step));
            current_step += step;
        }

        Assertions.assertEquals(Double.NaN, arrayTabulatedFunctionArray.getFunctionValue(-999));
        Assertions.assertEquals(Double.NaN, arrayTabulatedFunctionArray.getFunctionValue(999));
    }

    @Test
    void getFunctionValuePointsCount() {
        double expected = 0;

        Assertions.assertEquals(expected, tabulatedFunctionPointsCount.getFunctionValue(0));
        Assertions.assertEquals(Double.NaN, tabulatedFunctionPointsCount.getFunctionValue(-999));
        Assertions.assertEquals(Double.NaN, tabulatedFunctionPointsCount.getFunctionValue(999));
    }

    @Test
    void getPointsCount() {
        int expected = 9;

        Assertions.assertEquals(expected, arrayTabulatedFunctionArray.getPointsCount());
        Assertions.assertEquals(expected, tabulatedFunctionPointsCount.getPointsCount());
    }

    @Test
    void getPointArray() {
        double[] expected = {1, 2, 4, 8, 16, 32, 64, 128, 256};

        for (int i = 0; i < expected.length; i++) {
            Assertions.assertEquals(expected[i], arrayTabulatedFunctionArray.getPointY(i));
        }
    }

    @Test
    void getPointPointsCount() {
        double expect1 = -5;
        double expect2 = 5;

        Assertions.assertEquals(expect1, tabulatedFunctionPointsCount.getPoint(0).getX());
        Assertions.assertEquals(expect2, tabulatedFunctionPointsCount.getPoint(
                tabulatedFunctionPointsCount.getPointsCount() - 1).getX());
    }

    @Test
    void setPointInBorder() {
        double expect = -4;

        try {
            FunctionPoint functionPoint = Mockito.mock(FunctionPoint.class);
            Mockito.when(functionPoint.getX()).thenReturn(expect);
            arrayTabulatedFunctionArray.setPoint(0, functionPoint);

            Assertions.assertEquals(expect, arrayTabulatedFunctionArray.getPointX(0));
        } catch (InappropriateFunctionPointException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getPointX() {
        double expect1 = -5;
        double expect2 = 5;

        Assertions.assertEquals(expect1, tabulatedFunctionPointsCount.getPointX(0));
        Assertions.assertEquals(expect2, tabulatedFunctionPointsCount.getPointX(
                tabulatedFunctionPointsCount.getPointsCount() - 1));
    }

    @Test
    void getPointY() {
        double expect1 = 1;
        double expect2 = 256;

        Assertions.assertEquals(expect1, arrayTabulatedFunctionArray.getPointY(0));
        Assertions.assertEquals(expect2, arrayTabulatedFunctionArray.getPointY(
                tabulatedFunctionPointsCount.getPointsCount() - 1));
    }

    @Test
    void deletePoint() {
        double expect = arrayTabulatedFunctionArray.getPoint(1).getX();
        double expect_points = arrayTabulatedFunctionArray.getPointsCount() - 1;

        arrayTabulatedFunctionArray.deletePoint(0);

        Assertions.assertEquals(expect, arrayTabulatedFunctionArray.getPoint(0).getX());
        Assertions.assertEquals(expect_points, arrayTabulatedFunctionArray.getPointsCount());
    }

    @Test
    void addPoint1() {
        FunctionPoint functionPoint = new FunctionPoint(-5.5,0);
        int expect_points = arrayTabulatedFunctionArray.getPointsCount() + 1;

        try {
            arrayTabulatedFunctionArray.addPoint(functionPoint);
            Assertions.assertSame(functionPoint, arrayTabulatedFunctionArray.getPoint(0));
            Assertions.assertEquals(expect_points, arrayTabulatedFunctionArray.getPointsCount());
        } catch (InappropriateFunctionPointException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void addPoint2() {
        FunctionPoint functionPoint = new FunctionPoint(5.5,0);
        int expect_points = arrayTabulatedFunctionArray.getPointsCount() + 1;

        try {
            arrayTabulatedFunctionArray.addPoint(functionPoint);
            Assertions.assertSame(functionPoint, arrayTabulatedFunctionArray.getPoint(
                    arrayTabulatedFunctionArray.getPointsCount() - 1));
            Assertions.assertEquals(expect_points, arrayTabulatedFunctionArray.getPointsCount());
        } catch (InappropriateFunctionPointException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void addPoint3() {
        FunctionPoint functionPoint = new FunctionPoint(-4.5,0);
        int expect_points = arrayTabulatedFunctionArray.getPointsCount() + 1;

        try {
            arrayTabulatedFunctionArray.addPoint(functionPoint);
            Assertions.assertSame(functionPoint, arrayTabulatedFunctionArray.getPoint(1));
            Assertions.assertEquals(expect_points, arrayTabulatedFunctionArray.getPointsCount());
        } catch (InappropriateFunctionPointException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void addPoint4() {
        FunctionPoint functionPoint = new FunctionPoint(4.5,0);
        int expect_points = arrayTabulatedFunctionArray.getPointsCount() + 1;

        try {
            arrayTabulatedFunctionArray.addPoint(functionPoint);
            Assertions.assertSame(functionPoint, arrayTabulatedFunctionArray.getPoint(
                    arrayTabulatedFunctionArray.getPointsCount() - 2
            ));
            Assertions.assertEquals(expect_points, arrayTabulatedFunctionArray.getPointsCount());
        } catch (InappropriateFunctionPointException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void createFunctionWithLesserThan2Points() {
        Assertions.assertThrowsExactly(IllegalArgumentException.class,
                () -> new ArrayTabulatedFunction(-5, 5, 1));
        Assertions.assertThrowsExactly(IllegalArgumentException.class,
                () -> new ArrayTabulatedFunction(-5, 5, -1));
    }

    @Test
    void createFunctionWithEqualsX() {
        Assertions.assertThrowsExactly(IllegalArgumentException.class,
                () -> new ArrayTabulatedFunction(0, 0, 10));
    }

    @Test
    void createFunctionWithRightXGreaterThanLeftX() {
        Assertions.assertThrowsExactly(IllegalArgumentException.class,
                () -> new ArrayTabulatedFunction(5, -5, 10));
    }
    @Test
    void setPointExceptions() {
        FunctionPoint functionPoint = new FunctionPoint(-5.01, 0);

        Assertions.assertThrowsExactly(InappropriateFunctionPointException.class,
                () -> arrayTabulatedFunctionArray.setPoint(0, functionPoint));

        functionPoint.setX(5.01);

        Assertions.assertThrowsExactly(InappropriateFunctionPointException.class,
                () -> arrayTabulatedFunctionArray.setPoint(0, functionPoint));
    }
    @Test
    void setPoint() {
        FunctionPoint functionPoint = new FunctionPoint(1, 5);
        try {
            Assertions.assertNotEquals(functionPoint, arrayTabulatedFunctionArray.getPoint(5));
            arrayTabulatedFunctionArray.setPoint(5, functionPoint);
            Assertions.assertEquals(functionPoint, arrayTabulatedFunctionArray.getPoint(5));
        } catch (InappropriateFunctionPointException e) {
            Assertions.assertEquals(-1, 1);
        }
    }
    @Test
    void setPointXExceptions() {
        FunctionPoint functionPoint = new FunctionPoint(-5.01, 0);

        Assertions.assertThrowsExactly(InappropriateFunctionPointException.class,
                () -> arrayTabulatedFunctionArray.setPointX(1, functionPoint.getX()));

        functionPoint.setX(5.01);

        Assertions.assertThrowsExactly(InappropriateFunctionPointException.class,
                () -> arrayTabulatedFunctionArray.setPointX(0, functionPoint.getX()));
    }
    @Test
    void setPointX() {
        double expect = 1;
        try {
            Assertions.assertNotEquals(expect, arrayTabulatedFunctionArray.getPointX(5));
            arrayTabulatedFunctionArray.setPointX(5, 1);
            Assertions.assertEquals(expect, arrayTabulatedFunctionArray.getPointX(5));
        } catch (InappropriateFunctionPointException e) {
            Assertions.assertEquals(-1, 1);
        }
    }
    @Test
    void deletePointTwoPoints() {
        ArrayTabulatedFunction arrayTabulatedFunctionArray = new ArrayTabulatedFunction(-5, 5, 2);
        Assertions.assertThrowsExactly(IllegalStateException.class,
                () -> arrayTabulatedFunctionArray.deletePoint(0));
    }
    @Test
    void deletePointExceptionLeftBorder() {
        Assertions.assertThrowsExactly(FunctionPointIndexOutOfBoundsException.class,
                () -> arrayTabulatedFunctionArray.deletePoint(-1));
    }

    @Test
    void deletePointExceptionRightBorder() {
        Assertions.assertThrowsExactly(FunctionPointIndexOutOfBoundsException.class,
                () -> arrayTabulatedFunctionArray.deletePoint(9));
    }
    @Test
    void addPointExceptions() {
        FunctionPoint functionPoint = Mockito.mock(FunctionPoint.class);
        Mockito.when(functionPoint.getX()).thenReturn(-5.0);

        Assertions.assertThrowsExactly(InappropriateFunctionPointException.class,
                () -> arrayTabulatedFunctionArray.addPoint(functionPoint));
    }

    @Test
    void cloneTest(){
        ArrayTabulatedFunction arrayTabulatedFunction;
        try {
            arrayTabulatedFunction = (ArrayTabulatedFunction) arrayTabulatedFunctionArray.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < arrayTabulatedFunctionArray.getPointsCount(); i++) {
            Assertions.assertEquals(arrayTabulatedFunctionArray.getPointX(i), arrayTabulatedFunction.getPointX(i));
            Assertions.assertEquals(arrayTabulatedFunctionArray.getPointY(i), arrayTabulatedFunction.getPointY(i));
        }
    }

    @Test
    void toStringTest(){
        for (int i = 0; i < arrayTabulatedFunctionArray.getPointsCount(); i++)
            System.out.println(arrayTabulatedFunctionArray.getPoint(i).toString());
        System.out.println(arrayTabulatedFunctionArray.toString());
    }

    @Test
    void equalsTest(){
        double[] values = {1, 2, 4, 8, 16, 32, 64, 128, 256};
        LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(-5.0, 5.0, values);

        Assertions.assertTrue(arrayTabulatedFunctionArray.equals(linkedListTabulatedFunction));
        Assertions.assertTrue(arrayTabulatedFunctionArray.equals(arrayTabulatedFunctionArray));

        linkedListTabulatedFunction.setPointY(0,123123);
        Assertions.assertFalse(arrayTabulatedFunctionArray.equals(linkedListTabulatedFunction));
    }

    @Test
    void hashCodeTest(){
        double[] values = {1, 2, 4, 8, 16, 32, 64, 128, 256};
        LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(-5.0, 5.0, values);

        Assertions.assertEquals(arrayTabulatedFunctionArray.hashCode(), linkedListTabulatedFunction.hashCode());

        linkedListTabulatedFunction.setPointY(0, 3123);

        Assertions.assertNotEquals(arrayTabulatedFunctionArray.hashCode(), linkedListTabulatedFunction.hashCode());
    }
}