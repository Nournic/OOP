package ru.ssau.tk.nour.oop.functions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTabulatedFunctionTest {
    LinkedListTabulatedFunction linkedListTabulatedFunction;
    LinkedListTabulatedFunction linkedListTabulatedFunctionPointsCount;

    @BeforeEach
    void init() {
        double[] values = {1, 2, 4, 8, 16, 32, 64, 128, 256};
        linkedListTabulatedFunction = new LinkedListTabulatedFunction(-5.0, 5.0, values);
        linkedListTabulatedFunctionPointsCount = new LinkedListTabulatedFunction(-5.0, 5.0, 9);
    }

    @Test
    void getLeftDomainBorder() {
        double expected = -5.0;

        assertEquals(expected, linkedListTabulatedFunction.getLeftDomainBorder());
        assertEquals(expected, linkedListTabulatedFunctionPointsCount.getLeftDomainBorder());
    }

    @Test
    void getRightDomainBorder() {
        double expected = 5.0;

        Assertions.assertEquals(expected, linkedListTabulatedFunction.getRightDomainBorder());
        Assertions.assertEquals(expected, linkedListTabulatedFunctionPointsCount.getRightDomainBorder());
    }

    @Test
    void getFunctionValueArray() {
        double[] expected = {1, 2, 4, 8, 16, 32, 64, 128, 256};

        double step = 10.0 / 8.0;
        double current_step = -5.0;
        for (double v : expected) {
            Assertions.assertEquals(v, linkedListTabulatedFunction.getFunctionValue(current_step));
            current_step += step;
        }

        Assertions.assertEquals(Double.NaN, linkedListTabulatedFunction.getFunctionValue(-999));
        Assertions.assertEquals(Double.NaN, linkedListTabulatedFunction.getFunctionValue(999));
    }

    @Test
    void getFunctionValuePointsCount() {
        double expected = 0;

        Assertions.assertEquals(expected, linkedListTabulatedFunctionPointsCount.getFunctionValue(0));
        Assertions.assertEquals(Double.NaN, linkedListTabulatedFunctionPointsCount.getFunctionValue(-999));
        Assertions.assertEquals(Double.NaN, linkedListTabulatedFunctionPointsCount.getFunctionValue(999));
    }

    @Test
    void getPointsCount() {
        int expected = 9;

        Assertions.assertEquals(expected, linkedListTabulatedFunction.getPointsCount());
        Assertions.assertEquals(expected, linkedListTabulatedFunctionPointsCount.getPointsCount());
    }

    @Test
    void getPointArray() {
        double[] expected = {1, 2, 4, 8, 16, 32, 64, 128, 256};

        for (int i = 0; i < expected.length; i++) {
            Assertions.assertEquals(expected[i], linkedListTabulatedFunction.getPointY(i));
        }
    }

    @Test
    void getPointPointsCount() {
        double expect1 = -5;
        double expect2 = 5;

        Assertions.assertEquals(expect1, linkedListTabulatedFunctionPointsCount.getPoint(0).getX());
        Assertions.assertEquals(expect2, linkedListTabulatedFunctionPointsCount.getPoint(
                linkedListTabulatedFunctionPointsCount.getPointsCount() - 1).getX());
    }

    @Test
    void setPointInBorder() {
        double expect = -4;

        try {
            FunctionPoint functionPoint = Mockito.mock(FunctionPoint.class);
            Mockito.when(functionPoint.getX()).thenReturn(expect);
            linkedListTabulatedFunction.setPoint(0, functionPoint);

            Assertions.assertEquals(expect, linkedListTabulatedFunction.getPointX(0));
        } catch (InappropriateFunctionPointException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getPointX() {
        double expect1 = -5;
        double expect2 = 5;

        Assertions.assertEquals(expect1, linkedListTabulatedFunctionPointsCount.getPointX(0));
        Assertions.assertEquals(expect2, linkedListTabulatedFunctionPointsCount.getPointX(
                linkedListTabulatedFunctionPointsCount.getPointsCount() - 1));
    }

    @Test
    void getPointY() {
        double expect1 = 1;
        double expect2 = 256;

        Assertions.assertEquals(expect1, linkedListTabulatedFunction.getPointY(0));
        Assertions.assertEquals(expect2, linkedListTabulatedFunction.getPointY(
                linkedListTabulatedFunctionPointsCount.getPointsCount() - 1));
    }

    @Test
    void deletePoint() {
        double expect = linkedListTabulatedFunction.getPoint(1).getX();
        double expect_points = linkedListTabulatedFunction.getPointsCount() - 1;

        linkedListTabulatedFunction.deletePoint(0);

        Assertions.assertEquals(expect, linkedListTabulatedFunction.getPoint(0).getX());
        Assertions.assertEquals(expect_points, linkedListTabulatedFunction.getPointsCount());
    }

    @Test
    void addPoint1() {
        FunctionPoint functionPoint = new FunctionPoint(-5.5,0);
        int expect_points = linkedListTabulatedFunction.getPointsCount() + 1;

        try {
            linkedListTabulatedFunction.addPoint(functionPoint);
            Assertions.assertSame(functionPoint, linkedListTabulatedFunction.getPoint(0));
            Assertions.assertEquals(expect_points, linkedListTabulatedFunction.getPointsCount());
        } catch (InappropriateFunctionPointException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void addPoint2() {
        FunctionPoint functionPoint = new FunctionPoint(5.5,0);
        int expect_points = linkedListTabulatedFunction.getPointsCount() + 1;

        try {
            linkedListTabulatedFunction.addPoint(functionPoint);
            Assertions.assertSame(functionPoint, linkedListTabulatedFunction.getPoint(
                    linkedListTabulatedFunction.getPointsCount() - 1));
            Assertions.assertEquals(expect_points, linkedListTabulatedFunction.getPointsCount());
        } catch (InappropriateFunctionPointException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void addPoint3() {
        FunctionPoint functionPoint = new FunctionPoint(-4.5,0);
        int expect_points = linkedListTabulatedFunction.getPointsCount() + 1;

        try {
            linkedListTabulatedFunction.addPoint(functionPoint);
            Assertions.assertSame(functionPoint, linkedListTabulatedFunction.getPoint(1));
            Assertions.assertEquals(expect_points, linkedListTabulatedFunction.getPointsCount());
        } catch (InappropriateFunctionPointException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void addPoint4() {
        FunctionPoint functionPoint = new FunctionPoint(4.5,0);
        int expect_points = linkedListTabulatedFunction.getPointsCount() + 1;

        try {
            linkedListTabulatedFunction.addPoint(functionPoint);
            Assertions.assertSame(functionPoint, linkedListTabulatedFunction.getPoint(
                    linkedListTabulatedFunction.getPointsCount() - 2
            ));
            Assertions.assertEquals(expect_points, linkedListTabulatedFunction.getPointsCount());
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
                () -> linkedListTabulatedFunction.setPoint(0, functionPoint));

        functionPoint.setX(5.01);

        Assertions.assertThrowsExactly(InappropriateFunctionPointException.class,
                () -> linkedListTabulatedFunction.setPoint(0, functionPoint));
    }
    @Test
    void setPoint() {
        FunctionPoint functionPoint = new FunctionPoint(1, 5);
        try {
            Assertions.assertNotEquals(functionPoint, linkedListTabulatedFunction.getPoint(5));
            linkedListTabulatedFunction.setPoint(5, functionPoint);
            Assertions.assertEquals(functionPoint, linkedListTabulatedFunction.getPoint(5));
        } catch (InappropriateFunctionPointException e) {
            Assertions.assertEquals(-1, 1);
        }
    }
    @Test
    void deletePointTwoPoints() {
        ArrayTabulatedFunction linkedListTabulatedFunction = new ArrayTabulatedFunction(-5, 5, 2);
        Assertions.assertThrowsExactly(IllegalStateException.class,
                () -> linkedListTabulatedFunction.deletePoint(0));
    }
    @Test
    void deletePointExceptionLeftBorder() {
        Assertions.assertThrowsExactly(FunctionPointIndexOutOfBoundsException.class,
                () -> linkedListTabulatedFunction.deletePoint(-1));
    }

    @Test
    void deletePointExceptionRightBorder() {
        Assertions.assertThrowsExactly(FunctionPointIndexOutOfBoundsException.class,
                () -> linkedListTabulatedFunction.deletePoint(9));
    }
    @Test
    void addPointExceptions() {
        FunctionPoint functionPoint = Mockito.mock(FunctionPoint.class);
        Mockito.when(functionPoint.getX()).thenReturn(-5.0);

        Assertions.assertThrowsExactly(InappropriateFunctionPointException.class,
                () -> linkedListTabulatedFunction.addPoint(functionPoint));
    }
    @Test
    void setPointXExceptions() {
        FunctionPoint functionPoint = new FunctionPoint(-5.01, 0);

        Assertions.assertThrowsExactly(InappropriateFunctionPointException.class,
                () -> linkedListTabulatedFunction.setPointX(0, functionPoint.getX()));

        functionPoint.setX(5.01);

        Assertions.assertThrowsExactly(InappropriateFunctionPointException.class,
                () -> linkedListTabulatedFunction.setPointX(0, functionPoint.getX()));
    }
    @Test
    void setPointX() {
        double expect = 1;
        try {
            Assertions.assertNotEquals(expect, linkedListTabulatedFunction.getPointX(5));
            linkedListTabulatedFunction.setPointX(5, 1);
            Assertions.assertEquals(expect, linkedListTabulatedFunction.getPointX(5));
        } catch (InappropriateFunctionPointException e) {
            Assertions.assertEquals(-1, 1);
        }
    }

    @Test
    void cloneTest(){
        LinkedListTabulatedFunction linkedListTabulatedFunction1;
        try {
            linkedListTabulatedFunction1 = (LinkedListTabulatedFunction) linkedListTabulatedFunction.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < linkedListTabulatedFunction.getPointsCount(); i++) {
            Assertions.assertEquals(linkedListTabulatedFunction.getPointX(i), linkedListTabulatedFunction1.getPointX(i));
            Assertions.assertEquals(linkedListTabulatedFunction.getPointY(i), linkedListTabulatedFunction1.getPointY(i));
        }
    }

    @Test
    void toStringTest(){
        for (int i = 0; i < linkedListTabulatedFunction.getPointsCount(); i++)
            System.out.println(linkedListTabulatedFunction.getPoint(i).toString());
        System.out.println(linkedListTabulatedFunction.toString());
    }

    @Test
    void equalsTest(){
        double[] values = {1, 2, 4, 8, 16, 32, 64, 128, 256};
        ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(-5.0, 5.0, values);

        Assertions.assertTrue(linkedListTabulatedFunction.equals(arrayTabulatedFunction));
        Assertions.assertTrue(linkedListTabulatedFunction.equals(arrayTabulatedFunction));

        arrayTabulatedFunction.setPointY(0,123123);
        Assertions.assertFalse(linkedListTabulatedFunction.equals(arrayTabulatedFunction));
    }

    @Test
    void hashCodeTest(){
        double[] values = {1, 2, 4, 8, 16, 32, 64, 128, 256};
        ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(-5.0, 5.0, values);

        Assertions.assertEquals(linkedListTabulatedFunction.hashCode(), arrayTabulatedFunction.hashCode());

        arrayTabulatedFunction.setPointY(0, 3123);

        Assertions.assertNotEquals(linkedListTabulatedFunction.hashCode(), arrayTabulatedFunction.hashCode());
    }
}