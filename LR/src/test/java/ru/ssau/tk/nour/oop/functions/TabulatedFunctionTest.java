package ru.ssau.tk.nour.oop.functions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

class TabulatedFunctionTest {
    TabulatedFunction tabulatedFunctionArray;
    TabulatedFunction tabulatedFunctionPointsCount;

    @BeforeEach
    void init() {
        double[] values = {1, 2, 4, 8, 16, 32, 64, 128, 256};
        tabulatedFunctionArray = new TabulatedFunction(-5.0, 5.0, values);
        tabulatedFunctionPointsCount = new TabulatedFunction(-5.0, 5.0, 9);
    }

    @Test
    void getLeftDomainBorder() {
        double expected = -5.0;

        Assertions.assertEquals(expected, tabulatedFunctionArray.getLeftDomainBorder());
        Assertions.assertEquals(expected, tabulatedFunctionPointsCount.getLeftDomainBorder());
    }

    @Test
    void getRightDomainBorder() {
        double expected = 5.0;

        Assertions.assertEquals(expected, tabulatedFunctionArray.getRightDomainBorder());
        Assertions.assertEquals(expected, tabulatedFunctionPointsCount.getRightDomainBorder());
    }

    @Test
    void getFunctionValueArray() {
        double[] expected = {1, 2, 4, 8, 16, 32, 64, 128, 256};

        double step = 10.0 / 8.0;
        double current_step = -5.0;
        for (double v : expected) {
            Assertions.assertEquals(v, tabulatedFunctionArray.getFunctionValue(current_step));
            current_step += step;
        }

        Assertions.assertEquals(Double.NaN, tabulatedFunctionArray.getFunctionValue(-999));
        Assertions.assertEquals(Double.NaN, tabulatedFunctionArray.getFunctionValue(999));
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

        Assertions.assertEquals(expected, tabulatedFunctionArray.getPointsCount());
        Assertions.assertEquals(expected, tabulatedFunctionPointsCount.getPointsCount());
    }

    @Test
    void getPointArray() {
        double[] expected = {1, 2, 4, 8, 16, 32, 64, 128, 256};

        for (int i = 0; i < expected.length; i++) {
            Assertions.assertEquals(expected[i], tabulatedFunctionArray.getPointY(i));
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
            tabulatedFunctionArray.setPoint(0, functionPoint);

            Assertions.assertEquals(expect, tabulatedFunctionArray.getPointX(0));
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

        Assertions.assertEquals(expect1, tabulatedFunctionArray.getPointY(0));
        Assertions.assertEquals(expect2, tabulatedFunctionArray.getPointY(
                tabulatedFunctionPointsCount.getPointsCount() - 1));
    }

    @Test
    void deletePoint() {
        double expect = tabulatedFunctionArray.getPoint(1).getX();
        double expect_points = tabulatedFunctionArray.getPointsCount() - 1;

        tabulatedFunctionArray.deletePoint(0);

        Assertions.assertEquals(expect, tabulatedFunctionArray.getPoint(0).getX());
        Assertions.assertEquals(expect_points, tabulatedFunctionArray.getPointsCount());
    }

    @Test
    void addPoint1() {
        FunctionPoint functionPoint = new FunctionPoint(-5.5,0);
        int expect_points = tabulatedFunctionArray.getPointsCount() + 1;

        try {
            tabulatedFunctionArray.addPoint(functionPoint);
            Assertions.assertSame(functionPoint, tabulatedFunctionArray.getPoint(0));
            Assertions.assertEquals(expect_points, tabulatedFunctionArray.getPointsCount());
        } catch (InappropriateFunctionPointException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void addPoint2() {
        FunctionPoint functionPoint = new FunctionPoint(5.5,0);
        int expect_points = tabulatedFunctionArray.getPointsCount() + 1;

        try {
            tabulatedFunctionArray.addPoint(functionPoint);
            Assertions.assertSame(functionPoint, tabulatedFunctionArray.getPoint(
                    tabulatedFunctionArray.getPointsCount() - 1));
            Assertions.assertEquals(expect_points, tabulatedFunctionArray.getPointsCount());
        } catch (InappropriateFunctionPointException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void addPoint3() {
        FunctionPoint functionPoint = new FunctionPoint(-4.5,0);
        int expect_points = tabulatedFunctionArray.getPointsCount() + 1;

        try {
            tabulatedFunctionArray.addPoint(functionPoint);
            Assertions.assertSame(functionPoint, tabulatedFunctionArray.getPoint(1));
            Assertions.assertEquals(expect_points, tabulatedFunctionArray.getPointsCount());
        } catch (InappropriateFunctionPointException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void addPoint4() {
        FunctionPoint functionPoint = new FunctionPoint(4.5,0);
        int expect_points = tabulatedFunctionArray.getPointsCount() + 1;

        try {
            tabulatedFunctionArray.addPoint(functionPoint);
            Assertions.assertSame(functionPoint, tabulatedFunctionArray.getPoint(
                    tabulatedFunctionArray.getPointsCount() - 2
            ));
            Assertions.assertEquals(expect_points, tabulatedFunctionArray.getPointsCount());
        } catch (InappropriateFunctionPointException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void createFunctionWithLesserThan2Points() {
        Assertions.assertThrowsExactly(IllegalArgumentException.class,
                () -> new TabulatedFunction(-5, 5, 1));
        Assertions.assertThrowsExactly(IllegalArgumentException.class,
                () -> new TabulatedFunction(-5, 5, -1));
    }

    @Test
    void createFunctionWithEqualsX() {
        Assertions.assertThrowsExactly(IllegalArgumentException.class,
                () -> new TabulatedFunction(0, 0, 10));
    }

    @Test
    void createFunctionWithRightXGreaterThanLeftX() {
        Assertions.assertThrowsExactly(IllegalArgumentException.class,
                () -> new TabulatedFunction(5, -5, 10));
    }
    @Test
    void setPointExceptions() {
        FunctionPoint functionPoint = new FunctionPoint(-5.01, 0);

        Assertions.assertThrowsExactly(InappropriateFunctionPointException.class,
                () -> tabulatedFunctionArray.setPoint(0, functionPoint));

        functionPoint.setX(5.01);

        Assertions.assertThrowsExactly(InappropriateFunctionPointException.class,
                () -> tabulatedFunctionArray.setPoint(0, functionPoint));
    }
    @Test
    void setPoint() {
        FunctionPoint functionPoint = new FunctionPoint(1, 5);
        try {
            Assertions.assertNotEquals(functionPoint, tabulatedFunctionArray.getPoint(5));
            tabulatedFunctionArray.setPoint(5, functionPoint);
            Assertions.assertEquals(functionPoint, tabulatedFunctionArray.getPoint(5));
        } catch (InappropriateFunctionPointException e) {
            Assertions.assertEquals(-1, 1);
        }
    }
    @Test
    void deletePointTwoPoints() {
        TabulatedFunction tabulatedFunctionArray = new TabulatedFunction(-5, 5, 2);
        Assertions.assertThrowsExactly(IllegalStateException.class,
                () -> tabulatedFunctionArray.deletePoint(0));
    }
    @Test
    void deletePointExceptionLeftBorder() {
        Assertions.assertThrowsExactly(FunctionPointIndexOutOfBoundsException.class,
                () -> tabulatedFunctionArray.deletePoint(-1));
    }

    @Test
    void deletePointExceptionRightBorder() {
        Assertions.assertThrowsExactly(FunctionPointIndexOutOfBoundsException.class,
                () -> tabulatedFunctionArray.deletePoint(9));
    }
    @Test
    void addPointExceptions() {
        FunctionPoint functionPoint = Mockito.mock(FunctionPoint.class);
        Mockito.when(functionPoint.getX()).thenReturn(-5.0);

        Assertions.assertThrowsExactly(InappropriateFunctionPointException.class,
                () -> tabulatedFunctionArray.addPoint(functionPoint));
    }
}